package com.viamatica.login.domain.repository;

import com.viamatica.login.domain.Session;

import java.util.List;

public interface ISessionRespository {

    List<Session> getSessionByUserId(int idUser);
}
