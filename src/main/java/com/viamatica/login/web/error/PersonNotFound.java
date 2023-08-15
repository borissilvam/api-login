package com.viamatica.login.web.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PersonNotFound extends RuntimeException {

    public PersonNotFound(){
        super("No se han encontrado personas");
    }

    public PersonNotFound(Integer id){
        super("No se ha encontrado la persona con el ID:" + id);
    }
}
