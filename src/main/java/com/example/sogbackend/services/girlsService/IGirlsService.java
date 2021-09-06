package com.example.sogbackend.services.girlsService;

import com.example.sogbackend.model.Girl;

import java.util.List;

public interface IGirlsService {
    Girl addNewGirl(Girl girl, String ambassadorId);
    Girl getGirl(String girlId);
    List<Girl> getAllGirls();
    Girl updateGirl(String GirlId, Girl girl);
    void deleteGirl(String girlId);
}
