package com.commerce.agent.impl;

import com.commerce.agent.dao.AgentInfo;
import com.commerce.agent.service.AbstractAgentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgentInfoImpl extends AbstractAgentRepo {

    @Autowired
    AbstractAgentRepo abstractAgentRepo;

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
