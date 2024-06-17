package com.example.practicemodsenjava.model.entity;

import com.example.practicemodsenjava.model.enums.Gender;
import com.example.practicemodsenjava.model.enums.Role;
import jakarta.persistence.*;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PersonalInfo {
    @Column(name = "full_name", length = 45)
    private String fullName;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @Column(name = "birthday")
    private LocalDateTime birthday;

    @OneToMany(mappedBy = "personal_info", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Order> orders = new ArrayList<>();
}
