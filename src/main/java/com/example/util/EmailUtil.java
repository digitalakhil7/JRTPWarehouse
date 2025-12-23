package com.example.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Component
public class EmailUtil {
	@Autowired
	private JavaMailSender sender;
	
	public boolean sendEmail(String to, String subject, String body) {
		
		boolean sent = false;
		
//		1. Create Message
		MimeMessage mimeMessage = sender.createMimeMessage();
		
//		2. Fill Message
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
		try {
		helper.setTo(to);
		helper.setSubject(subject);
		helper.setText(body);
		}catch(MessagingException e) {
			e.printStackTrace();
		}
		
//		3. Send Message
		sender.send(mimeMessage);
		
		sent = true;
		return sent;
	}
}
