package com.example.sogbackend.services.visitorService.implementation;

import com.example.sogbackend.config.email.EmailService;
import com.example.sogbackend.config.exception.SogException;
import com.example.sogbackend.model.ConfirmationToken;
import com.example.sogbackend.model.Girl;
import com.example.sogbackend.model.Role;
import com.example.sogbackend.model.Visitor;
import com.example.sogbackend.repository.ConfirmationTokenRepository;
import com.example.sogbackend.repository.VisitorRepository;
import com.example.sogbackend.responce.VisitorResponse;
import com.example.sogbackend.services.accountService.IAccountService;
import com.example.sogbackend.services.visitorService.IVisitorService;
import com.example.sogbackend.shared.Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class VisitorService implements IVisitorService {

    @Autowired
    private Utils utils;

    @Autowired
    private VisitorRepository visitorRepository;

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private IAccountService accountService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Visitor addNewVisitor(Visitor visitor) {
        visitor.setUserId(utils.generateUserID(32));
        visitor.setPassword(passwordEncoder.encode(visitor.getPassword()));

        Visitor responce = visitorRepository.save(visitor);

        accountService.addRoleToUser(visitor.getEmail(), "visitor");

//        ConfirmationToken confirmationToken = new ConfirmationToken(visitor);

//        confirmationTokenRepository.save(confirmationToken);

//        SimpleMailMessage mailMessage = new SimpleMailMessage();
//        mailMessage.setTo(visitor.getEmail());
//        mailMessage.setSubject("Complete Registration!");
//        mailMessage.setFrom("zoubirtest12@gmail.com");
//        mailMessage.setText("To confirm your account, please click here : "
//                +"http://localhost:8080/api/v1/visitor/confirm-account?token="+confirmationToken.getConfirmationToken());
//        emailService.sendEmail(mailMessage);

        return responce;
    }

    @Override
    public VisitorResponse getVisitor(String visitorId) {
        Optional<Visitor> visitor = visitorRepository.findByUserId(visitorId);
        if (visitor.isEmpty()) throw new SogException("visitor not exist");

        VisitorResponse response = new VisitorResponse();
        BeanUtils.copyProperties(visitor.get(), response);
        List<Role> roles = visitor.get().getRoles();
        response.setRole(roles.get(0).getRoleName());
        return  response;
    }

    @Override
    public List<Visitor> getAllvisitors() {
        return visitorRepository.findAll();
    }


    //bug
    @Override
    public VisitorResponse updateVisitor(String visitorId, Visitor visitor) {
        Optional<Visitor> resp = visitorRepository.findByUserId(visitorId);
        if (resp.isEmpty()) throw new SogException("visitor not exist");
        resp.get().setFirstName(visitor.getFirstName());
        resp.get().setLastName(visitor.getLastName());
        resp.get().setAddress(visitor.getAddress());
//        resp.get().setPhoto(visitor.getPhoto());
        resp.get().setUserPhone(visitor.getUserPhone());
        Visitor visitor1 = visitorRepository.save(resp.get());
        System.out.println(visitor1.getFirstName());
        VisitorResponse visitorResponse = new VisitorResponse();
        BeanUtils.copyProperties(resp.get(), visitorResponse);
        return visitorResponse;
    }

    @Override
    public void deleteVisitor(String visitorId) {
        Optional<Visitor> visitor = visitorRepository.findByUserId(visitorId);
        if (visitor.isEmpty()) throw new SogException("visitor not exist");
        visitorRepository.delete(visitor.get());
    }
}
