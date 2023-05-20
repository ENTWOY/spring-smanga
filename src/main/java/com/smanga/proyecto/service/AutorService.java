package com.smanga.proyecto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smanga.proyecto.entity.Autor;
import com.smanga.proyecto.repository.AutorRepository;

@Service
public class AutorService {
	
	// inyeccion
	@Autowired
	private AutorRepository autorRepo;
	
	// metodos
	// listado
	public List<Autor> listarAutores(){
		return autorRepo.findAll();
	}
	
	// registra y actualiza
	public void grabar(Autor bean) {
		autorRepo.save(bean);
	}
	
	// buscar (si existe; devuelve un autor con valor, si no devuelve nulo)
	public Autor buscar(Integer cod) {
		return autorRepo.findById(cod).orElse(null);
	}
	
	// eliminar
	public void eliminar(Integer cod) {
		autorRepo.deleteById(cod);
	}
}
