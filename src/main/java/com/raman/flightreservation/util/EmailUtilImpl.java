package com.raman.flightreservation.util;

import java.io.File;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailUtilImpl implements EmailUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmailUtilImpl.class);

	@Autowired
	private JavaMailSender sender;

	@Override
	public void sendItinerary(String toAddress, String subject, String body, String filePath) {

		LOGGER.info("Inside sendItinerary()");
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		try {
			helper.setTo(toAddress);
			helper.setSubject(subject);
			helper.setText(body);
			helper.addAttachment("Itinerary", new File(filePath));
			LOGGER.info("About to send Email to {} ", toAddress);
			sender.send(message);
			LOGGER.info("Email successfully sent to {} ", toAddress);
		} catch (Exception e) {
			LOGGER.error("Email dispatch failed while sending to {} ", toAddress, e);
		}

	}

}
