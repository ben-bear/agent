package com.commerce.agent.controller;

import com.commerce.agent.dao.Template;
import com.commerce.agent.service.MongoService;
import com.commerce.agent.utils.JsonReturnTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class FollowController {
    @Autowired
    MongoService mongoService;

    @PostMapping("/info")
    public JsonReturnTemplate<Boolean> saveInfo() {
        Template template = Template.builder().age("1")
                                    .name("muyunhao")
                                    .sex("male")
                                    .build();
        return JsonReturnTemplate.success(mongoService.saveT(template));
    }

    @GetMapping("/info")
    public JsonReturnTemplate<?> getInfo(@RequestParam String userId) {
        return JsonReturnTemplate.success(mongoService.queryById(userId));
    }
}
