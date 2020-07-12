package com.webproject.isale.business.auth;

import com.webproject.isale.model.UserEntity;

public interface IAuthBusiness {
    String login(String username, String password) ;
    UserEntity signUp(UserEntity user);
}
