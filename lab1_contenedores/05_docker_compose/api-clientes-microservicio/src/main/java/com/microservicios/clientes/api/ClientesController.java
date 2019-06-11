package com.microservicios.clientes.api;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.microservicios.clientes.entity.Clientes;
import com.microservicios.clientes.service.cliente.ClientesServiceImpl;

@RestController
@RequestMapping("api/v1/clientes")
public class ClientesController {

	private static final Logger log = Logger.getLogger(ClientesController.class);
	@Autowired
	private ClientesServiceImpl clientesService;

	@GetMapping("/{id}")
	public Clientes leer(@PathVariable("id") String id) {
		log.info(">>> api/v1/clientes buscando clientes");

		return clientesService.consultarCliente(id);
	}

	@PostMapping
	@ResponseStatus(CREATED)
	public Clientes crear(@RequestBody Clientes cliente) {
		log.info(">>> api/v1/clientes creando clientes");
		return clientesService.guardarCliente(cliente);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(NO_CONTENT)
	public void borrar(@PathVariable("id") String id) {
		clientesService.borrarCliente(id);
	}

}
