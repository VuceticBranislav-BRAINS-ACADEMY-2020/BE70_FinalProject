package com.iktakademija.FinalProject.services;

import java.util.List;

import com.iktakademija.FinalProject.entities.SubjectEntity;
import com.iktakademija.FinalProject.entities.dtos.NewSubjectDTO;
import com.iktakademija.FinalProject.entities.dtos.SubjectDTO;

public interface SubjectService {
	SubjectDTO createDTO(SubjectEntity source);
	List<SubjectDTO> createDTOList(List<SubjectEntity> source);	
	List<SubjectDTO> getDTOList();
	SubjectDTO getSubjectDTO(Integer subjectId);
	SubjectDTO setSubject(Integer subjectId, NewSubjectDTO newSubject);
	SubjectDTO removeSubject(Integer subjectId);
	SubjectEntity createSubject(NewSubjectDTO source);

}
