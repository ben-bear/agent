package com.commerce.agent.dao;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class AgentInfo {
    @NonNull
    int agentId;
    int level;
    int points;
}
