package com.iktakademija.FinalProject.services;

import com.iktakademija.FinalProject.dtos.EmailObject;
import com.iktakademija.FinalProject.dtos.GradeDTO;

public interface EmailService {

	void sendTemplateMessage(EmailObject object, GradeDTO dto) throws Exception;

	void sendMessageWithAttachment(EmailObject object, String pathToAttachment) throws Exception;

	/**
	 * Deture email address with one provided in setting file.
	 * 
	 * @return detured email address.
	 */
	String[] emailDeture(String[] email);

	String[] emailDeture(String email);

}
