package com.iktakademija.FinalProject.services;

import java.util.List;

import com.iktakademija.FinalProject.entities.GradeEntity;
import com.iktakademija.FinalProject.entities.SubjectEntity;
import com.iktakademija.FinalProject.entities.dtos.GradeDTO;
import com.iktakademija.FinalProject.entities.dtos.SubjectDTO;
import com.iktakademija.FinalProject.entities.enums.EStage;

public interface GradeService {

	List<GradeDTO> getDTOList();
	GradeDTO createDTO(GradeEntity source);
	List<GradeDTO> createDTOList(List<GradeEntity> source);
	GradeDTO getGradeDTO(Integer gradeId);
	
	List<GradeDTO> getPageDTO(Integer page);	
	List<GradeEntity> getFiltered(Integer studentId, Integer subjectId, Integer teacherId, Integer groupId, Integer classId, EStage stage);
	
}
