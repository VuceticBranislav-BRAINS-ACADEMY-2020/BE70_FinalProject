package com.iktakademija.FinalProject.controllers;

import java.util.List;

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
import com.iktakademija.FinalProject.controllers.utils.RESTError;
import com.iktakademija.FinalProject.controllers.utils.enums.ERESTErrorCodes;
import com.iktakademija.FinalProject.entities.AddressEntity;
import com.iktakademija.FinalProject.entities.dtos.AddressDTO;
import com.iktakademija.FinalProject.entities.dtos.NewAddressDTO;
import com.iktakademija.FinalProject.securities.Views;
import com.iktakademija.FinalProject.services.AddressService;

@RestController
@RequestMapping(path = "/api/v1/address")
public class AddressController {
	
	@Autowired
	private AddressService addressService;
	
	/**
	 * REST endpoint that returns all addresses from data base. 
	 * Method always return {@link HttpStatus.OK} if there is no internal error.<BR><BR>
	 * REST method: GET, path: ""<BR>
	 * Error status messages: none<BR>
	 * Postman identification tag: <B>ADR10</B>
	 * @return set of {@link AddressEntity} from database or empty set if nothing to return.
	 */
	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.GET, path = "/admin")
	public ResponseEntity<?> getAllAddresses() {
		return new ResponseEntity<List<AddressDTO>>(addressService.getDTOList(), HttpStatus.OK);
	}
	
	// ADR11
	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.GET, path = "/admin/{id}")
	public ResponseEntity<?> getAddressesById(@PathVariable(value = "id") Integer addressId) {
		if (addressId == null) return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.INVALID_PARAMETERS), HttpStatus.NOT_ACCEPTABLE);		
		AddressDTO dto = addressService.getAddressDTO(addressId);
		if (dto == null) return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.NOT_FOUND), HttpStatus.NOT_ACCEPTABLE);		
		return new ResponseEntity<AddressDTO>(dto, HttpStatus.OK);	
	}
	
	// ADR12
	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.PUT, path = "/admin/{id}")
	public ResponseEntity<?> setAddresses(@PathVariable(value = "id") Integer addressId, @RequestBody NewAddressDTO newAddress) {
		if (addressId == null || newAddress == null) return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.INVALID_PARAMETERS), HttpStatus.NOT_ACCEPTABLE);			
		AddressDTO dto = addressService.setAddress(addressId, newAddress);
		if (dto == null) return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.NOT_FOUND), HttpStatus.NOT_ACCEPTABLE);
		return new ResponseEntity<AddressDTO>(dto, HttpStatus.OK);	
	}
	
	// ADR13
	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.DELETE, path = "/admin/{id}")
	public ResponseEntity<?> removeAdresses(@PathVariable(value = "id") Integer addressId) {
		if (addressId == null) return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.INVALID_PARAMETERS), HttpStatus.NOT_ACCEPTABLE);			
		AddressDTO dto = addressService.removeAddress(addressId);
		if (dto == null) return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.NOT_FOUND), HttpStatus.NOT_ACCEPTABLE);
		return new ResponseEntity<AddressDTO>(dto, HttpStatus.OK);	
	}

	/************************************************************
	 * Admin
	 ************************************************************/
	
	/**
	 * Add new adress to database.
	 * If there is no internal error method return {@link HttpStatus.OK}.<BR><BR>
	 * 
	 * REST method: <B>POST</B><BR>
	 * Path inside controller: <B>"/admin"</B><BR>
	 * Allowed for <B>Admin</B><BR>
	 * Error status messages: <B>none</B><BR>
	 * Postman identification tag: <B>ADR01</B>
	 * @return Added adress if there is no errors.
	 * @see AdminController
	 */
	@Secured("ROLE_ADMIN")
	@JsonView(value = Views.Admin.class)
	@RequestMapping(method = RequestMethod.POST, path = "/admin")
	public ResponseEntity<?> addAddress(@RequestBody NewAddressDTO newAddress) {	
			
		AddressEntity address = addressService.createAddress(newAddress);	
		if (address == null ) return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.INVALID_PARAMETERS), HttpStatus.NOT_ACCEPTABLE);
		return new ResponseEntity<AddressDTO>(addressService.createDTO(address), HttpStatus.OK);
	}
	
}
