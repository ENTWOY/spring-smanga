package com.smanga.proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smanga.proyecto.entity.Rol;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer>{

}
