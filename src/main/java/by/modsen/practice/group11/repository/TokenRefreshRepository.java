package by.modsen.practice.group11.repository;

import by.modsen.practice.group11.model.entity.User;
import by.modsen.practice.group11.model.entity.TokenRefresh;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRefreshRepository extends JpaRepository<TokenRefresh, Long> {
    Optional<TokenRefresh> findByToken(String token);

    @Modifying
    int deleteByUser(User user);
}
