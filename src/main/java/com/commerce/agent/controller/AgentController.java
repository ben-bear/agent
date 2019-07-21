package com.commerce.agent.controller;

import com.commerce.agent.dao.AgentInfo;
import com.commerce.agent.service.AbstractAgentRepo;
import com.commerce.agent.utils.JsonReturnTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class AgentController {
    @Autowired
    AbstractAgentRepo agentService;

    @PostMapping("/agent")
    @CrossOrigin("http://localhost:8000")
    public JsonReturnTemplate<Boolean> saveInfo(@RequestBody AgentInfo agentInfo) {
        System.out.println(agentInfo);
        return JsonReturnTemplate.success(agentService.saveInfo(agentInfo));
    }

    @GetMapping("/agent/{id}")
    @CrossOrigin("http://localhost:8000")
    public JsonReturnTemplate getInfo(@PathVariable int id) {
        return JsonReturnTemplate.success(agentService.getInfoByID(id));
    }

    @GetMapping("/agent")
    @CrossOrigin("http://localhost:8000")
    public JsonReturnTemplate getInfo() {
        return JsonReturnTemplate.success(agentService.query());
    }
}
