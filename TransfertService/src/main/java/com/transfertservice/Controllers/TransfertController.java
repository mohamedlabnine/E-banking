package com.transfertservice.Controllers;

import com.transfertservice.Config.Constant;
import com.transfertservice.Services.TransfertService;
import com.auto.entity.Entities.Transfert;
import com.auto.entity.Repositorys.ClientRepository;
import com.auto.entity.Shared.dto.TransfertDto;
import com.auto.entity.Shared.dto.TransfertMultipleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@ComponentScan(basePackages = "com.auto.entity.Repositorys")
@RequestMapping(path = "/transfert")
public class TransfertController {

    @Autowired
    TransfertService transfertService;
    @Autowired
    ClientRepository clientRepository;
    //just for testing
    @GetMapping("/hello")
    public String hello() {
        return "Hello, world";
    }


    @PutMapping(path = "/etat/{transfertId}", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Transfert> changeTransfertEtat(@PathVariable String transfertId, @RequestBody String etat) {
        Transfert updatedTransfert = transfertService.changeTransfertEtat(transfertId,etat);

        return new ResponseEntity<Transfert>(updatedTransfert, HttpStatus.ACCEPTED);
    }

    @PutMapping(path = "/finDelai/{transfertId}", consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Transfert> finDelai2(@PathVariable String transfertId) {
        Transfert updatedTransfert = transfertService.finDelai2(transfertId);
        return new ResponseEntity(updatedTransfert, HttpStatus.ACCEPTED);
    }

    @GetMapping(path="/transfertId/{transfertId}", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Transfert> getTransfertByTransfertId(@PathVariable String transfertId) {
        Transfert transfertEntity = transfertService.getTransfertByTransfertId(transfertId);
        return new ResponseEntity<Transfert>(transfertEntity, HttpStatus.OK);
    }

    @GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Transfert>> getAllTransferts() {

        List<Transfert> transfertList = transfertService.getAllTransfert();

        return new ResponseEntity<List<Transfert>>(transfertList, HttpStatus.OK);
    }

    @GetMapping(path="/etat/{etat}", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Transfert>> getTransfertByetat(@PathVariable String etat) {
        List<Transfert> transfertEntityList = transfertService.getAllTransfertByEtat(etat);

        return new ResponseEntity<List<Transfert>>(transfertEntityList, HttpStatus.OK);
    }

    @GetMapping(path="/clientBeneficaireId/{clientBeneficaireId}", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Transfert>> getAllTransfertByClientBeneficaire(@PathVariable String clientBeneficaireId) {
        List<Transfert> transfertEntityList = transfertService.getAllTransfertByClientBeneficaire(clientBeneficaireId);

        return new ResponseEntity<List<Transfert>>(transfertEntityList, HttpStatus.OK);
    }

    @GetMapping(path="/clientDonneurId/{clientDonneurId}", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Transfert>> getAllTransfertByClientDonneur(@PathVariable String clientDonneurId) {
        List<Transfert> transfertEntityList = transfertService.getAllTransfertByClientDonneur(clientDonneurId);

        return new ResponseEntity<List<Transfert>>(transfertEntityList, HttpStatus.OK);
    }

}
