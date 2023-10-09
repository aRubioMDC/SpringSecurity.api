package com.authentication.app.security.service;

import java.text.ParseException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.authentication.app.dto.Message;
import com.authentication.app.exceptions.CustomException;
import com.authentication.app.security.dto.JwtDto;
import com.authentication.app.security.dto.LoginUser;
import com.authentication.app.security.dto.NewUser;
import com.authentication.app.security.entity.Role;
import com.authentication.app.security.entity.User;
import com.authentication.app.security.enums.RoleName;
import com.authentication.app.security.jwt.JwtProvider;
import com.authentication.app.security.repository.UserRepository;

/**
 *
 * @author carubio
 */
@Service
@Transactional
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    RoleService roleService;

    @Autowired
    JwtProvider jwtProvider;

    public Optional<User> getByUserName(String userName) {
        return userRepository.findByUsername(userName);
    }

    public Optional<User> getByUsernameOrEmail(String usernameOrEmail) {
        return userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail);
    }

    public boolean existsByUserName(String userName) {
        return userRepository.existsByUsername(userName);
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public JwtDto login(LoginUser loginUser) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        return new JwtDto(jwt);
    }

    public Message save(NewUser newUser) {
        if (userRepository.existsByUsername(newUser.getUsername()))
            throw new CustomException(HttpStatus.BAD_REQUEST, "ese nombre de usuario ya existe");

        if (userRepository.existsByEmail(newUser.getEmail()))
            throw new CustomException(HttpStatus.BAD_REQUEST, "ese email de usuario ya existe");

        User user = new User(newUser.getName(), newUser.getUsername(), newUser.getEmail(),
                passwordEncoder.encode(newUser.getPassword()));

        Set<Role> roles = new HashSet<>();
        roles.add(roleService.getByRoleName(RoleName.ROLE_USER).get());

        if (newUser.getRoles().contains("admin"))
            roles.add(roleService.getByRoleName(RoleName.ROLE_ADMIN).get());

        user.setRoles(roles);
        userRepository.save(user);
        return new Message(user.getUsername() + " ha sido creado");
    }

    public JwtDto refresh(JwtDto jwtDto) throws ParseException {
        String token = jwtProvider.refreshToken(jwtDto);
        return new JwtDto(token);
    }
}
