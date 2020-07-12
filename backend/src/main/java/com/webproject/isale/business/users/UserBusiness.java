package com.webproject.isale.business.users;

import com.webproject.isale.dao.UserRepository;
import com.webproject.isale.exceptions.BusinessException;
import com.webproject.isale.exceptions.NotFoundException;
import com.webproject.isale.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserBusiness implements IUserBusiness {

    @Autowired
    private UserRepository repo;

    @Override
    public List<UserEntity> list() throws BusinessException {
        try{
            return repo.findAll();
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
    }

    @Override
    public UserEntity load(Long id) throws BusinessException, NotFoundException {
        Optional<UserEntity> op;
        try{
            op = repo.findById(id);
            if(!op.isPresent()){
                throw new NotFoundException("User not found");
            }
            return op.get();
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
    }

    @Override
    public UserEntity save(UserEntity user) throws BusinessException {
        try {
            return repo.save(user);
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
    }

    @Override
    public void remove(Long id) throws BusinessException, NotFoundException{
        Optional<UserEntity> op;
        try{
            op = repo.findById(id);
            if(!op.isPresent()){
                throw new NotFoundException("User not found");
            } else {
                try {
                  repo.delete(op.get());
                }catch (Exception e){
                    throw new BusinessException(e.getMessage());
                }
            }
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
    }

    @Override
    public UserEntity loadByUserName(String username) throws NotFoundException {
        return repo.findByUsername(username);
    }
}
