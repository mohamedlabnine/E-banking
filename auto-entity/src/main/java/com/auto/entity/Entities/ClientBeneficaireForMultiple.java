package com.auto.entity.Entities;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientBeneficaireForMultiple{

    @CsvBindByName
    private String fullName;

    @CsvBindByName
    private String titre; 	//  M. / Mme

    @CsvBindByName
    private String GSM;

    @CsvBindByName
    private String numCompte;

}
