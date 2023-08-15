package com.viamatica.login.percistence.mapper;

import com.viamatica.login.domain.Person;
import com.viamatica.login.percistence.entity.PersonEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    @Mappings({
            @Mapping(source = "idPerson", target = "idPerson"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "lastName", target = "lastName"),
            @Mapping(source = "birthdate", target = "birthdate"),
            @Mapping(source = "identification", target = "identification")

    })
    Person toPerson(PersonEntity personEntity);

    List<Person> toPersons(List<PersonEntity> personEntities);
    @InheritInverseConfiguration
    @Mapping(target = "userEntities", ignore = true)
    PersonEntity toPersonEntity(Person person);
}
