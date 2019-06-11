package com.microservicios.monitoreo.clientes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;


/**
 * <b>ClientesServiceApplication.java</b>
 *
 * @author Jovani AC
 * @version 1.0
 */
@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class ClientesServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(ClientesServiceApplication.class, args);
  }
  
  

}
