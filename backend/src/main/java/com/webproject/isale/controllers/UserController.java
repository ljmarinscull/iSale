package com.webproject.isale.controllers;

import com.webproject.isale.business.users.IUserBusiness;
import com.webproject.isale.exceptions.BusinessException;
import com.webproject.isale.exceptions.NotFoundException;
import com.webproject.isale.model.UserEntity;
import com.webproject.isale.model.dto.UserUpdateProfileRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.security.Principal;

import static com.webproject.isale.utils.Constants.URL_BASE_UPDATE_PROFILE;

@RestController
public class UserController {

    @Autowired
    IUserBusiness userBusiness;

    @PatchMapping(URL_BASE_UPDATE_PROFILE)
    ResponseEntity<Object> updateUserProfile(@Valid @RequestBody UserUpdateProfileRequest request, Principal principal){
        try {
            UserEntity user = userBusiness.loadByUserName(principal.getName());

            if(request.getName() != null && !request.getName().isEmpty())
                user.setName(request.getName());

            if(request.getPhone() != null && !request.getPhone().isEmpty())
                user.setPhone(request.getPhone());

            UserEntity newUser = userBusiness.save(user);
            return new ResponseEntity(newUser, HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity("User no found", HttpStatus.NOT_FOUND);
        } catch (BusinessException e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
