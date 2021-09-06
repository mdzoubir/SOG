package com.example.sogbackend.services.visitorService;

import com.example.sogbackend.model.Visitor;

import java.util.List;

public interface IVisitorService {

    Visitor addNewVisitor(Visitor visitor);
    Visitor getVisitor(String visitorId);
    List<Visitor> getAllvisitors();
    Visitor updateVisitor(String visitorId, Visitor visitor);
    void deleteVisitor(String visitorId);
}
