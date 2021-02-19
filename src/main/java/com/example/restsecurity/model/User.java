package com.example.restsecurity.model;

import com.example.restsecurity.common.enums.UserType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    @NotNull
    private String name;
    @Column(name = "surname")
    @NotNull
    private String surname;
    @Column(name = "email")
    @Email
    @NotEmpty(message = "Field email cannon be empty")
    @NotNull(message = "Field email cannot be null")
    private String email;
    @Column(name = "password")
    @Size(min = 8 ,message = "min length is 8 characters")
    @NotNull
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(name = "user_type", columnDefinition = " default 'USER'")
    UserType userType;
}
