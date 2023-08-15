package com.viamatica.login.domain;

import com.viamatica.login.percistence.entity.UserEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;

public class Session {

    private Integer id;

    private String eventType;

    private LocalDateTime timesTamp;

    private Integer idUser;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public LocalDateTime getTimesTamp() {
        return timesTamp;
    }

    public void setTimesTamp(LocalDateTime timesTamp) {
        this.timesTamp = timesTamp;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }
}
