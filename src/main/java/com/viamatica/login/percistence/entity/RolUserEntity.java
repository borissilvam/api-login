package com.viamatica.login.percistence.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "rol_users")
public class RolUserEntity {
    @EmbeddedId
    private RolUserEntityPK id;

    private boolean active;
    @MapsId("idUser")
    @ManyToOne
    @JoinColumn(name = "user_idUser", insertable = false, updatable = false)
    private UserEntity userEntity;
    @ManyToOne
    @JoinColumn(name = "rol_idRol", insertable = false, updatable = false)
    private RolEntity rolEntity;


    public RolUserEntityPK getId() {
        return id;
    }

    public void setId(RolUserEntityPK id) {
        this.id = id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public RolEntity getRolEntity() {
        return rolEntity;
    }

    public void setRolEntity(RolEntity rolEntity) {
        this.rolEntity = rolEntity;
    }
}
