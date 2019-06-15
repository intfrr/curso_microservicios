package com.banco.poder.creditos.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.banco.poder.creditos.modelo.CreditosDto;
import com.banco.poder.creditos.modelo.ReferenciasDto;
import com.banco.poder.creditos.repository.CreditosRepository;
import com.banco.poder.creditos.repository.ReferenciasRepository;

@Service
public class CreditosServiceImpl implements CreditosService {

	private CreditosRepository creditosRepository;
	private ReferenciasRepository referenciasRepository;

	public CreditosServiceImpl(CreditosRepository creditosRepository, ReferenciasRepository referenciasRepository) {
		this.creditosRepository = creditosRepository;
		this.referenciasRepository = referenciasRepository;
	}

	@Override
	public CreditosDto obtenerById(String id) {

		CreditosDto creditosDto = creditosRepository.buscarById(id);
		if (creditosDto == null)
			throw new CreditosNoEncontradoException("No se encontro el credito", id);

		List<ReferenciasDto> referencias = referenciasRepository.buscarTodo(creditosDto.getIdCredito());
		creditosDto.setReferencias(referencias);

		return creditosDto;
	}

	@Override
	public CreditosDto modificar(String id, CreditosDto creditosDto) {
		
		CreditosDto validate = creditosRepository.buscarById(id);
		if (validate == null)
			throw new CreditosNoEncontradoException("No se encontro el credito", id);
		
		CreditosDto creditosDto2 = creditosRepository.actualizar(id, creditosDto);

		List<ReferenciasDto> referencias = referenciasRepository.buscarTodo(creditosDto2.getIdCredito());
			creditosDto2.setReferencias(referencias);
	
		return creditosDto2;
	}

	@Override
	public CreditosDto guardar(CreditosDto creditosDto) {
		creditosDto.setFecha(new Date().toString());

		CreditosDto credito = creditosRepository.persistir(creditosDto);

		creditosDto.getReferencias()
				.forEach(referencias -> referenciasRepository.persistir(credito.getIdCredito(), referencias));

		List<ReferenciasDto> referencias = referenciasRepository.buscarTodo(credito.getIdCredito());

		credito.setReferencias(referencias);
		
		
			/*
			 * Se genera solicitud de aprobacion del credito
			 */

		return credito;
	}

	@Override
	public void borrar(String id) {
		
		/*
		 * Se eliminan las referencias del credito
		 */
		referenciasRepository.eliminar(id);
		
		creditosRepository.eliminar(id);
				
	}

}