package com.smanga.proyecto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smanga.proyecto.entity.Editorial;
import com.smanga.proyecto.repository.EditorialRepository;

@Service
public class EditorialService {

	// inyeccion
	@Autowired
	private EditorialRepository EditorialRepo;

	// metodos
	// listado
	public List<Editorial> listarEditoriales() {
		return EditorialRepo.findAll();
	}

	// registra y actualiza
	public void grabar(Editorial bean) {
		EditorialRepo.save(bean);
	}

	// buscar (si existe devuelve un autor con valor, si no devuelve nulo)
	public Editorial buscar(Integer cod) {
		return EditorialRepo.findById(cod).orElse(null);
	}

	// eliminar
	public void eliminar(Integer cod) {
		EditorialRepo.deleteById(cod);
	}
}
