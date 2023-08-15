package com.viamatica.login.web.controller;

import com.viamatica.login.domain.Session;
import com.viamatica.login.domain.service.SessionService;
import jakarta.persistence.GeneratedValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sessions")
public class SessionController {
    @Autowired
    private SessionService sessionService;
    @GetMapping("/user/{id}")
    public ResponseEntity<List<Session>> getSessionByIdUser(@PathVariable("id") int idUser){
        return new ResponseEntity<>(sessionService.getSessionByIdUser(idUser), HttpStatus.OK) ;
    }
    @GetMapping("/active")
    public String sesionActive(){
        return "Tiene una sesiòn activa";
    }
    @PostMapping("/close")
    public ResponseEntity<?> deleteSessionsActives(){
        sessionService.deleteSessions();
        return  new ResponseEntity<>("Sesiones cerradas exitosamente", HttpStatus.OK);
    }
}

