package com.empresa.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.empresa.entity.Medicamento;
import com.empresa.service.MedicamentoService;

@Controller
public class MedicamentoController {

	
	@Autowired
	private MedicamentoService service;
	
	@GetMapping(value = "/verMedicamento" )
	public String ver() {
		return "registraMedicamento";
	}
	
	@PostMapping("/registraMedicamento")
	
	//REPONSEENTITY ES PARA PONER UN VALIDACION DE MENSAJE 
		public ResponseEntity<?> registra(Medicamento obj) {
			HashMap<String, String> salida= new HashMap<>();
			
			Medicamento objSalida = service.insertaMedicamento(obj);
			if(objSalida == null) {
				salida.put("MENSAJE", "Error en el registro");
			}else {
				salida.put("MENSAJE", "Registro Exitoso");
			}
			
			return ResponseEntity.ok(salida);
		}
	
	
}
