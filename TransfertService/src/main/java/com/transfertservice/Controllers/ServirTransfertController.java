package com.transfertservice.Controllers;

import com.auto.entity.Entities.Transfert;
import com.auto.entity.Repositorys.ClientRepository;
import com.auto.entity.Shared.dto.TransfertDto;
import com.transfertservice.Services.TransfertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@ComponentScan(basePackages = "com.auto.entity.Repositorys")
@RequestMapping(path = "/serviretransfert")
public class ServirTransfertController {
    @Autowired
    TransfertService transfertService;
    @Autowired
    ClientRepository clientRepository;
    @PostMapping(
            consumes= MediaType.APPLICATION_JSON_VALUE,
            produces=MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> Transfert(@RequestBody TransfertDto transfertDto){
        try {
            if(transfertDto.getReferenceTransfert()==null)
                throw new RuntimeException("vous oublier des champs obligatoire");
            Transfert transfertEntity = transfertService.ServirTransfert(transfertDto);
            return new ResponseEntity(transfertEntity, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
