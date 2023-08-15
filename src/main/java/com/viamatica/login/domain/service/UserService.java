package com.viamatica.login.domain.service;

import com.viamatica.login.domain.User;
import com.viamatica.login.percistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAll(){
        return userRepository.getAll();
    }

    public Optional<User> getUser(int id){
        return userRepository.getUser(id);
    }

    public Optional<User> getUserByUserName(String userName){
        return userRepository.getUserByUserName(userName);
    }

    public Optional<User> getUserByEmail(String email){
        return userRepository.getUerByEmail(email);
    }
    public User save(User user){
        return userRepository.save(user);
    }

    public boolean delete(int idUer){
        return getUser(idUer).map(user -> {
            userRepository.delete(idUer);
            return true;
        }).orElse(false);
    }
}
