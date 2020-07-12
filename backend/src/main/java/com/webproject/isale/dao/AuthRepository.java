package com.webproject.isale.dao;

import com.webproject.isale.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository<UserEntity, Long> {}
