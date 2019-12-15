package com.IRL.appformacioncontinua.models.services;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

// Interface con los metodos con el codigo relacionado con las imagenes
public interface IUploadFileService {
	
	// metodo para cargar o mostrar la imagen
	public Resource cargar(String nombreImagen) throws MalformedURLException;
	
	// metodo para copiar o guardar la imagen 
	public String copiar(MultipartFile archivo) throws IOException;
	
	// metodo para eliminar la imagen
	public boolean eliminar(String nombreImagen);
	
	// metodo para obtener la ruta donde vamos a guardar el archivo
	public Path getPath(String nombreImagen);
		
	

}
