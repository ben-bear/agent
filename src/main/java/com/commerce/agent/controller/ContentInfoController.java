package com.commerce.agent.controller;

import com.commerce.agent.dao.AgentInfo;
import com.commerce.agent.dao.ContentInfo;
import com.commerce.agent.service.AbstractContentRepo;
import com.commerce.agent.utils.JsonReturnTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping
@Slf4j
public class ContentInfoController {
    @Autowired
    AbstractContentRepo contentInfoService;

    @GetMapping("/content")
    @CrossOrigin("http://localhost:8000")
    public JsonReturnTemplate getContent() {
        log.info("GET: ./content");
        List<ContentInfo> list = contentInfoService.query();
        for (ContentInfo contentInfo: list) {
            if (Objects.isNull(contentInfo.getTag())) {
                contentInfo.setTag(new ArrayList<>());
            }
        }
        return JsonReturnTemplate.success(contentInfoService.query());
    }

    @GetMapping(path = "/content/{tag}")
    @CrossOrigin("http://localhost:8000")
    public JsonReturnTemplate getContentByTag(@PathVariable String tag) {
        return JsonReturnTemplate.success(contentInfoService.queryByTag(tag));
    }

    @GetMapping(path = "/star/{id}")
    @CrossOrigin("http://localhost:8000")
    public JsonReturnTemplate starContent(@PathVariable int id) {
        log.info("POST: /star/" + id);
        // 现在默认都是1号经纪人点的赞
        if (contentInfoService.isMatch(id, 1)) {
            return JsonReturnTemplate.unSuccess("请不要一天点两次");
        }
        return JsonReturnTemplate.success(contentInfoService.starContent(id));
    }

    @PostMapping(path = "/content")
    @CrossOrigin("http://localhost:8000")
    public JsonReturnTemplate uploadContent(@RequestBody @Valid ContentInfo contentInfo) {
        return JsonReturnTemplate.success(contentInfoService.saveByAutoIncreaseId(contentInfo));
    }
}
