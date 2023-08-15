package com.viamatica.login.percistence.crud;

import com.viamatica.login.percistence.entity.RolEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolCrudRepository extends JpaRepository<RolEntity, Integer> {
}
