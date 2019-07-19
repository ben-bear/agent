package com.commerce.agent.dao;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
public class ContentInfo implements Serializable {
    int infoId;
    int agentId;
    int stars;
    String content;
    boolean isWorthy;
    int count;
    List<String> tag;
}
