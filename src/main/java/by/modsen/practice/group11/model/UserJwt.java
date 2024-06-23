package by.modsen.practice.group11.model;

import by.modsen.practice.group11.model.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Getter
public class UserJwt implements UserDetails {

    private final UUID id;

    private final String login;

    private final String email;

    @JsonIgnore
    private final String password;

    private final Collection<? extends GrantedAuthority> accessRights;

    public static UserJwt build(User user) {
        List<GrantedAuthority> accessRights = List.of(new SimpleGrantedAuthority(user.getRole().name()));
        return new UserJwt(
                user.getId(),
                user.getLogin(),
                user.getEmail(),
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
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    private UserJwt(UUID id, String login, String email, String password, List<GrantedAuthority> accessRights) {
        this.id = id;
        this.login = login;
        this.email = email;
        this.password = password;
        this.accessRights = accessRights;
    }
}
