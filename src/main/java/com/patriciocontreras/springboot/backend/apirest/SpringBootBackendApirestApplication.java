package com.patriciocontreras.springboot.backend.apirest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SpringBootBackendApirestApplication implements CommandLineRunner  {

	// vamos a inyectar BCryptPasswordEncoder
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootBackendApirestApplication.class, args);
	}
	
	//con este script se codificara la comntraseña
	// el metodo run se ejecuta antes de la aplicacion
	// generar contraseñas encriptadas

	@Override
	public void run(String... args) throws Exception {
		
		String password = "12345";
		
		// se va a generar 4 contraseñas encriptadas para esta clave
		for (int i = 0; i < 4; i++) {
			String passwordBcrypt = passwordEncoder.encode(password);
			System.out.println(passwordBcrypt);
		}
		
	}

}
