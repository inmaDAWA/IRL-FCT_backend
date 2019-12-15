package com.IRL.appformacioncontinua.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.IRL.appformacioncontinua.models.entity.Curso;
import com.IRL.appformacioncontinua.models.entity.Nivel;

public interface ICursoService {
	
	public List<Curso> findAll();
	
	public Page<Curso> findAll(Pageable pageable);
	
	public Curso findById(Long id);
	
	public Curso save(Curso curso);
	
	public void delete(Long id);
	
	public List<Nivel> findAllNiveles();	

}
