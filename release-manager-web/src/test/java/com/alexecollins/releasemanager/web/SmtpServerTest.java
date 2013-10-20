package com.alexecollins.releasemanager.web;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author alexec (alex.e.c@gmail.com)
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "applicationContext.xml")
public class SmtpServerTest {

	@Autowired
	JavaMailSenderImpl javaMailSender;
	private SmtpServer server;

	@Before
	public void setUp() throws Exception {
		server = new SmtpServer();
		server.start();
	}

	@After
	public void tearDown() throws Exception {
		server.close();
	}

	@Test
	public void sentMailIsSent() throws Exception {

		final SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("release-manager@alexecollins.com");
		message.setTo("alex@alexecollins.com");
		message.setSubject("Test Email");
		message.setText("Test Text");
		javaMailSender.send(message);


	}
}
