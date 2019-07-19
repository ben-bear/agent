package com.commerce.agent.service;

import com.commerce.agent.dao.AgentInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AbstractAgentRepo<T> {
    private final static String COLLECTION_NAME = "agent_info";

    @Autowired
    MongoTemplate mongoTemplate;

    public void save(T template) {
        mongoTemplate.save(template, COLLECTION_NAME);
        System.out.println(template);
    }

    public List<AgentInfo> query() {
        return mongoTemplate.findAll(AgentInfo.class, COLLECTION_NAME);
    }
}
