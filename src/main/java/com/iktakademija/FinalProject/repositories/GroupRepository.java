package com.iktakademija.FinalProject.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.iktakademija.FinalProject.entities.GroupEntity;

public interface GroupRepository extends CrudRepository<GroupEntity, Integer> {

	@Override
	Optional<GroupEntity> findById(@Param("id") Integer id);

	@Query(value = "FROM GroupEntity AS t WHERE t.status <> 'DELETED'")
	List<GroupEntity> findAllUndeleted();
	
}
