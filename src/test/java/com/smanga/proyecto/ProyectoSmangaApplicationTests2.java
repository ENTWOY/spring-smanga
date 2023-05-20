package com.smanga.proyecto;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.smanga.proyecto.entity.Manga;
import com.smanga.proyecto.service.MangaService;

@SpringBootTest
class ProyectoSmangaApplicationTests2 {

	@Autowired
	private MangaService servi;
	
	@Test
	void contextLoads() {
		
		List<Manga> data = servi.listarMangas();
		
		for(Manga s:data)
			System.out.println(s.getCodLib() + "---" + s.getTitLib() + "----" + s.getDesLib() + "----" + s.getMangaAutor().getNomAut());
		
	}

}
