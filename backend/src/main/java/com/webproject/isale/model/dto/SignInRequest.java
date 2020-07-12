package com.webproject.isale.model.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SignInRequest {

    @NotNull(message = "The field username is required" )
    @Email(message = "The field username must be a valid email" )
    private String username;

    @NotNull(message = "The field password is required" )
    @Size(min = 8, message = "The field password must be at least 8 characters long" )
    @Size(max = 30, message = "The field password is too long" )
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}