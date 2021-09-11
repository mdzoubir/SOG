package com.example.sogbackend.controller.storiesController;

import com.example.sogbackend.model.Stories;
import com.example.sogbackend.services.storiesService.IStoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping(path = "/api/v1/stories")
public class StoriesController {

    @Autowired
    private IStoriesService storiesService;

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Stories> addNewStories(@RequestBody Stories stories){
        return new ResponseEntity<>(storiesService.addNewStories(stories), HttpStatus.CREATED);
    }


    @GetMapping(
            path = "/{storiesId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Stories> getStories(@PathVariable String storiesId){
        return new ResponseEntity<>(storiesService.findStories(storiesId), HttpStatus.OK);
    }

    @GetMapping(
            path = "/all",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<Stories>> getAllStories(){
        return new ResponseEntity<>(storiesService.allStories(), HttpStatus.OK);
    }

    @PutMapping(
            path = "/{storiesId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Stories> updateStories(@PathVariable String storiesId, @RequestBody Stories stories){
        return new ResponseEntity<>(storiesService.updateStories(storiesId, stories), HttpStatus.ACCEPTED);
    }

    @DeleteMapping(
            path = "/{storiesId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> deleteStories(@PathVariable String storiesId){
        storiesService.deleteStories(storiesId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
