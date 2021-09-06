package com.example.sogbackend.controller.storiesController;

import com.example.sogbackend.model.Stories;
import com.example.sogbackend.services.storiesService.IStoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/stories")
public class StoriesController {

    @Autowired
    private IStoriesService storiesService;

    @PostMapping
    public Stories addNewStories(@RequestBody Stories stories){
        return storiesService.addNewStories(stories);
    }


    @GetMapping(path = "/{storiesId}")
    public Stories getStories(@PathVariable String storiesId){
        return storiesService.findStories(storiesId);
    }

    @GetMapping(path = "/all")
    public List<Stories> getAllStories(){
        return storiesService.allStories();
    }

    @PutMapping(path = "/{storiesId}")
    public Stories updateStories(@PathVariable String storiesId, @RequestBody Stories stories){
        return storiesService.updateStories(storiesId, stories);
    }

    @DeleteMapping(path = "/{storiesId}")
    public void deleteStories(@PathVariable String storiesId){
        storiesService.deleteStories(storiesId);
    }
}
