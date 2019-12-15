package com.IRL.appformacioncontinua.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
//import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="cursos")
public class Curso implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message= "no puede estar vacío.")
	@Size(min=3,  message = "el tamaño tiene que estar entre 3 y 100 caracteres.")
	@Column(nullable=false)
	private String titulo;
	
	@NotEmpty(message= "no puede estar vacío.")
	private String descripcion;	
	
	
	// campo para fecha
	@NotNull(message= "no puede estar vacío.")
	@Column(name="create_at")
	@Temporal(TemporalType.DATE)
	private Date createAt;
	
	// campo para imagen
	private String imagen;
	
	
	@NotNull(message= "el Nivel no puede estar vacío.")
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="nivel_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Nivel nivel;
	
	
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
		
	
	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	


	public Nivel getNivel() {
		return nivel;
	}

	public void setNivel(Nivel nivel) {
		this.nivel = nivel;
	}




	private static final long serialVersionUID = 1L;

}
