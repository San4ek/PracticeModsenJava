package by.modsen.practice.group11.repository;

import by.modsen.practice.group11.model.entity.TokenRefresh;
import by.modsen.practice.group11.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TokenRefreshRepository extends JpaRepository<TokenRefresh, Long> {
    Optional<TokenRefresh> findByToken(String token);
    Optional<TokenRefresh> findByUserId(UUID userId);

    @Modifying
    int deleteByUser(User user);
}
