package by.modsen.practice.group11.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.UuidGenerator;

import java.time.Instant;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "tokens_refresh")
public class TokenRefresh {
    @Id
    @UuidGenerator
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "token", nullable = false, unique = true)
    private String token;

    @Column(name = "expiry_date", nullable = false)
    private Instant expiryDate;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    @ToString.Exclude
    private User user;

    @PreRemove
    public void dismissParent() {
        user.setTokenRefresh(null);
        user = null;
    }
}
