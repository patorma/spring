package com.patriciocontreras.springboot.backend.apirest.auth;

import java.util.Arrays;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;



//Declaramos como clase de configuracion y la habilitamos

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter{
	
	// se implementa un solo metodo , que nos permite implementar todas las reglas de 
	// seguridad de nuestros endpoints de nuestras rutas hacia los recursos 
	// pero de nuestra aplicacion 
	//.anyRequest().authenticated() siempre se coloca al final, para todas las rutas 
	//(endpoints) que no hayamos asignado permiso
	// Aqui se tienen reglas de acceso de usuarios por .oauth2
	
	//en el siguiente metodo se dan los permisos a los distintos tipos de usuarios
	//a los recuroso endpoints segun su rol
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		//vamos a dar acceso al listado clientes(sin autorizacion)
		http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/clientes","/api/clientes/page/**","/api/uploads/img/**","/images/**").permitAll()
		/*.antMatchers(HttpMethod.GET, "/api/clientes/{id}").hasAnyRole("USER", "ADMIN")
		.antMatchers(HttpMethod.POST, "/api/clientes/upload").hasAnyRole("USER", "ADMIN")
		.antMatchers(HttpMethod.POST, "/api/clientes").hasRole("ADMIN")
		.antMatchers("/api/clientes/**").hasRole("ADMIN")*/
		.anyRequest().authenticated()
		.and().cors().configurationSource(corsConfigurationSource());
		
		
	}
	
	//luego vamos a SpringSecurityConfig
	
	// -------AQUI SE CONFIGURA CORS-----------
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
		config.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE","OPTIONS"));
		config.setAllowCredentials(true);
		config.setAllowedHeaders(Arrays.asList("Content-Type", "Authorization"));
		
		//ahora hay que registrar esta configuracion del cors para todas nuestras rutas , nuestros 
		//endpoints del backend
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);
		return source;
	}
	
	// se creo un filtro de cors se le pasa la configuracion anterior y luego se registra dentro del stack
	// o cunjunto de filtros que maneja spring framework y se le dio una prioridad 
	
	@Bean
	public FilterRegistrationBean<CorsFilter> corsFilter(){
		
		// se va a crear la instancia de esta clase
		FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<CorsFilter>(new CorsFilter(corsConfigurationSource()));
		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return bean;
	}
	
	
	

}
