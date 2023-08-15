package com.viamatica.login.percistence.crud;

import com.viamatica.login.percistence.entity.SessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SessionCrudRepository extends JpaRepository<SessionEntity, Integer> {

    List<SessionEntity> findByIdUser(int idUser);
}
