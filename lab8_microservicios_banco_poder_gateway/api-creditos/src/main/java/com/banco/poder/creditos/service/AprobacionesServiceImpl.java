package com.banco.poder.creditos.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.banco.poder.creditos.excepciones.AprobacionesNoEncontradoException;
import com.banco.poder.creditos.modelo.AprobacionDto;
import com.banco.poder.creditos.repository.AprobacionesRepository;

@Service
public class AprobacionesServiceImpl implements AprobacionesService {

	private AprobacionesRepository aprobacionesRepository;

	public AprobacionesServiceImpl(AprobacionesRepository aprobacionesRepository) {
		this.aprobacionesRepository = aprobacionesRepository;
	}

	@Override
	public void guardar(AprobacionDto aprobacionDto) {

		aprobacionDto.setFecha(new Date().toString());

		aprobacionesRepository.persistir(aprobacionDto);
	}

	@Override
	public List<AprobacionDto> obtenerTodo() {
		return aprobacionesRepository.obtenerTodo();
	}

	@Override
	public AprobacionDto obtenerById(String idCredito) {

		AprobacionDto aprobacionDto = aprobacionesRepository.obtenerById(idCredito);

		if (aprobacionDto == null)
			throw new AprobacionesNoEncontradoException("No se encontro la aprobacion buscada", idCredito);

		return aprobacionDto;
	}
}
