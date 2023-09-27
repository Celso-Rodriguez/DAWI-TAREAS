package com.empresa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.empresa.entity.Modalidad;

public interface ModalidadRepository extends JpaRepository<Modalidad, Integer> {

	//VALIDACIONES PARA QUE NO SE REPITA EL NOMBRE CUANDO SE VUELVA A ESCRIBIR LA MISMA EN EL TXT
	public abstract List<Modalidad> findByNombreIgnoreCase (String nombre);
	
	//PARA BUSCAR EL NOMBRE LETRA POR LETRA 
	public List<Modalidad> findByNombreLike(String nombre);
}
