package com.alexecollins.releasemanager.web;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.seleniumhq.selenium.fluent.FluentExecutionStopped;
import org.seleniumhq.selenium.fluent.FluentWebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import static org.openqa.selenium.By.cssSelector;

/**
 * @author alexec (alex.e.c@gmail.com)
 */
@ContextConfiguration(locations = "cucumber.xml")
public class WatchesStepDefs {
	@Autowired
	WebDriver driver;
	/*
	@Autowired
	FakeSmtpServer smtpServer;
	*/
	@Autowired
	FluentWebDriver fluent;
	@Autowired
	Releases releases;

	@Before
	public void setUp() throws Exception {
		//smtpServer.clear();
	}

	@Given("^I add a watch to (.*\\.html)")
	public void I_add_a_watch_to(String page) throws Throwable {
		driver.get("http://localhost:8080/release-manager-web" + page);
		try {
			fluent.button(cssSelector("[value=Watch]")).click();
		} catch (FluentExecutionStopped ignored) {
		}
		fluent.button(cssSelector("[value=Unwatch]"));
	}

	@Then("^I get an email$")
	public void I_get_an_email() throws Throwable {
		Thread.sleep(200);
		//assertNotNull(smtpServer.lastMessage());
	}
}
