package com.webproject.isale.model.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SignUpRequest {

    @NotNull(message = "The field username is required" )
    @Email(message = "The field username must be a valid email" )
    private String username;

    @NotNull(message = "The field password is required" )
    @Size(min = 8,max = 30, message = "The field password must be a valid email" )
    private String password;

    @NotNull(message = "The field name is required" )
    @Size(min = 4,max = 30, message = "The field name must be a valid name" )
    private String name;

    @NotNull(message = "The field name is required" )
    @Size(min = 4,max = 30, message = "The field name must be a valid name" )
    private String phone;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}