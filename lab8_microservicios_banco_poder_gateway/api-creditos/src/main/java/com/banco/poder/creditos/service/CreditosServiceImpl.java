package com.banco.poder.creditos.service;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.banco.poder.creditos.modelo.CreditosDto;
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
		return creditosRepository.buscarById(id);
	}

	@Override
	public CreditosDto modificar(String id, CreditosDto creditosDto) {
		return creditosRepository.actualizar(id, creditosDto);
	}

	@Transactional
	@Override
	public CreditosDto guardar(CreditosDto creditosDto) {
		creditosDto.setFecha(new Date().toString());

		CreditosDto credito = creditosRepository.persistir(creditosDto);

		creditosDto.getReferencias()
				.forEach(referencias -> referenciasRepository.persistir(credito.getIdCredito(), referencias));

		return credito;
	}

	@Override
	public void borrar(String id) {
		creditosRepository.eliminar(id);
	}

}