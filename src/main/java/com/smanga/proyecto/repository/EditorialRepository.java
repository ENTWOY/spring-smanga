package com.smanga.proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smanga.proyecto.entity.Editorial;

@Repository
public interface EditorialRepository extends JpaRepository<Editorial, Integer>{

}
