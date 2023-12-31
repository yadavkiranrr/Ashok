package com.kiran.it.utils;

import org.hibernate.Internal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import jakarta.mail.internet.MimeMessage;

public class EmailUtils {
	
	@Autowired
	private JavaMailSender mailSender;
	
	public boolean sentEmail(String to,String subject,String body) {
		
		boolean isMailSent= false;
		
		try {
			
			MimeMessage mimeMessage= mailSender.createMimeMessage();
			
			MimeMessageHelper helper= new MimeMessageHelper(mimeMessage);
			
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(body,true);
			
			mailSender.send(mimeMessage);
			
			isMailSent=true;
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}
	
	

}
