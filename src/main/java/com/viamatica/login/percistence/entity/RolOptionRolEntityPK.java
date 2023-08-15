package com.viamatica.login.percistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class RolOptionRolEntityPK implements Serializable {
    @Column(name = "rol_idRol")
    private Integer idRol;
    @Column(name = "rolOption_idOption")
    private Integer idOption;
}
