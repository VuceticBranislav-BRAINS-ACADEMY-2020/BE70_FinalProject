package com.iktakademija.FinalProject.services;

import com.iktakademija.FinalProject.entities.AddressEntity;
import com.iktakademija.FinalProject.entities.dtos.AddressDTO;

public interface AddressService {
	
	public AddressDTO createDTO(AddressEntity source);
	
}
