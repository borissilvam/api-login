package com.viamatica.login.web.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.viamatica.login.percistence.crud.UserCrudRepository;
import com.viamatica.login.percistence.entity.UserEntity;
import com.viamatica.login.web.error.ApiError;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    private final ObjectMapper objectMapper = new ObjectMapper();

    private static final int MAX_FAILED_ATTEMPTS = 3;
    @Autowired
    private UserCrudRepository userCrudRepository;
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        ApiError apiError = new ApiError();

        String userName = request.getParameter("username");
        System.out.println("Error al inicio de sesion username : "+ userName);

       Optional<UserEntity>  user = userCrudRepository.findByUserName(userName);

       if (user.isPresent()){

           user.get().setFailedLoginAttempts(user.get().getFailedLoginAttempts() + 1);

           if (user.get().getFailedLoginAttempts() == MAX_FAILED_ATTEMPTS){
               user.get().setLocked(true);
               System.out.println("El usuario se ha bloqueado");

           }

           userCrudRepository.save(user.get());
       } else if (user.isPresent() && user.get().isLocked()) {
           apiError.setFecha(Calendar.getInstance().getTime() );
           apiError.setMensaje( "El usuario está bloqueado, contacta con el Administrador");

           String jsonResponse = objectMapper.writeValueAsString(apiError);

           response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
           response.setContentType("application/json");
           response.getWriter().write(jsonResponse);

           System.out.println("El usuario está bloqueado, contacta con el Administrador");

       }else {
           apiError.setFecha(Calendar.getInstance().getTime() );

           if (exception instanceof BadCredentialsException) {
                apiError.setMensaje("La contraseña es incorrecta");
               System.out.println("Usuario o contraseña incorrecta");
           } else {

               apiError.setMensaje( exception.getMessage());

           }
           String jsonResponse = objectMapper.writeValueAsString(apiError);
           response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
           response.setContentType("application/json");
           response.getWriter().write(jsonResponse);
       }


    }
}
