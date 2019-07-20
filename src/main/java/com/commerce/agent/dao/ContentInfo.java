package com.commerce.agent.dao;

import com.google.common.collect.Lists;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
public class ContentInfo implements Serializable {
    int infoId;
    int agentId;
    int stars;
    @Value("${''}")
    String content;
    boolean isWorthy;
    boolean isStared;
    int count;
    List<String> tag;
}
