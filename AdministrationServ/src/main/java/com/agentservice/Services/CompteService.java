package com.agentservice.Services;

import com.auto.entity.Shared.dto.CompteDto;

import java.util.List;

public interface CompteService {
    CompteDto createCompte(CompteDto compte);
    CompteDto getCompteByNumCompte (String numCompte);
    CompteDto getCompteByCompteId (String compteId);
    List<CompteDto> getAllComptes(String search);
    void deleteCompteByNumCompte(String numCompte);
    void deleteCompteByCompteId(String compteId);
}
