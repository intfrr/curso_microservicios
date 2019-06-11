package com.segurosguadalupe.administracion.service.remote.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.segurosguadalupe.administracion.RabbitConfigConsumer;
import com.segurosguadalupe.administracion.modelo.SegurosDto;
import com.segurosguadalupe.administracion.service.AdministracionServiceImpl;
@Component
public class SegurosConsumer {

	public static final Logger logger = LoggerFactory.getLogger(SegurosConsumer.class);
	private Gson json = new Gson();
	private AdministracionServiceImpl administracionServiceImpl;

	public SegurosConsumer(AdministracionServiceImpl administracionServiceImpl) {
		this.administracionServiceImpl = administracionServiceImpl;
	}

	@RabbitListener(queues = RabbitConfigConsumer.QUEUE_ADMINISTRACION_AUTORIZACION)
	public void listenConfirmaciones(Message message) {
		String body = new String(message.getBody());
		logger.info("Event autorizaciones de seguros" + body);

		SegurosDto segurosDto = json.fromJson(body, SegurosDto.class);

			administracionServiceImpl.guardarAutorizaciones(segurosDto);
	}
}
