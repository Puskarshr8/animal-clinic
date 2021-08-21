package com.spring5app.animalclinic.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring5app.animalclinic.model.Owner;
import com.spring5app.animalclinic.rest.api.controllers.OwnersApi;
import com.spring5app.animalclinic.rest.api.dto.model.ModelApiResponse;
import com.spring5app.animalclinic.rest.api.dto.model.OwnerDTO;
import com.spring5app.animalclinic.rest.mapper.Mapper;
import com.spring5app.animalclinic.rest.mapper.OwnerMapper;
import com.spring5app.animalclinic.rest.validators.ValidationErrors;
import com.spring5app.animalclinic.rest.validators.ValidationFactory;
import com.spring5app.animalclinic.services.OwnerService;

import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-01-19T22:53:48.929Z")

@Controller
public class OwnersApiController implements OwnersApi {

	private static final Logger log = LoggerFactory.getLogger(OwnersApiController.class);

	private final OwnerService ownerService;

	@org.springframework.beans.factory.annotation.Autowired
	public OwnersApiController(ObjectMapper objectMapper, HttpServletRequest request, OwnerService ownerService) {
		this.ownerService = ownerService;
	}

	@Override
	public ResponseEntity<ModelApiResponse> createOwner(
			@ApiParam(value = "Created owner object", required = true) @Valid @RequestBody OwnerDTO body) {

		final ValidationErrors errors = new ValidationErrors();
		final ModelApiResponse response = new ModelApiResponse();

		if (!ValidationFactory.getValidator(OwnerDTO.class).validate(body, errors))
			try {
				Mapper<Owner, OwnerDTO> mapper = new OwnerMapper();
				ownerService.save(mapper.mapTo(body));
				response.setCode(1);
				response.setMessage("success");
				return new ResponseEntity<ModelApiResponse>(response, HttpStatus.ACCEPTED);
			} catch (Exception e) {
				log.error("Couldn't save the sent request", e);
				return new ResponseEntity<ModelApiResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
			}

		log.error(errors.getErrors().stream().collect(Collectors.joining(" ")));

		response.setCode(1);
		response.setMessage(errors.getErrors().stream().collect(Collectors.joining(" ")));
		return new ResponseEntity<ModelApiResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<Void> deleteUser(
			@ApiParam(value = "The name that needs to be deleted", required = true) @PathVariable("Id") String id) {

		final ValidationErrors errors = new ValidationErrors();

		if (!ValidationFactory.getValidator(String.class).validate(id, errors)) {
			try {
				ownerService.deleteById(Long.valueOf(id));
				new ResponseEntity<Void>(HttpStatus.OK);
			} catch (Exception e) {
				log.error("Couldn't serialize response for content type application/xml", e);
				return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		log.error(errors.getErrors().stream().collect(Collectors.joining(" ")));

		return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<OwnerDTO> getOwnerById(
			@ApiParam(value = "The name that needs to be fetched. Use user1 for testing.", required = true) @PathVariable("Id") String id) {

		final ValidationErrors errors = new ValidationErrors();

		if (!ValidationFactory.getValidator(String.class).validate(id, errors)) {
			try {
				Mapper<Owner, OwnerDTO> mapper = new OwnerMapper();
				return new ResponseEntity<OwnerDTO>(mapper.mapFrom(ownerService.findById(Long.valueOf(id))),
						HttpStatus.OK);
			} catch (Exception e) {
				log.error("Couldn't serialize response for content type application/json", e);
				return new ResponseEntity<OwnerDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		log.error(errors.getErrors().stream().collect(Collectors.joining(" ")));

		return new ResponseEntity<OwnerDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<List<OwnerDTO>> getOwners() {
		try {
			Mapper<Owner, OwnerDTO> mapper = new OwnerMapper();
			return new ResponseEntity<List<OwnerDTO>>(
					ownerService.findAll().stream().map(mapper::mapFrom).collect(Collectors.toList()), HttpStatus.OK);
		} catch (Exception e) {
			log.error("Couldn't serialize response for content type application/json", e);
			return new ResponseEntity<List<OwnerDTO>>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
