package com.IRL.appformacioncontinua.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.IRL.appformacioncontinua.models.entity.Curso;
import com.IRL.appformacioncontinua.models.entity.Nivel;
import com.IRL.appformacioncontinua.models.services.ICursoService;
import com.IRL.appformacioncontinua.models.services.IUploadFileService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api")
public class CursoRestController {

	@Autowired
	private ICursoService cursoService;
	
	@Autowired
	private IUploadFileService uploadService;
	


	@GetMapping("/cursos")
	public List<Curso> index() {
		return cursoService.findAll();
	}

	// paginar:
	@GetMapping("/cursos/page/{page}")
	public Page<Curso> index(@PathVariable Integer page) {
		//Pageable pageable = PageRequest.of(page, 8);
		Pageable pageable = PageRequest.of(page, 3);
		return cursoService.findAll(pageable);
	}


	
	// mostrar por id (con manejo de errores)
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@GetMapping("/cursos/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {

		Curso curso = null;
		Map<String, Object> response = new HashMap<>();

		try {
			curso = cursoService.findById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la Base de Datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (curso == null) {
			response.put("mensaje", "El curso ID: ".concat(id.toString().concat(" no existe la Base de Datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Curso>(curso, HttpStatus.OK);
	}

	
	

	// crear (con manejo de errores y validaciones)
	@Secured({"ROLE_ADMIN"})
	@PostMapping("/cursos")
	public ResponseEntity<?> create(@Valid @RequestBody Curso curso, BindingResult result) {

		Curso cursoNew = null;
		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()

					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		try {
			cursoNew = cursoService.save(curso);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la Base de Datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El curso ha sido creado con éxito");
		response.put("curso", cursoNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	

	
	
	// actualizar (con manejo de errores y validaciones)
	@Secured({"ROLE_ADMIN"})
	@PutMapping("/cursos/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Curso curso, BindingResult result, @PathVariable Long id) {

		Curso cursoActual = cursoService.findById(id);
		Curso cursoUpdated = null;
		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if (cursoActual == null) {
			response.put("mensaje", "Error: no se pudo editar, el curso ID: "
					.concat(id.toString().concat(" no existe la Base de Datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
			cursoActual.setDescripcion(curso.getDescripcion());
			cursoActual.setTitulo(curso.getTitulo());
			cursoActual.setCreateAt(curso.getCreateAt());
			cursoActual.setNivel(curso.getNivel());

			cursoUpdated = cursoService.save(cursoActual);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar el curso en la Base de Datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		

		response.put("mensaje", "El curso ha sido actualizado con éxito");
		response.put("curso", cursoUpdated);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}




	// eliminar (con manejo de errores)
	@Secured({"ROLE_ADMIN"})
	@DeleteMapping("/cursos/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();

		try {

			// codigo para elimnar imagen si se elimina el curso:
			Curso curso = cursoService.findById(id);
			String nombreImagenAnterior = curso.getImagen();
			
			uploadService.eliminar(nombreImagenAnterior);

			cursoService.delete(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar el curso en la Base de Datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El curso ha sido eliminado con éxito");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	
	
	

	
	
	// Metodo para subir imagen (los metodos copiar y eliminar ahora se implementan en la clase UploadFileService)
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@PostMapping("/cursos/upload")
	public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Long id) {
		Map<String, Object> response = new HashMap<>();

		Curso curso = cursoService.findById(id);

		if (!archivo.isEmpty()) {
			
			String nombreArchivo = null;
			try {
				nombreArchivo = uploadService.copiar(archivo);
			} catch (IOException e) {
				response.put("mensaje", "Error al subir la imagen del curso ");
				response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}

			String nombreImagenAnterior = curso.getImagen();

			uploadService.eliminar(nombreImagenAnterior);
			
			curso.setImagen(nombreArchivo);

			cursoService.save(curso);

			response.put("curso", curso);
			response.put("mensaje", "Has subido correctamente la imagen: " + nombreArchivo);
		}

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	
	
	

	

	// método para mostrar la imagen al usuario (el metodo cargar ahora se implementa en la clase UploadFileService)
	@GetMapping("/uploads/img/{nombreImagen:.+}")
	public ResponseEntity<Resource> verImagen(@PathVariable String nombreImagen) {
		
		Resource recurso = null;
		
		try {
			recurso = uploadService.cargar(nombreImagen);
		} catch(MalformedURLException e) {
			e.printStackTrace();
		}
		
		HttpHeaders cabecera = new HttpHeaders();
		cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"");

		return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);

	}
	
	
	
	
	
	
	// método para listar los niveles
	@Secured({"ROLE_ADMIN"})
	@GetMapping("/cursos/niveles")
	public List<Nivel> listarNiveles() {
		return cursoService.findAllNiveles();
	}

	
}
