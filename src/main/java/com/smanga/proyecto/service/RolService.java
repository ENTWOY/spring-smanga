package com.smanga.proyecto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smanga.proyecto.entity.Rol;
import com.smanga.proyecto.repository.RolRepository;

@Service
public class RolService {
	
	// injection
	@Autowired
	private RolRepository rolRepo;
	
	// list
	public List<Rol> listarRoles() {
		return rolRepo.findAll();
	}
}
