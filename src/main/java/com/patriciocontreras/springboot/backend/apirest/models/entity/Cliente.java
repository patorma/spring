package com.patriciocontreras.springboot.backend.apirest.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@NonNull
@Entity
@Table(name = "clientes" )
public class Cliente implements Serializable {
	 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty    
	@Size(min = 4, max=12)
	@Column(nullable = false)
	private String nombre;
	
	@NotEmpty 
	private String apellido;
	
	@NotEmpty 
	@Email
	@Column(nullable = false, unique=true)
	private String email;
	
	@Column(name="create_at")
	@Temporal(TemporalType.DATE)
	private Date createAt;
	
	//antes de persistir vamos a hacer la fecha automatica
	@PrePersist
	public void prePersist() {
		createAt = new Date(); 
	}
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
