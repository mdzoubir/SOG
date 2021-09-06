package com.example.sogbackend.services.donationService;

import com.example.sogbackend.model.Donation;

import java.util.List;


public interface IDonationService {
    Donation addNewDonation(Donation donation, String girlId, String visitorId);
    Donation getDonation(String donationId);
    List<Donation> getAllDonation();
}
