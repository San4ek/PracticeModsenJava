package com.example.practicemodsenjava.model.entity;

import com.example.practicemodsenjava.model.enums.Gender;
import com.example.practicemodsenjava.model.enums.Role;
import jakarta.persistence.*;
import lombok.ToString;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "personal_info")
public class PersonalInfo {

    @Id
    @UuidGenerator
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "full_name", length = 45)
    private String fullName;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @Column(name = "birthday")
    private LocalDateTime birthday;

    @OneToMany(
            mappedBy = "personalInfo",
            cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Order> orders = new ArrayList<>();

    // TODO oneToOne
    @OneToOne
    private User user;
}
