package com.agentservice.Controllers;

import com.agentservice.Services.AdminService;
import com.auto.entity.Entities.Agent;
import com.auto.entity.Repositorys.AgenceRepository;
import com.auto.entity.Repositorys.AgentRepository;
import com.auto.entity.Shared.dto.AgentDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agent")
public class AdminController {
    @Autowired
    AgentRepository agentRepository;
    @Autowired
    AdminService agentService;
    @Autowired
    AgenceRepository agenceRepository;

    @PostMapping(consumes= MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Agent> createAgent(@RequestBody AgentDto agentDto) throws Exception{
        if(agentDto.getUsername().isEmpty() || agentDto.getPassword().isEmpty() || agentDto.getAgenceId() == null)
            throw new RuntimeException("vous oublier des champs obligatoire");
        agentService.createAgent(agentDto);
        Agent agentEntity = new Agent();
        BeanUtils.copyProperties(agentDto,agentEntity);
        agentEntity.setAgence(agenceRepository.findById(agentDto.getAgenceId()).orElseThrow(() -> new RuntimeException("Ce id de Agence n'existe pas")));

        return new ResponseEntity<Agent>(agentEntity, HttpStatus.CREATED);
    }

    @GetMapping(path="/{id}", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Agent> getAgentById(@PathVariable long id) {
        AgentDto agentDto = agentService.getAgentById(id);
        Agent agentEntity = new Agent();
        BeanUtils.copyProperties(agentDto, agentEntity);
        agentEntity.setAgence(agenceRepository.findById(agentDto.getAgenceId()).orElseThrow(() -> new RuntimeException("Ce id de Agence n'existe pas")));

        return new ResponseEntity<Agent>(agentEntity, HttpStatus.OK);
    }

    @GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Agent>> getAllAgents(@RequestParam(value="search", defaultValue = "") String search) {

        List<Agent> agentList = agentRepository.findAll();

        return new ResponseEntity<List<Agent>>(agentList, HttpStatus.OK);
    }

    @DeleteMapping(path="/{id}")
    public ResponseEntity<Object> deleteAgentById(@PathVariable long id) {
        agentService.deleteAgent(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(path = "/{id}", consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Agent> updateAgent(@PathVariable long id, @RequestBody AgentDto agentDto) {
        AgentDto agentUpdated = agentService.updateAgent(id,agentDto);
        Agent agentEntity = new Agent();
        BeanUtils.copyProperties(agentUpdated, agentEntity);
        agentEntity.setAgence(agenceRepository.findById(agentDto.getAgenceId()).orElseThrow(() -> new RuntimeException("Ce id de Agence n'existe pas")));

        return new ResponseEntity<Agent>(agentEntity, HttpStatus.ACCEPTED);
    }

    @GetMapping(path="/username/{username}", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Agent> getAgentByUsername(@PathVariable String username) {
        Agent agentEntity = agentRepository.findByUsername(username);

        return new ResponseEntity<Agent>(agentEntity, HttpStatus.OK);
    }

}
