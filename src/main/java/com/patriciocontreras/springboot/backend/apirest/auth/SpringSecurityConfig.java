package com.patriciocontreras.springboot.backend.apirest.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
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
	
	// Aqui se tienen reglas de acceso de usuarios por el el lado de spring 
	// en este lado debemos deshabilitar ciertas protecciones como
	// CSRF: Cross-site request forgery o falsificacion de peticion de sitios cruzados
	//Esta es para evitar cualquierb ataque es para proteger nuestro formulario atraves de
	//un token 
	// como trabajamos en el frontend con angular no la necesitamos en spring
	@Override
	public void configure(HttpSecurity http) throws Exception {
		//vamos a dar acceso al listado clientes(sin autorizacion)
		// ademas lo vamos a dejar sin estado (statelesss)
		//ya que vamos a trabajar con token
		http.authorizeRequests()
		.anyRequest().authenticated()
		.and()
		.csrf().disable()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); 
	
	}
	
	

}
