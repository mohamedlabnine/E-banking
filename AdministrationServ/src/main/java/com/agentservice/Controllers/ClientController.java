package com.agentservice.Controllers;

import com.agentservice.Services.ClientService;
import com.auto.entity.Entities.Client;
import com.auto.entity.Repositorys.CompteRepository;
import com.auto.entity.Shared.dto.ClientBeneficiaryDto;
import com.auto.entity.Shared.dto.ClientDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {
    @Autowired
    ClientService clientService;
    @Autowired
    CompteRepository compteRepository;


    @PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Client> createClient(@RequestBody ClientDto clientDto) throws Exception{
        System.out.println(clientDto.getFullName());
        if(clientDto.getFullName().isEmpty() || clientDto.getGSM().isEmpty()) throw new RuntimeException("vous oublier des champs obligatoire");

        clientService.createClient(clientDto);
        Client client = new Client();
        BeanUtils.copyProperties(clientDto, client);
        client.setCompte(compteRepository.findByNumCompte(clientDto.getNumCompte()));

        return new ResponseEntity<Client>(client, HttpStatus.CREATED);
    }

    @GetMapping(path="/{clientId}", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Client> getClientByClientId(@PathVariable String clientId) {
        ClientDto clientDto = clientService.getClientByClientId(clientId);
        Client client = new Client();
        BeanUtils.copyProperties(clientDto, client);
        client.setCompte(compteRepository.findByNumCompte(clientDto.getNumCompte()));
        return new ResponseEntity<Client>(client, HttpStatus.OK);
    }

    @GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Client>> getAllClients(@RequestParam(value="search", defaultValue = "") String search) {

        List<Client> clientsEntities = new ArrayList<>();

        List<ClientDto> clients = clientService.getClients(search);

        for(ClientDto clientDto: clients) {

            Client client = new Client();
            BeanUtils.copyProperties(clientDto, client);
            client.setCompte(compteRepository.findByNumCompte(clientDto.getNumCompte()));
            clientsEntities.add(client);
        }

        return new ResponseEntity<List<Client>>(clientsEntities, HttpStatus.OK);
    }

    @DeleteMapping(path="/{clientId}")
    public ResponseEntity<Object> deleteCompteByCompteId(@PathVariable String clientId) {

        clientService.deleteClient(clientId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(path = "/{clientId}", consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Client> updateUser(@PathVariable String clientId, @RequestBody ClientDto clientDto) {
        ClientDto clientUpdated = clientService.updateClient(clientId,clientDto);
        Client clientEntity = new Client();
        BeanUtils.copyProperties(clientUpdated, clientEntity);
        clientEntity.setCompte(compteRepository.findByNumCompte(clientDto.getNumCompte()));

        return new ResponseEntity<Client>(clientEntity, HttpStatus.ACCEPTED);
    }

    @GetMapping(path = "/clientsHasNoCompte",produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Client>> getAllClientsHasCompte() {
        List<Client> clientList = clientService.getClientsWithNoCompte();

        if(clientList!=null)
            return new ResponseEntity<List<Client>>(clientList, HttpStatus.OK);
        return null;
    }

    @PostMapping(path = "/addBeneficiary" , consumes=MediaType.APPLICATION_JSON_VALUE , produces=MediaType.APPLICATION_JSON_VALUE)
    public void addBeneficiary(@RequestBody ClientBeneficiaryDto clientBeneficiaryDto) {
        clientService.addBeneficiary(clientBeneficiaryDto.getClientId() , clientBeneficiaryDto.getClientIdBeneficiary());
    }

}

