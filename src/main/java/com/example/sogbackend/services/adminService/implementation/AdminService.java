package com.example.sogbackend.services.adminService.implementation;


import com.example.sogbackend.model.Admin;
import com.example.sogbackend.repository.AdminRepository;
import com.example.sogbackend.services.accountService.IAccountService;
import com.example.sogbackend.services.adminService.IAdminService;
import com.example.sogbackend.shared.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class AdminService implements IAdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IAccountService accountService;

    @Autowired
    private Utils utils;

    @Override
    public Admin addNewAdmin(Admin admin) {
        admin.setUserId(utils.generateUserID(32));
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        Admin res = adminRepository.save(admin);
        accountService.addRoleToUser(admin.getEmail(), "admin");
        return res;
    }
}
