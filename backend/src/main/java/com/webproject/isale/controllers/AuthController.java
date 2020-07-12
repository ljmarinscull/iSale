package com.webproject.isale.controllers;

import com.webproject.isale.business.services.JwtUtil;
import com.webproject.isale.business.users.IUserBusiness;
import com.webproject.isale.exceptions.ApiError;
import com.webproject.isale.model.UserEntity;
import com.webproject.isale.model.dto.SignInRequest;
import com.webproject.isale.model.dto.SignUpRequest;
import com.webproject.isale.model.response.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.io.UnsupportedEncodingException;

import static com.webproject.isale.utils.Constants.URL_BASE_LOGIN;
import static com.webproject.isale.utils.Constants.URL_BASE_SIGNUP;


@RestController
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private IUserBusiness userBusiness;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping(URL_BASE_LOGIN)
    ResponseEntity<?> login(@Valid @RequestBody SignInRequest request) throws UnsupportedEncodingException {
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );
        } catch (BadCredentialsException ex) {
            return new ResponseEntity(new ApiError(HttpStatus.BAD_REQUEST,"Invalid credentials","BadCredentialsException"),HttpStatus.BAD_REQUEST);
        } catch (Exception e){
            return new ResponseEntity(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR,e.getLocalizedMessage(),e.getCause().toString()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        UserEntity user = userBusiness.loadByUserName(request.getUsername());
        String token = jwtUtil.generateToken(user);
        return new ResponseEntity(new LoginResponse(token), HttpStatus.OK);
    }

    @PostMapping(URL_BASE_SIGNUP)
    ResponseEntity<Object> signUp(@Valid @RequestBody SignUpRequest request){
        try {
            userBusiness.save(new UserEntity(request.getUsername(), passwordEncoder.encode(request.getPassword()),request.getName(),request.getPhone()));
        }catch (Exception e){
            return new ResponseEntity(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR,"The username "+ request.getUsername() +" already exists","ConstraintViolationException"),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(HttpStatus.CREATED);
    }
}