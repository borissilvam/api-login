package com.viamatica.login.percistence;

import com.viamatica.login.domain.Person;
import com.viamatica.login.domain.repository.IPersonRepository;
import com.viamatica.login.percistence.crud.PersonCrudRepository;
import com.viamatica.login.percistence.entity.PersonEntity;
import com.viamatica.login.percistence.mapper.PersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PersonRepository implements IPersonRepository {
    @Autowired
    private PersonCrudRepository personCrudRepository;
    @Autowired
    private PersonMapper mapper;
    @Override
    public List<Person> getAll(){
        List<PersonEntity> personEntities = personCrudRepository.findAll();

        return mapper.toPersons(personEntities) ;
    }
    @Override
    public Optional<Person> getPerson(int id){
        Optional<PersonEntity> person = personCrudRepository.findById(id);
        return  person.map(personEntity -> mapper.toPerson(personEntity));
    }

    @Override
    public Person save(Person person) {
        PersonEntity personEntity = mapper.toPersonEntity(person);
        return mapper.toPerson(personCrudRepository.save(personEntity));
    }

    @Override
    public void delete(int idUser){
       personCrudRepository.deleteById(idUser);
    }
}
