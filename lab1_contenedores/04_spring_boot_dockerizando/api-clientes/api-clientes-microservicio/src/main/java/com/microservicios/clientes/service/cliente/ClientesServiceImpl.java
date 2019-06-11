package com.microservicios.clientes.service.cliente;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.microservicios.clientes.modelo.ClienteBean;

@Service
public class ClientesServiceImpl implements ClientesService {

	private static final Logger log = Logger.getLogger(ClientesServiceImpl.class);

	private static List<ClienteBean> clientesNuevos = new ArrayList<ClienteBean>();

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
		return clienteBean;
	}

	public ClienteBean consultarCliente(String idCliente) {

		Optional<ClienteBean> cliente = clientesNuevos.stream().filter(s -> idCliente.equals(s.getFolioCliente()))
				.findFirst();

		return cliente.orElseThrow(
				() -> new ClienteNoEncontradoException("No se encontro al cliente con el id: " + idCliente, idCliente));
	}

	@Override
	public void borrarCliente(String idCliente) {
		clientesNuevos.removeIf(l -> l.getFolioCliente().equals(idCliente));

	}

}
