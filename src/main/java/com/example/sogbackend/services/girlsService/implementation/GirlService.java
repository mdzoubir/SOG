package com.example.sogbackend.services.girlsService.implementation;

import com.example.sogbackend.config.exception.SogException;
import com.example.sogbackend.model.Ambassador;
import com.example.sogbackend.model.Donation;
import com.example.sogbackend.model.Girl;
import com.example.sogbackend.repository.AmbassadorRepository;
import com.example.sogbackend.repository.DonationRepository;
import com.example.sogbackend.repository.GirlRepository;
import com.example.sogbackend.responce.GirlResponse;
import com.example.sogbackend.services.accountService.IAccountService;
import com.example.sogbackend.services.girlsService.IGirlsService;
import com.example.sogbackend.shared.Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class GirlService implements IGirlsService {

    @Autowired
    private GirlRepository girlRepository;

    @Autowired
    private DonationRepository donationRepository;

    @Autowired
    private Utils utils;

    @Autowired
    private AmbassadorRepository ambassadorRepository;

    @Autowired
    private IAccountService accountService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public GirlResponse addNewGirl(Girl girl, String ambassadorId) {
        Optional<Ambassador> ambassador = ambassadorRepository.findByUserId(ambassadorId);
        if (ambassador.isEmpty()){
            throw  new SogException("ambassador not exist");
        }else{
            girl.setUserId(utils.generateUserID(32));
            girl.setPassword(passwordEncoder.encode(girl.getPassword()));
            girl.setAmbassador(ambassador.get());
            Girl girl1 = girlRepository.save(girl);
            accountService.addRoleToUser(girl1.getEmail(), "girl");

            GirlResponse response=new GirlResponse();
            BeanUtils.copyProperties(girl1, response);
            response.setFullName(girl1.getFirstName() + " " + girl1.getLastName());
            response.setRaised(getRaised(girl1.getUserId()));
            return response;
        }
     }

    @Override
    public Girl getGirl(String girlId) {
        Optional<Girl> girl = girlRepository.findByUserId(girlId);
        if (girl.isEmpty()) throw new SogException("girl not exist");
        return girl.get();
    }

    @Override
    public List<GirlResponse> getAllGirls(int page, int limit) {
        if (page > 0) page -= 1;
        PageRequest pageable = PageRequest.of(page, limit);
        Page<Girl> girlPage = girlRepository.findAll(pageable);
        List<Girl> girls = girlPage.getContent();

        List<GirlResponse> responses = new ArrayList<>();
        for (Girl girl: girls){
            GirlResponse girlResponse = new GirlResponse();
            BeanUtils.copyProperties(girl, girlResponse);
            girlResponse.setFullName(girl.getFirstName()+ " " + girl.getLastName());
            girlResponse.setRaised(getRaised(girl.getUserId()));
            responses.add(girlResponse);
        }
        return responses;
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

    @Override
    public Double getRaised(String userId) {
        List<Donation> getAll = donationRepository.findAllByGirl_UserId(userId);
        Double raised = 0.00;
        for (Donation donation : getAll){
            raised = donation.getPrice() + raised;
        }
        return raised;
    }
}
