package com.viamatica.login.percistence.mapper;

import com.viamatica.login.domain.RolUser;
import com.viamatica.login.percistence.entity.RolUserEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface RolUserMapper {

    @Mappings({
            @Mapping(source = "id.idRol", target = "idRol"),
            @Mapping(source = "active", target = "active")
    })
    RolUser toRolUser(RolUserEntity rolUser);
    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "id.idUser", ignore = true),
            @Mapping(target = "rolEntity", ignore = true),
            @Mapping(target = "userEntity", ignore = true)
    })
    RolUserEntity toRolUserEntity(RolUser rolUser);
}
