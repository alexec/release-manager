package com.alexecollins.releasemanager.web;

import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import org.seleniumhq.selenium.fluent.FluentWebDriver;
import org.seleniumhq.selenium.fluent.FluentWebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author alexec (alex.e.c@gmail.com)
 */
@ContextConfiguration(locations = "cucumber.xml")
public class AuditingStepdefs {
	@Autowired
	WebDriver driver;
	@Autowired
	FluentWebDriver fluent;

	String name;

	@Before
	public void before() {
		name = null;
	}

	@When("^I look at the audit history$")
	public void I_look_at_the_audit_history() throws Throwable {
		driver.get("http://localhost:8080/release-manager-web/audit_logs.html");
	}

	@Then("^I see the newest audit record is \"([^\"]*)\" \"([^\"]*)\"$")
	public void I_see_the_newest_audit_record_is(String username, String message) throws Throwable {
		final FluentWebElement trs = fluent.trs().get(1);

		trs.tds().get(1).getText().shouldBe(username);
		trs.tds().get(2).getText().shouldBe(message);
	}
}
