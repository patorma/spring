package com.patriciocontreras.springboot.backend.apirest.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.patriciocontreras.springboot.backend.apirest.models.dao.IClienteDao;
import com.patriciocontreras.springboot.backend.apirest.models.entity.Cliente;

@Service
public class ClienrteServiceImpl implements IClienteService {

	//se hace inyeccion de dependencias con @AutoWired
	//se inyecta el cliente DAO
	@Autowired
	private IClienteDao clienteDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Cliente> findAll() {
		
		return (List<Cliente>) clienteDao.findAll();
	}

}
