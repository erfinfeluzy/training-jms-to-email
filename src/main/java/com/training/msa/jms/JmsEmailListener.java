package com.training.msa.jms;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.training.msa.email.Email;

@Component
public class JmsEmailListener {

	@JmsListener(destination = "q.email")
	public void receiveEmailMessage(Email email) {
		System.out.println("Queue Received <" + email + ">");
		
		sendSimpleMessage(email.getReceiverEmail(), email.getSubject(), email.getBody());
	}

	@Autowired
	public JavaMailSender emailSender;

	public void sendSimpleMessage(String to, String subject, String text) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(to);
		message.setSubject(subject);
		message.setText(text);
		emailSender.send(message);
		System.out.println("Mail sent");
	}

}
