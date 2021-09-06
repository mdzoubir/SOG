package com.example.sogbackend.services.accountService;


import com.example.sogbackend.model.AppUser;
import com.example.sogbackend.model.Role;

import java.util.List;

public interface IAccountService {
    void addRoleToUser(String username,String rolename);
    AppUser loadUserByUsername(String username);
}
