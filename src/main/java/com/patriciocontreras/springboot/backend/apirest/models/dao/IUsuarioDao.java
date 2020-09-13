package com.patriciocontreras.springboot.backend.apirest.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.patriciocontreras.springboot.backend.apirest.models.entity.Usuario;

public interface IUsuarioDao extends CrudRepository<Usuario, Long> {

	//cualquiera de los dos métodos sirve para buscar el username
	public Usuario findByUsername(String username); 
	
	/*@Query("select u from Usuario u where u.username=?1 and u.otro=?2")
	public Usuario findByUsername2(String username,String otro); */
	
	@Query("select u from Usuario u where u.username=?1")
	public Usuario findByUsername2(String username); 
}
