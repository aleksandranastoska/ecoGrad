package com.ecograd.ecograd.service.impl;

import com.ecograd.ecograd.model.Litter;
import com.ecograd.ecograd.model.exception.InvalidLitterException;
import com.ecograd.ecograd.repository.LitterRepository;
import com.ecograd.ecograd.service.LitterService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LitterServiceImpl implements LitterService {
    private final LitterRepository litterRepository;

    public LitterServiceImpl(LitterRepository litterRepository) {
        this.litterRepository = litterRepository;
    }

    @Override
    public List<Litter> findAll() {
        return litterRepository.findAll();
    }

    @Override
    public Litter addLitter(Litter litter) {
        return litterRepository.save(litter);
    }

    @Override
    public Litter findById(Long id) {
        return litterRepository.findById(id).orElseThrow(InvalidLitterException::new);
    }
}
