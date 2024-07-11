package by.modsen.practice.group11.repository;

import by.modsen.practice.group11.model.entity.User;
import by.modsen.practice.group11.model.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByLogin(String login);
    Optional<User> findByEmail(String email);

    Boolean existsByLogin(String login);
    Boolean existsByEmail(String email);

    List<User> findAllByRole(Role role);
}
