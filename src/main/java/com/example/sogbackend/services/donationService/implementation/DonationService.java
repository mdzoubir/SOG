package com.example.sogbackend.services.donationService.implementation;

import com.example.sogbackend.config.exception.SogException;
import com.example.sogbackend.model.Donation;
import com.example.sogbackend.model.Girl;
import com.example.sogbackend.model.Visitor;
import com.example.sogbackend.repository.DonationRepository;
import com.example.sogbackend.repository.GirlRepository;
import com.example.sogbackend.repository.VisitorRepository;
import com.example.sogbackend.services.donationService.IDonationService;
import com.example.sogbackend.shared.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DonationService implements IDonationService {

    @Autowired
    private GirlRepository girlRepository;

    @Autowired
    private VisitorRepository visitorRepository;

    @Autowired
    private DonationRepository donationRepository;

    @Autowired
    private Utils utils;

    @Override
    public Donation addNewDonation(Donation donation, String girlId, String visitorId) {
        Optional<Girl> girl = girlRepository.findByUserId(girlId);
        Optional<Visitor> visitor = visitorRepository.findByUserId(visitorId);
        if (girl.isEmpty() || visitor.isEmpty()){
            throw new SogException("girl or visitor not exist");
        }else{
            donation.setDonationId(utils.generateUserID(32));
            donation.setGirl(girl.get());
            donation.setVisitor(visitor.get());
            return donationRepository.save(donation);
        }
    }

    @Override
    public Donation getDonation(String donationId) {
        Optional<Donation> donation = donationRepository.findByDonationId(donationId);
        if (donation.isEmpty()) throw new SogException("doantion not exist");
        return donation.get();
    }

    @Override
    public List<Donation> getAllDonation() {
        return donationRepository.findAll();
    }
}
