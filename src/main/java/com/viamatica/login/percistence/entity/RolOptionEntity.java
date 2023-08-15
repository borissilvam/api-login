package com.viamatica.login.percistence.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "rol_option")
@Data
public class RolOptionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idOption;
    @Column(name = "name_option")
    private String nameOption;
}
