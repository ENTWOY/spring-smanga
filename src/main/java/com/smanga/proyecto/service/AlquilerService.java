package com.smanga.proyecto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smanga.proyecto.entity.Alquiler;
import com.smanga.proyecto.entity.AlquilerDetalle;
import com.smanga.proyecto.entity.Manga;
import com.smanga.proyecto.repository.AlquilerDetalleRepository;
import com.smanga.proyecto.repository.AlquilerRepository;

@Service
public class AlquilerService {
	
	@Autowired
	private AlquilerRepository repoAlquiler;
	@Autowired 
	private AlquilerDetalleRepository repoADetalle;
	
	// List
	public List<Alquiler> listarAlquileres() {
		return repoAlquiler.findAll();
	}
	// Actualizar(Estado)
	public void actualizar(Alquiler bean) {
		repoAlquiler.save(bean);
	}
	// Buscar
	public Alquiler buscar(Integer cod) {
		return repoAlquiler.findById(cod).orElse(null);
	}
	// Eliminar
	public void eliminar(Integer cod) {
		repoAlquiler.deleteById(cod);
	}
	
	// Transaccion
	@Transactional
	public void registrarAlquiler(Alquiler bean) throws Exception {
		try {
			// GrabarAlquiler
			repoAlquiler.save(bean);		
			// GrabarAlquilerDetalle
			for(AlquilerDetalle ad:bean.getListaADetalle()) {
				ad.setCodDetAlq(bean.getCodAlq());
				ad.getADetalleManga().getCodLib();
				repoADetalle.save(ad);
			}
		} catch (Exception ex) {
	        throw new Exception("Se produjo un error al guardar la información del alquiler o sus detalles!", ex);
		}
	}
}
