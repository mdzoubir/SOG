package com.example.sogbackend.services.ambassadorService.implementation;


import com.example.sogbackend.config.exception.SogException;
import com.example.sogbackend.model.Ambassador;
import com.example.sogbackend.repository.AmbassadorRepository;
import com.example.sogbackend.services.accountService.IAccountService;
import com.example.sogbackend.services.ambassadorService.IAmbassadorService;
import com.example.sogbackend.shared.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AmbassadorService implements IAmbassadorService {

    @Autowired
    private AmbassadorRepository ambassadorRepository;

    @Autowired
    private IAccountService accountService;

    @Autowired
    private Utils utils;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Ambassador addAmbassador(Ambassador ambassador) {
        ambassador.setUserId(utils.generateUserID(32));
        ambassador.setPassword(passwordEncoder.encode(ambassador.getPassword()));
        Ambassador responce = ambassadorRepository.save(ambassador);
        accountService.addRoleToUser(ambassador.getEmail(), "ambassador");
        return responce;
    }

    @Override
    public Ambassador updateAmbassador(Ambassador ambassador, String ambassadorId) {
        Optional<Ambassador> ambassador1 = ambassadorRepository.findByUserId(ambassadorId);
        if (ambassador1.isEmpty()) throw  new SogException("ambassador not exist");
        ambassador1.get().setFirstName(ambassador.getFirstName());
        ambassador1.get().setLastName(ambassador.getLastName());
        ambassador1.get().setRegion(ambassador.getRegion());
        ambassador1.get().setPhoto(ambassador.getPhoto());
        ambassador1.get().setUserPhone(ambassador.getUserPhone());
        return ambassadorRepository.save(ambassador1.get());
    }

    @Override
    public Ambassador findAmbassador(String ambassadorId) {
        Optional<Ambassador> ambassador = ambassadorRepository.findByUserId(ambassadorId);
        if (ambassador.isEmpty()) throw  new SogException("ambassador not exist");
        return ambassador.get();
    }

    @Override
    public void deleteAmbassador(String ambassadorId) {
        Optional<Ambassador> ambassador = ambassadorRepository.findByUserId(ambassadorId);
        if (ambassador.isEmpty()) throw new SogException("ambassador not exist");
        ambassadorRepository.delete(ambassador.get());
    }

    @Override
    public List<Ambassador> getAllAmbassadors() {
        return ambassadorRepository.findAll();
    }
}
