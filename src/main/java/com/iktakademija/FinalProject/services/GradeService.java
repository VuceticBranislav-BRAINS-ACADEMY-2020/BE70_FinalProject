package com.iktakademija.FinalProject.services;

import java.util.List;

import com.iktakademija.FinalProject.entities.GradeEntity;
import com.iktakademija.FinalProject.entities.dtos.GradeDTO;

public interface GradeService {

	List<GradeDTO> getDTOList();
	GradeDTO createDTO(GradeEntity source);
	List<GradeDTO> createDTOList(List<GradeEntity> source);
	GradeDTO getGradeDTO(Integer gradeId);
	
}
