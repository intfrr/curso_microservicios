package com.microservicios.monitoreo.creditos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
@ComponentScan(basePackages="com.microservicios.monitoreo.creditos")
public class CreditoServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(CreditoServiceApplication.class, args);
  }

}
