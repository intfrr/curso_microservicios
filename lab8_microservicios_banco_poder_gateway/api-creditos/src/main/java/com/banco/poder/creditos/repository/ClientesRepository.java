package com.banco.poder.creditos.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.banco.poder.creditos.entity.Clientes;

@Repository
public interface ClientesRepository  {
	Optional<Clientes> findById(String id);
}
