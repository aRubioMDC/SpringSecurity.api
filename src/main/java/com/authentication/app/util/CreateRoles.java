package com.authentication.app.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.authentication.app.security.entity.Role;
import com.authentication.app.security.enums.RoleName;
import com.authentication.app.security.service.RoleService;

/**
 *
 * @author carubio
 */
@Component
public class CreateRoles implements CommandLineRunner {

    @Autowired
    RoleService roleService;

    @Override
    public void run(String... args) throws Exception {
        if (!roleService.existsByRoleName(RoleName.ROLE_ADMIN)) {
            Role adminRole = new Role(RoleName.ROLE_ADMIN);
            roleService.save(adminRole);
        }
        if (!roleService.existsByRoleName(RoleName.ROLE_USER)) {
            Role userRole = new Role(RoleName.ROLE_USER);
            roleService.save(userRole);
        }
    }
}
