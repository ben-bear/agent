package com.commerce.agent.dao;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Template {
    String userId;
    String name;
    String age;
    String sex;
}
