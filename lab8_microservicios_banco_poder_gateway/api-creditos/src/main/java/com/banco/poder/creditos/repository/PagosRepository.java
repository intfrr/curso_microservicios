package com.banco.poder.creditos.repository;

import java.util.List;

import com.banco.poder.creditos.modelo.PagosDto;

public interface PagosRepository {

	List<PagosDto> obtenerTodo(String id);

	PagosDto persistir(PagosDto pagosDto);
}
