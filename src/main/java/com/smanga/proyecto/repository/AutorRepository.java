package com.smanga.proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smanga.proyecto.entity.Autor;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Integer>{

}
