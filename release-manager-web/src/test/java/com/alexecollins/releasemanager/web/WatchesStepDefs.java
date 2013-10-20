package com.alexecollins.releasemanager.web;

import com.alexecollins.testsupport.mail.FakeSmtpServer;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.seleniumhq.selenium.fluent.FluentWebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.By.cssSelector;
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
	@Autowired
	Releases releases;

	@Given("^I add a watch to releases$")
	public void I_add_a_watch_to_releases() throws Throwable {
		fluent.link(linkText("Releases")).click();
		fluent.button(cssSelector("[value=Watch]")).click();
		fluent.button(cssSelector("[value=Unwatch]"));
	}

	@Then("^I get an email$")
	public void I_get_an_email() throws Throwable {
		assertTrue(smtpServer.lastMessage().getText().contains(releases.name));
	}
}
