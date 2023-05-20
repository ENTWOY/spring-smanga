package com.smanga.proyecto.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;

// la anotación @ControllerAdvice indica al controlador 
// que maneje todas las excepciones no controladas en la aplicación.
@ControllerAdvice
public class ErrorController {

	/* Cuando ocurre un error en la aplicación, 
	 * Spring redirige automáticamente la solicitud 
	 * a un controlador de errores designado, que tiene 
	 * una anotación */
	@RequestMapping("/error")
    public String handleError() {
        // Devuelve la página de error personalizada .html
        return "error";
    }
}
