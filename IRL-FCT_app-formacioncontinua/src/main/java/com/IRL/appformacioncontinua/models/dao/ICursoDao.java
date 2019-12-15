package com.IRL.appformacioncontinua.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;

import com.IRL.appformacioncontinua.models.entity.Curso;
import com.IRL.appformacioncontinua.models.entity.Nivel;

public interface ICursoDao extends JpaRepository<Curso, Long>{
	
	@Query("from Nivel")
	public List<Nivel> findAllNiveles();	

}
