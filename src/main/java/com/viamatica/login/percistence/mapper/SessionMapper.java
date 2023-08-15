package com.viamatica.login.percistence.mapper;

import com.viamatica.login.domain.Session;
import com.viamatica.login.percistence.entity.SessionEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SessionMapper {
    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "idUser", target = "idUser"),
            @Mapping(source = "eventType", target = "eventType"),
            @Mapping(source = "timesTamp", target = "timesTamp")
    })
    Session toSession(SessionEntity sessionEntity);
    List<Session> toSessions(List<SessionEntity> sessionEntityList);
    @InheritInverseConfiguration
    @Mapping(target = "user", ignore = true)
    SessionEntity toSessionEntity(Session session);
}
