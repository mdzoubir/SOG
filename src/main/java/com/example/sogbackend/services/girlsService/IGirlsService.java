package com.example.sogbackend.services.girlsService;

import com.example.sogbackend.model.Girl;
import com.example.sogbackend.responce.GirlResponse;
import org.springframework.data.domain.Pageable;
import com.example.sogbackend.services.girlsService.implementation.GirlService;

import java.util.List;

public interface IGirlsService {
    GirlResponse addNewGirl(Girl girl, String ambassadorId);
    Girl getGirl(String girlId);
    List<GirlResponse> getAllGirls(int page, int limit);
    Girl updateGirl(String GirlId, Girl girl);
    void deleteGirl(String girlId);
    Double getRaised(String userId);
}
