package com.iktakademija.FinalProject.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.iktakademija.FinalProject.entities.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Integer> {

	Optional<UserEntity> findByUsername(String username);
	
}
