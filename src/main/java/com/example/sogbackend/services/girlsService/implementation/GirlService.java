package com.example.sogbackend.services.girlsService.implementation;

import com.example.sogbackend.config.exception.SogException;
import com.example.sogbackend.model.Ambassador;
import com.example.sogbackend.model.Girl;
import com.example.sogbackend.repository.AmbassadorRepository;
import com.example.sogbackend.repository.GirlRepository;
import com.example.sogbackend.services.girlsService.IGirlsService;
import com.example.sogbackend.shared.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class GirlService implements IGirlsService {

    @Autowired
    private GirlRepository girlRepository;

    @Autowired
    private Utils utils;

    @Autowired
    private AmbassadorRepository ambassadorRepository;


    @Override
    public Girl addNewGirl(Girl girl, String ambassadorId) {
        Optional<Ambassador> ambassador = ambassadorRepository.findByUserId(ambassadorId);
        if (ambassador.isEmpty()){
            throw  new SogException("ambassador not exist");
        }else{
            girl.setUserId(utils.generateUserID(32));
            girl.setAmbassador(ambassador.get());
            return girlRepository.save(girl);
        }
     }

    @Override
    public Girl getGirl(String girlId) {
        Optional<Girl> girl = girlRepository.findByUserId(girlId);
        if (girl.isEmpty()) throw new SogException("girl not exist");
        return girl.get();
    }

    @Override
    public List<Girl> getAllGirls() {
        return girlRepository.findAll();
    }

    @Override
    public Girl updateGirl(String girlId, Girl girl) {
        Optional<Girl> savedGirl = girlRepository.findByUserId(girlId);
        if (savedGirl.isEmpty()) throw new SogException("girl not exist");
        savedGirl.get().setDescription(girl.getDescription());
        savedGirl.get().setTarget(girl.getTarget());
        savedGirl.get().setRegion(girl.getRegion());
        savedGirl.get().setBirthDay(girl.getBirthDay());
        savedGirl.get().setFirstName(girl.getFirstName());
        savedGirl.get().setLastName(girl.getLastName());
        savedGirl.get().setPhoto(girl.getPhoto());
        savedGirl.get().setUserPhone(girl.getUserPhone());
        return girlRepository.save(girl);
    }

    @Override
    public void deleteGirl(String girlId) {
        Optional<Girl> girl = girlRepository.findByUserId(girlId);
        if (girl.isEmpty()) throw new SogException("girl not exist");
        girlRepository.delete(girl.get());
    }
}
