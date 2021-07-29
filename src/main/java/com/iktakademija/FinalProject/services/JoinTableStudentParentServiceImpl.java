package com.iktakademija.FinalProject.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iktakademija.FinalProject.entities.JoinTableStudentParent;
import com.iktakademija.FinalProject.entities.ParentEntity;
import com.iktakademija.FinalProject.entities.StudentEntity;
import com.iktakademija.FinalProject.entities.dtos.JoinTableStudentParentDTO;
import com.iktakademija.FinalProject.repositories.JoinTableStudentParentRepository;
import com.iktakademija.FinalProject.repositories.ParentRepository;
import com.iktakademija.FinalProject.repositories.StudentRepository;

@Service
public class JoinTableStudentParentServiceImpl implements JoinTableStudentParentService {

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private JoinTableStudentParentRepository joinTableStudentParentRepository;

	@Autowired
	private ParentRepository parentRepository;

	@Override
	public JoinTableStudentParent createEntity(JoinTableStudentParentDTO source) {

		if (source == null)
			return null;

		Optional<ParentEntity> op1 = parentRepository.findById(source.getParent());
		if (op1.isPresent() == false)
			return null;
		ParentEntity parent = op1.get();

		Optional<StudentEntity> op2 = studentRepository.findById(source.getStudent());
		if (op2.isPresent() == false)
			return null;
		StudentEntity student = op2.get();

		JoinTableStudentParent entity = new JoinTableStudentParent();
		entity.setParent(parent);
		entity.setStudent(student);

		entity = joinTableStudentParentRepository.save(entity);
		return entity;
	}

	@Override
	public JoinTableStudentParentDTO createDTO(JoinTableStudentParent source) {

		if (source == null)
			return null;
		JoinTableStudentParentDTO retVal = new JoinTableStudentParentDTO();
		retVal.setParent(source.getParent().getId());
		retVal.setStudent(source.getStudent().getId());
		retVal.setId(source.getId());

		return retVal;
	}

	@Override
	public JoinTableStudentParentDTO getEntityDTO(Integer id) {
		Optional<JoinTableStudentParent> op = joinTableStudentParentRepository.findById(id);
		if (op.isPresent() == false)
			return null;
		return this.createDTO(op.get());
	}

	@Override
	public JoinTableStudentParentDTO setEntity(Integer id, JoinTableStudentParentDTO newEntity) {
		
		Optional<JoinTableStudentParent> op = joinTableStudentParentRepository.findById(id);
		if (op.isPresent() == false)
			return null;
		JoinTableStudentParent entity = op.get();
		
		Optional<ParentEntity> op1 = parentRepository.findById(newEntity.getParent());
		if (op1.isPresent() == false)
			return null;
		ParentEntity parent = op1.get();

		Optional<StudentEntity> op2 = studentRepository.findById(newEntity.getStudent());
		if (op2.isPresent() == false)
			return null;
		StudentEntity student = op2.get();
		
		// Change entity
		if (newEntity.getParent() != null)
			entity.setParent(parent);
		if (newEntity.getStudent() != null)
			entity.setStudent(student);

		// Save and return DTO
		entity = joinTableStudentParentRepository.save(entity);
		return this.createDTO(entity);
	}

	@Override
	public JoinTableStudentParentDTO removeEntity(Integer id) {
		
		Optional<JoinTableStudentParent> op = joinTableStudentParentRepository.findById(id);
		if (op.isPresent() == false)
			return null;
		JoinTableStudentParent entity = op.get();
		
		joinTableStudentParentRepository.delete(entity);
		return this.createDTO(entity);
	}
	
	@Override
	public List<JoinTableStudentParentDTO> getDTOList() {
		List<JoinTableStudentParentDTO> list = new ArrayList<>();
		for (JoinTableStudentParent entity : joinTableStudentParentRepository.findAll())
			list.add(this.createDTO(entity));
		return list;
	}	

}
