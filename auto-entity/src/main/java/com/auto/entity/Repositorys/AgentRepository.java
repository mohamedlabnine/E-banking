package com.auto.entity.Repositorys;


import com.auto.entity.Entities.Agent;
import com.auto.entity.Entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgentRepository extends JpaRepository<Agent, Long> {

    @Query(value="SELECT * FROM agents where username = :username", nativeQuery=true)
    Agent findByUsername(@Param("username") String username);

//    @Query(value="SELECT * FROM agents", nativeQuery=true)
//    List<Agent> findAllAgents();

    @Query(value="SELECT * FROM agents WHERE username LIKE %:search%", nativeQuery=true)
    List<Agent> findAllAgentsByCriteria(@Param("search") String search);
}
