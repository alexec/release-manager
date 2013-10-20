package com.alexecollins.releasemanager.web;

import com.alexecollins.releasemanager.model.Watch;
import com.alexecollins.releasemanager.model.WatchRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.Principal;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * @author alexec (alex.e.c@gmail.com)
 */
@Service
@Slf4j
public class WatchService {
	@Autowired
	JavaMailSender mailSender;
	@Autowired
	WatchRepository watchRepository;

	ExecutorService executorService = Executors.newSingleThreadExecutor(new ThreadFactory() {
		@Override
		public Thread newThread(Runnable r) {
			final Thread t = new Thread(r);
			t.setName(WatchService.class.getSimpleName() + "Executor");
			t.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
				@Override
				public void uncaughtException(Thread t, Throwable e) {
					log.error("failed to process task " + e, e);
				}
			});
			return t;
		}
	});

	void notifyOfCreation(final String page) {

		executorService.submit(new Runnable() {
			@Override
			public void run() {
				log.info("sending notification of new page {}", page);
				final MimeMessage message = mailSender.createMimeMessage();
				try {
					for (Watch watch : watchRepository.findBySubject(subjectOf(page))) {
						message.addRecipients(Message.RecipientType.TO, watch.getUser() + "@localhost");
					}

					if (message.getAllRecipients().length > 0) {

						message.setSubject("Page " + page + " created");
						message.setText(getPage(page));

						mailSender.send(message);
						log.info("sent notification of new page {}", page);
					} else {
						log.info("no recipients of page {}", page);
					}
				} catch (Exception e) {
					log.error("failed to send notification", e);
				}
			}
		});
	}

	private String subjectOf(String page) {
		return "page:" + page;
	}

	private String getPage(String page) throws IOException {
		final HttpURLConnection conn = (HttpURLConnection) new URL("http://localhost:8080/release-manager-web" + page).openConnection();
		conn.connect();
		try {
			return new Scanner(conn.getInputStream()).useDelimiter("\\A").next();
		} finally {
			conn.disconnect();
		}
	}

	public boolean isWatchingPage(Principal user, String page) {
		return watchRepository.findByUserAndSubject(user.getName(), subjectOf(page)) != null;
	}
}
