package com.banco.poder.creditos.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.banco.poder.creditos.excepciones.PagosNoEncontradoException;
import com.banco.poder.creditos.modelo.DetallePagosDto;
import com.banco.poder.creditos.modelo.PagosDto;
import com.banco.poder.creditos.repository.PagosRepository;

@Service
public class PagosServiceImpl implements PagosService {

	private PagosRepository pagosRepository;

	public PagosServiceImpl(PagosRepository pagosRepository) {
		this.pagosRepository = pagosRepository;
	}

	@Override
	public DetallePagosDto obtenerDetalle(String id) {

		List<PagosDto> pagosList = pagosRepository.consultarTodo(id);

		if (pagosList.size() == 0)
			throw new PagosNoEncontradoException("Pagos no registrados", id);

		DetallePagosDto detallePagosDto = new DetallePagosDto();

		double capital = 0;
		for (PagosDto pagos : pagosList) {
			capital += pagos.getImporte();
		}
		detallePagosDto.setCapital(capital);
		detallePagosDto.setIdCredito(id);
		detallePagosDto.setPagos(pagosList);

		return detallePagosDto;
	}

	@Override
	public PagosDto obtenerById(String idCredito) {

		return pagosRepository.consultarById(idCredito);
	}

	@Override
	public PagosDto guardar(PagosDto pagosDto) {

		pagosDto.setFecha(new Date().toString());

		return pagosRepository.persistir(pagosDto, pagosDto.getIdCredito());
	}

}
