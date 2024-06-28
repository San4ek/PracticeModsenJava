package by.modsen.practice.group11.model;

import by.modsen.practice.group11.model.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class UserJwt implements UserDetails {

    private final UUID id;

    private final String username;

    @JsonIgnore
    private final String password;

    private final Collection<? extends GrantedAuthority> accessRights;

    @Builder
    public static UserJwt build(User user, String username) {
        List<GrantedAuthority> accessRights = List.of(new SimpleGrantedAuthority(user.getRole().name()));
        return new UserJwt(
                user.getId(),
                username,
                user.getPassword(),
                accessRights);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return accessRights;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    private UserJwt(UUID id, String username, String password, List<GrantedAuthority> accessRights) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.accessRights = accessRights;
    }
}
