package com.commerce.agent.impl;

import com.commerce.agent.dao.ContentInfo;
import com.commerce.agent.service.AbstractContentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContentInfoImpl extends AbstractContentRepo {

    private final static String COLLECTION_NAME = "content_info";

    private final static String INFO_ID = "infoId";
    private final static String AGENT_ID = "agentId";
    private final static String STARS = "stars";
    private final static String CONTENT = "content";
    private final static String IS_WORTHY = "isWorthy";
    private final static String COUNT = "count";
    private final static String TAG = "tag";
    private final static String isStared = "isStared";

    @Autowired
    AbstractContentRepo contentInfoService;

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public void upsert(int id, int stars) {
        Query query = new Query(Criteria.where(INFO_ID).is(id));
        Update update = Update.update(STARS, stars);
        mongoTemplate.upsert(query, update, COLLECTION_NAME);
    }

    @Override
    public Boolean starContent(int contentId) {
        List<ContentInfo> contentInfos = contentInfoService.query();
        int stars = 0;
        for (ContentInfo content: contentInfos) {
            if (contentId == content.getInfoId()) {
                stars = content.getStars();
                stars++;
            }
        }
        upsert(contentId, stars);
        return true;
    }

    @Override
    public List<ContentInfo> queryByTag(String tag) {
        List<ContentInfo> contentInfoList = contentInfoService.query();
        return contentInfoList.stream().filter(contentInfo -> tag.equals(contentInfo.getTag()))
                .collect(Collectors.toList());
    }
}
