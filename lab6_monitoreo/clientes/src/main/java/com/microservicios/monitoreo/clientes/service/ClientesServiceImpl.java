package com.microservicios.monitoreo.clientes.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.stereotype.Service;

import com.microservicios.monitoreo.clientes.modelo.ClienteBean;
import com.microservicios.monitoreo.clientes.remote.CreditosServiceRemoteClient;
import com.microservicios.monitoreo.clientes.remote.modelo.Credito;
import com.microservicios.monitoreo.clientes.remote.modelo.ResumenCredito;

/**
 * <b>ClientesServiceImpl.java</b>
 *
 * @author Jovani AC
 * @version 1.0
 */

@Service
public class ClientesServiceImpl {

	private static final Logger log = Logger.getLogger(ClientesServiceImpl.class);

	private static List<ClienteBean> clientesNuevos = new ArrayList<ClienteBean>();
	@Autowired
	private CreditosServiceRemoteClient creditoServiceRemoteCliente;
	@Autowired
	private CounterService counterService;

	static {
		ClienteBean clienteBean = new ClienteBean();
		clienteBean.setFolioCliente("999");
		clienteBean.setNombre("Jovani");
		clienteBean.setApellidoPaterno("Arzate");
		clienteBean.setApellidoMaterno("Cabrera");
		clienteBean.setEmail("jovaniac@gmail.com");
		clienteBean.setDireccion("azaleas temixco Mor");
		clienteBean.setGenero("masculino");
		clienteBean.setEdad(26);

		clientesNuevos.add(clienteBean);
	}

	public ClienteBean guardarCliente(ClienteBean clienteBean) {

		clienteBean.setFolioCliente(String.valueOf(new Random().nextInt()));
		clientesNuevos.add(clienteBean);
		counterService.increment("banco.poder.clientes.nuevos");

		return clienteBean;
	}

	public ClienteBean consultarCliente(String idCliente) {

		Optional<ClienteBean> cliente = clientesNuevos.stream().filter(s -> idCliente.equals(s.getFolioCliente()))
				.findFirst();

		return cliente.orElseThrow(
				() -> new ClienteNoEncontradoException("No se encontro al cliente con el id: " + idCliente, idCliente));
	}

	public void borrarCliente(String idCliente) {

		clientesNuevos.removeIf(l -> l.getFolioCliente().equals(idCliente));

	}

	public void modificar(String idCliente) {
		// TODO Auto-generated method stub

	}

	public ResumenCredito generarCredito(String folioCliente, Credito credito) {
		credito.setFolioCliente(folioCliente);

		log.info(">> api/v1/creditos invocando servicio remoto");

		return creditoServiceRemoteCliente.guardarCredito(credito);
	}

}
