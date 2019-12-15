package com.IRL.appformacioncontinua.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.IRL.appformacioncontinua.models.dao.ICursoDao;
import com.IRL.appformacioncontinua.models.entity.Curso;
import com.IRL.appformacioncontinua.models.entity.Nivel;

@Service
public class CursoServiceImpl implements ICursoService{
	
	@Autowired
	private ICursoDao cursoDao;
	
	@Override
	@Transactional(readOnly=true)
	public List<Curso> findAll() {
		return (List<Curso>) cursoDao.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public Page<Curso> findAll(Pageable pageable) {
		return cursoDao.findAll(pageable);
	}
	
	@Override
	@Transactional(readOnly=true)
	public Curso findById(Long id) {
		return cursoDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Curso save(Curso curso) {
		return cursoDao.save(curso);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		cursoDao.deleteById(id);
		
	}

	@Override
	@Transactional(readOnly=true)
	public List<Nivel> findAllNiveles() {
		return cursoDao.findAllNiveles();
	}



}
