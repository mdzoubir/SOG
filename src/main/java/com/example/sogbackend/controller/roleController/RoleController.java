package com.example.sogbackend.controller.roleController;

import com.example.sogbackend.model.Role;
import com.example.sogbackend.services.roleService.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/role")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @PostMapping
    public Role addNewRole(@RequestBody Role role){
        return roleService.addNewRole(role);
    }

    @GetMapping(path = "/all")
    public List<Role> allRoles(){
        return roleService.findAllRoles();
    }

    @GetMapping(path = "/{roleId}")
    public Role findRole(@PathVariable Long roleId){
        return roleService.findRole(roleId);
    }

    @PutMapping(path = "/{roleId}")
    public Role ronameRole(@PathVariable Long roleId,@RequestBody Role role){
        return roleService.updateRoleName(roleId, role);
    }

    @DeleteMapping(path = "{roleId}")
    public void deleteRole(@PathVariable Long roleId){
        roleService.deleteRole(roleId);
    }

}
