package com.microservicios.monitoreo.creditos.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.microservicios.monitoreo.creditos.modelo.ClienteBean;

@FeignClient(name = "ms-clientes", 
configuration = ClientRemoteConfig.class)
public interface ClientesServiceRemoteClient {
	@RequestMapping(method = RequestMethod.GET, value = "api/v1/clientes")
	ClienteBean buscarCliente(@PathVariable("id") String folioCliente);
}
