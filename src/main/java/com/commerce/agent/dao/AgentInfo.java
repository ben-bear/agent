package com.commerce.agent.dao;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AgentInfo {
    int agentId;
    int level;
    int points;
    int type;
}
