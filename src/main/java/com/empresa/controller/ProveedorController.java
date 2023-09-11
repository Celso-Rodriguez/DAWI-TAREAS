package com.empresa.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.empresa.entity.Pais;
import com.empresa.entity.Proveedor;
import com.empresa.entity.Tipo;
import com.empresa.service.PaisService;
import com.empresa.service.ProveedorService;
import com.empresa.service.TipoService;

@Controller
public class ProveedorController {
	@Autowired
	private TipoService service;
	
	@Autowired
	private PaisService Paisservice;
	
	@Autowired
	private ProveedorService proveedorservice;
	
	@GetMapping(value = "/verProveedor" )
	public String verProveedor() {return "registraProveedor";}
	
	@ResponseBody
	@GetMapping(value="/listaTipo")
	public List<Tipo> cargaTipo(){
		return service.listaTipo();	
	}
	@ResponseBody
	@GetMapping(value="/listaPais")
	public List<Pais> cargaPais(){
		return Paisservice.listaPais();	
	}
	
	@PostMapping("/registraProveedor")
	@ResponseBody
	public Map<?, ?> registra(Proveedor obj){
		HashMap<String, String> map = new HashMap<String, String>();
		obj.setFechaRegistro(new Date());
		Proveedor objSalida = proveedorservice.insertaProveedor(obj);
		
		if (objSalida == null) {
			map.put("MENSAJE", "Error en el registro");
		}else {
			map.put("MENSAJE", "Registro exitoso");
		}
		return map;
	}
	
	@GetMapping("/buscaPorNombreProveedor")
	@ResponseBody
	public String validaNombre(String nombre) {
		List<Proveedor> lstProveedor = proveedorservice.buscaPorNombre(nombre);
		if (CollectionUtils.isEmpty(lstProveedor)) {
			return "{\"valid\" : true }";
		} else {
			return "{\"valid\" : false }";
		}
	}
	
	@GetMapping("/buscaPorDniProveedor")
	@ResponseBody
	public String validaDni(String dni) {
		List<Proveedor> lstProveedor = proveedorservice.buscaPorDni(dni);
		if (CollectionUtils.isEmpty(lstProveedor)) {
			return "{\"valid\" : true }";
		} else {
			return "{\"valid\" : false }";
		}
	}
}
