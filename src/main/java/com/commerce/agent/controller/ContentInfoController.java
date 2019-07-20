package com.commerce.agent.controller;

import com.commerce.agent.dao.AgentInfo;
import com.commerce.agent.dao.ContentInfo;
import com.commerce.agent.service.AbstractContentRepo;
import com.commerce.agent.utils.JsonReturnTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
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
        log.info("getting a content message");
        List<AgentInfo> list = contentInfoService.query();
//        list.forEach();
        return JsonReturnTemplate.success(contentInfoService.query());
    }

    @GetMapping(path = "/content/{tag}")
    @CrossOrigin("http://localhost:8000")
    public JsonReturnTemplate getContentByTag(@PathVariable String tag) {
        return JsonReturnTemplate.success(contentInfoService.queryByTag(tag));
    }

    @PostMapping(path = "/content")
    @CrossOrigin("http://localhost:8000")
    public JsonReturnTemplate uploadContent(@RequestBody @Valid ContentInfo contentInfo) {
        return JsonReturnTemplate.success(contentInfoService.save(contentInfo));
    }
}
