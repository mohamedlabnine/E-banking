package com.transfertservice.Services;



import com.auto.entity.Entities.Transfert;
import com.auto.entity.Shared.dto.TransfertDto;
import com.auto.entity.Shared.dto.TransfertMultipleDto;

import java.util.List;

public interface TransfertService {

    Transfert EmissionTransfert(TransfertDto transfertDto);
    Transfert ServirTransfert(TransfertDto transfertDto);
    //_______________________________________________________________________
    Transfert changeTransfertEtat(String transfertId, String newEtat);

    //_______________________________________________________________________
    Transfert getTransfertByTransfertId(String transfertId);
    List<Transfert> getAllTransfert();
    List<Transfert> getAllTransfertByEtat(String etat);

    List<Transfert> getAllTransfertByClientBeneficaire(String clientBeneficaireId);
    List<Transfert> getAllTransfertByClientDonneur(String clientDonneurId);

    List<Transfert> TransfertMultiple(TransfertMultipleDto transfertMultipleDto);

    public void BatchmettreAJourEtatTransferts();
    float calculateRemainingAnnualCeiling(String clientDonneurId,Boolean agent);
}