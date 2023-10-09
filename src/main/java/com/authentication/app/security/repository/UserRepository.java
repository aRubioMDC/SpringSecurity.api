package com.authentication.app.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.authentication.app.security.entity.User;

/**
 *
 * @author carubio
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);

    Optional<User> findByUsernameOrEmail(String username, String email);

    Optional<User> findByTokenPassword(String tokenPassword);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
