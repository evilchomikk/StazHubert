package com.example.SpringStart.tables.customer.userDetails;

import com.example.SpringStart.commons.dto.customer.CustomerDTO;
import com.example.SpringStart.tables.customer.model.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@RequiredArgsConstructor
public class CustomerUserDetails implements UserDetails {

    private final CustomerDTO customer;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_customer"));
    }


    @Override
    public String getPassword() {
        return customer.getLoginData().getPassword();
    }

    @Override
    public String getUsername() {
        return customer.getLoginData().getLogin();
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
