package com.smanga.proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smanga.proyecto.entity.Alquiler;

@Repository
public interface AlquilerRepository extends JpaRepository<Alquiler, Integer> {

}
