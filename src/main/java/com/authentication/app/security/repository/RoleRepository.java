package com.authentication.app.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.authentication.app.security.entity.Role;
import com.authentication.app.security.enums.RoleName;

/**
 *
 * @author carubio
 */
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByRoleName(RoleName roleName);

    Boolean existsByRoleName(RoleName roleName);
}
