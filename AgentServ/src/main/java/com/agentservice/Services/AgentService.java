package com.agentservice.Services;


import com.auto.entity.Entities.Agent;
import com.auto.entity.Shared.dto.AgentDto;

import java.util.List;

public interface AgentService {

    AgentDto createAgent(AgentDto agent);

    AgentDto getAgentById(long id);

    AgentDto updateAgent(long id, AgentDto agent);

    void deleteAgent(long id);

    List<AgentDto> getAgents(String search);

    Agent logIn(String username, String password)  throws Exception;
}
