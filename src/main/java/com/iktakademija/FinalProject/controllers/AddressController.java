package com.iktakademija.FinalProject.controllers;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.event.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.iktakademija.FinalProject.dtos.AddressDTO;
import com.iktakademija.FinalProject.dtos.NewAddressDTO;
import com.iktakademija.FinalProject.entities.AddressEntity;
import com.iktakademija.FinalProject.entities.UserEntity;
import com.iktakademija.FinalProject.securities.Views;
import com.iktakademija.FinalProject.services.AddressService;
import com.iktakademija.FinalProject.services.LoggingService;
import com.iktakademija.FinalProject.services.LoginService;
import com.iktakademija.FinalProject.utils.ERESTErrorCodes;
import com.iktakademija.FinalProject.utils.RESTError;

/**
 * Address controller. Address controller provide entpoints for manipulation
 * with addresses.<BR>
 */
@RestController
@RequestMapping(path = "/api/v1/address")
public class AddressController {

	@Autowired
	private AddressService addressService;

	@Autowired
	private LoginService loginService;

	@Autowired
	private LoggingService loggingService;

	/**
	 * REST endpoint that returns all addresses from data base. Method always return
	 * {@link HttpStatus.OK} if there is no internal error.<BR>
	 * 
	 * Postman code: <B>ADR10</B>
	 * 
	 * @return set of {@link AddressDTO} from database or empty set if nothing to
	 *         return.
	 */
	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.GET, path = "/admin")
	public ResponseEntity<?> getAllAddresses() {

		// Logging and retriving user.
		UserEntity user = loginService.getUser();
		loggingService.loggAndGetUser(user, Level.INFO);
		loggingService.loggMessage("Method: AddressController.getAllAddresses()", Level.INFO);

		// Get list of all addresses
		List<AddressDTO> retVal = addressService.getDTOList();

		// Log results and make respons
		loggingService.loggOutMessage(HttpStatus.OK.toString(), Level.INFO);
		return new ResponseEntity<List<AddressDTO>>(retVal, HttpStatus.OK);
	}

	/**
	 * REST endpoint that return addresses by id. Method always return
	 * {@link HttpStatus.OK} if there is no internal error.<BR>
	 * 
	 * Postman code: <B>ADR11</B>
	 * 
	 * @return {@link AddressDTO} from database or empty set if nothing to return.
	 */
	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.GET, path = "/admin/{id}")
	public ResponseEntity<?> getAddressesById(@PathVariable(value = "id") Integer addressId) {

		// Logging and retriving user.
		UserEntity user = loginService.getUser();
		loggingService.loggAndGetUser(user, Level.INFO);
		loggingService.loggMessage("Method: AddressController.getAddressesById()", Level.INFO);

		// Check address id
		if (addressId == null) {
			loggingService.loggTwoOutMessage("Invalid address id.", HttpStatus.BAD_REQUEST.toString(), Level.INFO);
			return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.INVALID_PARAMETERS),
					HttpStatus.BAD_REQUEST);
		}

		// Find address in database
		AddressDTO dto = addressService.getAddressDTO(addressId);
		if (dto == null) {
			loggingService.loggTwoOutMessage("Address not found.", HttpStatus.BAD_REQUEST.toString(), Level.INFO);
			return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.NOT_FOUND), HttpStatus.BAD_REQUEST);
		}

		// Log results and make respons
		loggingService.loggOutMessage(HttpStatus.OK.toString(), Level.INFO);
		return new ResponseEntity<AddressDTO>(dto, HttpStatus.OK);
	}

	/**
	 * Add new adress to database. If there is no internal error method return
	 * {@link HttpStatus.OK}.<BR>
	 * <BR>
	 * 
	 * Postman code: <B>ADR01</B>
	 * 
	 * @return Added address if there is no errors.
	 */
	@Secured("ROLE_ADMIN")
	@JsonView(value = Views.Admin.class)
	@RequestMapping(method = RequestMethod.POST, path = "/admin")
	public ResponseEntity<?> addAddress(@Valid @RequestBody NewAddressDTO newAddress) {

		// Logging and retriving user.
		UserEntity user = loginService.getUser();
		loggingService.loggAndGetUser(user, Level.INFO);
		loggingService.loggMessage("Method: AddressController.addAddress()", Level.INFO);

		AddressEntity address = addressService.createAddress(newAddress);
		if (address == null) {
			loggingService.loggTwoOutMessage("Address can not be added. Invalid data provided.",
					HttpStatus.BAD_REQUEST.toString(), Level.INFO);
			return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.INVALID_PARAMETERS),
					HttpStatus.BAD_REQUEST);
		}

		// Log results and make respons
		loggingService.loggOutMessage(HttpStatus.OK.toString(), Level.INFO);
		return new ResponseEntity<AddressDTO>(addressService.createDTO(address), HttpStatus.OK);
	}

	/**
	 * REST endpoint for changing address entity
	 * 
	 * Postman code: <B>ADR12</B>
	 * 
	 * @return changed address entity DTO or error code.
	 */
	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.PUT, path = "/admin/{id}")
	public ResponseEntity<?> setAddresses(@PathVariable(value = "id") Integer addressId,
			@RequestBody NewAddressDTO newAddress) {

		// Logging and retriving user.
		UserEntity user = loginService.getUser();
		loggingService.loggAndGetUser(user, Level.INFO);
		loggingService.loggMessage("Method: AddressController.setAddresses()", Level.INFO);

		// Check id and new address data
		if (addressId == null || newAddress == null) {
			loggingService.loggTwoOutMessage("Invalid address id or new address data.",
					HttpStatus.BAD_REQUEST.toString(), Level.INFO);
			return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.INVALID_PARAMETERS),
					HttpStatus.BAD_REQUEST);
		}

		// Retrive address DTO by id
		AddressDTO dto = addressService.setAddress(addressId, newAddress);
		if (dto == null) {
			loggingService.loggTwoOutMessage("Address not found. Nothing changed.", HttpStatus.BAD_REQUEST.toString(),
					Level.INFO);
			return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.NOT_FOUND), HttpStatus.BAD_REQUEST);
		}

		// Log results and make respons
		loggingService.loggOutMessage(HttpStatus.OK.toString(), Level.INFO);
		return new ResponseEntity<AddressDTO>(dto, HttpStatus.OK);
	}

	/**
	 * REST endpoint for removing address entity.
	 * 
	 * Postman code: <B>ADR13</B>
	 * 
	 * @return removed address entity.
	 */
	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.DELETE, path = "/admin/{id}")
	public ResponseEntity<?> removeAdresses(@PathVariable(value = "id") Integer addressId) {

		// Logging and retriving user.
		UserEntity user = loginService.getUser();
		loggingService.loggAndGetUser(user, Level.INFO);
		loggingService.loggMessage("Method: AddressController.removeAdresses()", Level.INFO);

		if (addressId == null) {
			loggingService.loggTwoOutMessage("Invalid address id.", HttpStatus.BAD_REQUEST.toString(), Level.INFO);
			return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.INVALID_PARAMETERS),
					HttpStatus.BAD_REQUEST);
		}

		AddressDTO dto = addressService.removeAddress(addressId);
		if (dto == null) {
			loggingService.loggTwoOutMessage("Address not found. Nothing changed.", HttpStatus.BAD_REQUEST.toString(),
					Level.INFO);
			return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.NOT_FOUND), HttpStatus.BAD_REQUEST);
		}

		// Log results and make respons
		loggingService.loggOutMessage(HttpStatus.OK.toString(), Level.INFO);
		return new ResponseEntity<AddressDTO>(dto, HttpStatus.OK);
	}

}
