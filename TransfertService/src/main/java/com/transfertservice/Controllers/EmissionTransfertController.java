package com.transfertservice.Controllers;

import com.auto.entity.Entities.Transfert;
import com.auto.entity.Repositorys.ClientRepository;
import com.auto.entity.Shared.dto.TransfertDto;
import com.auto.entity.Shared.dto.TransfertMultipleDto;
import com.transfertservice.Config.Constant;
import com.transfertservice.Services.TransfertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ComponentScan(basePackages = "com.auto.entity.Repositorys")
@RequestMapping(path = "/emissiontransfert")
public class EmissionTransfertController {

    @Autowired
    TransfertService transfertService;
    @Autowired
    ClientRepository clientRepository;
    //just for testing
    @GetMapping("/hello")
    public String hello() {
        return "Hello, world";
    }

    @PostMapping(
            consumes= MediaType.APPLICATION_JSON_VALUE,
            produces=MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> Transfert(@RequestBody TransfertDto transfertDto) throws Exception{
        try {
            if(transfertDto.getClientDonneurId()==null || transfertDto.getClientBeneficaireId()==null
                    || transfertDto.getMontant()<100 ||transfertDto.getTypeTransfert()==null)
                throw new RuntimeException("vous oublier des champs obligatoire");
            //
            if (transfertDto.getTypeTransfert().equals("DEBIT")) {
                // Check the amount per transfer
                if (transfertDto.getMontant() > Constant.clientannualCeiling) {
                    throw new RuntimeException("Le montant du transfert dépasse le plafond de 2000,00 DH par transfert.");
                }
                // Check the annual ceiling
                float annualCeilingRemaining = transfertService.calculateRemainingAnnualCeiling(transfertDto.getClientDonneurId(),Boolean.FALSE);

                if (annualCeilingRemaining > Constant.clientannualCeilingTotal) {
                    throw new RuntimeException("Le montant du transfert dépasse le plafond annuel de 20 000,00 DH. Pour client");
                }
            }else {
                // Check the annual ceiling
                float annualCeilingRemaining = transfertService.calculateRemainingAnnualCeiling(transfertDto.getAgentId(),Boolean.TRUE);
                if (annualCeilingRemaining > Constant.agentannualCeilingTotal) {
                    throw new RuntimeException("Le montant du transfert dépasse le plafond annuel de 80 000,00 DH. Pour Agent");
                }
            }
            Transfert transfertEntity = transfertService.EmissionTransfert(transfertDto);
            return new ResponseEntity(transfertEntity, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping(path="/transfertMultiple",
            consumes= MediaType.APPLICATION_JSON_VALUE,
            produces=MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<Transfert>> TransfertMultiple(@RequestBody TransfertMultipleDto transfertMultipleDto) throws Exception{
        if(transfertMultipleDto.getClientDonneurId().isEmpty() || transfertMultipleDto.getClientBeneficaireIdList().isEmpty()
                || transfertMultipleDto.getMontant()<100)
            throw new RuntimeException("vous oublier des champs obligatoire");

        List<Transfert> transfertEntity = transfertService.TransfertMultiple(transfertMultipleDto);

        return new ResponseEntity<List<Transfert>>(transfertEntity, HttpStatus.CREATED);
    }
}
