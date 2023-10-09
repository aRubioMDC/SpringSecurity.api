package com.authentication.app.security.controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.authentication.app.dto.Message;
import com.authentication.app.security.dto.JwtDto;
import com.authentication.app.security.dto.LoginUser;
import com.authentication.app.security.dto.NewUser;
import com.authentication.app.security.jwt.JwtProvider;
import com.authentication.app.security.service.UserService;

import jakarta.validation.Valid;

/**
 *
 * @author carubio
 */
@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {
    @Autowired
    UserService userService;

    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/register")
    public ResponseEntity<Message> register(@Valid @RequestBody NewUser newUser) {
        return ResponseEntity.ok(userService.save(newUser));
    }

    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUser loginUser) {
        return ResponseEntity.ok(userService.login(loginUser));
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtDto> refresh(@RequestBody JwtDto jwtDto) throws ParseException {
        return ResponseEntity.ok(userService.refresh(jwtDto));
    }
}
