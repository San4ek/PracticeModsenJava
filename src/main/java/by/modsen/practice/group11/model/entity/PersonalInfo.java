package by.modsen.practice.group11.model.entity;

import by.modsen.practice.group11.model.enums.Gender;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "personal_info")
public class PersonalInfo {
    @Id
    @UuidGenerator
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "full_name", nullable = false, length = 45)
    private String fullName;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @Column(name = "birthday")
    private LocalDateTime birthday;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(
            mappedBy = "personalInfo",
            cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Order> orders = new ArrayList<>();
}
