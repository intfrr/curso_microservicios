package com.microservicios.clientes.service.cliente;

public class ClienteNoEncontradoException extends RuntimeException {
  private final String id;

  public ClienteNoEncontradoException(String message, String id) {
    super(message);
    this.id = id;
  }

  public static ClienteNoEncontradoException from(String message, String id) {
    return new ClienteNoEncontradoException(message, id);
  }

  public String getId() {
    return id;
  }
}
