package com.microservicios.monitoreo.clientes.remote;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.microservicios.monitoreo.clientes.remote.modelo.Credito;
import com.microservicios.monitoreo.clientes.remote.modelo.ResumenCredito;
@FeignClient(name = "ms-creditos", 
configuration = ClientRemoteConfig.class)
public interface CreditosServiceRemoteClient {
	
	
	@RequestMapping(method = POST, value = "api/v1/creditos")
	ResumenCredito guardarCredito(@RequestBody Credito credito);
}
