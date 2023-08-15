package com.viamatica.login.web.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFound extends RuntimeException{

    public UserNotFound(){
        super("No se han encontrado usuarios");
    }

    public UserNotFound(Integer id){
        super("No se ha encontrado al usuario con el ID: " + id);
    }

}
