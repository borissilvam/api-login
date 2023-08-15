package com.viamatica.login.percistence.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "rolOption_rol")
@Data
public class RolOptionRolEntity {
    @EmbeddedId
    private RolOptionRolEntityPK id;
    @MapsId("idRol")
    @ManyToOne
    @JoinColumn(name = "rol_idRol", insertable = false, updatable = false)
    private RolEntity rolEntity;
    @ManyToOne
    @JoinColumn(name = "rolOption_idOption", insertable = false, updatable = false)
    private RolOptionEntity rolOptionEntity;
}
