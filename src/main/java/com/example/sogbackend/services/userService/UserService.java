package com.example.sogbackend.services.userService;

import com.example.sogbackend.config.exception.SogException;
import com.example.sogbackend.model.AppUser;
import com.example.sogbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public AppUser getUser(String email){
        AppUser appUser = userRepository.findByEmail(email);
        if (appUser.equals(null)) throw  new SogException("user nnot found");
        return appUser;
    }
}
