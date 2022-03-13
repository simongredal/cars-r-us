package gredal.simon.carsrus.security;

import gredal.simon.carsrus.security.UserWithPassword;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UserDetailsImp implements UserDetails {
    private final String email;
    private final String password;
    private final boolean isEnabled;
    private final List<? extends GrantedAuthority> authorities;

    public UserDetailsImp(final UserWithPassword user) {
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.isEnabled = user.isEnabled();
        this.authorities = user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.toString())).toList();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isEnabled;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isEnabled;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isEnabled;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}
