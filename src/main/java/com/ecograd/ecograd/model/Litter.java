package com.ecograd.ecograd.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import jakarta.persistence.Lob;
import org.hibernate.annotations.Type;

@Data
@Entity
public class Litter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date dateReported;
    private double longitude;
    private double latitude;
    private LitterType litterType;
    private LitterSeverity litterSeverity;
    @Lob
    @Column(name="picture")
    private byte[] imageData;
    @Transient
    private Double score;
}
