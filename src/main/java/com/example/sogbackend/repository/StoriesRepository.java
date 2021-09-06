package com.example.sogbackend.repository;

import com.example.sogbackend.model.Stories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StoriesRepository extends JpaRepository<Stories, Long> {
    Optional<Stories> findByStoriesId(String storieId);
}
