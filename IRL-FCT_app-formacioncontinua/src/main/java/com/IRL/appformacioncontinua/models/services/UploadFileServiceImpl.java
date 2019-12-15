package com.IRL.appformacioncontinua.models.services;

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


// clase que implementa la interface IUploadFileService y sus metodos
@Service
public class UploadFileServiceImpl implements IUploadFileService {
	
	private final Logger log = LoggerFactory.getLogger(UploadFileServiceImpl.class);
	private final static String DIRECTORIO_UPLOAD = "uploads";
	
	// implementacion del metodo cargar
	@Override
	public Resource cargar(String nombreImagen) throws MalformedURLException {
		Path rutaArchivo = getPath(nombreImagen);
		log.info(rutaArchivo.toString());

		Resource recurso =  new UrlResource(rutaArchivo.toUri());
		
		if (!recurso.exists() && !recurso.isReadable()) {
			rutaArchivo = Paths.get("src/main/resources/static/images").resolve("no-usuario.png").toAbsolutePath();

			recurso = new UrlResource(rutaArchivo.toUri());
			
			log.error("Error no se pudo cargar la imagen: " + nombreImagen);
		}
		return recurso;
	}

	
	// implementacion del metodo copiar
	@Override
	public String copiar(MultipartFile archivo) throws IOException {
		
		String nombreArchivo = UUID.randomUUID().toString() + "_" + archivo.getOriginalFilename().replace(" ", "");

		Path rutaArchivo = getPath(nombreArchivo);
		log.info(rutaArchivo.toString());

		Files.copy(archivo.getInputStream(), rutaArchivo);
		
		return nombreArchivo;
	}
	
	// implementacion del metodo eliminar
	@Override
	public boolean eliminar(String nombreImagen) {
		
		if (nombreImagen != null && nombreImagen.length() > 0) {
			Path rutaFotoAnterior = Paths.get("uploads").resolve(nombreImagen).toAbsolutePath();
			File archivoFotoAnterior = rutaFotoAnterior.toFile();
			if (archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()) {
				archivoFotoAnterior.delete();
				return true;
			}
		}
		return false;
	}

	// implementacion del metodo para obtener la ruta donde vamos a guardar el archivo	
	@Override
	public Path getPath(String nombreImagen) {
		return Paths.get(DIRECTORIO_UPLOAD).resolve(nombreImagen).toAbsolutePath();
	}

}
