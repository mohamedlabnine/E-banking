package com.agentservice.Services.Impl;

import com.agentservice.Services.ClientService;
import com.auto.entity.Entities.Client;
import com.auto.entity.Entities.Compte;
import com.auto.entity.Repositorys.ClientRepository;
import com.auto.entity.Repositorys.CompteRepository;
import com.auto.entity.Shared.dto.ClientDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    CompteRepository compteRepository;
    @Autowired
    Utils util;

    @Override
    public ClientDto createClient(ClientDto client) {
        Client checkClient = clientRepository.findByFullName(client.getFullName());
        if(checkClient != null)
            throw new RuntimeException("Ce Client ( "+ client.getFullName() +" ) est déjà existe");
        Client clientEntity = new Client();
        BeanUtils.copyProperties(client,clientEntity);
        if(!client.getGSM().isEmpty()) {
            Compte compteEntity = compteRepository.findByNumCompte(client.getNumCompte());
            clientEntity.setCompte(compteEntity);
        }

        clientEntity.setClientId(util.generateStringId(30));

        clientRepository.save(clientEntity);

        BeanUtils.copyProperties(clientEntity,client);


        return client;
    }


    @Override
    public ClientDto getClientByClientId(String clientId) {
        Client clientEntity = clientRepository.findByClientId(clientId);
        ClientDto clientDto = new ClientDto();
        BeanUtils.copyProperties(clientEntity,clientDto);
        return clientDto;
    }

    @Override
    public ClientDto updateClient(String clientId, ClientDto client) {
        Client clientEntity = clientRepository.findByClientId(clientId);
        if(clientEntity == null) throw new RuntimeException("Ce Client n'existe pas");

        if(client.getFullName()!= null)
            clientEntity.setFullName(client.getFullName());
        if(client.getGSM()!= null)
            clientEntity.setGSM(client.getGSM());
        if(client.getTitre()!= null)
            clientEntity.setTitre(client.getTitre());

        Client clientUpdated = clientRepository.save(clientEntity);
        ClientDto clientDto = new ClientDto();
        BeanUtils.copyProperties(clientUpdated,clientDto);
        return clientDto;
    }

    @Override
    public void deleteClient(String clientId) {
        Client clientEntity = clientRepository.findByClientId(clientId);
        if(clientEntity == null) throw new RuntimeException("ce Client n'existe pas");
        clientRepository.delete(clientEntity);


    }

    @Override
    public List<ClientDto> getClients(String search) {
        List<ClientDto> clientDtoList = new ArrayList<>();
        List<Client> clientList;
        if(search.isEmpty()) {
            clientList = clientRepository.findAll();
        }
        else {
            clientList = clientRepository.findAllClientsByCriteria(search);
        }
        for(Client clientEntity: clientList) {

            ClientDto clientDto = new ClientDto();
            BeanUtils.copyProperties(clientEntity,clientDto);

            clientDtoList.add(clientDto);
        }
        return clientDtoList;
    }

    @Override
    public List<Client> getClientsWithNoCompte() {
        List<Client> clientList;
        List<Client> clientListHasNoCompte = new ArrayList<>();
        clientList = clientRepository.findAll();
        for(Client clientEntity: clientList) {
            if(!clientEntity.isHasCompte()){
                clientListHasNoCompte.add(clientEntity);
            }
        }
        return clientListHasNoCompte;
    }

    @Override
    public void addBeneficiary(int clientid , int clientBeneficiaryId) {

        Client client = clientRepository.findByClientId(String.valueOf(clientid)) ;
        Client clientBeneficiary = clientRepository.findByClientId(String.valueOf(clientBeneficiaryId)) ;
        client.getClientList().add(clientBeneficiary) ;

        clientRepository.save(client) ;
    }
}
