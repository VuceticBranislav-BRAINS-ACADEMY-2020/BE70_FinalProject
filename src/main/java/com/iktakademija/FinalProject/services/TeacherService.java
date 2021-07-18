package com.iktakademija.FinalProject.services;

import com.iktakademija.FinalProject.entities.TeacherEntity;
import com.iktakademija.FinalProject.entities.dtos.TeacherDTO;

public interface TeacherService {
	TeacherDTO createDTO(TeacherEntity source);
}
