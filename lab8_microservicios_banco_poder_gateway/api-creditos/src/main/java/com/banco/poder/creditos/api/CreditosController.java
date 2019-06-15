package com.banco.poder.creditos.api;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.banco.poder.creditos.modelo.CreditosDto;
import com.banco.poder.creditos.modelo.TemplateResponse;
import com.banco.poder.creditos.service.CreditosServiceImpl;

@RestController
@RequestMapping("creditos/v1")
public class CreditosController {

	private static final Logger log = Logger.getLogger(CreditosController.class);

	private CreditosServiceImpl creditosServiceImpl;

	public CreditosController(CreditosServiceImpl creditosServiceImpl) {
		this.creditosServiceImpl = creditosServiceImpl;
	}

	@PostMapping
	@ResponseStatus(CREATED)
	public TemplateResponse crear(@RequestBody CreditosDto creditosDto) {
		log.info(">>> creditos/v1 creando creditos, wuju dinero!!");

		CreditosDto creditos = creditosServiceImpl.guardar(creditosDto);

		TemplateResponse response = new TemplateResponse("El Credito se ha creado exitosamente", "201", creditos);

		return response;
	}

	@GetMapping("/{id}")
	@ResponseStatus(OK)
	public CreditosDto consultarCreditosById(@PathVariable("id") String id) {
		log.info(">>> creditos/v1 consultarCreditosById ");

		return creditosServiceImpl.obtenerById(id);
	}

	@PutMapping("/{id}")
	@ResponseStatus(OK)
	public TemplateResponse modificarCreditos(@PathVariable("id") String id, @RequestBody CreditosDto creditosDto) {
		log.info(">>> creditos/v1 modificarCreditos ");

		CreditosDto creditos = creditosServiceImpl.modificar(id, creditosDto);

		TemplateResponse response = new TemplateResponse("El Credito se ha actualizado exitosamente", "200", creditos);

		return response;

	}

	@DeleteMapping("/{id}")
	@ResponseStatus(OK)
	public TemplateResponse borrarCreditoById(@PathVariable("id") String id) {
		log.info(">>> creditos/v1 borrarCreditoById ");

		creditosServiceImpl.borrar(id);

		TemplateResponse response = new TemplateResponse("La solicitud de credito se ha dado de baja exitosamente",
				"200", "");

		return response;
	}

	@PostMapping("/aprobaciones")
	@ResponseStatus(CREATED)
	public TemplateResponse aprobaciones(@RequestBody CreditosDto creditosDto) {
		log.info(">>> creditos/v1 aprobaciones");

		CreditosDto creditos = creditosServiceImpl.guardar(creditosDto);

		TemplateResponse response = new TemplateResponse("Se realizo la autorizacion del credito", "201", creditos);

		return response;
	}
}
