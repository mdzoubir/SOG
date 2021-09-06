package com.example.sogbackend;

import com.example.sogbackend.model.Ambassador;
import com.example.sogbackend.model.Role;
import com.example.sogbackend.services.accountService.IAccountService;
import com.example.sogbackend.services.ambassadorService.IAmbassadorService;
import com.example.sogbackend.services.roleService.IRoleService;
import com.example.sogbackend.services.roleService.implementaion.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true)
public class SogBackendApplication {

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public static void main(String[] args) {
        SpringApplication.run(SogBackendApplication.class, args);
    }

}
