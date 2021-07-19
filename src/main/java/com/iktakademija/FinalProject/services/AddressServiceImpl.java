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
		address.setStatus(EStatus.ACTIVE);
		
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

	@Override
	public List<AddressDTO> getDTOList() {
		List<AddressDTO> list = new ArrayList<>();
		for (AddressEntity parent : addressRepository.findAllUndeleted()) 
			list.add(this.createDTO(parent));		
		return list;
	}

	@Override
	public AddressDTO getAddressDTO(Integer addressId) {
		Optional<AddressEntity> op = addressRepository.findById(addressId);
		if (op.isPresent() == false) return null;
		return this.createDTO(op.get());
	}	
	
	@Override
	public AddressDTO setAddress(Integer addressId, NewAddressDTO newAddress) {			
		Optional<AddressEntity> op = addressRepository.findById(addressId);
		if (op.isPresent() == false) return null;
		AddressEntity address = op.get();			
		if (newAddress.getCity() != null) address.setCity(newAddress.getCity());
		if (newAddress.getStreet() != null) address.setStreet(newAddress.getStreet());
		if (newAddress.getNumber() != null) address.setNumber(newAddress.getNumber());
		if (newAddress.getApartment() != null) address.setApartment(newAddress.getApartment());	
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
