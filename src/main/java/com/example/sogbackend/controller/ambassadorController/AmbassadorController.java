package com.example.sogbackend.controller.ambassadorController;

import com.example.sogbackend.model.Ambassador;
import com.example.sogbackend.services.ambassadorService.IAmbassadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/ambassador")
public class AmbassadorController {

    @Autowired
    private IAmbassadorService ambassadorService;

    @PostMapping
    public Ambassador addNewAmbassador(@RequestBody Ambassador ambassador){
        return ambassadorService.addAmbassador(ambassador);
    }

    @GetMapping("/all")
    public List<Ambassador> getAllAmbassador(){
        return ambassadorService.getAllAmbassadors();
    }

    @GetMapping(path = "{userId}")
    public Ambassador getAmbassador(@PathVariable String userId){
        return ambassadorService.findAmbassador(userId);
    }

    @PutMapping(path = "/{userId}")
    public Ambassador updataAmbassador(@PathVariable String userId, @RequestBody Ambassador ambassador){
        return ambassadorService.updateAmbassador(ambassador, userId);
    }

    @DeleteMapping("/{userId}")
    public void delateAmbassador(@PathVariable String userId){
        ambassadorService.deleteAmbassador(userId);
    }
}
