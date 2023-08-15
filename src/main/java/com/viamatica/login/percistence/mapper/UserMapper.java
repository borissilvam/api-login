package com.viamatica.login.percistence.mapper;

import com.viamatica.login.domain.User;
import com.viamatica.login.percistence.entity.UserEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {RolUserMapper.class})
public interface UserMapper {
    @Mappings({
            @Mapping(source = "idUser", target = "idUser"),
            @Mapping(source = "userName", target = "userName"),
            @Mapping(source = "password", target = "password"),
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "idPerson", target = "idPerson"),
            @Mapping(source = "disabled", target = "disabled"),
            @Mapping(source = "locked", target = "locked"),
            @Mapping(source = "rolUserEntityList", target = "rolUserList")
    })
    User toUser(UserEntity userEntity);
    List<User> toUsers(List<UserEntity> userEntities);

    @InheritInverseConfiguration
    @Mapping(target = "sessionActive", ignore = true)
    @Mapping(target = "personEntity", ignore = true)
    UserEntity toUserEntity(User user);
}
