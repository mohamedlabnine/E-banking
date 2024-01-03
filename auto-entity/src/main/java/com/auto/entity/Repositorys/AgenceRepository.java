package com.auto.entity.Repositorys;

import com.auto.entity.Entities.Agence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgenceRepository extends JpaRepository<Agence, Long> {

    @Query(value="SELECT * FROM agences", nativeQuery=true)
    List<Agence> findAllAgences();

    @Query(value="SELECT * FROM agences WHERE adresseAgence LIKE %:search% OR villeAgence LIKE %:search% OR nomAgence LIKE %:search% OR telephoneAgence LIKE %:search%", nativeQuery=true)
    List<Agence> findAllAgencesByCriteria(@Param("search") String search);
}
