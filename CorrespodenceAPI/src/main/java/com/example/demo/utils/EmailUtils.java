package com.example.demo.utils;

import java.io.File;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Component
public class EmailUtils {

	@Autowired
	private static JavaMailSender sender;

	public static void sendMail(String email, String subject, String body, File file) throws MessagingException {
		MimeMessage messag = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(messag, true);
		helper.setTo(email);
		helper.setSentDate(new Date());
		helper.setSubject(subject);
		helper.setText(body, true);
		helper.addAttachment(file.getName(), file);
		sender.send(messag);
	}

}
