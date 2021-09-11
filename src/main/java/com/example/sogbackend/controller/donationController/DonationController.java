package com.example.sogbackend.controller.donationController;


import com.example.sogbackend.model.Donation;
import com.example.sogbackend.responce.DonationResponse;
import com.example.sogbackend.services.donationService.IDonationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping(path = "api/v1/donation")
public class DonationController {

    @Autowired
    private IDonationService donationService;

    @PostMapping(
            path = "/{girlId}/{visitorId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Donation> addDonation(@RequestBody Donation donation, @PathVariable String girlId, @PathVariable String visitorId){
        Donation d =  donationService.addNewDonation(donation, girlId, visitorId);
        return new ResponseEntity<>(d, HttpStatus.CREATED);
    }

    @GetMapping(
            path = "/{donationId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Donation> getDonation(@PathVariable String donationId){
        Donation donation = donationService.getDonation(donationId);
        return new ResponseEntity<>(donation, HttpStatus.OK);
    }

    @GetMapping(
            path = "/all}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<Donation>> getAllDonation(){
        List<Donation> donation = donationService.getAllDonation();
        return new ResponseEntity<>(donation, HttpStatus.OK);
    }


    @GetMapping(path = "/all/{userId}")
    public ResponseEntity<List<DonationResponse>> getAllByVisitor(@PathVariable String userId){
        return new ResponseEntity<>(donationService.getAllDonationByVisior(userId), HttpStatus.OK);
    }
}
