package com.example.sogbackend;

import com.example.sogbackend.model.*;
import com.example.sogbackend.responce.GirlResponse;
import com.example.sogbackend.services.accountService.IAccountService;
import com.example.sogbackend.services.adminService.IAdminService;
import com.example.sogbackend.services.ambassadorService.IAmbassadorService;
import com.example.sogbackend.services.donationService.IDonationService;
import com.example.sogbackend.services.girlsService.IGirlsService;
import com.example.sogbackend.services.roleService.IRoleService;
import com.example.sogbackend.services.roleService.implementaion.RoleService;
import com.example.sogbackend.services.visitorService.IVisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

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

    @Bean
    CommandLineRunner start(
        IRoleService roleService,
        IAdminService adminService,
        IGirlsService girlsService,
        IVisitorService visitorService,
        IAmbassadorService ambassadorService,
        IDonationService donationService
    ){
        return args -> {

            //Add roles
            roleService.addNewRole(new Role(null, "admin"));
            roleService.addNewRole(new Role(null, "ambassador"));
            roleService.addNewRole(new Role(null, "visitor"));
            roleService.addNewRole(new Role(null, "girl"));

            //add new admin
            Admin admin = new Admin();
            admin.setFirstName("admin");
            admin.setLastName("admin");
            admin.setEmail("admin@gmail.com");
            admin.setPassword("12345678");
            admin.setPhoto("admin.png");
            admin.setUserPhone("0617171717");
            admin.setAddress("casa");
            adminService.addNewAdmin(admin);

            //add new visitor
            Visitor visitor = new Visitor();
            visitor.setFirstName("visitor");
            visitor.setLastName("visitor");
            visitor.setEmail("visitor@gmail.com");
            visitor.setPassword("12345678");
            visitor.setPhoto("visitor.png");
            visitor.setUserPhone("0617171717");
            visitor.setAddress("casa");
            Visitor visitor1 = visitorService.addNewVisitor(visitor);

            //add new ambassador
            Ambassador ambassador = new Ambassador();
            ambassador.setFirstName("ambassador");
            ambassador.setPassword("12345678");
            ambassador.setLastName("ambassador");
            ambassador.setEmail("ambassador@gmail.com");
            ambassador.setPhoto("ambassador.png");
            ambassador.setUserPhone("0617171717");
            ambassador.setAddress("casa");
            ambassador.setRegion("daraa");
            Ambassador ambassador1 = ambassadorService.addAmbassador(ambassador);

            //add new girl
            Girl girl = new Girl();
            girl.setFirstName("girl");
            girl.setLastName("girl");
            girl.setEmail("girl@gmail.com");
            girl.setPassword("12345678");
            girl.setPhoto("girl.png");
            girl.setUserPhone("0617171717");
            girl.setAddress("casa");
            girl.setRegion("daraa");
            girl.setBirthDay(new Date());
            girl.setDescription("zoe eorer eroazepo ezpifhaef");
            girl.setTarget(10000.00);
            GirlResponse girl1 = girlsService.addNewGirl(girl, ambassador1.getUserId());

            //add new donation
            Donation donation = new Donation();
            donation.setPrice(200.00);
            donationService.addNewDonation(donation, girl1.getUserId(), visitor1.getUserId());
        };
    }

}
