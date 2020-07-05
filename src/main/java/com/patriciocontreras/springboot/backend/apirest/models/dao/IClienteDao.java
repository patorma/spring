package com.patriciocontreras.springboot.backend.apirest.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.patriciocontreras.springboot.backend.apirest.models.entity.Cliente;
import com.patriciocontreras.springboot.backend.apirest.models.entity.Region;

public interface IClienteDao extends JpaRepository<Cliente, Long> {
     // aca tenemos que mapear(asignar) este método a una consulta JPQL
	// (JPA Query) es la forma de utilizar Repository en JPA
	//debemos personalizarla nuestra consulta (JPA)
	// aca se trabaja con objetos y no con tablas
	// @Query se usa para personalizar nuestras consultas select o inclusos de operaciones
	//en JPA
	@Query("from Region")
	public List<Region> findAllRegiones();
}
