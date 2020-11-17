package com.patriciocontreras.springboot.backend.apirest.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
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
		// con security damos el acceso a los endpoint
		//se le da permiso a cualquier usuario, como usuario anonimo
		// de poder autenticarse en el Endpoint de login: /oauth/token/
		//generar el token cuando se autentica
		//Endpoint que verfica el token y su firma /oauth/check_token
		// estos endpoint estan protegidos por header authorization basic: Client Id + Client secret
		security.tokenKeyAccess("permitAll()")
		.checkTokenAccess("isAuthenticated()");
	}
	
	// En esta caso se va a registrar nuestra aplicacion cliente angular, si fueran mas 
	// se haria para cada uno con su id 

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		
		// vamos a crear un cliente con withClient
		//authorizedGrantTypes
		//asignar el tipo de concesion de nuestra aplicacion , osea como obtenemos el token
		//se obtiene un token de acceso renovado con refresh_token o token de actualizcion
		// se obtiene este token nuevo antes que expire el tiempo del token 
		// refresh token evitamos tener que a cada rato iniciar sesion al momento de solicitar
		//recursos
		clients.inMemory().withClient("angularapp")
		.secret(passwordEncoder.encode("12345"))
		.scopes("read","write") // le damos permiso de escritura y lectura CRUD
		.authorizedGrantTypes("password","refresh_token")
		.accessTokenValiditySeconds(3600) // tiempo de caducidad del token
		.refreshTokenValiditySeconds(3600); // tiemnpo de caducidad refresh token
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
	
	// con RSA_PRIVADA firmamos el token (jwt)
	// luego con RSA_PUBLICA para validar que nuestro token sea autentico

	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();  
		jwtAccessTokenConverter.setSigningKey(JwtConfig.RSA_PRIVADA);
		jwtAccessTokenConverter.setVerifierKey(JwtConfig.RSA_PUBLICA);
		return jwtAccessTokenConverter;
	}
	
	
	
}
