package com.authentication.app.security.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.authentication.app.security.entity.Role;
import com.authentication.app.security.enums.RoleName;
import com.authentication.app.security.repository.RoleRepository;

/**
 *
 * @author carubio
 */
@Service
@Transactional
public class RoleService {
    @Autowired
    RoleRepository roleRepository;

    public Optional<Role> getByRoleName(RoleName roleName) {
        return roleRepository.findByRoleName(roleName);
    }

    public void save(Role role) {
        roleRepository.save(role);
    }

    public boolean existsByRoleName(RoleName roleName) {
        return roleRepository.existsByRoleName(roleName);
    }
}
