package com.example.sogbackend.services.donationService;

import com.example.sogbackend.model.Donation;
import com.example.sogbackend.responce.DonationResponse;

import java.util.List;


public interface IDonationService {
    Donation addNewDonation(Donation donation, String girlId, String visitorId);
    Donation getDonation(String donationId);
    List<Donation> getAllDonation();
    List<DonationResponse> getAllDonationByVisior(String userId);
}
