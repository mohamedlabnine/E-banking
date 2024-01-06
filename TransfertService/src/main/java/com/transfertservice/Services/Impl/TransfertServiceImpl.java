package com.transfertservice.Services.Impl;

import com.auto.entity.Repositorys.AgentRepository;
import com.transfertservice.Config.Utils;
import com.transfertservice.Services.TransfertService;
import com.auto.entity.Entities.Compte;
import com.auto.entity.Entities.Transfert;
import com.auto.entity.Repositorys.ClientRepository;
import com.auto.entity.Repositorys.TransfertRepository;
import com.auto.entity.Repositorys.CompteRepository;
import com.auto.entity.Shared.dto.TransfertDto;
import com.auto.entity.Shared.dto.TransfertMultipleDto;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.Instant;
import java.time.Year;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class TransfertServiceImpl implements TransfertService {
    @Autowired
    TransfertRepository transfertRepository;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    AgentRepository agentRepository;
    @Autowired
    CompteRepository compteRepository;
    @Autowired
    Utils util;
    @Override
    public Transfert EmissionTransfert(TransfertDto transfertDto) {
        Transfert transfertcheck = transfertRepository.findTransfertByReferenceTransfert(transfertDto.getReferenceTransfert());
        //check si le transsfet est deja existe :
        if(transfertcheck != null){throw new RuntimeException("Ce Transfert est déjà existe");}
        //Si non :
        Date now = new Date(System.currentTimeMillis());
        Transfert transfertEntity = new Transfert();
        BeanUtils.copyProperties(transfertDto,transfertEntity);
        //Si le transfert est par debit de compte client : le compte du client qui sera debite
        if(transfertDto.getTypeTransfert().equals("DEBIT")){
            Compte compteDoneur = clientRepository.findByClientId(transfertDto.getClientDonneurId()).getCompte();
            compteDoneur.setSolde(compteDoneur.getSolde() - transfertDto.getMontant());
        }
        //Si nn le compte de l'agent qui sera debite
        else{
            Compte compteagent = agentRepository.findById(Long.parseLong(transfertDto.getAgentId())).get().getCompte();
            compteagent.setSolde(compteagent.getSolde()- transfertDto.getMontant());
            transfertEntity.setAgent(agentRepository.findById(Long.parseLong(transfertDto.getAgentId())).get());
        }
        //Agoutez les parametre necessaires :
		transfertEntity.setClientDonneur(clientRepository.findByClientId(transfertDto.getClientDonneurId()));
        transfertEntity.setClientBeneficaire(clientRepository.findByClientId(transfertDto.getClientBeneficaireId()));
        transfertEntity.setDateTransfert(now);
        transfertEntity.setTransfertId(util.generateStringId(30));
        transfertEntity.setReferenceTransfert("837" + util.generateNumbre(10));
        //si il sera servi par Un GAG-BOA danc il faut generer un pin :
        if(transfertDto.getGAB_BOA()) {
            transfertEntity.setPin(util.generateNumbre(4));
        }
        transfertEntity.setDelaiTransfert(7);
        transfertEntity.setEtat("A servir");

        //Envoyer la notification si demande : 2 cas
        if(transfertDto.getNotification() && transfertDto.getGAB_BOA()){
            //Ajouter service de Notification
        }
        else if(transfertDto.getNotification()){
            //Ajouter service de Notification
        }
        return transfertRepository.save(transfertEntity);
    }

    @Override
    public Transfert ServirTransfert(TransfertDto transfertDto) {
        Transfert transfertEntity = transfertRepository.findByTransfertId(transfertDto.getTransfertId());
        if(transfertEntity==null){
            //si le transfert est n'est pas bloqué
            if(!transfertEntity.getEtat().equals("Bloqué")){
                if(!transfertEntity.getGAB_BOA()){
                    if(transfertEntity.getTypeTransfert().equals("DEBIT")){
                        Compte compteBeneficiere = transfertEntity.getClientBeneficaire().getCompte();
                        compteBeneficiere.setSolde(compteBeneficiere.getSolde()+ transfertEntity.getMontant());
                    }else{
                        Compte compteagent = transfertEntity.getAgent().getCompte();
                        compteagent.setSolde(compteagent.getSolde()+ transfertEntity.getMontant());
                    }
                }else{
                    if(!transfertDto.getPin().equals(transfertEntity.getPin())){
                        throw new RuntimeException("Le code pin est incorrect");
                    }

                }
                transfertEntity.setEtat("Servie");
                Date now = new Date(System.currentTimeMillis());
                transfertEntity.setDateReception(now);
            }else {
                throw new RuntimeException("Ce Transfert est Bloqué");
            }

        }else{
            throw new RuntimeException("Ce Transfert n'exist pas");
        }
        return transfertRepository.save(transfertEntity);
    }


    @Override
    public Transfert changeTransfertEtat(String transfertId, String newEtat) {
        Transfert transfertEntity = transfertRepository.findByTransfertId(transfertId);
        if(newEtat.equals("Débloqué") || newEtat.equals("Debloque") || newEtat.equals("DEBLOQUE") || newEtat.equals("debloque"))
            transfertEntity.setEtat("Débloqué à servir");
        if(newEtat.equals("Déshérence") || newEtat.equals("Desherence") || newEtat.equals("desherence") || newEtat.equals("DESHERENCE"))
            transfertEntity.setDelaiTransfert(2);
        transfertEntity.setEtat(newEtat);
        transfertRepository.save(transfertEntity);
        return transfertEntity;
    }


    @Override
    public Transfert getTransfertByTransfertId(String transfertId) {
        Transfert transfertEntity = transfertRepository.findByTransfertId(transfertId);
        return transfertEntity;
    }


    @Override
    public List<Transfert> getAllTransfert() {
        List<Transfert> transfertList = transfertRepository.findAll();
        return transfertList;
    }

    @Override
    public List<Transfert> getAllTransfertByEtat(String etat) {
        List<Transfert> transfertList = transfertRepository.getAllTransfertByEtat(etat);
        return transfertList;
    }

    @Override
    public List<Transfert> getAllTransfertByClientBeneficaire(String clientBeneficaireId) {
        List<Transfert> transfertList = transfertRepository.getAllTransfertByClientBeneficaire(clientBeneficaireId);
        return transfertList;
    }

    @Override
    public List<Transfert> getAllTransfertByClientDonneur(String clientDonneurId) {
        List<Transfert> transfertList = transfertRepository.getAllTransfertByClientDonneur(clientDonneurId);
        return transfertList;
    }

    @Override
    public List<Transfert> TransfertMultiple(TransfertMultipleDto transfertMultipleDto) {
        TransfertDto transfertDto = new TransfertDto();
        BeanUtils.copyProperties(transfertMultipleDto,transfertDto);
        List<String> clientsBeneficairesIdList = transfertMultipleDto.getClientBeneficaireIdList();
        List<Transfert> transfertList = new ArrayList<>();
        //Pour chaque ClientBeneficiere if faut emit un transfert :
        for(String cId : clientsBeneficairesIdList){
            transfertDto.setClientBeneficaireId(cId);
            Transfert transfert = EmissionTransfert(transfertDto);
            transfertList.add(transfert);
            transfertRepository.save(transfert);
        }
        return transfertList;
    }

    public float calculateRemainingAnnualCeiling(String clientDonneurId,Boolean agent) {
        float totalAmountThisYear = getTotalAmountThisYear(clientDonneurId,agent);
        return totalAmountThisYear;
    }

    // Assuming you have a method to get the total amount for the client's transfers in the current year
    private float getTotalAmountThisYear(String clientDonneurId,Boolean agent) {
        float totalAmount = 0.0f;
        if(!agent){
            List<Transfert> transfertsThisYear = transfertRepository.findByClientDonneurIdAndDateTransfertYear(clientDonneurId, Year.now().getValue());
            if(!transfertsThisYear.isEmpty()){
                for (Transfert transfert : transfertsThisYear) {
                    totalAmount += transfert.getMontant();
                }
            }
        }else {
            List<Transfert> transfertsThisYear = transfertRepository.findByAgentIdAndDateTransfertYear(clientDonneurId, Year.now().getValue());
            if(!transfertsThisYear.isEmpty()){
                for (Transfert transfert : transfertsThisYear) {
                    totalAmount += transfert.getMontant();
                }
            }
        }

        return totalAmount;
    }

    @Override
    public void BatchmettreAJourEtatTransferts() {
        List<Transfert> transfertsAUpdate = transfertRepository.findAll();
        Instant currentDate = Instant.now();
        for (Transfert transfert : transfertsAUpdate) {
            // Get the dateTransfert value
            Instant transfertDate = transfert.getDateTransfert().toInstant();
            long daysDifference = ChronoUnit.DAYS.between(transfertDate, currentDate);
            if(transfert.getEtat().equals("A servir")){
                if(daysDifference>=7){
                    transfert.setEtat("Bloqué");
                    transfert.setMotif("Bloqué après expiration du délai");
                }if(transfert.getClientBeneficaire().getBlacklist()){
                    transfert.setEtat("Bloqué");
                    transfert.setMotif("Bloqué client benef dans est Blackliste");
                }if(transfert.getClientDonneur().getBlacklist()){
                    transfert.setEtat("Bloqué");
                    transfert.setMotif("Bloqué client donneur dans est Blackliste");
                }
            }
            else if(transfert.getEtat().equals("Bloqué")){
                if(daysDifference>=14){
                    transfert.setEtat("sherence");
                }
            }
        }
    }

}
