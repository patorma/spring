package com.patriciocontreras.springboot.backend.apirest.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{
	
	// interface de spring security
	// al inyectar la interface va a buscar una implementacion
	//concreta que sea del tipo UserDetailsService
	//como hay una sola va inyectar UsuarioService
	@Autowired
	private UserDetailsService usuarioService;
	
	// la forma de registrar objetos que retorna un metodo 
	//lo hacemos con la anotacion bean
	// la anotacion @Bean nos permite a traves de un metodo el objeto que 
	// retorna lo va a registrar en el contenedor de spring y despues se puede 
	//inyectar con autowired y utilizar en cualquier otra clase de configuracion
	// o encualquier clase de spring ,incluso en en un controlador, una clase service 
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		// se retona la instancia de este bean 
		return new BCryptPasswordEncoder();
	}

	// Lo siguiente es registrar en el authemtication manager de spring security
		// este servicio para auntenticar
		// sobreescribimos un metodo
	// se debe inyectar este componente spring via argumento
	@Override
	@Autowired
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//auth objeto a configurar
		// y se  registra el userdetailservice
		// y se pasa el usuarioService como parametro
		//despues se configura el passwordEncoder
		// y luego se pasa el tipo de encriptacion 
		auth.userDetailsService(this.usuarioService).passwordEncoder(passwordEncoder());
	}

	// Se sobreescribe el metodo authenticationManager() para registrar AuthenticationManager 
	// de la clase AuthorizationServerConfig
	// En este caso no es necesario poner el nombre ya que por defecto pone el nombre
	//que viene en el metodo
	@Bean("authenticationManager")
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManager();
	}
	
	
	

}
