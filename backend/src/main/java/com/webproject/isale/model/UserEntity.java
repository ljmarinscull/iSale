package com.webproject.isale.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@JsonIgnoreProperties({"id", "password"})
@Entity
@Table(name = "users")
public class UserEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @NotNull
    @Column(columnDefinition = "text", unique = true)
    @Email(message = "Enter a valid email")
    private String username;

    @NotNull
    @Size(min = 8,message = "Enter a valid password")
    @Column(columnDefinition = "text")
    private String password;

    @NotNull
    @Size(min = 4, max = 30, message = "Enter a valid password")
    @Column(columnDefinition = "text")
    private String name;

    @NotNull
    @Size(min = 10, max = 12, message = "Enter a valid phone number")
    @Column(columnDefinition = "text")
    private String phone;

    public UserEntity(){}

    public UserEntity(String username, String password, String name, String phone){
        this.username = username;
        this.password = password;
        this.name = name;
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
