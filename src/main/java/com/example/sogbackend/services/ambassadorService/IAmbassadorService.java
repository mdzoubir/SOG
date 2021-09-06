package com.example.sogbackend.services.ambassadorService;


import com.example.sogbackend.model.Ambassador;

import java.util.List;

public interface IAmbassadorService {
    Ambassador addAmbassador(Ambassador ambassador);
    Ambassador updateAmbassador(Ambassador ambassador, String ambassadorId);
    Ambassador findAmbassador(String ambassadorId);
    void deleteAmbassador(String ambassadorId);
    List<Ambassador> getAllAmbassadors();
}
