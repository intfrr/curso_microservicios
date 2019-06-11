package com.microservicios.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
public class GatewayApplication {

  public static void main(String[] args) {
    SpringApplication.run(GatewayApplication.class, args);
  }

//  @Bean
//  public io.opentracing.Tracer zipkinTracer(@Value("${zipkin.endpoint:http://localhost:9411}") String zipkinEndpoint, @Value("${spring.application.name}") String serviceName) {
//    OkHttpSender okHttpSender = OkHttpSender.builder()
//      .encoding(Encoding.JSON)
//      .endpoint(zipkinEndpoint + "/api/v1/spans")
//      .build();
//    AsyncReporter<Span> reporter = AsyncReporter.builder(okHttpSender).build();
//    Tracing braveTracer = Tracing.newBuilder()
//      .localServiceName(serviceName)
//      .reporter(reporter)
//      .traceId128Bit(true)
//      .sampler(Sampler.ALWAYS_SAMPLE)
//      .build();
//    return BraveTracer.create(braveTracer);
//  }
}
