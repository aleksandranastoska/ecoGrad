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
    @Transient
    private Double score;

    public Litter(LocalDate dateReported, double longitude, double latitude, LitterType litterType, LitterSeverity litterSeverity, byte[] imageData) {
        this.dateReported = dateReported;
        this.longitude = longitude;
        this.latitude = latitude;
        this.litterType = litterType;
        this.litterSeverity = litterSeverity;
        this.imageData = imageData;
    }

    public Litter() {
    }
}
