package com.example.sogbackend.services.visitorService;

import com.example.sogbackend.model.Girl;
import com.example.sogbackend.model.Visitor;
import com.example.sogbackend.responce.VisitorResponse;

import java.util.List;

public interface IVisitorService {

    Visitor addNewVisitor(Visitor visitor);
    VisitorResponse getVisitor(String visitorId);
    List<Visitor> getAllvisitors();
    VisitorResponse updateVisitor(String visitorId, Visitor visitor);
    void deleteVisitor(String visitorId);
}
