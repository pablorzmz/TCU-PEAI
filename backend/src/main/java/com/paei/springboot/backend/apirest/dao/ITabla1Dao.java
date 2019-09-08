package com.paei.springboot.backend.apirest.dao;

import com.paei.springboot.backend.apirest.model.entity.Tabla1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ITabla1Dao extends JpaRepository <Tabla1, Long> {
}
