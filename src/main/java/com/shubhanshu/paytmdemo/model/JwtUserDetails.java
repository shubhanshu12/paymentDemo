package com.shubhanshu.paytmdemo.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class JwtUserDetails implements UserDetails {
    private String username;
    private Long id;
    private String token;
    private List<GrantedAuthority> grantedAuthorities;

    public JwtUserDetails(String username, Long id, String token, List<GrantedAuthority> grantedAuthorities) {
        this.username = username;
        this.id = id;
        this.token = token;
        this.grantedAuthorities = grantedAuthorities;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }


    public List<GrantedAuthority> getGrantedAuthorities() {
        return grantedAuthorities;
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
        return username;
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
