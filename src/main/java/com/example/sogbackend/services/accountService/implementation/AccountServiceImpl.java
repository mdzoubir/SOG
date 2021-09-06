package com.example.sogbackend.services.accountService.implementation;

import com.example.sogbackend.model.AppUser;
import com.example.sogbackend.model.Role;
import com.example.sogbackend.repository.RoleRepository;
import com.example.sogbackend.repository.UserRepository;
import com.example.sogbackend.services.accountService.IAccountService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class AccountServiceImpl implements IAccountService {


    private UserRepository appUserRepository;
    private RoleRepository appRoleRepository;
    private PasswordEncoder passwordEncoder;

    public AccountServiceImpl(UserRepository appUserRepository, RoleRepository appRoleRepository, PasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.appRoleRepository = appRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public void addRoleToUser(String email, String rolename) {
        AppUser appUser=appUserRepository.findByEmail(email);
        Role appRole=appRoleRepository.findByRoleName(rolename);
        appUser.getRoles().add(appRole);
    }

    @Override
    public AppUser loadUserByUsername(String email) {
        return appUserRepository.findByEmail(email);
    }

}