package com.segurosguadalupe.service.remote;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.segurosguadalupe.modelo.SegurosDto;
import com.segurosguadalupe.modelo.remote.AutorizacionesResumenDto;

@FeignClient(name = "api-seguros-guadalupe-administracion", 
fallback = AutorizacionesServiceClientFallback.class, 
configuration = ClientRemoteConfig.class)
public interface AdministracionSegurosServiceRemote {

	@RequestMapping(method = POST, value = "administracion/v1/autorizaciones", produces = "application/json")
	AutorizacionesResumenDto solicitaAutorizacion(@RequestBody SegurosDto datosSeguro);

}
