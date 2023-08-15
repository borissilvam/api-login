package com.viamatica.login.domain.service;

import com.viamatica.login.domain.Session;
import com.viamatica.login.percistence.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SessionService {
    @Autowired
    private SessionRepository sessionRepository;
    @Autowired
    private SessionRegistry sessionRegistry;

    public List<Session> getSessionByIdUser(int idUser){
        return sessionRepository.getSessionByUserId(idUser);
    }

    public void deleteSessions(){
        List<Object> principals = sessionRegistry.getAllPrincipals();

        for (Object principal : principals) {
            List<SessionInformation> sessionInformationList = sessionRegistry.getAllSessions(principal, false);

            for (SessionInformation sessionInformation : sessionInformationList) {
                sessionInformation.expireNow();
            }
        }

    }
}
