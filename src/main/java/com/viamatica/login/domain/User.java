package com.viamatica.login.domain;

import com.viamatica.login.percistence.entity.PersonEntity;
import com.viamatica.login.percistence.entity.RolUserEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

public class User {

    private Integer idUser;
    private String userName;
    private String password;
    private String email;

    private Integer idPerson;
    private boolean locked;
    private boolean disabled;

    private List<RolUser> rolUserList;


    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(Integer idPerson) {
        this.idPerson = idPerson;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public List<RolUser> getRolUserList() {
        return rolUserList;
    }

    public void setRolUserList(List<RolUser> rolUserList) {
        this.rolUserList = rolUserList;
    }
}
