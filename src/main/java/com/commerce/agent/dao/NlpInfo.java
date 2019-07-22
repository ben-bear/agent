package com.commerce.agent.dao;

import lombok.Data;

import java.util.List;

@Data
public class NlpInfo {
    String isEffective;
    List<String> tags;
}
