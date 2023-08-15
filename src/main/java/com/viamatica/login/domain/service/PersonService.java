package com.viamatica.login.domain.service;

import com.viamatica.login.domain.Person;
import com.viamatica.login.percistence.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    public List<Person> getAll(){
        return personRepository.getAll();
    }

    public Optional<Person> getPerson(int id){
        return personRepository.getPerson(id);
    }

    public Person save(Person person){
        return personRepository.save(person);
    }

    public boolean delete(int idPerson){
        return getPerson(idPerson).map(person -> {
            personRepository.delete(idPerson);
            return true;
        }).orElse(false);
    }
}
