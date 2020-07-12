package com.webproject.isale.business.auth;

import com.webproject.isale.dao.UserRepository;
import com.webproject.isale.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthBusiness implements IAuthBusiness {

    @Autowired
    private UserRepository repo;

    @Override
    public String login(String username, String password) {
        return null;
    }

    @Override
    public UserEntity signUp(UserEntity user) {
        return null;
    }

}
