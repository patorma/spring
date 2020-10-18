package com.patriciocontreras.springboot.backend.apirest.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

// Es de configuracion y se debe habilitar
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter{

	// Aca inyectamos BCryptPasswordEncoder que se anoto con @Bean
	// En SpringSecurityConfig
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	// Se necesita para que el AuthorizationServer lo pueda utilizar para el proceso de
	//login
	// Ver clase de SpringSecurityConfig que la implementa
	//Pero AuthenticationManager por defecto no es un Bean ,un componente de Spring,de
		// una forma hay que registrar este objeto en el contenedor de spring, lo intectamos
		// con autowired para que sea componente de spring
		// para eso en la clase SpringSecurityConfig  sobreescribimos un metodo  WebSecurityConfigurerAdapter
		// (AuthenticationManager)
	//Para asegurarnos que hacemos uso del objeto AuthenticationManager en @Autowired
	//lo vamos a seleccionar con el nombre del bean con @Qualifier (qualificador), dentro
	// como argumento se pone el nombre del bean a inyectar
	@Autowired
	@Qualifier("authenticationManager")
	private AuthenticationManager authenticationManager;
	
	// Ahora se debe implementar tres metodos de configuracion
	// Para eso se sobreescribi metodos de la clase AuthorizationServerConfigurerAdapter
	
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		
		super.configure(security);
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		
		super.configure(clients);
	}

	// Este se encarga del proceso de autenticacion y de validar el token
	// primera que iniciamos sesion enviamos nuestro usuario y contraseña y
	//si todo sale bien realiza la autenticacion genera el token , lo entrega al
	// usuario y el usuario con ese token puede acceder a las distintas paginas 
	// y recursos de nuestra aplicacion backend perop para poder accedr se debe validar
	// y esto se realiza en rutas o endpoints 
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
	
		// primero se registra el authenticationManager
		endpoints.authenticationManager(authenticationManager)
		.tokenStore(tokenStore()) // se encarga de crear un token, se podria omitir
		.accessTokenConverter(accessTokenConverter());// tiene que ver con la persistencia del token 
	}

	@Bean
	public JwtTokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}

	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();  
		return jwtAccessTokenConverter;
	}
	
	
	
}
