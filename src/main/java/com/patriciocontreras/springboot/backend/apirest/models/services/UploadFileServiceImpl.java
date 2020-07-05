package com.patriciocontreras.springboot.backend.apirest.models.services;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadFileServiceImpl implements IUploadFileService{
	
	private final Logger log = LoggerFactory.getLogger(UploadFileServiceImpl.class);
	// es final ya que no se le puede volver asignar un valor y static porque le pertenece a 
	// la clase y no al objeto 
	private final static String DIRECTORIO_UPLOAD = "uploads"; 

	@Override
	public Resource cargar(String nombreFoto) throws MalformedURLException {
		
		Path rutaArchivo = getPath(nombreFoto);
		log.info(rutaArchivo.toString());
		// creamos el recurso
		//se crea la instancia de UrlResource se pasa en el constructor la ruta de archivo convertida 
		// en una uri
		Resource recurso = new UrlResource(rutaArchivo.toUri());
			
		// se valida que la imagen no exista y sea validó o leible
		if(!recurso.exists() && !recurso.isReadable()) {
			rutaArchivo = Paths.get("src/main/resources/static/images").resolve("no-usuario.png").toAbsolutePath();
			// ahora se carga el recurso
			
				recurso = new UrlResource(rutaArchivo.toUri());	
			log.error("Error no se pudo cargar la imagen: " + nombreFoto);
		}
		return recurso;
	}

	@Override
	public String copiar(MultipartFile archivo) throws IOException {
		// se obtiene el nombre original del archivo
		// que viene en la peticion
		// se agrega un identificador random unico (generado con UUID)
		// si el archivo viene con espacios en blancos lo remplaze con nada replace()
		String nombreArchivo = UUID.randomUUID().toString() + "_" + archivo.getOriginalFilename().replace(" ", "");
		// se selecciona una ruta del equipo
		Path rutaArchivo = getPath(nombreArchivo);
		log.info(rutaArchivo.toString());
		
		Files.copy(archivo.getInputStream(), rutaArchivo);
		
		return nombreArchivo;
	}

	@Override
	public boolean eliminar(String nombreFoto) {
		
		if(nombreFoto != null &&  nombreFoto.length() >0) {
			// contiene la ruta completa y tambien el nombre de la imagen
			Path rutaFotoAnterior = Paths.get(DIRECTORIO_UPLOAD).resolve(nombreFoto).toAbsolutePath();
			// se convierte el path a archivo, con toFile() se convierte en archivo
			File archivoFotoAnterior = rutaFotoAnterior.toFile();
			// se valida que este archivo exista , se pueda leer y se elimina
			if(archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()) {
				archivoFotoAnterior.delete();
				return true;
			}
		}
		return false;
	}

	@Override
	public Path getPath(String nombreFoto) {
		// se encarga de contruir el path y retornar la ruta absoluta
		return  Paths.get(DIRECTORIO_UPLOAD).resolve(nombreFoto).toAbsolutePath();
	}

}
