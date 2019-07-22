package com.commerce.agent.impl;

import com.commerce.agent.dao.AgentInfo;
import com.commerce.agent.service.AbstractAgentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class AgentInfoImpl extends AbstractAgentRepo {
    private final static String COLLECTION_NAME = "agent_info";

    private final static String AGENT_ID = "agentId";
    private final static String LEVEL = "level";
    private final static String POINTS = "points";

    @Autowired
    AbstractAgentRepo abstractAgentRepo;

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public void upgradeLevel(int agentId) {
        Query query = new Query(Criteria.where(AGENT_ID).is(agentId));
        AgentInfo info = mongoTemplate.findOne(query, AgentInfo.class, COLLECTION_NAME);
        int points = Objects.isNull(info.getPoints()) ?  0 : info.getPoints() + 1;
        if (points == 100 || points == 500 || points == 1000 || points == 3000) {
            int level = info.getLevel() + 1;
            Update update = Update.update(LEVEL, level);
            mongoTemplate.upsert(new Query(Criteria.where(AGENT_ID).is(agentId)), update, COLLECTION_NAME);
        }
    }

    @Override
    public AgentInfo getInfoByID(int id) {
        List<AgentInfo> agentInfoList = abstractAgentRepo.query();
        for (AgentInfo info: agentInfoList) {
            if (id == info.getAgentId()) {
                return info;
            }
        }
        return new AgentInfo();
    }

}
