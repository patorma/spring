package com.patriciocontreras.springboot.backend.apirest.auth;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import com.patriciocontreras.springboot.backend.apirest.models.entity.Usuario;
import com.patriciocontreras.springboot.backend.apirest.models.services.IUsuarioService;

//Este componente se debe registrar en Authorization
@Component
public class InfoAdicionalToken implements TokenEnhancer {

	@Autowired
	private IUsuarioService usuarioService;
	
	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		
		Usuario usuario = usuarioService.findByUsername(authentication.getName());
		// se debe inyectar el usuario service para obteenr informacion adicional
		// se retorna el accessToken con la informacion adicional al token
		//se guarda Object porque el dato es generico
		Map<String, Object> info = new HashMap<>();
		info.put("info_adicional","Hola que tal!" + ": "+ usuario.getUsername());
		info.put("nombre_usuario", usuario.getId() + ": "+ usuario.getUsername());
		
		
		// se debe asignar info a accessToken
		// se hace un cast para acceder a este metodo
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
		return accessToken;
	}

}
