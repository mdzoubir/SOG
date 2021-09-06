package com.example.sogbackend.services.storiesService;

import com.example.sogbackend.model.Stories;

import java.util.List;

public interface IStoriesService {
    Stories addNewStories(Stories stories);
    Stories updateStories(String storieId, Stories stories);
    void deleteStories(String storiesId);
    List<Stories> allStories();
    Stories findStories(String storiesId);
}
