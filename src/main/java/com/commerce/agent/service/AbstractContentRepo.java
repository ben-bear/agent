package com.commerce.agent.service;

import com.commerce.agent.dao.ContentInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;

public abstract class AbstractContentRepo<T> {
    private final static String COLLECTION_NAME = "content_info";

    @Autowired
    MongoTemplate mongoTemplate;

    public abstract String getCollectionName();

    public abstract List<ContentInfo> queryByTag(String tag);

    public abstract void upsert(int id, int stars);

    public void save(ContentInfo contentInfo) {
        mongoTemplate.save(contentInfo, COLLECTION_NAME);
    }

    public Boolean saveByAutoIncreaseId(ContentInfo contentInfo) {
        List<ContentInfo> list = query();
        IntSummaryStatistics stats = list.stream().mapToInt(ContentInfo::getInfoId).summaryStatistics();
        contentInfo.setInfoId(stats.getMax() + 1);
        // TODO  对接算法策略组，set获取的评分与tag
//        OkHttpClient mOkHttpClient = new OkHttpClient();
//        FormBody.Builder formBodyBuilder = new FormBody.Builder();
//        Set<String> keySet = paramsMap.keySet();
//        formBodyBuilder.add("content", contentInfo.getContent());
//        FormBody formBody = formBodyBuilder.build();
//        Request request = new Request
//                .Builder()
//                .post(formBody)
//                .url(****************url*******************)
//                .build();
//        try (Response response = mOkHttpClient.newCall(request).execute()) {
//            System.out.println(response.body().string());
//        }catch (Exception e){
//            e.printStackTrace();
//        }
        save(contentInfo);
        return true;
    }

    public boolean isMatch(int contentId, int agentIdWhoStared){
        ContentInfo contentInfo = getContentByID(contentId);
        HashMap<Integer,String> map = contentInfo.getMap();
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(currentTime);
        if(map.containsKey(agentIdWhoStared) && dateString.equals(map.get(agentIdWhoStared))){
            return false;
        }
        map.put(agentIdWhoStared,dateString);
        contentInfo.setMap(map);
        save(contentInfo);
        return true;
    }

    public ContentInfo getContentByID(int contentId){
        List<ContentInfo> contentInfoList = query();
        for (ContentInfo info: contentInfoList) {
            if (contentId == info.getAgentIdWhoStared()) {
                return info;
            }
        }
        return null;
    }

    public List<ContentInfo> query() {
        return mongoTemplate.findAll(ContentInfo.class, COLLECTION_NAME);
    }

}
