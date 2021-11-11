package com.example.sogbackend.controller.girlController;

import com.example.sogbackend.model.Girl;
import com.example.sogbackend.responce.GirlResponse;
import com.example.sogbackend.services.girlsService.IGirlsService;
import com.example.sogbackend.services.girlsService.implementation.GirlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping(path = "api/v1/girl")
public class GirlController {

    @Autowired
    private IGirlsService girlsService;

    @GetMapping(path = "/all")
    public List<GirlResponse> allGirls(@RequestParam(value = "page", defaultValue = "1") int page, @RequestParam(value = "limit", defaultValue = "9") int limit){
        return girlsService.getAllGirls(page, limit);
    }

    @PostMapping("/{ambassadorId}")
    public GirlResponse addNewGirl(@PathVariable String ambassadorId,@RequestBody Girl girl){
        return girlsService.addNewGirl(girl, ambassadorId);
    }
}
