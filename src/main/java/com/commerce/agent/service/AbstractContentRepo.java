package com.commerce.agent.service;

import com.commerce.agent.dao.AgentInfo;
import com.commerce.agent.dao.ContentInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public abstract class AbstractContentRepo {
    private final static String COLLECTION_NAME = "content_info";

    @Autowired
    MongoTemplate mongoTemplate;

    public abstract String getCollectionName();

    public abstract List<ContentInfo> queryByTag(String tag);

    public Boolean save(ContentInfo ContentInfo) {
        mongoTemplate.save(ContentInfo, COLLECTION_NAME);
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
