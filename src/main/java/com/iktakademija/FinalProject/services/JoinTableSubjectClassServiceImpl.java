package com.iktakademija.FinalProject.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iktakademija.FinalProject.dtos.JoinTableSubjectClassDTO;
import com.iktakademija.FinalProject.entities.ClassEntity;
import com.iktakademija.FinalProject.entities.JoinTableSubjectClass;
import com.iktakademija.FinalProject.entities.SubjectEntity;
import com.iktakademija.FinalProject.repositories.ClassRepository;
import com.iktakademija.FinalProject.repositories.JoinTableSubjectClassRepository;
import com.iktakademija.FinalProject.repositories.SubjectRepository;

@Service
public class JoinTableSubjectClassServiceImpl implements JoinTableSubjectClassService {

	@Autowired
	private JoinTableSubjectClassRepository joinTableSubjectClassRepository;

	@Autowired
	private SubjectRepository subjectRepository;

	@Autowired
	private ClassRepository classRepository;

	@Override
	public JoinTableSubjectClass createEntity(JoinTableSubjectClassDTO source) {

		if (source == null)
			return null;

		Optional<SubjectEntity> op1 = subjectRepository.findById(source.getSubject());
		if (op1.isPresent() == false)
			return null;
		SubjectEntity subject = op1.get();

		Optional<ClassEntity> op2 = classRepository.findById(source.getClazz());
		if (op2.isPresent() == false)
			return null;
		ClassEntity clazz = op2.get();

		JoinTableSubjectClass table = new JoinTableSubjectClass();
		table.setSubject(subject);
		table.setClazz(clazz);
		table.setFond(source.getFond());

		table = joinTableSubjectClassRepository.save(table);
		return table;
	}

	@Override
	public JoinTableSubjectClassDTO createDTO(JoinTableSubjectClass source) {

		if (source == null)
			return null;
		JoinTableSubjectClassDTO retVal = new JoinTableSubjectClassDTO();
		retVal.setClazz(source.getClazz().getId());
		retVal.setSubject(source.getSubject().getId());
		retVal.setFond(source.getFond());
		retVal.setId(source.getId());

		return retVal;
	}

	@Override
	public JoinTableSubjectClassDTO getEntityDTO(Integer id) {
		Optional<JoinTableSubjectClass> op = joinTableSubjectClassRepository.findById(id);
		if (op.isPresent() == false)
			return null;
		return this.createDTO(op.get());
	}

	@Override
	public JoinTableSubjectClassDTO setEntity(Integer id, JoinTableSubjectClassDTO newEntity) {

		Optional<JoinTableSubjectClass> op = joinTableSubjectClassRepository.findById(id);
		if (op.isPresent() == false)
			return null;
		JoinTableSubjectClass entity = op.get();

		Optional<SubjectEntity> op1 = subjectRepository.findById(newEntity.getSubject());
		if (op1.isPresent() == false)
			return null;
		SubjectEntity subject = op1.get();

		Optional<ClassEntity> op2 = classRepository.findById(newEntity.getClazz());
		if (op2.isPresent() == false)
			return null;
		ClassEntity clazz = op2.get();

		// Change entity
		if (newEntity.getSubject() != null)
			entity.setSubject(subject);
		if (newEntity.getClazz() != null)
			entity.setClazz(clazz);
		if (newEntity.getFond() != null)
			entity.setFond(newEntity.getFond());

		// Save and return DTO
		entity = joinTableSubjectClassRepository.save(entity);
		return this.createDTO(entity);
	}

	@Override
	public JoinTableSubjectClassDTO removeEntity(Integer id) {

		Optional<JoinTableSubjectClass> op = joinTableSubjectClassRepository.findById(id);
		if (op.isPresent() == false)
			return null;
		JoinTableSubjectClass entity = op.get();

		joinTableSubjectClassRepository.delete(entity);
		return this.createDTO(entity);
	}

	@Override
	public List<JoinTableSubjectClassDTO> getDTOList() {
		List<JoinTableSubjectClassDTO> list = new ArrayList<>();
		for (JoinTableSubjectClass entity : joinTableSubjectClassRepository.findAll())
			list.add(this.createDTO(entity));
		return list;
	}

}
