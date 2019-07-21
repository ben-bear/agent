package com.commerce.agent.dao;

import com.google.common.collect.Lists;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import java.io.Serializable;
import java.util.List;

@Data
public class ContentInfo implements Serializable {
    int infoId;
    int agentId;
    int stars;
    String content;
    boolean isWorthy;
    boolean isStared;
    int count;
    List<String> tag;
}
