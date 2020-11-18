package com.patriciocontreras.springboot.backend.apirest.models.services;

import com.patriciocontreras.springboot.backend.apirest.models.entity.Usuario;

public interface IUsuarioService {
   // vamos a agregar metodos  o contratos que nos devuelve el username
	public Usuario findByUsername(String username); 
}
