package com.iktakademija.FinalProject.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iktakademija.FinalProject.entities.AddressEntity;
import com.iktakademija.FinalProject.entities.dtos.AddressDTO;
import com.iktakademija.FinalProject.entities.dtos.NewAddressDTO;
import com.iktakademija.FinalProject.entities.enums.EStatus;
import com.iktakademija.FinalProject.repositories.AddressRepository;

@Service
public class AddressServiceImpl implements AddressService {
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Override
	public AddressEntity createAddress(NewAddressDTO source) {		
		if (source == null) return null;	
		
		AddressEntity address = new AddressEntity();
		address.setCity(source.getCity());
		address.setStreet(source.getStreet());
		address.setNumber(source.getNumber());
		address.setApartment(source.getApartment());
		address.setStatus(source.getStatus());
		
		// Exit if already exists
		if (this.isAlreadyPresent(address)) return null;	
		
		address = addressRepository.save(address);		
		return address;
	}
	
	@Override
	public AddressDTO createDTO(AddressEntity source) {		
		AddressDTO retVal = new AddressDTO();
		if (source == null) return retVal;
		retVal.setId( source.getId());
		retVal.setCity( source.getCity());
		retVal.setStreet( source.getStreet());
		retVal.setNumber(source.getNumber());
		retVal.setApartment(source.getApartment());	
		retVal.setVersion( source.getVersion());
		retVal.setStatus( source.getStatus());
		return retVal;		
	}

	/**
	 * Return list of DTOs from all adresses in base
	 */
	@Override
	public List<AddressDTO> getDTOList() {
		List<AddressDTO> list = new ArrayList<>();
		for (AddressEntity address : addressRepository.findAll())
			list.add(this.createDTO(address));
		return list;
	}

	/**
	 * Get address DTO from address id.
	 * Returns null if no such address in database.
	 */
	@Override
	public AddressDTO getAddressDTO(Integer addressId) {
		Optional<AddressEntity> op = addressRepository.findById(addressId);
		if (op.isPresent() == false)
			return null;
		return this.createDTO(op.get());
	}	
	
	@Override
	public AddressDTO setAddress(Integer addressId, NewAddressDTO newAddress) {			
		
		// Find address by id
		Optional<AddressEntity> op = addressRepository.findById(addressId);
		if (op.isPresent() == false) return null;
		AddressEntity address = op.get();	
		
		// Change address
		if (newAddress.getCity() != null) address.setCity(newAddress.getCity());
		if (newAddress.getStreet() != null) address.setStreet(newAddress.getStreet());
		if (newAddress.getNumber() != null) address.setNumber(newAddress.getNumber());
		if (newAddress.getApartment() != null) address.setApartment(newAddress.getApartment());	
		
		// Save and return DTO
		address = addressRepository.save(address);			
		return this.createDTO(address);		
	}
	
	@Override
	public AddressDTO removeAddress(Integer addressId) {			
		Optional<AddressEntity> op = addressRepository.findById(addressId);
		if (op.isPresent() == false) return null;
		AddressEntity address = op.get();		
		address.setStatus(EStatus.DELETED);			
		address = addressRepository.save(address);
		return this.createDTO(address);		
	}
	
	@Override
	public Boolean isAlreadyPresent(AddressEntity address) {
		if (address.getApartment() == null) {
			return addressRepository.isAddressExists(address.getCity(), address.getStreet(), address.getNumber());
		}
		else {
			return addressRepository.isAddressExists(address.getCity(), address.getStreet(), address.getNumber(), address.getApartment());
		}
	}
	
}
