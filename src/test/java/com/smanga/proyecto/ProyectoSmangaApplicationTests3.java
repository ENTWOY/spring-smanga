package com.smanga.proyecto;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.smanga.proyecto.repository.AutorRepository;
import com.smanga.proyecto.repository.EditorialRepository;

@SpringBootTest
class ProyectoSmangaApplicationTests3 {
	
	@Autowired
	private EditorialRepository repo;
	
	@Test
	void contextLoads() {

		long count = repo.count();
		
		System.out.println("Total de registros: " + count);

	}

}
