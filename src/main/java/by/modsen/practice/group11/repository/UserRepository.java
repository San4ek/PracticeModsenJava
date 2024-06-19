package by.modsen.practice.group11.repository;

import by.modsen.practice.group11.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByLogin(String login);

    Boolean existsByLogin(String login);
    Boolean existsByEmail(String email);
}