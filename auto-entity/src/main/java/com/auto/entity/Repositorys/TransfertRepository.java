package com.auto.entity.Repositorys;

import com.auto.entity.Entities.Transfert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransfertRepository extends JpaRepository<Transfert, Long> {

    Transfert findByTransfertId(String transfertId);
    Transfert findTransfertByReferenceTransfert(String referenceTransfert);

    @Query(value="SELECT * FROM transferts WHERE etat LIKE %:etat%", nativeQuery=true)
    List<Transfert> getAllTransfertByEtat(@Param("etat") String etat);

    @Query(value="SELECT * FROM transferts WHERE clientBeneficaireId = :clientId", nativeQuery=true)
    List<Transfert> getAllTransfertByClientBeneficaire(@Param("clientId") String clientId);

    @Query(value="SELECT * FROM transferts WHERE clientDonneurId = :clientId", nativeQuery=true)
    List<Transfert> getAllTransfertByClientDonneur(@Param("clientId") String clientId);
    @Query(value = "SELECT * FROM transferts WHERE clientDonneurId = :clientId AND YEAR(dateTransfert) = :year", nativeQuery = true)
    List<Transfert> findByClientDonneurIdAndDateTransfertYear(@Param("clientId") String clientId, @Param("year") int year);
    @Query(value = "SELECT * FROM transferts WHERE agentId = :agentId AND YEAR(dateTransfert) = :year", nativeQuery = true)
    List<Transfert> findByAgentIdAndDateTransfertYear(@Param("agentId") String agentId, @Param("year") int year);

}
