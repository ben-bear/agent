package com.commerce.agent.service;

import com.commerce.agent.dao.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class MongoService<T> {
    /**
     * 常量字段与对象字段一致
     */
    private static final String NAME = "name";

    @Autowired
    @Qualifier("mongoTemplate")
    MongoTemplate mongoTemplate;

    private final static String COLLECTION_NAME = "muyunhao";

    public MongoService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public Template queryById(String name) {
        Query query = Query.query(Criteria.where(NAME).is(name));
        return mongoTemplate.findOne(query, Template.class, COLLECTION_NAME);
    }

    public List<Template> query() {
        return mongoTemplate.findAll(Template.class, COLLECTION_NAME);
    }

    public Boolean saveT(T template) {
        if (Objects.nonNull(template)) {
            mongoTemplate.save(template, COLLECTION_NAME);
        }
        return true;
    }

}
