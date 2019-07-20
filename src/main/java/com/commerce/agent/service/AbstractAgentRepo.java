package com.commerce.agent.service;

import com.commerce.agent.dao.AgentInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public abstract class AbstractAgentRepo<T> {
    private final static String COLLECTION_NAME = "agent_info";

    @Autowired
    MongoTemplate mongoTemplate;

    public boolean saveInfo(T template) {
        mongoTemplate.save(template, COLLECTION_NAME);
        return true;
//        System.out.println(template);
    }

    abstract public AgentInfo getInfoByID(int id);



    public List<AgentInfo> query() {
        return mongoTemplate.findAll(AgentInfo.class, COLLECTION_NAME);
    }
}
