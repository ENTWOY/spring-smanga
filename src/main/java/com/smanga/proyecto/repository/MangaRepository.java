package com.smanga.proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.smanga.proyecto.entity.Manga;

import jakarta.transaction.Transactional;

@Repository
public interface MangaRepository extends JpaRepository<Manga, Integer>{

	// call transactional and query
	@Transactional
	// forzar la ejecucion de la sentencia, actualizacion de registro !important
	@Modifying
	@Query("update Manga m set m.img_lib=?1, m.nomArchivo=?2 where m.codLib=?3")
	public void actualizarImagen(byte[] img, String nomAr, Integer cod);
}
