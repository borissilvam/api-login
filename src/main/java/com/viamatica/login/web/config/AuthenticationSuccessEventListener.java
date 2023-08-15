package com.viamatica.login.web.config;

import com.viamatica.login.percistence.crud.SessionCrudRepository;
import com.viamatica.login.percistence.crud.UserCrudRepository;
import com.viamatica.login.percistence.entity.SessionEntity;
import com.viamatica.login.percistence.entity.UserEntity;
import org.apache.catalina.SessionEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class AuthenticationSuccessEventListener implements ApplicationListener<AuthenticationSuccessEvent> {
    @Autowired
    private SessionCrudRepository sessionCrudRepository;
    @Autowired
    private UserCrudRepository userCrudRepository;
    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {
        String userName = event.getAuthentication().getName();
       Optional<UserEntity> user =  userCrudRepository.findByUserName(userName);
       saveLoginSessionEvent(user.get().getIdUser(), "Login");

    }

    public void saveLoginSessionEvent(Integer idUser, String eventType){
        SessionEntity sessionEntity = new SessionEntity();
        sessionEntity.setEventType(eventType);
        sessionEntity.setIdUser(idUser);
        sessionEntity.setTimesTamp(LocalDateTime.now());
        sessionCrudRepository.save(sessionEntity);

    }
}
