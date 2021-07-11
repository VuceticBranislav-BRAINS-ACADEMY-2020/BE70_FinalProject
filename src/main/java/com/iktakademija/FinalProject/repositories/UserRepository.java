package com.iktakademija.FinalProject.repositories;

import org.springframework.data.repository.CrudRepository;

import com.iktakademija.FinalProject.entities.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Integer> {

	public UserEntity findByUsername(String username);

}
