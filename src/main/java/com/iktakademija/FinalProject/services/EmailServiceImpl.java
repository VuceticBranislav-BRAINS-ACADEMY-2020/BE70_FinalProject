package com.iktakademija.FinalProject.services;

import java.io.File;
import java.time.format.DateTimeFormatter;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.iktakademija.FinalProject.entities.dtos.EmailObject;
import com.iktakademija.FinalProject.entities.dtos.GradeDTO;

@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	private JavaMailSender mailSender;
	
	@Override
	public void sendTemplateMessage(EmailObject object, GradeDTO dto) throws Exception {
		
		MimeMessage mail = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mail, true);

		helper.setTo(object.getTo());
		helper.setSubject(object.getSubject());
		String text = "Grade report: <br> <table style='width:900px;' border='2px;'> <tbody> <tr>"
				+ "<td style='border-style: hidden;'><strong>Value</strong></td>"
				+ "<td style='border-style: hidden;'><strong>Type</strong></td>"
				+ "<td style='border-style: hidden;'><strong>Stage</strong></td>"
				+ "<td style='border-style: hidden;'><strong>Date</strong></td>"
				+ "<td style='border-style: hidden;'><strong>Group</strong></td>"
				+ "<td style='border-style: hidden;'><strong>Teacher</strong></td>"
				+ "<td style='border-style: hidden;'><strong>Subject</strong></td>"
				+ "<td style='border-style: hidden;'><strong>Status</strong></td> </tr> <tr>"
				+ "<td style='border-style: hidden;'>%s</td>"
				+ "<td style='border-style: hidden;'>%s</td>"
				+ "<td style='border-style: hidden;'>%s</td>"
				+ "<td style='border-style: hidden;'>%s</td>"
				+ "<td style='border-style: hidden;'>%s</td>"
				+ "<td style='border-style: hidden;'>%s</td>"
				+ "<td style='border-style: hidden;'>%s</td>"
				+ "<td style='border-style: hidden;'>%s</td> </tr> </tbody></table>"
				+ "<br>Have a nice day.";

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MMM-dd");
		text = String.format(text, 
				dto.getValue().toString(), 
				dto.getType().toString(),
				dto.getStage().toString(),
				dto.getEntered().format(formatter),
				dto.getGroupName(),
				dto.getTeacherName(),
				dto.getSubjectName(),
				dto.getStatus().toString());
		helper.setText(text, true);
		mailSender.send(mail);

	}

	@Override
	public void sendMessageWithAttachment(EmailObject object, String pathToAttachment) throws Exception {

		MimeMessage mimeMessage = mailSender.createMimeMessage();
		FileSystemResource file = new FileSystemResource(new File(pathToAttachment));
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
		helper.setTo(object.getTo());
		helper.setSubject(object.getSubject());
		helper.setText("Log file from: " + pathToAttachment);
		helper.addAttachment(file.getFilename(), file);
		this.mailSender.send(mimeMessage);

	}

}
