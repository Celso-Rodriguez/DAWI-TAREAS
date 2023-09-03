package com.empresa.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.empresa.entity.Concurso;
import com.empresa.service.ConcursoService;

@Controller
public class ConcursoController {

	@Autowired
	private ConcursoService service;
	
	@GetMapping(value = "/verConcurso" )
	public String ver() {
		return "registraConcurso";
	}
	
	@PostMapping("/registraConcurso")
	
	//REPONSEENTITY ES PARA PONER UN VALIDACION DE MENSAJE 
		public ResponseEntity<?> registra(Concurso obj) {
			HashMap<String, String> salida= new HashMap<>();
			
			Concurso objSalida = service.insertaConcurso(obj);
			if(objSalida == null) {
				salida.put("MENSAJE", "Error en el registro");
			}else {
				salida.put("MENSAJE", "Registro Exitoso");
			}
			
			return ResponseEntity.ok(salida);
		}
}
