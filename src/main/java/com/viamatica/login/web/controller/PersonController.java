package com.viamatica.login.web.controller;

import com.viamatica.login.domain.Person;
import com.viamatica.login.domain.service.PersonService;
import com.viamatica.login.web.error.PersonNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@RestController
@RequestMapping("/person")
public class PersonController {
    @Autowired
    private PersonService personService;
    @GetMapping("/all")
    public ResponseEntity<List<Person> > getAll(){
        try {
            List<Person> personList = personService.getAll();
            if (personList.isEmpty()){
                throw new PersonNotFound();
            }else {
                return new ResponseEntity<>(personList, HttpStatus.OK);
            }
        }catch (PersonNotFound ex){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, ex.getMessage()
            );
        }

    }
    @GetMapping("/{id}")
    public ResponseEntity<Person> getPerson(@PathVariable("id") int id){

        try {
            return personService.getPerson(id)
                    .map(person -> new ResponseEntity<>(person, HttpStatus.OK))
                    .orElseThrow(()-> new PersonNotFound(id));
        }catch (PersonNotFound ex){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, ex.getMessage()
            );
        }


    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Person person){
        try {
            if (!person.getIdentification().matches("^\\d{10}$")){
                String mensaje = "El numero de identificación debe contener 10 dijitos";

                return new ResponseEntity<>(mensaje, HttpStatus.CONFLICT);
            } else if (!person.getIdentification().matches("^\\d+$")) {
                String mensaje = "El numero de identificación solo debe contener numeros";

                return new ResponseEntity<>(mensaje, HttpStatus.CONFLICT);
            } else if (!person.getIdentification().matches("^(?!.*(\\d)\\1{3})\\d{10}$")) {
                String mensaje = "El numero de identificación solo contiene 4 numeros consecutivos";

                return new ResponseEntity<>(mensaje, HttpStatus.CONFLICT);
            } else {
                return new ResponseEntity<>(personService.save(person), HttpStatus.CREATED);
            }
        }catch (Exception ex){
           throw  new ResponseStatusException(
                    HttpStatus.CONFLICT, ex.getMessage()
            );
        }

    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") int idPerson){
        try {
            if (personService.getPerson(idPerson).isEmpty()){
                throw new PersonNotFound(idPerson);
            }else {
                personService.delete(idPerson);
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }catch (PersonNotFound ex){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, ex.getMessage()
            );
        }

    }
}
