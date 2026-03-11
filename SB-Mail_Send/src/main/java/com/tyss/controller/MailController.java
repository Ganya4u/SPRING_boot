package com.tyss.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tyss.service.MailService;

import jakarta.mail.MessagingException;

@RestController
public class MailController {
	
	@Autowired
	private MailService mailService;

	
	
	@GetMapping("/send")
	public String sendEmail(@RequestParam String email) {
       	mailService.sendMail(email);
		return "Email Sent, check your email : " + email;
	}
	
	@GetMapping("/send2")
	public String sendEmailTemplate(@RequestParam String email) throws IOException, MessagingException {
    	mailService.sendMailTemplate(email);
		return "Email Sent, check your email : " + email;
	}
	

}
