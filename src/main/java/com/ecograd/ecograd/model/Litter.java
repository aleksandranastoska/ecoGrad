package com.ecograd.ecograd.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import jakarta.persistence.Lob;

@Data
@Entity
public class Litter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dateReported;
    private double longitude;
    private double latitude;
    private LitterType litterType;
    private LitterSeverity litterSeverity;
    @Lob
    @Column(name="picture")
    private byte[] imageData;
    private Double score;
    @ManyToOne
    private User user;

    public Litter(LocalDate dateReported, double longitude, double latitude, LitterType litterType, LitterSeverity litterSeverity, byte[] imageData) {
        this.dateReported = dateReported;
        this.longitude = longitude;
        this.latitude = latitude;
        this.litterType = litterType;
        this.litterSeverity = litterSeverity;
        this.imageData = imageData;
        double score = 1d;
        if (litterSeverity.equals(LitterSeverity.SEVERE))
            score+=3;
        else if (litterSeverity.equals(LitterSeverity.MEDIUM))
            score+=2;
        else if (litterSeverity.equals(LitterSeverity.SMALL))
            score+=1;
        if (litterType.equals(LitterType.PLASTIC))
            score+=5;
        else if (litterType.equals(LitterType.GLASS))
            score+=4;
        else if (litterType.equals(LitterType.OTHER))
            score+=3;
        else if (litterType.equals(LitterType.PAPER))
            score+=2;
        this.score = score;
    }

    public Litter() {
    }
}
