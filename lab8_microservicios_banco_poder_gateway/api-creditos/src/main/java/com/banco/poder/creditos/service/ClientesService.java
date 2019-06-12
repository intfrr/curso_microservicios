package com.banco.poder.creditos.service;

import java.util.List;

import com.banco.poder.creditos.entity.Clientes;
import com.banco.poder.creditos.modelo.ClientesDto;

public interface ClientesService {

	Clientes obtenerById(String id);

	Clientes guardar(ClientesDto clientesDto);

	Clientes modificar(String id, ClientesDto clientesDto);

	List<Clientes> obtenerTodo();
	
	void remover(String id);
}
