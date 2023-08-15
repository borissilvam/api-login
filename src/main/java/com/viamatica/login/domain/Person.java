package com.viamatica.login.domain;

import com.viamatica.login.percistence.entity.UserEntity;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

public class Person {

    private Integer idPerson;
    private String name;
    private String lastName;
    private String identification;
    private LocalDate birthdate;



    public Integer getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(Integer idPerson) {
        this.idPerson = idPerson;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }


}
