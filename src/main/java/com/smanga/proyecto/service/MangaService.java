package com.smanga.proyecto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smanga.proyecto.entity.Manga;
import com.smanga.proyecto.repository.MangaRepository;

@Service
public class MangaService {

	// inyeccion
	@Autowired
	private MangaRepository mangaRepo;
	
	// listado
	public List<Manga> listarMangas() {
		return mangaRepo.findAll();
	}

	// registra y actualiza
	public void grabar(Manga bean) {
		mangaRepo.save(bean);
	}

	// buscar (si existe devuelve un autor con valor, si no devuelve null)
	public Manga buscar(Integer cod) {
		return mangaRepo.findById(cod).orElse(null);
	}

	// eliminar
	public void eliminar(Integer cod) {
		mangaRepo.deleteById(cod);
	}
	
	// List X ID(Detalle)
	public Manga listById(int id) {
		return mangaRepo.findById(id).get();
	}
	
	// parte de la implementacion de manejar update con una img
	public void actualizarImg(byte[] img, String nomAr, Integer cod) {
		mangaRepo.actualizarImagen(img, nomAr, cod);
	}
}
