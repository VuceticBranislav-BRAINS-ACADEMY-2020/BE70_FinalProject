package com.iktakademija.FinalProject.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.iktakademija.FinalProject.entities.GroupEntity;
import com.iktakademija.FinalProject.entities.TeacherEntity;

public interface GroupRepository extends CrudRepository<GroupEntity, Integer> {

	Optional<GroupEntity> findById(Integer id);

	@Query(value = "FROM GroupEntity AS t WHERE t.status <> 'DELETED'")
	List<GroupEntity> findAllUndeleted();

	List<GroupEntity> findAllById(Integer id);

	List<GroupEntity> findAll();

	Optional<GroupEntity> findByHomeClassMaster(TeacherEntity homeClassMaster);
}
