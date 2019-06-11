package com.microservicios.monitoreo.clientes.remote.modelo;

/**
 * <b>Credito.java</b>
 *
 * @author Jovani AC
 * @version 1.0
 */

public class Credito {

  private Double montoCredito;
  private String folioCliente;

  public Double getMontoCredito() {
    return montoCredito;
  }

  public void setMontoCredito(Double montoCredito) {
    this.montoCredito = montoCredito;
  }

  public String getFolioCliente() {
    return folioCliente;
  }

  public void setFolioCliente(String folioCliente) {
    this.folioCliente = folioCliente;
  }
}
