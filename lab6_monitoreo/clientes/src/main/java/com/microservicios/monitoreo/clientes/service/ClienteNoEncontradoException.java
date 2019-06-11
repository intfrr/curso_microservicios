package com.microservicios.monitoreo.clientes.service;

/**
 * <b>ClienteNoEncontradoException.java</b>
 *
 * @author Jovani AC
 * @version 1.0
 */
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
