package com.smanga.proyecto.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smanga.proyecto.entity.Rol;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer>{

	//Método para buscar un role por su nombre en nuestra base de datos
    Optional<Rol> findByNomRol(String name);
}
