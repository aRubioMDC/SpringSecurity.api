package com.authentication.app.security.dto;

import jakarta.validation.constraints.NotBlank;

/**
 *
 * @author carubio
 */
public class LoginUser {
    @NotBlank(message = "user name/email obligatory")
    private String username;
    @NotBlank(message = "password obligatory")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String userName) {
        this.username = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
