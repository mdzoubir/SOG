package com.example.sogbackend.services.userdetails.implementation;

import com.example.sogbackend.model.AppUser;
import com.example.sogbackend.services.accountService.IAccountService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private IAccountService accountService;

    public UserDetailsServiceImpl(IAccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AppUser appUser = accountService.loadUserByUsername(email);
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        appUser.getRoles().forEach(appRole -> {
            authorities.add(new SimpleGrantedAuthority(appRole.getRoleName()));
        });
        return new User(appUser.getEmail(),appUser.getPassword(),authorities);
    }
}
