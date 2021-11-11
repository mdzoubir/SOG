package com.example.sogbackend.repository;

import com.example.sogbackend.model.Donation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DonationRepository extends JpaRepository<Donation, Long> {
    Optional<Donation> findByDonationId(String donationId);
    List<Donation> findAllByVisitorUserId(String userId);
    List<Donation> findAllByGirl_UserId(String userId);
}
