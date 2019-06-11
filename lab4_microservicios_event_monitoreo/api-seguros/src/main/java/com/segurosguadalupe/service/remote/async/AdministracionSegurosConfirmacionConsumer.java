package com.segurosguadalupe.service.remote.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.segurosguadalupe.RabbitConfigConsumer;
import com.segurosguadalupe.modelo.remote.ConfirmacionDto;
import com.segurosguadalupe.service.SegurosService;
@Component
public class AdministracionSegurosConfirmacionConsumer {

	public static final Logger logger = LoggerFactory.getLogger(AdministracionSegurosConfirmacionConsumer.class);
	private SegurosService serSegurosService;
	private Gson json = new Gson();

	public AdministracionSegurosConfirmacionConsumer(
			SegurosService serSegurosService) {
		this.serSegurosService = serSegurosService;
	}

	@RabbitListener(queues = RabbitConfigConsumer.QUEUE_ADMINISTRACION_CONFIRMACION)
	public void listenConfirmaciones(Message message) {
		String body = new String(message.getBody());
		logger.info("Event confirmacion del adm de autorizaciones" + body);

		ConfirmacionDto confirmacionDto = json.fromJson(body, ConfirmacionDto.class);

		serSegurosService.modificaSeguro(confirmacionDto);
	}
}
