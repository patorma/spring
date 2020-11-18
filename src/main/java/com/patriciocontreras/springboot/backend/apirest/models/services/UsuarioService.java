package com.patriciocontreras.springboot.backend.apirest.models.services;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.patriciocontreras.springboot.backend.apirest.models.dao.IUsuarioDao;
import com.patriciocontreras.springboot.backend.apirest.models.entity.Usuario;

//IUsuarioService es una interface personalizada y  UserDetailsService es de spring
@Service
public class UsuarioService implements IUsuarioService,UserDetailsService{
	//Manejar errores
	// como parametro se pasa el nombre de la clase
	private Logger logger = LoggerFactory.getLogger(UsuarioService.class); 
	
	// se inyecta el objeto bean dao
	@Autowired
	private IUsuarioDao usuarioDao;
	
	//El método retorna un UserDetails osea el usuario de spring security
	// Esto es una interface pero tambien tenemos la implementacion concreta
	//Return vamos a crear la instancia del User pero lo importamos desde spring
	//security la clase User

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		//obtenemos el usuario a traves de su username
		//authoritieses un list o collection
		Usuario usuario = usuarioDao.findByUsername(username);
		
		if(usuario == null) {
			logger.error("Error en el login: no existe el usuario '"+username+"' en el sistema!");
			throw new UsernameNotFoundException("Error en el login: no existe el usuario '"+username+"' en el sistema!");
		}
		//declaracion de la derecha roles del usuario
		//luego lo convertimos en un stream() para dejarlo como un GrantedAuthority
		// con stream convertimos cada elemento de esta lista en GrantedAuthority
		List<GrantedAuthority> authorities = usuario.getRoles()
				.stream()
				.map(role -> new SimpleGrantedAuthority(role.getNombre()))
				.peek(authority -> logger.info("Role: " + authority.getAuthority()))
				.collect(Collectors.toList());
		
		return new User(usuario.getUsername(), usuario.getPassword(), usuario.getEnabled(), true, true, true, authorities);
	}

	@Override
	@Transactional(readOnly = true)
	public Usuario findByUsername(String username) {
		
		return usuarioDao.findByUsername(username);
	}

}
