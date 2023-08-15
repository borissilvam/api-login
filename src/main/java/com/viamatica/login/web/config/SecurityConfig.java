package com.viamatica.login.web.config;

import com.viamatica.login.percistence.crud.SessionCrudRepository;
import com.viamatica.login.percistence.crud.UserCrudRepository;
import com.viamatica.login.percistence.entity.SessionEntity;
import com.viamatica.login.percistence.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.time.LocalDateTime;
import java.util.Optional;

@Configuration
public class SecurityConfig {

    @Autowired
    private SessionCrudRepository sessionCrudRepository;

    @Autowired
    private UserCrudRepository userCrudRepository;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(httpSecuritySessionManagementConfigurer -> {
                    httpSecuritySessionManagementConfigurer
                            .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                            .maximumSessions(1)
                            ;
                })

                .authorizeHttpRequests(customizeRequests -> {
                    customizeRequests
                            .requestMatchers(HttpMethod.GET, "/login/api/user/**").hasAnyRole("ADMIN", "AUDITOR")
                            .requestMatchers(HttpMethod.PUT, "/login/api/user/**").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.POST, "/login/api/user/**").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.DELETE, "/login/api/user/**").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.GET, "/login/api/person/**").hasAnyRole("ADMIN", "AUDITOR")
                            .requestMatchers(HttpMethod.PUT, "/login/api/person/**").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.POST, "/login/api/person/**").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.DELETE, "/login/api/person/**").hasRole("ADMIN")
                            .anyRequest()
                            .authenticated();


                })
                .formLogin(httpSecurityFormLoginConfigurer -> {
                    httpSecurityFormLoginConfigurer
                            .failureHandler(authenticationFailureHandler())
                            ;
                })
                .logout(httpSecurityLogoutConfigurer -> {
                    httpSecurityLogoutConfigurer
                            .logoutSuccessHandler((request, response, authentication) -> {
                                saveSessionEvent(authentication.getName(),"logOut");
                            });
                })

                .httpBasic(Customizer.withDefaults());
        return http.build();
    }
    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler(){
        return new CustomAuthenticationFailureHandler();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    private  void saveSessionEvent(String userName, String eventType){
        Optional<UserEntity> user = userCrudRepository.findByUserName(userName);

        SessionEntity sessionEntity = new SessionEntity();
        sessionEntity.setEventType(eventType);
        sessionEntity.setIdUser(user.get().getIdUser());
        sessionEntity.setTimesTamp(LocalDateTime.now());
        sessionCrudRepository.save(sessionEntity);

    }
}
