package com.example.sogbackend.services.visitorService.implementation;

import com.example.sogbackend.config.email.EmailService;
import com.example.sogbackend.config.exception.SogException;
import com.example.sogbackend.model.ConfirmationToken;
import com.example.sogbackend.model.Visitor;
import com.example.sogbackend.repository.ConfirmationTokenRepository;
import com.example.sogbackend.repository.VisitorRepository;
import com.example.sogbackend.services.accountService.IAccountService;
import com.example.sogbackend.services.visitorService.IVisitorService;
import com.example.sogbackend.shared.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

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

        ConfirmationToken confirmationToken = new ConfirmationToken(visitor);

        confirmationTokenRepository.save(confirmationToken);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(visitor.getEmail());
        mailMessage.setSubject("Complete Registration!");
        mailMessage.setFrom("zoubirtest12@gmail.com");
        mailMessage.setText("To confirm your account, please click here : "
                +"http://localhost:8080/api/v1/visitor/confirm-account?token="+confirmationToken.getConfirmationToken());
        emailService.sendEmail(mailMessage);

        return responce;
    }

    @Override
    public Visitor getVisitor(String visitorId) {
        Optional<Visitor> visitor = visitorRepository.findByUserId(visitorId);
        if (visitor.isEmpty()) throw new SogException("visitor not exist");
        return  visitor.get();
    }

    @Override
    public List<Visitor> getAllvisitors() {
        return visitorRepository.findAll();
    }

    @Override
    public Visitor updateVisitor(String visitorId, Visitor visitor) {
        Optional<Visitor> resp = visitorRepository.findByUserId(visitorId);
        if (resp.isEmpty()) throw new SogException("visitor not exist");
        resp.get().setFirstName(visitor.getFirstName());
        resp.get().setLastName(visitor.getLastName());
        resp.get().setPhoto(visitor.getPhoto());
        resp.get().setUserPhone(visitor.getUserPhone());
        return visitorRepository.save(resp.get());
    }

    @Override
    public void deleteVisitor(String visitorId) {
        Optional<Visitor> visitor = visitorRepository.findByUserId(visitorId);
        if (visitor.isEmpty()) throw new SogException("visitor not exist");
        visitorRepository.delete(visitor.get());
    }
}
