package com.viamatica.login.domain.repository;

import com.viamatica.login.domain.User;

import java.util.List;
import java.util.Optional;

public interface IUserRepository {

    List<User> getAll();
    Optional<User> getUser(int id);
    Optional<User> getUserByUserName(String userName);
    Optional<User> getUerByEmail(String email);
    User save(User user);
    void delete(int idUser);

}
