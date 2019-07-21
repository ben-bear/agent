package com.commerce.agent.controller;

import com.commerce.agent.dao.AgentInfo;
import com.commerce.agent.service.AbstractAgentRepo;
import com.commerce.agent.utils.JsonReturnTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
public class AgentController {
    @Autowired
    AbstractAgentRepo agentInfoService;

    @PostMapping("/info")
    public JsonReturnTemplate<Boolean> saveInfo(@RequestBody @Valid AgentInfo agentInfo) {
        return JsonReturnTemplate.success(agentInfoService.saveInfo(agentInfo));
    }

    @GetMapping("/agent/{id}")
    public JsonReturnTemplate getInfo(@PathVariable int id) {
        return JsonReturnTemplate.success(agentInfoService.getInfoByID(id));
    }
}
