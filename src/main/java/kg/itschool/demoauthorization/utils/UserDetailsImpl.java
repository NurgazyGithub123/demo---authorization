package kg.itschool.demoauthorization.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kg.itschool.demoauthorization.mappers.UserMapper;
import kg.itschool.demoauthorization.models.entitys.User;
import kg.itschool.demoauthorization.services.AccountService;
import kg.itschool.demoauthorization.services.impl.AccountServiceImpl;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDetailsImpl implements UserDetails {

    static final Long serialVersionUID = 1l;

    Long id;
    String userName;
    String email;
    @JsonIgnore
    String password;
    Collection<? extends GrantedAuthority> authorities;

    public static UserDetailsImpl build(User user){
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().getRole().toString()));

        AccountService accountService = new AccountServiceImpl();
        return new UserDetailsImpl(
                user.getId(),
                user.getName(),
                user.getEmail(),
                accountService.findByUser(UserMapper.INSTANCE.toDto(user)).getPassword(),
                authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
