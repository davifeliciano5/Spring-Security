package com.example.jwt.infra.securyt;


import com.example.jwt.model.Userjwt;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UserAuthenticated implements UserDetails {

    private final Userjwt userjwt;

    public UserAuthenticated(Userjwt userjwt){
        this.userjwt = userjwt;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(() -> "read");
    }

    @Override
    public String getPassword() {
        return userjwt.getPassword();
    }

    @Override
    public String getUsername() {
        return userjwt.getUsername();
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
}
