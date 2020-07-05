package com.patriciocontreras.springboot.backend.apirest.models.services;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface IUploadFileService {
	
	// Cargar y mantener la imagen
	public Resource cargar(String nombreFoto) throws MalformedURLException; 
	
	// metodo para copiar la imagen
	public String copiar(MultipartFile archivo)throws IOException;
	
	// eliminar foto, retorna si se elimino o no la foto
	public boolean eliminar(String nombreFoto);
	
	// obtener la ruta donde se guardara la foto
	public Path getPath(String nombreFoto);
	
}
