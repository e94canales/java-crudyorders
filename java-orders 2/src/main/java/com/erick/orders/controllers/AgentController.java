package com.erick.orders.controllers;

import com.erick.orders.models.Agent;
import com.erick.orders.services.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/agents")
public class AgentController {
    @Autowired
    private AgentService agentService;
    // http://localhost:2019/agents/agent/{id}
    @GetMapping(value = "/agent/{id}", produces = {"application/json"})
    public ResponseEntity<?> getById(@PathVariable long id){
        Agent res = agentService.getAgentBy(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
