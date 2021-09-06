package com.example.sogbackend.services.storiesService.implementation;

import com.example.sogbackend.config.exception.SogException;
import com.example.sogbackend.model.Stories;
import com.example.sogbackend.repository.StoriesRepository;
import com.example.sogbackend.services.storiesService.IStoriesService;
import com.example.sogbackend.shared.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StoriesService implements IStoriesService {

    @Autowired
    private StoriesRepository storiesRepository;

    @Autowired
    private Utils utils;

    @Override
    public Stories addNewStories(Stories stories) {
        stories.setStoriesId(utils.generateUserID(32));
        return storiesRepository.save(stories);
    }

    @Override
    public Stories updateStories(String storieId, Stories stories) {
        Optional<Stories> getStories = storiesRepository.findByStoriesId(storieId);
        if (getStories.isEmpty()) throw new SogException("stories not exist");
        getStories.get().setDescription(stories.getDescription());
        getStories.get().setTitle(stories.getTitle());
        return storiesRepository.save(getStories.get());
    }

    @Override
    public void deleteStories(String storiesId) {
        Optional<Stories> getStories = storiesRepository.findByStoriesId(storiesId);
        if (getStories.isEmpty()) throw new SogException("stories not exist");
        storiesRepository.delete(getStories.get());
    }

    @Override
    public List<Stories> allStories() {
        return storiesRepository.findAll();
    }

    @Override
    public Stories findStories(String storiesId) {
        Optional<Stories> getStories = storiesRepository.findByStoriesId(storiesId);
        if (getStories.isEmpty()) throw new SogException("stories not exist");
        return getStories.get();
    }
}
