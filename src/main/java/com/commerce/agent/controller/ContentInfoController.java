package com.commerce.agent.controller;

import com.commerce.agent.dao.AgentInfo;
import com.commerce.agent.dao.ContentInfo;
import com.commerce.agent.service.AbstractContentRepo;
import com.commerce.agent.utils.JsonReturnTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
        List<AgentInfo> list = contentInfoService.query();
        return JsonReturnTemplate.success(contentInfoService.query());
    }

    @GetMapping(path = "/content/{tag}")
    @CrossOrigin("http://localhost:8000")
    public JsonReturnTemplate getContentByTag(@PathVariable String tag) {
        return JsonReturnTemplate.success(contentInfoService.queryByTag(tag));
    }

    @GetMapping(path = "/star/{id}")
    public JsonReturnTemplate starContent(@PathVariable int id) {
        return JsonReturnTemplate.success(contentInfoService.starContent(id));
    }

    @PostMapping(path = "/content")
    @CrossOrigin("http://localhost:8000")
    public JsonReturnTemplate uploadContent(@RequestBody @Valid ContentInfo contentInfo) {
        return JsonReturnTemplate.success(contentInfoService.saveByAutoIncreaseId(contentInfo));
    }
}
