package com.webproject.isale.model.dto;

import javax.validation.constraints.Size;

public class UserUpdateProfileRequest {

    @Size(min = 4, message = "The field name should have at least 4 characters" )
    @Size(max = 15, message = "The field name is too long" )
    private String name;

    @Size(min = 10, message = "The field phone number should have at least 10 characters" )
    @Size(max = 12, message = "Invalid phone number" )
    private String phone;

    @Size(min = 8,  message = "The field password should have at least 8 characters")
    @Size(max = 30, message = "The field password is too long" )
    private String password;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

