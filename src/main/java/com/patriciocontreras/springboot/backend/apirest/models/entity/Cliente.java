package com.patriciocontreras.springboot.backend.apirest.models.entity;

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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
	@Column(nullable = false, unique=false)
	private String email;
	
	@NotNull(message = "no puede estar vacio")
	@Column(name="create_at")
	@Temporal(TemporalType.DATE)
	private Date createAt;
	
	private String foto;
	
	// fetch la forma como se obtiene los datos en la relacion
	// lazy (peresoza) cada vez que invoquemos a traves del metodo get region , cuando
	// se llame a ese metodo recien se hace la carga , cuando se le llama al atributo
	// es carga peresoza (lazy)
	// se ignora "hibernateLazyInitializer","hadler " ya que son propios de hibernate
	// del objeto proxy que esta relacionado al objeto region
	@NotNull(message = "la región puede ser vacia")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="region_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer","hadler"})
	private Region region;
	
	//antes de persistir vamos a hacer la fecha automatica
	/*@PrePersist
	public void prePersist() {
		createAt = new Date(); 
	}*/
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
