package com.example.sogbackend.controller.roleController;

import com.example.sogbackend.model.Role;
import com.example.sogbackend.services.roleService.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(path = "/api/v1/role")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Role> addNewRole(@RequestBody Role role){
        return new ResponseEntity<>(roleService.addNewRole(role), HttpStatus.ACCEPTED);
    }

    @GetMapping(
            path = "/all",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<Role>> allRoles(){
        return new ResponseEntity<>(roleService.findAllRoles(), HttpStatus.OK);
    }

    @GetMapping(
            path = "/{roleId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Role> findRole(@PathVariable Long roleId){
        return new ResponseEntity<>(roleService.findRole(roleId), HttpStatus.OK);
    }

    @PutMapping(
            path = "/{roleId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Role> ronameRole(@PathVariable Long roleId,@RequestBody Role role){
        return new ResponseEntity<>(roleService.updateRoleName(roleId, role), HttpStatus.ACCEPTED);
    }

    @DeleteMapping(
            path = "{roleId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> deleteRole(@PathVariable Long roleId){
        roleService.deleteRole(roleId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
