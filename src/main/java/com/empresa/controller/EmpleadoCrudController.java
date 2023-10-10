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

import com.empresa.entity.Empleado;
import com.empresa.service.EmpleadoService;
import com.sistema.util.FunctionUtil;

import lombok.extern.apachecommons.CommonsLog;

@CommonsLog
@Controller
public class EmpleadoCrudController {

	@Autowired
	private EmpleadoService empleadoService;
	
	@GetMapping("/verCrudEmpleado")
	public String verInicio() {
		return "crudEmpleado";
	}
	
	@GetMapping("/consultaCrudEmpleado")
	@ResponseBody
	public List<Empleado> consulta(String filtro) {
		return empleadoService.listaPorNombreApellidoLike("%"+filtro+"%");
	}
	
	@PostMapping("/registraCrudEmpleado")
	@ResponseBody
	public Map<?, ?> registra(Empleado obj) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		obj.setEstado(1);
		obj.setFechaRegistro(new Date());
		obj.setFechaActualizacion(new Date());  
		List<Empleado> lstSalida = empleadoService.listaPorNombreApellidoIgual(obj.getNombres(), obj.getApellidos());
		if(!CollectionUtils.isEmpty(lstSalida)) {
			map.put("mensaje","El Empleado '" + obj.getNombres() + " " + obj.getApellidos() + "' ya existe");
			return map;
		}
		
		Empleado objSalida = empleadoService.insertaEmpleado(obj);
		if (objSalida == null) {
			map.put("mensaje", "Error en el registro");
		} else {
			List<Empleado> lista = empleadoService.listaPorNombreApellidoLike("%");
			map.put("lista", lista);
			map.put("mensaje", "Registro exitoso");
		}
		return map;
	}
	
	@ResponseBody
	@PostMapping("/eliminaCrudEmpleado")
	public Map<?, ?> elimina(int id) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		Empleado obj= empleadoService.buscaEmpleado(id).get();
		obj.setFechaActualizacion(new Date());  
		obj.setEstado( obj.getEstado() == 1 ? 0 : 1);
		Empleado objSalida = empleadoService.actualizaEmpleado(obj);
		if (objSalida == null) {
			map.put("mensaje", "Error en actualizar");
		} else {
			List<Empleado> lista = empleadoService.listaPorNombreApellidoLike("%");
			map.put("lista", lista);
		}
		return map;
	}
	
	/*@GetMapping("/buscaEmpleadoMayorEdad")
	@ResponseBody
	public String validaFecha(String fechaNacimiento) {
		
		log.info(">>VALIDA FECHA >>" + fechaNacimiento);
	
		//CONVERTIR DE STRING A DATE
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date fechaIngresada = null;
		try {
			fechaIngresada =  sdf.parse(fechaNacimiento);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		Calendar cal = Calendar.getInstance();//5/10/2023
		int anioActual = cal.get(Calendar.YEAR);
		cal.set(Calendar.YEAR, anioActual - 18);
		
		Date fechaMayorEdadLimite = cal.getTime(); //5/10/2005
		
		if(fechaIngresada.before(fechaMayorEdadLimite)) {
			return "{\"valid\":true}";
		}else {
			return "{\"valid\":false}";
		}
	}*/
	
	@GetMapping("/buscaEmpleadoMayorEdad")
	@ResponseBody
	public String validaFecha(String fechaNacimiento) {
		log.info(">> validaFecha >> " + fechaNacimiento);
		if(FunctionUtil.isMayorEdad(fechaNacimiento)) {
			return "{\"valid\":true}";
		}else {
			return "{\"valid\":false}";
		}
	}
}

