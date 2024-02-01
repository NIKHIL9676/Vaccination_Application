package com.example.practise.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailSender {
	@Autowired
	JavaMailSender javaMailSender;
	
	public void sendEmail(String to, String Subject, String body) {
		SimpleMailMessage message=new SimpleMailMessage();
		message.setTo(to);
		message.setSubject(Subject);
		message.setText(body);
		javaMailSender.send(message);
	}
}
