package com.empresa.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.empresa.entity.Alumno;
import com.empresa.service.AlumnoService;

@Controller
public class AlumnoController {
	
	
	@Autowired
	private AlumnoService service;
	
	@GetMapping(value = "/verAlumno" )
	public String ver() {
		return "registraAlumno";
	}
	
	
	@PostMapping("/registraAlumno")
	
	//REPONSEENTITY ES PARA PONER UN VALIDACION DE MENSAJE 
	public ResponseEntity<?> registra(Alumno obj) {
		HashMap<String, String> salida= new HashMap<>();
		
		Alumno objSalida = service.insertaAlumno(obj);
		if(objSalida == null) {
			salida.put("MENSAJE", "Error en el registro");
		}else {
			salida.put("MENSAJE", "Registro Exitoso");
		}
		
		return ResponseEntity.ok(salida);
	}
	

}
