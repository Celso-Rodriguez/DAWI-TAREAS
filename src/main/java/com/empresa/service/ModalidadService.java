package com.empresa.service;

import java.util.List;
import java.util.Optional;

import com.empresa.entity.Modalidad;

public interface ModalidadService {
 
	//INSERTAR
	public abstract Modalidad insertaModalidad(Modalidad obj);
	
	//BUSCAR POR NOMBRE
	public abstract List<Modalidad> buscaPorNombre(String nombre);
	
	//LISTAR POR NOMBRE
	public abstract List<Modalidad> listaPorNombre(String nombre);
	
	//ACTUALIZAR
	public abstract Modalidad actualizaModalidad(Modalidad obj);
	
	//BUSCAR POR NOMBRE LETRA POR LETRA
	public abstract List<Modalidad> listaPorNombreLike(String nombre);
	
	//BUSCA MODALIDAD
	public abstract Optional<Modalidad> buscaModalidad(int idModalidad);
}


