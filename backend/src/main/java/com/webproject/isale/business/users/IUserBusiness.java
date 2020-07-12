package com.webproject.isale.business.users;

import com.webproject.isale.exceptions.BusinessException;
import com.webproject.isale.exceptions.NotFoundException;
import com.webproject.isale.model.UserEntity;

import java.util.List;

public interface IUserBusiness {
    List<UserEntity> list() throws BusinessException;
    UserEntity load(Long id) throws BusinessException, NotFoundException;
    UserEntity save(UserEntity user) throws BusinessException;
    void remove(Long id) throws BusinessException, NotFoundException;
    UserEntity loadByUserName(String username) throws NotFoundException;
}
