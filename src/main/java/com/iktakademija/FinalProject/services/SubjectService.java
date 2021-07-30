package com.iktakademija.FinalProject.services;

import java.util.List;

import com.iktakademija.FinalProject.dtos.NewSubjectDTO;
import com.iktakademija.FinalProject.dtos.SubjectDTO;
import com.iktakademija.FinalProject.entities.SubjectEntity;

public interface SubjectService {
	SubjectDTO createDTO(SubjectEntity source);
	List<SubjectDTO> createDTOList(List<SubjectEntity> source);	
	List<SubjectDTO> getDTOList();
	SubjectDTO getSubjectDTO(Integer subjectId);
	SubjectDTO setSubject(Integer subjectId, NewSubjectDTO newSubject);
	SubjectDTO removeSubject(Integer subjectId);
	SubjectEntity createSubject(NewSubjectDTO source);

}
