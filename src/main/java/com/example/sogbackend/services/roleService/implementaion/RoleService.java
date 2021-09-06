package com.example.sogbackend.services.roleService.implementaion;

import com.example.sogbackend.config.exception.SogException;
import com.example.sogbackend.model.Role;
import com.example.sogbackend.repository.RoleRepository;
import com.example.sogbackend.services.roleService.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RoleService implements IRoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role addNewRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role updateRoleName(Long roleId, Role role) {
        Optional<Role> respence = roleRepository.findById(roleId);
        if(respence.isEmpty()) throw new SogException("role not exist");
        respence.get().setRoleName(role.getRoleName());
        return roleRepository.save(respence.get());
    }

    @Override
    public Role findRole(Long role) {
        Optional<Role> role1 = roleRepository.findById(role);
        if (role1.isEmpty()) throw new SogException("role not exist");
        return role1.get();
    }

    @Override
    public List<Role> findAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public void deleteRole(Long roleId) {
        Optional<Role> role = roleRepository.findById(roleId);
        if (role.isEmpty()) throw new SogException("role not exist");
        roleRepository.delete(role.get());
    }
}
