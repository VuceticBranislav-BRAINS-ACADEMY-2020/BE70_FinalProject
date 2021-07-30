package com.iktakademija.FinalProject.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iktakademija.FinalProject.entities.SubjectEntity;
import com.iktakademija.FinalProject.entities.dtos.NewSubjectDTO;
import com.iktakademija.FinalProject.entities.dtos.SubjectDTO;
import com.iktakademija.FinalProject.entities.enums.EStatus;
import com.iktakademija.FinalProject.repositories.SubjectRepository;

@Service
public class SubjectServiceImpl implements SubjectService {

	@Autowired
	private SubjectRepository subjectRepository;
	
	@Override
	public SubjectDTO createDTO(SubjectEntity source) {				
		SubjectDTO retVal = new SubjectDTO();
		if (source == null) return retVal;
		retVal.setId(source.getId());
		retVal.setName(source.getName());
		retVal.setVersion(source.getVersion());
		retVal.setStatus(source.getStatus());
		return retVal;
	}

	@Override
	public List<SubjectDTO> getDTOList() {
		List<SubjectDTO> list = new ArrayList<>();
		for (SubjectEntity subject : subjectRepository.findAllUndeleted()) 
			list.add(this.createDTO(subject));		
		return list;
	}
	
	@Override
	public List<SubjectDTO> createDTOList(List<SubjectEntity> source) {		
		List<SubjectDTO> list = new ArrayList<>();
		for (SubjectEntity subject : source) 
			list.add(this.createDTO(subject));		
		return list;
	}

	@Override
	public SubjectDTO getSubjectDTO(Integer subjectId) {
		Optional<SubjectEntity> op = subjectRepository.findById(subjectId);
		if (op.isPresent() == false) return null;
		return this.createDTO(op.get());
	}

	@Override
	public SubjectDTO setSubject(Integer subjectId, NewSubjectDTO newSubject) {
		Optional<SubjectEntity> op = subjectRepository.findById(subjectId);
		if (op.isPresent() == false) return null;
		SubjectEntity student = op.get();		
		
		if (newSubject.getName() != null) student.setName(newSubject.getName());

		student = subjectRepository.save(student);
		return this.createDTO(student);
	}

	@Override
	public SubjectDTO removeSubject(Integer subjectId) {
		Optional<SubjectEntity> op = subjectRepository.findById(subjectId);
		if (op.isPresent() == false) return null;
		SubjectEntity subject = op.get();		
		subject.setStatus(EStatus.DELETED);			
		subject = subjectRepository.save(subject);
		return this.createDTO(subject);	
	}

	@Override
	public SubjectEntity createSubject(NewSubjectDTO source) {				
		SubjectEntity subject = new SubjectEntity(source.getName());	
		subject = subjectRepository.save(subject);
		return subject;	
	}

}
