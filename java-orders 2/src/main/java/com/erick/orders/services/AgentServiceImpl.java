package com.erick.orders.services;

import com.erick.orders.models.Agent;
import com.erick.orders.repositories.AgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service(value = "AgentService")
public class AgentServiceImpl implements AgentService {

    @Autowired
    private AgentRepository agentrepo;

    @Override
    public Agent getAgentBy(long id) {
        return agentrepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Agent " + id + " Not Found"));
    }
}
