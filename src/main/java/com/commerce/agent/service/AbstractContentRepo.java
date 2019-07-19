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

    public Boolean save(T ContentInfo) {
        mongoTemplate.save(ContentInfo, COLLECTION_NAME);
        return true;
    }

    public List<ContentInfo> query() {
        return mongoTemplate.findAll(ContentInfo.class, COLLECTION_NAME);
    }

}
