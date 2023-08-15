package com.viamatica.login.percistence;

import com.viamatica.login.domain.User;
import com.viamatica.login.domain.repository.IUserRepository;
import com.viamatica.login.percistence.crud.PersonCrudRepository;
import com.viamatica.login.percistence.crud.UserCrudRepository;
import com.viamatica.login.percistence.entity.PersonEntity;
import com.viamatica.login.percistence.entity.UserEntity;
import com.viamatica.login.percistence.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Repository
public class UserRepository implements IUserRepository {
    @Autowired
    private UserCrudRepository userCrudRepository;
    @Autowired
    private PersonCrudRepository personCrudRepository;
    @Autowired
    private UserMapper mapper;


    @Override
    public List<User> getAll(){
        return mapper.toUsers(userCrudRepository.findAll()) ;
    }
    @Override
    public Optional<User> getUser(int id){
        Optional<UserEntity> user = userCrudRepository.findById(id);
        return user.map(userEntity -> mapper.toUser(userEntity)) ;
    }

    @Override
    public Optional<User> getUserByUserName(String userName) {
        Optional<UserEntity> user = userCrudRepository.findByUserName(userName);
        return user.map(userEntity -> mapper.toUser(userEntity));
    }

    @Override
    public Optional<User> getUerByEmail(String email) {
        Optional<UserEntity> user = userCrudRepository.findByEmail(email);

        return user.map(userEntity -> mapper.toUser(userEntity));
    }

    @Override
    public User save(User user) {


        UserEntity userEntity = mapper.toUserEntity(user);

        PersonEntity personEntity = personCrudRepository.getReferenceById(userEntity.getIdPerson());

        userEntity.getRolUserEntityList().forEach(rolUser -> rolUser.setUserEntity(userEntity));

        String email = personEntity.getName().substring(0,1).toLowerCase() +
                personEntity.getLastName().toLowerCase() +
                "@" + "mail.com" ;



        if (userCrudRepository.findByEmail(userEntity.getEmail()).isPresent()){
            char simbolo = '@';
            Random random = new Random();
            int randomNumber = random.nextInt(1000);
            String randomString = String.valueOf(randomNumber);
            int index = email.indexOf(simbolo);
            email = email.substring(0, index) + randomString + email.substring(index);
        }
        userEntity.setEmail(email);


        return mapper.toUser(userCrudRepository.save(userEntity));


    }

    @Override
    public void delete(int idUser){
        userCrudRepository.deleteById(idUser);
    }
    @FunctionalInterface
    public interface EmailSetter {
        void setEmail(String email);
    }
}
