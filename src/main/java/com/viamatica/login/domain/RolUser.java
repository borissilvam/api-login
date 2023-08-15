package com.viamatica.login.domain;

import lombok.Data;


public class RolUser {

    private Integer idRol;

    private boolean active;


    public Integer getIdRol() {
        return idRol;
    }

    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
