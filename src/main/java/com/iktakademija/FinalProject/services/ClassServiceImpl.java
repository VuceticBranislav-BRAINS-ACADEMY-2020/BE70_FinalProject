package com.iktakademija.FinalProject.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iktakademija.FinalProject.dtos.ClassDTO;
import com.iktakademija.FinalProject.dtos.NewClassDTO;
import com.iktakademija.FinalProject.entities.ClassEntity;
import com.iktakademija.FinalProject.entities.JoinTableSubjectClass;
import com.iktakademija.FinalProject.entities.SubjectEntity;
import com.iktakademija.FinalProject.entities.enums.EStatus;
import com.iktakademija.FinalProject.repositories.ClassRepository;
import com.iktakademija.FinalProject.repositories.JoinTableSubjectClassRepository;
import com.iktakademija.FinalProject.repositories.SubjectRepository;

@Service
public class ClassServiceImpl implements ClassService {

	@Autowired
	private ClassRepository classRepository;
	
	@Autowired
	private SubjectRepository subjectRepository;

	@Autowired
	private JoinTableSubjectClassRepository joinTableSubjectClassRepository;

	@Override
	public ClassEntity createClass(NewClassDTO source) {
		ClassEntity clazz = new ClassEntity();
		clazz.setName(source.getName());
		clazz.setStatus(source.getStatus());
		clazz.setYear(source.getYear());
		clazz = classRepository.save(clazz);
		return clazz;
	}

	@Override
	public ClassDTO createDTO(ClassEntity source) {
		ClassDTO retVal = new ClassDTO();
		if (source == null)
			return retVal;
		retVal.setId(source.getId());
		retVal.setName(source.getName());
		retVal.setStatus(source.getStatus());
		retVal.setVersion(source.getVersion());
		retVal.setYear(source.getYear());
		return retVal;
	}

	@Override
	public List<ClassDTO> getDTOList() {
		List<ClassDTO> list = new ArrayList<>();
		for (ClassEntity clazz : classRepository.findAllUndeleted())
			list.add(this.createDTO(clazz));
		return list;
	}

	@Override
	public ClassDTO getClassDTO(Integer classId) {
		Optional<ClassEntity> op = classRepository.findById(classId);
		if (op.isPresent() == false)
			return null;
		return this.createDTO(op.get());
	}

	@Override
	public ClassDTO setClass(Integer classId, NewClassDTO newClass) {

		// Find class by id
		Optional<ClassEntity> op1 = classRepository.findById(classId);
		if (op1.isPresent() == false)
			return null;
		ClassEntity clazz = op1.get();

		// Change teacher
		if (newClass.getName() != null)
			clazz.setName(newClass.getName());
		if (newClass.getStatus() != null)
			clazz.setStatus(newClass.getStatus());
		if (newClass.getYear() != null)
			clazz.setYear(newClass.getYear());

		// Save and return DTO
		clazz = classRepository.save(clazz);
		return this.createDTO(clazz);
	}

	@Override
	public ClassDTO removeClass(Integer classId) {
		Optional<ClassEntity> op = classRepository.findById(classId);
		if (op.isPresent() == false)
			return null;
		ClassEntity clazz = op.get();
		clazz.setStatus(EStatus.DELETED);
		clazz = classRepository.save(clazz);
		return this.createDTO(clazz);
	}

	@Override
	public JoinTableSubjectClass addSubjectToClass(Integer subjectId, Integer classId, Integer fond) {

		// Find froup by id
		Optional<SubjectEntity> op1 = subjectRepository.findById(subjectId);
		if (op1.isPresent() == false)
			return null;
		SubjectEntity subject = op1.get();

		// Find student by id
		Optional<ClassEntity> op2 = classRepository.findById(classId);
		if (op2.isPresent() == false)
			return null;
		ClassEntity clazz = op2.get();

		JoinTableSubjectClass item = new JoinTableSubjectClass();
		item.setClazz(clazz);
		item.setSubject(subject);
		if (fond == null) return null;
		item.setFond(fond);

		item = joinTableSubjectClassRepository.save(item);
		return item;
	}
	
}
