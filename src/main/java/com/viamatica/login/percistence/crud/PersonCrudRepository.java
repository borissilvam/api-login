package com.viamatica.login.percistence.crud;

import com.viamatica.login.percistence.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonCrudRepository extends JpaRepository<PersonEntity, Integer> {
}
