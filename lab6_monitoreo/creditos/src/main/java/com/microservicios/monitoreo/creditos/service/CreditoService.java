package com.microservicios.monitoreo.creditos.service;

import com.microservicios.monitoreo.creditos.modelo.Credito;
import com.microservicios.monitoreo.creditos.modelo.ResumenCredito;

public interface CreditoService {

	public ResumenCredito generarCredito(Credito credito);
}
