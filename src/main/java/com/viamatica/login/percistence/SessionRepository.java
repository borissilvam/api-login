package com.viamatica.login.percistence;

import com.viamatica.login.domain.Session;
import com.viamatica.login.domain.repository.ISessionRespository;
import com.viamatica.login.percistence.crud.SessionCrudRepository;
import com.viamatica.login.percistence.mapper.SessionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SessionRepository implements ISessionRespository {
    @Autowired
    private SessionCrudRepository sessionCrudRepository;
    @Autowired
    private SessionMapper mapper;


    @Override
    public List<Session> getSessionByUserId(int idUser) {

        return mapper.toSessions(sessionCrudRepository.findByIdUser(idUser)) ;
    }
}
