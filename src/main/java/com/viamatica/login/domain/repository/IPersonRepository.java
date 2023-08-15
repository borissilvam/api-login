package com.viamatica.login.domain.repository;

import com.viamatica.login.domain.Person;

import java.util.List;
import java.util.Optional;

public interface IPersonRepository {
    List<Person> getAll();
    Optional<Person> getPerson(int id);
    Person save(Person person);
    void delete(int idPerson);
}
