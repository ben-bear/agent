package com.commerce.agent.service;

import com.alibaba.fastjson.JSONObject;
import com.commerce.agent.dao.ContentInfo;
import com.google.common.collect.Maps;
import com.sun.deploy.util.StringUtils;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public abstract class AbstractContentRepo<T> {
    private final static String COLLECTION_NAME = "content_info";
    private static final MediaType JSONTYPE = MediaType.parse("application/json; charset=utf-8");

    @Autowired
    MongoTemplate mongoTemplate;

    public abstract List<ContentInfo> queryByTag(String tag);

    public abstract void upsert(int id, int stars);

    public abstract Boolean starContent(int contentId);

    public void save(ContentInfo contentInfo) {
        mongoTemplate.save(contentInfo, COLLECTION_NAME);
    }

    public Boolean saveByAutoIncreaseId(ContentInfo contentInfo) {
        if (Objects.isNull(contentInfo.getMap())) {
            contentInfo.setMap(new HashMap<>());
        }
        List<ContentInfo> list = query();
        IntSummaryStatistics stats = list.stream().mapToInt(ContentInfo::getInfoId).summaryStatistics();
        contentInfo.setInfoId(stats.getMax() + 1);
        // TODO  对接算法策略组，set获取的评分与tag. Done!
        JSONObject map = new JSONObject();
        map.put("content", contentInfo.getContent());
        Response response = doPost("http://192.168.191.1:9003/lushi", map.toString());
        try {
            JSONObject body = JSONObject.parseObject(response.body().string());
            Object isEffieve = body.get("class");
            contentInfo.setWorthy(isEffieve.toString().equals("1"));
            if ("".equals(body.get("tag"))) {
                List<String> tags = Arrays.asList(StringUtils.splitString(body.get("tag").toString(), ","));
                contentInfo.setTag(tags);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        save(contentInfo);
        return true;
    }

    public boolean isMatch(int contentId, int agentIdWhoStared){
        ContentInfo contentInfo = getContentByID(contentId);
        HashMap<Integer,String> map = Objects.isNull(contentInfo.getMap())
                ? Maps.newHashMap() : contentInfo.getMap();
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(currentTime);
        if (map.containsKey(agentIdWhoStared) && dateString.equals(map.get(agentIdWhoStared))) {
            return true;
        }
        map.put(agentIdWhoStared,dateString);
        contentInfo.setMap(map);
        save(contentInfo);
        return false;
    }

    public ContentInfo getContentByID(int infoId){
        List<ContentInfo> contentInfoList = query();
        for (ContentInfo info: contentInfoList) {
            if (infoId == info.getInfoId()) {
                return info;
            }
        }
        return null;
    }

    public List<ContentInfo> query() {
        return mongoTemplate.findAll(ContentInfo.class, COLLECTION_NAME);
    }

    private Response doPost(String url, String jsonObject) {
        OkHttpClient mOkHttpClient = new OkHttpClient();
//        FormBody.Builder formBodyBuilder = new FormBody.Builder();
//        for (String key: map.keySet()) {
//            formBodyBuilder.add(key, map.get(key));
//        }
//        FormBody formBody = formBodyBuilder.build();
        RequestBody body = RequestBody.create(JSONTYPE, jsonObject);
        Request request = new Request
                .Builder()
                .post(body)
                .url(url)
                .build();
        try {
            Response response = mOkHttpClient.newCall(request).execute();
            return response;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
