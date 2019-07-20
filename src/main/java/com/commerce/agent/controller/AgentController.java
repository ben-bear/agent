package com.commerce.agent.controller;

import com.commerce.agent.service.AbstractAgentRepo;
import com.commerce.agent.utils.JsonReturnTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class AgentController {
    @Autowired
    AbstractAgentRepo agentService;

    @PostMapping("/agent")
    public JsonReturnTemplate<Boolean> saveInfo() {
        return JsonReturnTemplate.success();
    }

    @GetMapping("/agent/{id}")
    public JsonReturnTemplate getInfo(@PathVariable String id) {
        return JsonReturnTemplate.success();
    }
}
