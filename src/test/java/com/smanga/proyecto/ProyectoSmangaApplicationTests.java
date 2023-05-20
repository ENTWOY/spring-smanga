package com.smanga.proyecto;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.smanga.proyecto.entity.Autor;
import com.smanga.proyecto.service.AutorService;

@SpringBootTest
class ProyectoSmangaApplicationTests {

	@Autowired
	private AutorService servi;
	
	@Test
	void contextLoads() {
		
		List<Autor> data = servi.listarAutores();
		
		for(Autor s:data)
			System.out.println(s.getCodAut() + "---" + s.getNomAut() + "----" + s.getPaiAut());
		
	}

}
