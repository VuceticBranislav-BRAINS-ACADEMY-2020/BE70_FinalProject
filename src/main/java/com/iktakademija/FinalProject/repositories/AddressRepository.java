package com.iktakademija.FinalProject.repositories;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import com.iktakademija.FinalProject.entities.AddressEntity;
import com.iktakademija.FinalProject.entities.PersonEntity;

public interface AddressRepository extends CrudRepository<AddressEntity, Integer> {
	
	@Override
	public Optional<AddressEntity> findById(Integer id);
	 
	@Override
	Set<AddressEntity> findAll();

}
