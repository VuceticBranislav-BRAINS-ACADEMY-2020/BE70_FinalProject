package com.iktakademija.FinalProject.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iktakademija.FinalProject.entities.GroupEntity;
import com.iktakademija.FinalProject.entities.JoinTableSubjectClass;
import com.iktakademija.FinalProject.entities.JoinTableSubjectTeacher;
import com.iktakademija.FinalProject.entities.TeacherEntity;
import com.iktakademija.FinalProject.entities.dtos.JoinTableSubjectTeacherDTO;
import com.iktakademija.FinalProject.repositories.GroupRepository;
import com.iktakademija.FinalProject.repositories.JoinTableSubjectClassRepository;
import com.iktakademija.FinalProject.repositories.JoinTableSubjectTeacherRepository;
import com.iktakademija.FinalProject.repositories.TeacherRepository;

@Service
public class JoinTableSubjectTeacherServiceImpl implements JoinTableSubjectTeacherService {

	@Autowired
	private TeacherRepository teacherRepository;

	@Autowired
	private JoinTableSubjectTeacherRepository joinTableSubjectTeacherRepository;

	@Autowired
	private GroupRepository groupRepository;

	@Autowired
	private JoinTableSubjectClassRepository joinTableSubjectClassRepository;

	@Override
	public JoinTableSubjectTeacher createEntity(JoinTableSubjectTeacherDTO source) {

		if (source == null)
			return null;

		Optional<GroupEntity> op1 = groupRepository.findById(source.getGroup());
		if (op1.isPresent() == false)
			return null;
		GroupEntity group = op1.get();

		Optional<TeacherEntity> op2 = teacherRepository.findById(source.getTeacher());
		if (op2.isPresent() == false)
			return null;
		TeacherEntity teacher = op2.get();

		Optional<JoinTableSubjectClass> op3 = joinTableSubjectClassRepository.findById(source.getSub_cls());
		if (op3.isPresent() == false)
			return null;
		JoinTableSubjectClass join = op3.get();

		JoinTableSubjectTeacher entity = new JoinTableSubjectTeacher();
		entity.setGroup(group);
		entity.setTeachers(teacher);
		entity.setSub_cls(join);

		entity = joinTableSubjectTeacherRepository.save(entity);
		return entity;
	}

	@Override
	public JoinTableSubjectTeacherDTO createDTO(JoinTableSubjectTeacher source) {

		if (source == null)
			return null;
		JoinTableSubjectTeacherDTO retVal = new JoinTableSubjectTeacherDTO();
		retVal.setGroup(source.getGroup().getId());
		retVal.setTeacher(source.getTeachers().getId());
		retVal.setSub_cls(source.getSub_cls().getId());
		retVal.setId(source.getId());

		return retVal;
	}

	@Override
	public JoinTableSubjectTeacherDTO getEntityDTO(Integer id) {
		Optional<JoinTableSubjectTeacher> op = joinTableSubjectTeacherRepository.findById(id);
		if (op.isPresent() == false)
			return null;
		return this.createDTO(op.get());
	}

	@Override
	public JoinTableSubjectTeacherDTO setEntity(Integer id, JoinTableSubjectTeacherDTO newEntity) {

		Optional<JoinTableSubjectTeacher> op = joinTableSubjectTeacherRepository.findById(id);
		if (op.isPresent() == false)
			return null;
		JoinTableSubjectTeacher entity = op.get();

		Optional<GroupEntity> op1 = groupRepository.findById(newEntity.getGroup());
		if (op1.isPresent() == false)
			return null;
		GroupEntity group = op1.get();

		Optional<TeacherEntity> op2 = teacherRepository.findById(newEntity.getTeacher());
		if (op2.isPresent() == false)
			return null;
		TeacherEntity teacher = op2.get();

		Optional<JoinTableSubjectClass> op3 = joinTableSubjectClassRepository.findById(newEntity.getSub_cls());
		if (op3.isPresent() == false)
			return null;
		JoinTableSubjectClass join = op3.get();

		// Change entity
		if (newEntity.getGroup() != null)
			entity.setGroup(group);
		if (newEntity.getTeacher() != null)
			entity.setTeachers(teacher);
		if (newEntity.getSub_cls() != null)
			entity.setSub_cls(join);

		// Save and return DTO
		entity = joinTableSubjectTeacherRepository.save(entity);
		return this.createDTO(entity);
	}

	@Override
	public JoinTableSubjectTeacherDTO removeEntity(Integer id) {

		Optional<JoinTableSubjectTeacher> op = joinTableSubjectTeacherRepository.findById(id);
		if (op.isPresent() == false)
			return null;
		JoinTableSubjectTeacher entity = op.get();

		joinTableSubjectTeacherRepository.delete(entity);
		return this.createDTO(entity);
	}

	@Override
	public List<JoinTableSubjectTeacherDTO> getDTOList() {
		List<JoinTableSubjectTeacherDTO> list = new ArrayList<>();
		for (JoinTableSubjectTeacher entity : joinTableSubjectTeacherRepository.findAll())
			list.add(this.createDTO(entity));
		return list;
	}

}
