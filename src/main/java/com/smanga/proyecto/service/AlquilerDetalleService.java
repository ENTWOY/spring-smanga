package com.smanga.proyecto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smanga.proyecto.entity.AlquilerDetalle;
import com.smanga.proyecto.repository.AlquilerDetalleRepository;

@Service
public class AlquilerDetalleService {
	
	@Autowired
	private AlquilerDetalleRepository repoAlquilerDetalle;
	
	public List<AlquilerDetalle> listaDetalles(){
		return repoAlquilerDetalle.findAll();
	}
	
	public void eliminar(Integer cod) {
		repoAlquilerDetalle.deleteById(cod);
	}

}
