package com.alexecollins.releasemanager.web;

import com.alexecollins.testsupport.mail.FakeSmtpServer;
import org.seleniumhq.selenium.fluent.FluentWebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.By.linkText;

/**
 * @author alexec (alex.e.c@gmail.com)
 */
@ContextConfiguration(locations = "cucumber.xml")
public class WatchesStepDefs {
	@Autowired
	FakeSmtpServer smtpServer;
	@Autowired
	FluentWebDriver fluent;

	private void verifyNewReleaseEmail(String name) {
		assertTrue(smtpServer.lastMessage().getText().contains(name));
	}

	private void watchReleases() {
		fluent.link(linkText("Releases")).click();
		fluent.button(linkText("Watch")).click();
		fluent.button(linkText("Unwatch"));
	}


}
