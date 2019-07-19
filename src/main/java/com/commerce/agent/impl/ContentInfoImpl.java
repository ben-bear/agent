package com.commerce.agent.impl;

import com.commerce.agent.dao.ContentInfo;
import com.commerce.agent.service.AbstractContentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContentInfoImpl extends AbstractContentRepo {

    private final static String INFO_ID = "infoId";
    private final static String AGENT_ID = "agentId";
    private final static String STARS = "stars";
    private final static String CONTENT = "content";
    private final static String IS_WORTHY = "isWorthy";
    private final static String COUNT = "count";
    private final static String TAG = "tag";

    @Autowired
    AbstractContentRepo contentInfoService;

    @Override
    public String getCollectionName() { return "content_info"; }

    @Override
    public List<ContentInfo> queryByTag(String tag) {
//        Query query = new Query(Criteria.where(TAG).is(tag));
//        return contentInfoService.query(query, ContentInfo.class, getCollectionName());
        List<ContentInfo> contentInfoList = contentInfoService.query();
        return contentInfoList.stream().filter(contentInfo -> tag.equals(contentInfo.getTag()))
                .collect(Collectors.toList());
    }

}
