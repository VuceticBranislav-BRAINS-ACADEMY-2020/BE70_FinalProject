package com.iktakademija.FinalProject.repositories;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import com.iktakademija.FinalProject.entities.AddressEntity;

public interface AddressRepository extends CrudRepository<AddressEntity, Integer> {

	@Override
	Set<AddressEntity> findAll();

}