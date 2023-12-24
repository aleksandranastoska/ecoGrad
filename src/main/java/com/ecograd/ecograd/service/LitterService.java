package com.ecograd.ecograd.service;

import com.ecograd.ecograd.model.Litter;

import java.util.List;

public interface LitterService {
    List<Litter> findAll();

    Litter addLitter(Litter litter);

    Litter findById(Long id);
    Double calculateScoreById(Long id);
}
