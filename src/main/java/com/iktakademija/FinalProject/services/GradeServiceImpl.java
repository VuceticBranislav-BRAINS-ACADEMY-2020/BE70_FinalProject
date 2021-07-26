package com.iktakademija.FinalProject.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iktakademija.FinalProject.entities.GradeEntity;
import com.iktakademija.FinalProject.entities.dtos.GradeDTO;
import com.iktakademija.FinalProject.repositories.GradeRepository;

@Service
public class GradeServiceImpl implements GradeService {
	
	@Autowired
	private GradeRepository gradeRepository;
	
	@Override
	public List<GradeDTO> getDTOList() {
		List<GradeDTO> list = new ArrayList<>();
		for (GradeEntity grade : gradeRepository.findAllUndeleted()) 
			list.add(this.createDTO(grade));		
		return list;
	}
	
	@Override
	public GradeDTO createDTO(GradeEntity source) {
		GradeDTO retVal = new GradeDTO();
		if (source == null) return retVal;		
		retVal.setId(source.getId());
		retVal.setType(source.getType());
		retVal.setValue(source.getValue());
		retVal.setEntered(source.getEntered());
		retVal.setStage(source.getStage());
		retVal.setVersion(source.getVersion());
		retVal.setStatus(source.getStatus());
		retVal.setGroupName(source.getStd_grp().getGroup().getClazz().getName()+"-"+source.getStd_grp().getGroup().getLetter());
		retVal.setTeacherName(source.getSub_tch().getTeachers().getPerson().getFirstname()+" "+source.getSub_tch().getTeachers().getPerson().getLastname());
		retVal.setSubjectName(source.getSub_tch().getSub_cls().getSubject().getName());
		retVal.setStudentName(source.getStd_grp().getStudent().getPerson().getFirstname()+" "+source.getStd_grp().getStudent().getPerson().getLastname());
		return retVal;
	}
	
	@Override
	public List<GradeDTO> createDTOList(List<GradeEntity> source) {		
		List<GradeDTO> list = new ArrayList<>();
		for (GradeEntity grade : source) 
			list.add(this.createDTO(grade));		
		return list;
	}
	
}
