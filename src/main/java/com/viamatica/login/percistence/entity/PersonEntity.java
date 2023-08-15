package com.viamatica.login.percistence.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.apache.catalina.User;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "persons")
@Data
public class PersonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_person")
    private Integer idPerson;
    private String name;
    private String lastName;
    private String identification;
    private LocalDate birthdate;

    @OneToMany(mappedBy = "personEntity")
    private List<UserEntity> userEntities;
}
