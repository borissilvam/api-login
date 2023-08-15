package com.viamatica.login.domain.service;

import com.viamatica.login.percistence.crud.UserCrudRepository;
import com.viamatica.login.percistence.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserSecurityService implements UserDetailsService {
    @Autowired
    private UserCrudRepository userCrudRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userCrudRepository.findByUserName(username)
                .orElseThrow(()-> new  UsernameNotFoundException("User " + username + "not found"));


        List<String> roles = userEntity.getRolUserEntityList()
                .stream()
                .map(rolUser -> rolUser.getRolEntity().getRolName())
                .collect(Collectors.toList());

        return User.builder()
                .username(userEntity.getUserName())
                .password(userEntity.getPassword())
                .roles(roles.toArray(new String[0]))
                .accountLocked(userEntity.isLocked())
                .build();
    }
}
