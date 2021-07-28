package com.iktakademija.FinalProject.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.iktakademija.FinalProject.entities.PersonEntity;

public interface PersonRepository extends CrudRepository<PersonEntity, Integer> {

	Optional<PersonEntity> findById(@Param("id") Integer id);
	
	@Query(value = "FROM PersonEntity AS p WHERE p.status<>'DELETED'")
	List<PersonEntity> findAllUndeleted();
}
