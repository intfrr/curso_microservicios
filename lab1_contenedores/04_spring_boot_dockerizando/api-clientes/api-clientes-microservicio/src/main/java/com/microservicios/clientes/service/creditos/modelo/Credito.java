package com.microservicios.clientes.service.creditos.modelo;

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
