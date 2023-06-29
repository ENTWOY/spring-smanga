package com.smanga.proyecto;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ConfigureImage implements WebMvcConfigurer{
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		/*WebMvcConfigurer.super.addResourceHandlers(registry);*/
		
		// llamar al objecto ResourceHandlerRegistry
		// con el 1.alias ingresamos a la 2.ruta fisica
		// EXTERNO
		// registry.addResourceHandler("/datosImg/**").addResourceLocations("file:/C:/Users/Usuario/Desktop/soImportant/datosImg/");
		
		// INTERNO
		// registry.addResourceHandler("/datosImg/**").addResourceLocations("classpath:/static/files/");
		// String ruta=".//src//main//resources//static//files//";
		
		// USB(LECTURA)
		registry.addResourceHandler("/datosImg/**").addResourceLocations("file:/D:/saveIMG/");
	}

}
