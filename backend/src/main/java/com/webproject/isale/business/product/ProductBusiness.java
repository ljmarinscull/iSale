package com.webproject.isale.business.product;

import com.webproject.isale.dao.ProductRepository;
import com.webproject.isale.exceptions.BusinessException;
import com.webproject.isale.exceptions.NotFoundException;
import com.webproject.isale.model.ProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductBusiness implements IProductBusiness {

    @Autowired
    private ProductRepository repo;

    @Override
    public List<ProductEntity> list() throws BusinessException {
        try{
            return repo.findAll();
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
    }

    @Override
    public List<ProductEntity> popularsList() throws BusinessException {
        return repo.popularsList();
    }

    @Override
    public List<ProductEntity> userProductList(Long id) {
        try{
            return repo.userProductList(id);
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
    }

    @Override
    public ProductEntity load(Long id) throws NotFoundException, BusinessException  {
        Optional<ProductEntity> op;
        op = repo.findById(id);
        if(!op.isPresent()){
            throw new NotFoundException("Product not found");
        }
        return op.get();
    }

    @Override
    public ProductEntity save(ProductEntity productEntity) throws BusinessException {
        try {
            return repo.save(productEntity);
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
    }

    @Override
    public void remove(Long id) throws BusinessException, NotFoundException{
        Optional<ProductEntity> op;
        try{
            op = repo.findById(id);
            if(!op.isPresent()){
                throw new NotFoundException("Product not found");
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
    public void addView(Long id) throws BusinessException, NotFoundException {
        Optional<ProductEntity> op;

        op = repo.findById(id);
        if(!op.isPresent()){
            throw new NotFoundException("Product not found");
        } else {
            try {
                repo.addView(id);
            }catch (Exception e){
                throw new BusinessException(e.getMessage());
            }
        }
    }
}
