package com.agentservice.Services;

import com.auto.entity.Entities.Client;
import com.auto.entity.Shared.dto.ClientDto;

import java.util.List;

public interface ClientService {

    ClientDto createClient(ClientDto client);

    ClientDto getClientByClientId(String clientId);

    ClientDto updateClient(String clientId, ClientDto clientDto);

    void deleteClient(String clientId);

    List<ClientDto> getClients(String search);

    List<Client> getClientsWithNoCompte();
    void addBeneficiary(int clientid , int ClientBeneficiaryId) ;

}
