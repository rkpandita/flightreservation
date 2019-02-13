package com.raman.flightreservation.util;

import java.io.File;

import javax.mail.internet.MimeMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailUtilImpl implements EmailUtil {

	private Log logger = LogFactory.getLog(EmailUtilImpl.class);

	@Autowired
	private JavaMailSender sender;

	@Override
	public void sendItinerary(String toAddress, String subject, String body, String filePath) {

		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		try {
			helper.setTo(toAddress);
			helper.setSubject(subject);
			helper.setText(body);
			helper.addAttachment("Itinerary", new File(filePath));
			logger.info("About to send Email to " + toAddress);
			sender.send(message);
			logger.info("Email successfully sent to " + toAddress);
		} catch (Exception e) {
			logger.error("Email dispatch failed while sending to " + toAddress);
			logger.error(e);
		}

	}

}
