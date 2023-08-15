package com.viamatica.login.percistence.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "rol")
@Data
public class RolEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRol;
    private String rolName;

    @OneToMany(mappedBy = "rolEntity", cascade = CascadeType.ALL)
    private List<RolOptionRolEntity> rolOptionRolEntityList;
}
