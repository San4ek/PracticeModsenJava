package by.modsen.practice.group11.model.entity;


import by.modsen.practice.group11.model.enums.Role;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.proxy.HibernateProxy;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @UuidGenerator
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "email", unique = true, nullable = false, length = 20)
    private String email;

    @Column(name = "login", unique = true, nullable = false, length = 20)
    private String login;

    @Column(name = "password", nullable = false, length = 300)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

    @OneToOne
    @JoinColumn(name = "personal_info_id", nullable = false)
    @ToString.Exclude
    private PersonalInfo personalInfo;

    @OneToOne(
            mappedBy = "user",
            cascade = CascadeType.REMOVE)
    @ToString.Exclude
    private TokenRefresh tokenRefresh;

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Order> orders = new ArrayList<>();

    @Builder
    public User(UUID id, String email, String login, String password, Role role,
                PersonalInfo personalInfo, TokenRefresh tokenRefresh, List<Order> orders) {
        this.id = id;
        this.email = email;
        this.login = login;
        this.password = password;
        this.role = role;
        this.personalInfo = personalInfo;
        this.tokenRefresh = tokenRefresh;
        this.orders = orders;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        User user = (User) o;
        return getId() != null && Objects.equals(getId(), user.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
