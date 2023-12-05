package com.example.usersystem.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull(message = "name cannot be null")
    @Size(min = 5, message = "length must be more than 4")
    @Column(columnDefinition = "varchar(20) not null")
    private String name;
    @NotNull(message = "username cannot be null")
    @Size(min = 5,message = "username length must be more than 4")
    @Column(columnDefinition = "varchar(15) unique not null")
    private String username;
    @NotNull(message = "password cannot be null")
    @Column(columnDefinition = "varchar(20) not null")
    private String password;
    @NotNull(message = "email cannot be null")
    @Email(message = "must be a valid email")
    @Column(columnDefinition = "varchar(20) not null unique")
    private String email;
    @NotNull(message = "role cannot be null")
    @Column(columnDefinition = "varchar(5) not null  check(role='user' or role='admin')")
    private String role;
    @NotNull(message = "age cannot be null ")
    @Digits(integer = 2,fraction = 0,message = "Age must be numbers only")
    @Column(columnDefinition = "int not null")
    private Integer age;
}
