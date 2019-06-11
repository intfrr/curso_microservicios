package com.segurosguadalupe.service.remote.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import com.segurosguadalupe.RabbitConfigConsumer;
@Component
public class AdministracionSegurosProducer {

	private RabbitTemplate rabbitTemplate;

	public static final Logger logger = LoggerFactory.getLogger(AdministracionSegurosProducer.class);

	public AdministracionSegurosProducer(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}

	public void sendMessage(String contractEvent) {
		rabbitTemplate.convertAndSend(RabbitConfigConsumer.QUEUE_ADMINISTRACION_AUTORIZACION, contractEvent);
		logger.info(">>>Envio de autorizacion correctamente...");
	}
}
