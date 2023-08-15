package com.viamatica.login.domain.service;

import com.viamatica.login.domain.Session;
import com.viamatica.login.percistence.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SessionService {
    @Autowired
    private SessionRepository sessionRepository;

    public List<Session> getSessionByIdUser(int idUser){
        return sessionRepository.getSessionByUserId(idUser);
    }
}
