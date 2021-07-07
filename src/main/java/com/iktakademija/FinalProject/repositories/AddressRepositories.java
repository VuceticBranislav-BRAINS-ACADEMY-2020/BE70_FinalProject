package com.iktakademija.FinalProject.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.iktakademija.FinalProject.entities.AddressEntity;

public interface AddressRepositories extends CrudRepository<AddressEntity, Integer> {

	@Override
	List<AddressEntity> findAll();

}
