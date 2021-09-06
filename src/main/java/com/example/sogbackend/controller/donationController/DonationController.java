package com.example.sogbackend.controller.donationController;


import com.example.sogbackend.model.Donation;
import com.example.sogbackend.services.donationService.IDonationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/donation")
public class DonationController {

    @Autowired
    private IDonationService donationService;

    @PostMapping("/{girlId}/{visitorId}")
    public Donation addDonation(@RequestBody Donation donation, @PathVariable String girlId, @PathVariable String visitorId){
        return donationService.addNewDonation(donation, girlId, visitorId);
    }

    @GetMapping("/{donationId}")
    public Donation getDonation(@PathVariable String donationId){
        return donationService.getDonation(donationId);
    }

    @GetMapping("/all}")
    public List<Donation> getAllDonation(){
        return donationService.getAllDonation();
    }
}
