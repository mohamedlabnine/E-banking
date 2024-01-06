package com.agentservice.Services.Impl;

import com.agentservice.Services.ClientService;
import com.agentservice.Services.CompteService;
import com.auto.entity.Entities.Client;
import com.auto.entity.Entities.Compte;
import com.auto.entity.Repositorys.CompteRepository;
import com.auto.entity.Shared.dto.ClientDto;
import com.auto.entity.Shared.dto.CompteDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CompteServiceImpl implements CompteService {
    @Autowired
    CompteRepository compteRepository;
    @Autowired
    ClientService clientService;
    @Autowired
    Utils util;

    @Override
    public CompteDto createCompte(CompteDto compte) {
        Compte checkCompte = compteRepository.findByNumCompte(compte.getNumCompte());
        if(checkCompte != null)
            throw new RuntimeException("Ce Compte ( "+ compte.getNumCompte() +" ) est déjà existe");
        Compte compteEntity = new Compte();

        //SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss");
        Date now = new Date(System.currentTimeMillis());
        //String d = formatter.format(now);

        BeanUtils.copyProperties(compte,compteEntity);
        compteEntity.setCompteId(util.generateStringId(30));
        //compteEntity.setNumCompte(util.generateNumbre(16));
        //compteEntity.setSolde(0);
        compteEntity.setDateCreation(now);
        Client clientEntity = new Client();
        ClientDto clientDto = clientService.getClientByClientId(compte.getClientId());
        BeanUtils.copyProperties(clientDto,clientEntity);
        compteEntity.setClient(clientEntity);
        clientEntity.setHasCompte(true);
        compteRepository.save(compteEntity);

        return compte;
    }

    @Override
    public CompteDto getCompteByNumCompte(String numCompte) {

        Compte compteEntity = compteRepository.findByNumCompte(numCompte);
        CompteDto compteDto = new CompteDto();
        BeanUtils.copyProperties(compteEntity,compteDto);

        return compteDto;
    }

    @Override
    public CompteDto getCompteByCompteId(String compteId) {

        Compte compteEntity = compteRepository.findByCompteId(compteId);
        CompteDto compteDto = new CompteDto();
        BeanUtils.copyProperties(compteEntity,compteDto);
        return compteDto;
    }

    @Override
    public List<CompteDto> getAllComptes(String search) {
        List<CompteDto> comptesDto = new ArrayList<>();
        List<Compte> comptes;
        if(search.isEmpty()) {
            comptes = compteRepository.findAllComptes();
        }
        else {
            comptes = compteRepository.findAllComptesByCriteria(search);
        }

        for(Compte compteEntity: comptes) {
            CompteDto compteDto = new CompteDto();
            BeanUtils.copyProperties(compteEntity,compteDto);
            compteDto.setClientId(compteEntity.getClient().getClientId());
            comptesDto.add(compteDto);
        }

        return comptesDto;
    }

    @Override
    public void deleteCompteByNumCompte(String numCompte) {

        Compte compteEntity = compteRepository.findByNumCompte(numCompte);

        if(compteEntity == null) throw new RuntimeException("ce Compte n'existe pas");

        compteRepository.delete(compteEntity);
    }

    @Override
    public void deleteCompteByCompteId(String compteId) {

        Compte compteEntity = compteRepository.findByCompteId(compteId);

        if(compteEntity == null) throw new RuntimeException("ce Compte n'existe pas");

        compteRepository.delete(compteEntity);
    }
}
