package com.example.sogbackend.repository;

import com.example.sogbackend.model.Ambassador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AmbassadorRepository extends JpaRepository<Ambassador, Long> {

    Optional<Ambassador> findByUserId(String userId);
}
