package com.example.sogbackend.services.roleService;

import com.example.sogbackend.model.Role;

import java.util.List;

public interface IRoleService {
    Role addNewRole(Role role);
    Role updateRoleName(Long roleId, Role role);
    Role findRole(Long role);
    List<Role> findAllRoles();
    void deleteRole(Long roleId);
}
