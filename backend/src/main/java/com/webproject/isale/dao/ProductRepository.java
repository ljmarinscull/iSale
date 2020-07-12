package com.webproject.isale.dao;

import com.webproject.isale.exceptions.BusinessException;
import com.webproject.isale.model.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long>{
    @Query(value = "SELECT * FROM products p ORDER BY p.views DESC LIMIT 3",
            nativeQuery = true)
    List<ProductEntity> popularsList();

    @Query(value = "SELECT * FROM products p WHERE p.user_id = ?1 ORDER BY p.created_at",
            nativeQuery = true)
    List<ProductEntity> userProductList(Long id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE products SET views = views + 1 WHERE id = ?1", nativeQuery = true)
    void addView(Long id);
}
