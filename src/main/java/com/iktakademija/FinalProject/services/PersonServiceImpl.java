package com.iktakademija.FinalProject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iktakademija.FinalProject.entities.PersonEntity;
import com.iktakademija.FinalProject.entities.dtos.PersonDTO;

@Service
public class PersonServiceImpl implements PersonService {
	
	@Autowired
	private AddressService addressService;
	
	@Override
	public PersonDTO createDTO(PersonEntity source) {
		
		PersonDTO retVal = new PersonDTO();		
		retVal.setId( source.getId());
		retVal.setFirstname( source.getFirstname());
		retVal.setLastname( source.getLastname());
		retVal.setJmbg( source.getJmbg());
		retVal.setMphone( source.getMphone());
		retVal.setBirthdate( source.getBirthdate());
		retVal.setAddress( addressService.createDTO(source.getAddress()));
		retVal.setVersion( source.getVersion());
		retVal.setStatus( source.getStatus());
		return retVal;
	}
}
