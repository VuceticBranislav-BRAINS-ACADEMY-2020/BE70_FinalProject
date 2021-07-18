package com.iktakademija.FinalProject.services;

import org.springframework.stereotype.Service;

import com.iktakademija.FinalProject.entities.AddressEntity;
import com.iktakademija.FinalProject.entities.dtos.AddressDTO;

@Service
public class AddressServiceImpl implements AddressService {
	
	@Override
	public AddressDTO createDTO(AddressEntity source) {
		
		AddressDTO retVal = new AddressDTO();		
		retVal.setCity( source.getCity());
		retVal.setStreet( source.getStreet());
		retVal.setNumber(source.getNumber());
		retVal.setApartment(source.getApartment());			
		return retVal;
		
	}
	
}
