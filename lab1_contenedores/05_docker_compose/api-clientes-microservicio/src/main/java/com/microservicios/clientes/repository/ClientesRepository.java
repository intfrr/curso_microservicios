package com.microservicios.clientes.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.microservicios.clientes.entity.Clientes;

public interface ClientesRepository extends JpaRepository<Clientes, String> {

	Optional<Clientes> findById(@Param("id") String id);
}
