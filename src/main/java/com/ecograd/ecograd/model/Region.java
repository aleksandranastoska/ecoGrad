package com.ecograd.ecograd.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double c11;
    private Double c12;
    private Double c21;
    private Double c22;
    private Double c31;
    private Double c32;
    private Double c41;
    private Double c42;
    @Transient
    private Double score;
}
