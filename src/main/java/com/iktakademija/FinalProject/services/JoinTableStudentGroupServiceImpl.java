package com.iktakademija.FinalProject.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iktakademija.FinalProject.entities.GroupEntity;
import com.iktakademija.FinalProject.entities.JoinTableStudentGroup;
import com.iktakademija.FinalProject.entities.StudentEntity;
import com.iktakademija.FinalProject.entities.dtos.JoinTableStudentGroupDTO;
import com.iktakademija.FinalProject.repositories.GroupRepository;
import com.iktakademija.FinalProject.repositories.JoinTableStudentGroupRepository;
import com.iktakademija.FinalProject.repositories.StudentRepository;

@Service
public class JoinTableStudentGroupServiceImpl implements JoinTableStudentGroupService {

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private JoinTableStudentGroupRepository joinTableStudentGroupRepository;

	@Autowired
	private GroupRepository groupRepository;

	@Override
	public JoinTableStudentGroup createEntity(JoinTableStudentGroupDTO source) {

		if (source == null)
			return null;

		Optional<GroupEntity> op1 = groupRepository.findById(source.getGroup());
		if (op1.isPresent() == false)
			return null;
		GroupEntity group = op1.get();

		Optional<StudentEntity> op2 = studentRepository.findById(source.getStudent());
		if (op2.isPresent() == false)
			return null;
		StudentEntity student = op2.get();

		JoinTableStudentGroup entity = new JoinTableStudentGroup();
		entity.setGroup(group);
		entity.setStudent(student);

		entity = joinTableStudentGroupRepository.save(entity);
		return entity;
	}

	@Override
	public JoinTableStudentGroupDTO createDTO(JoinTableStudentGroup source) {

		if (source == null)
			return null;
		JoinTableStudentGroupDTO retVal = new JoinTableStudentGroupDTO();
		retVal.setGroup(source.getGroup().getId());
		retVal.setStudent(source.getStudent().getId());
		retVal.setId(source.getId());

		return retVal;
	}

	@Override
	public JoinTableStudentGroupDTO getEntityDTO(Integer id) {
		Optional<JoinTableStudentGroup> op = joinTableStudentGroupRepository.findById(id);
		if (op.isPresent() == false)
			return null;
		return this.createDTO(op.get());
	}

	@Override
	public JoinTableStudentGroupDTO setEntity(Integer id, JoinTableStudentGroupDTO newEntity) {

		Optional<JoinTableStudentGroup> op = joinTableStudentGroupRepository.findById(id);
		if (op.isPresent() == false)
			return null;
		JoinTableStudentGroup entity = op.get();

		Optional<GroupEntity> op1 = groupRepository.findById(newEntity.getGroup());
		if (op1.isPresent() == false)
			return null;
		GroupEntity parent = op1.get();

		Optional<StudentEntity> op2 = studentRepository.findById(newEntity.getStudent());
		if (op2.isPresent() == false)
			return null;
		StudentEntity student = op2.get();

		// Change entity
		if (newEntity.getGroup() != null)
			entity.setGroup(parent);
		if (newEntity.getStudent() != null)
			entity.setStudent(student);

		// Save and return DTO
		entity = joinTableStudentGroupRepository.save(entity);
		return this.createDTO(entity);
	}

	@Override
	public JoinTableStudentGroupDTO removeEntity(Integer id) {

		Optional<JoinTableStudentGroup> op = joinTableStudentGroupRepository.findById(id);
		if (op.isPresent() == false)
			return null;
		JoinTableStudentGroup entity = op.get();

		joinTableStudentGroupRepository.delete(entity);
		return this.createDTO(entity);
	}

	@Override
	public List<JoinTableStudentGroupDTO> getDTOList() {
		List<JoinTableStudentGroupDTO> list = new ArrayList<>();
		for (JoinTableStudentGroup entity : joinTableStudentGroupRepository.findAll())
			list.add(this.createDTO(entity));
		return list;
	}

}
