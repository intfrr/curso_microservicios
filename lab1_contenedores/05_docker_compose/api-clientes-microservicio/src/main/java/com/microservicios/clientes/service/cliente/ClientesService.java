package com.microservicios.clientes.service.cliente;

import com.microservicios.clientes.entity.Clientes;

public interface ClientesService {

	Clientes guardarCliente(Clientes id);

	Clientes consultarCliente(String id);

	void borrarCliente(String id);

}
