package com.iktakademija.FinalProject.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.iktakademija.FinalProject.entities.UserEntity;
import com.iktakademija.FinalProject.entities.enums.EStatus;


public interface tests extends CrudRepository<UserEntity, Integer> {

	@Query(value = "FROM user as r where r.status = :id")
	public List<UserEntity> findByUsername2(@Param("id")EStatus asdasd);

}
