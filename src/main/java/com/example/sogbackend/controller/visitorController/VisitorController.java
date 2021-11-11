package com.example.sogbackend.controller.visitorController;

import com.example.sogbackend.model.AppUser;
import com.example.sogbackend.model.ConfirmationToken;
import com.example.sogbackend.model.Visitor;
import com.example.sogbackend.repository.ConfirmationTokenRepository;
import com.example.sogbackend.repository.UserRepository;
import com.example.sogbackend.responce.VisitorResponse;
import com.example.sogbackend.services.visitorService.IVisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping(path = "api/v1/visitor")
public class VisitorController {

    @Autowired
    private IVisitorService visitorService;

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public Visitor addNewVisitor(@RequestBody Visitor visitor){
        return visitorService.addNewVisitor(visitor);
    }

    @RequestMapping(value="/confirm-account", method= {RequestMethod.GET, RequestMethod.POST})
    public AppUser confirmUserAccount(@RequestParam("token")String confirmationToken) {
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

        if(token != null) {
            AppUser user = userRepository.findByEmail(token.getUserEntity().getEmail());
            user.setEmailVerificationStatus(true);
            userRepository.save(user);
            confirmationTokenRepository.delete(token);
            return user;
        } else {
            throw new RuntimeException("token not exist");
        }
    }


    @GetMapping(path = "/{visitorId}")
    public VisitorResponse getVisitor(@PathVariable String visitorId){
        return visitorService.getVisitor(visitorId);
    }

    @GetMapping(path = "/all")
    public List<Visitor> visitorsList(){
        return visitorService.getAllvisitors();
    }


    @DeleteMapping(path = "/{visitorId}")
    public void deleteVisitor(@PathVariable String visitorId){
        visitorService.deleteVisitor(visitorId);
    }

    @PostMapping(path = "/{visitorId}")
    public VisitorResponse updateVisitor(@PathVariable String visitorId, @RequestBody Visitor visitor){
        return visitorService.updateVisitor(visitorId, visitor);
    }
}
