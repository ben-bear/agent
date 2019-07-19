package com.commerce.agent.controller;

import com.commerce.agent.dao.ContentInfo;
import com.commerce.agent.service.AbstractContentRepo;
import com.commerce.agent.utils.JsonReturnTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping
public class ContentInfoController {
    @Autowired
    AbstractContentRepo contentInfoService;

    @GetMapping("/content")
    public JsonReturnTemplate getContent() {
        return JsonReturnTemplate.success(contentInfoService.query());
    }

    @GetMapping(path = "/content/{tag}")
    public JsonReturnTemplate getContentByTag(@PathVariable String tag) {
        return JsonReturnTemplate.success(contentInfoService.queryByTag(tag));
    }

    @PostMapping(path = "/content")
    public JsonReturnTemplate uploadContent(@RequestBody @Valid ContentInfo contentInfo) {
        return JsonReturnTemplate.success(contentInfoService.save(contentInfo));
    }
}
