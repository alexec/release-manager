package com.alexecollins.releasemanager.web;

import com.alexecollins.releasemanager.model.Release;
import com.alexecollins.releasemanager.model.Watch;
import com.alexecollins.releasemanager.model.WatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author alexec (alex.e.c@gmail.com)
 */
@Service
public class WatchService {
	@Autowired
	JavaMailSender mailSender;
	@Autowired
	WatchRepository watchRepository;

	ExecutorService executorService = Executors.newSingleThreadExecutor();

	void notifyOfNewRelease(final Release release, final String contextPath) {

		executorService.submit(new Callable<Void>() {
			@Override
			public Void call() throws Exception {
				final MimeMessage message = mailSender.createMimeMessage();

				final List<Watch> watches = watchRepository.findBySubject("releases");
				for (Watch watch : watches) {
					message.addRecipients(Message.RecipientType.TO, watch.getUser() + "@localhost");
				}

				message.setSubject("Release " + release.getName() + " created");

				message.setText(getPage(contextPath + "/releases/" + release.getId() + ".html"));
				return null;
			}
		});
	}

	private String getPage(String url) throws IOException {
		final HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
		conn.connect();
		try {
			return new Scanner(conn.getInputStream()).useDelimiter("\\A").next();
		} finally {
			conn.disconnect();
		}
	}
}
