package com.iktakademija.FinalProject.services;

import java.util.List;

import com.iktakademija.FinalProject.entities.AddressEntity;
import com.iktakademija.FinalProject.entities.dtos.AddressDTO;
import com.iktakademija.FinalProject.entities.dtos.NewAddressDTO;

public interface AddressService {
	
	List<AddressDTO> getDTOList();
	AddressDTO createDTO(AddressEntity source);
	AddressDTO getAddressDTO(Integer addressId);
	AddressEntity createAddress(NewAddressDTO source);
	Boolean isAlreadyPresent(AddressEntity address);
	AddressDTO setAddress(Integer addressId, NewAddressDTO newAddress);
	AddressDTO removeAddress(Integer addressId);
}
