package com.authentication.app.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.authentication.app.security.entity.MainUser;
import com.authentication.app.security.entity.User;
import com.authentication.app.security.repository.UserRepository;

/**
 *
 * @author carubio
 */
@Service
public class UserDetailsServiceImplemet implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail).get();
        return MainUser.build(user);
    }
}
