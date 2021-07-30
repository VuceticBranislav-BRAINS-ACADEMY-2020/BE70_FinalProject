package com.iktakademija.FinalProject.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.iktakademija.FinalProject.entities.AdminEntity;

public interface AdminRepository extends CrudRepository<AdminEntity, Integer> {

	@Override
	public List<AdminEntity> findAll();

	Optional<AdminEntity> findById(Integer id);

	@Query(value = "FROM AdminEntity AS a WHERE a.status<>'DELETED'")
	List<AdminEntity> findAllUndeleted();

	@Query(value = "FROM AdminEntity AS a WHERE a.username = :username AND a.status <> 'DELETED'")
	Optional<AdminEntity> findByUsername(@Param("username") String username);
}
