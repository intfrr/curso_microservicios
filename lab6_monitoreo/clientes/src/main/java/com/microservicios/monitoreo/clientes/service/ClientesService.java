package com.microservicios.monitoreo.clientes.service;

import com.microservicios.monitoreo.clientes.modelo.ClienteBean;
import com.microservicios.monitoreo.clientes.remote.modelo.Credito;
import com.microservicios.monitoreo.clientes.remote.modelo.ResumenCredito;

/**
 * <b>ClientesService.java</b>
 *
 * @author Jovani AC
 * @version 1.0
 */
public interface ClientesService {

    ClienteBean guardarCliente(ClienteBean clienteBean);
    ClienteBean consultarCliente(String idCliente);
    void borrarCliente(String idCliente);
    void modificar(String idCliente);
    ResumenCredito generarCredito(Credito credito);

}
