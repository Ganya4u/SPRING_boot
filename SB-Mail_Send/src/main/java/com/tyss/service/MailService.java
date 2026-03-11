package com.tyss.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class MailService {
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	
	public void sendMail(String toEmail) {

		SimpleMailMessage message = new SimpleMailMessage();

		message.setFrom("khandekar0000021@gmail.com");
		message.setTo(toEmail);
		message.setSubject("Welcome to EmailService app");
		message.setText("Hello you are selected," + "Enjoy the services");

		javaMailSender.send(message);

	}
	

	public void sendMailTemplate(String toEmail) throws IOException, MessagingException {

		String body = new String(Files.readAllBytes(Paths.get("src/main/resources/templates/SendMail.html")));

		MimeMessage message = javaMailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(message);

		helper.setFrom("khandekar0000021@gmail.com");
		helper.setTo(toEmail);
		helper.setSubject("Thank you for Registering!!!!!!!!");
		helper.setText(body, true);// body contains the html code

		javaMailSender.send(message);

	}
	

}
