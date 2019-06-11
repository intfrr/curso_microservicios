package com.microservicios.monitoreo.clientes.remote.modelo;

/**
 * <b>Credito.java</b>
 *
 * @author Jovani AC
 * @version 1.0
 */

public class ResumenCredito {

  private String folioCredito;
  private Double montoDeuda;
  private String folioCliente;

  public String getFolioCredito() {
    return folioCredito;
  }

  public void setFolioCredito(String folioCredito) {
    this.folioCredito = folioCredito;
  }

  public Double getMontoDeuda() {
    return montoDeuda;
  }

  public void setMontoDeuda(Double montoDeuda) {
    this.montoDeuda = montoDeuda;
  }

  public String getFolioCliente() {
    return folioCliente;
  }

  public void setFolioCliente(String folioCliente) {
    this.folioCliente = folioCliente;
  }
}
