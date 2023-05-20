package com.smanga.proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smanga.proyecto.entity.AlquilerDetalle;

@Repository
public interface AlquilerDetalleRepository extends JpaRepository<AlquilerDetalle, Integer>{
	
}
