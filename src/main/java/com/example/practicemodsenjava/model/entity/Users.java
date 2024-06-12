package com.example.practicemodsenjava.model.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "email", unique = true, nullable = false, length = 20)
    private String email;

    @Column(name = "login", unique = true, nullable = false, length = 20)
    private String login;

    @Column(name = "password", nullable = false, length = 300)
    private String password;

    @Column(name = "full_name", length = 45)
    private String full_name;

    @Column(name = "gender", length = 2)
    private int gender;

    @Column(name = "birthday", length = 64)
    private LocalDateTime birthday;

    @Column(name = "role", length = 2)
    private int role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Orders> orders = new ArrayList<>();
}
