package com.microservicios.clientes.service.cliente;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservicios.clientes.entity.Clientes;
import com.microservicios.clientes.repository.ClientesRepository;

@Service
public class ClientesServiceImpl implements ClientesService {

	private static final Logger log = Logger.getLogger(ClientesServiceImpl.class);
	@Autowired
	private ClientesRepository clientesRepository;

	@Override
	public Clientes guardarCliente(Clientes clienteEntity) {
		return clientesRepository.save(clienteEntity);
	}

	@Override
	public Clientes consultarCliente(String id) {

		return clientesRepository.findById(id)
				.orElseThrow(() -> new ClienteNoEncontradoException("No se encontro el cliente : " , id));
	}

	@Override
	public void borrarCliente(String id) {

		Clientes clientes = clientesRepository.findById(id)
				.orElseThrow(() -> new ClienteNoEncontradoException("No se encontro el cliente : " ,id));

		clientesRepository.delete(clientes.getId());
	}

}
