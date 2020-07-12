package com.webproject.isale.business.product;

import com.webproject.isale.exceptions.BusinessException;
import com.webproject.isale.exceptions.NotFoundException;
import com.webproject.isale.model.ProductEntity;

import java.util.List;

public interface IProductBusiness {
    List<ProductEntity> list() throws BusinessException;
    List<ProductEntity> popularsList() throws BusinessException;
    List<ProductEntity> userProductList(Long id);
    ProductEntity load(Long id) throws BusinessException, NotFoundException;
    ProductEntity save(ProductEntity productEntity) throws BusinessException;
    void remove(Long id) throws BusinessException, NotFoundException;
    void addView(Long id) throws BusinessException, NotFoundException;
}
