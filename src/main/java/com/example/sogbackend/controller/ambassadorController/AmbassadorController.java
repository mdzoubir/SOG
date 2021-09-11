package com.example.sogbackend.controller.ambassadorController;

import com.example.sogbackend.model.Ambassador;
import com.example.sogbackend.services.ambassadorService.IAmbassadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping(path = "/api/v1/ambassador")
public class AmbassadorController {

    @Autowired
    private IAmbassadorService ambassadorService;

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Ambassador> addNewAmbassador(@RequestBody Ambassador ambassador){
        return new ResponseEntity<>(ambassadorService.addAmbassador(ambassador), HttpStatus.CREATED) ;
    }

    @GetMapping(
            path = "/all",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<Ambassador>> getAllAmbassador(){
        return new ResponseEntity<>(ambassadorService.getAllAmbassadors(), HttpStatus.OK);
    }

    @GetMapping(
            path = "{userId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Ambassador> getAmbassador(@PathVariable String userId){
        Ambassador ambassador =  ambassadorService.findAmbassador(userId);
        return new ResponseEntity<>(ambassador, HttpStatus.OK);
    }

    @PutMapping(
            path = "/{userId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Ambassador> updataAmbassador(@PathVariable String userId, @RequestBody Ambassador ambassador){
        Ambassador ambassador1 = ambassadorService.updateAmbassador(ambassador, userId);
        return new ResponseEntity<>(ambassador1, HttpStatus.ACCEPTED);
    }

    @DeleteMapping(
            path = "/{userId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> delateAmbassador(@PathVariable String userId){
        ambassadorService.deleteAmbassador(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
