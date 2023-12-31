package com.auto.entity.Repositorys;


import com.auto.entity.Entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    Client findByClientId(String clintId);
    Client findByFullName(String fullName) ;

    @Query(value="SELECT * FROM clients WHERE fullName LIKE %:search%", nativeQuery=true)
    List<Client> findAllClientsByCriteria(@Param("search") String search);
}
