package com.patriciocontreras.springboot.backend.apirest.auth;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

//Declaramos como clase de configuracion y la habilitamos

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter{
	
	// se implementa un solo metodo , que nos permite implementar todas las reglas de 
	// seguridad de nuestros endpoints de nuestras rutas hacia los recursos 
	// pero de nuestra aplicacion 
	//.anyRequest().authenticated() siemrpe se coloca al final, para todas las rutas 
	//(endpoints) que no hayamos asignado permiso
	// Aqui se tienen reglas de acceso de usuarios por .oauth2
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		//vamos a dar acceso al listado clientes(sin autorizacion)
		http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/clientes").permitAll()
		.anyRequest().authenticated();
	}
	
	//luego vamos a SpringSecurityConfig
	
	
	
	

}