package com.IRL.appformacioncontinua.models.services;

import com.IRL.appformacioncontinua.models.entity.Usuario;

public interface IUsuarioService {
	
	public Usuario findByUsername(String username);

	

}
