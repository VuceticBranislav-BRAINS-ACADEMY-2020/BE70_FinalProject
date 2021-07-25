package com.iktakademija.FinalProject.services;

import com.iktakademija.FinalProject.entities.dtos.EmailObject;
import com.iktakademija.FinalProject.entities.dtos.GradeDTO;

public interface EmailService {
	
	void sendTemplateMessage(EmailObject object, GradeDTO dto) throws Exception;
	void sendMessageWithAttachment(EmailObject object, String pathToAttachment) throws Exception;
	
}
