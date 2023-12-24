package com.ecograd.ecograd.service.impl;

import com.ecograd.ecograd.model.Litter;
import com.ecograd.ecograd.model.LitterSeverity;
import com.ecograd.ecograd.model.LitterType;
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

    @Override
    public Double calculateScoreById(Long id) {
        Litter litter = litterRepository.findById(id).orElseThrow(InvalidLitterException::new);
        double score = 1d;
        if (litter.getLitterSeverity().equals(LitterSeverity.SEVERE))
            score+=3;
        else if (litter.getLitterSeverity().equals(LitterSeverity.MEDIUM))
            score+=2;
        else if (litter.getLitterSeverity().equals(LitterSeverity.SMALL))
            score+=1;
        if (litter.getLitterType().equals(LitterType.PLASTIC))
            score+=5;
        else if (litter.getLitterType().equals(LitterType.GLASS))
            score+=4;
        else if (litter.getLitterType().equals(LitterType.OTHER))
            score+=3;
        else if (litter.getLitterType().equals(LitterType.PAPER))
            score+=2;
        litter.setScore(score);
        litterRepository.save(litter);
        return score;
    }
}
