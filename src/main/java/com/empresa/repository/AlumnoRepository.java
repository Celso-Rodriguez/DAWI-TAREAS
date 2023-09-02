package com.empresa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.empresa.entity.Alumno;

public interface AlumnoRepository extends JpaRepository<Alumno, Integer> {
	
	//Es interface para que el servidor se conecte.
	//Jpa es para llama al PK (idAlumno = Alumno) - (tipo de dato del PK = Int (Integer)).
}
