package com.ecograd.ecograd.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Data
@Entity(name="app_users")
public class User {
    @Id
    private String username;
    private String password;
    private String name;
    private String surname;
    private String email;
    private Date dateOfBirth;
    private Integer points;

}
