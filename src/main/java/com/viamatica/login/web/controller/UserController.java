package com.viamatica.login.web.controller;

import com.viamatica.login.domain.User;
import com.viamatica.login.domain.service.UserService;
import com.viamatica.login.web.error.UserNotFound;
import com.viamatica.login.web.validation.PasswordValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    PasswordValidator validator = new PasswordValidator();
    @GetMapping("/all")
    public ResponseEntity<List<User>>  getAll(){
        try {
            List<User> users = userService.getAll();
            if (users.isEmpty()){
                throw new UserNotFound();
            }
            return new ResponseEntity<>(users, HttpStatus.OK);
        }catch (UserNotFound ex){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, ex.getMessage()
            );
        }

    }
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") int id){
        try {
            return userService.getUser(id)
                    .map(user -> new ResponseEntity<>(user,HttpStatus.OK))
                    .orElseThrow(()-> new UserNotFound(id));
        }catch (UserNotFound ex){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, ex.getMessage()
            );
        }

    }
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody User user){

       try {
           String mensaje;
           if (!user.getUserName().matches("^[\\w]+$")){
               mensaje = "En nombre de usuario contiene signos";
               return new ResponseEntity<>(mensaje, HttpStatus.CONFLICT);
           }
           else if (userService.getUserByUserName(user.getUserName()).isPresent()) {
                   mensaje = "El nombre de usuario ya esta en uso";
                   return new ResponseEntity<>(mensaje, HttpStatus.CONFLICT);
           }
           else if (!user.getUserName().matches("^(?=.*\\\\d).+$")){
               mensaje = "El nombre de usuario debe contener al menos un numero";
               return new ResponseEntity<>(mensaje, HttpStatus.CONFLICT);
           }
           else if (!user.getUserName().matches("^(?=.*[A-Z]).+$")) {
               mensaje = "El nombre de usuario debe contener al menos una mayúscula";
               return new ResponseEntity<>(mensaje, HttpStatus.CONFLICT);
           } else if (!user.getUserName().matches("^.{8,20}$")) {
               mensaje = "El nombre de usuario debe contener entre 8 y 20 caracteres";
               return new ResponseEntity<>(mensaje, HttpStatus.CONFLICT);

           } else if (user.getRolUserList().size() == 2) {
               mensaje = "Esta Persona tiene muchos usuario creador";
               return new ResponseEntity<>(mensaje, HttpStatus.CONFLICT);

           } else {
               return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
           }
       }catch (Exception e){
           throw new ResponseStatusException(
                   HttpStatus.CONFLICT, e.getMessage()
           );
       }

    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") int idUser){

        try {
            if (userService.getUser(idUser).isEmpty()){
                throw new UserNotFound(idUser);
            }else {
                userService.delete(idUser);
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }catch (UserNotFound ex){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, ex.getMessage()
            );
        }

    }
}
