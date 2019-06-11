package com.segurosguadalupe.administracion.service.remote.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import com.segurosguadalupe.administracion.RabbitConfigConsumer;
@Component
public class SegurosProducer {

	private RabbitTemplate rabbitTemplate;

	public static final Logger logger = LoggerFactory.getLogger(SegurosProducer.class);

	public SegurosProducer(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}

	public void sendMessage(String contractEvent) {
		rabbitTemplate.convertAndSend(RabbitConfigConsumer.QUEUE_ADMINISTRACION_CONFIRMACION, contractEvent);
		logger.info(">>>Envio de confirmacion a seguros...");
	}
}
