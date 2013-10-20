package com.alexecollins.releasemanager.web;

import cucumber.api.java.Before;
import cucumber.api.java.en.But;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.seleniumhq.selenium.fluent.FluentExecutionStopped;
import org.seleniumhq.selenium.fluent.FluentWebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author alexec (alex.e.c@gmail.com)
 */
@ContextConfiguration(locations = "cucumber.xml")
public class SecurityStepdefs {
	@Autowired
	WebDriver driver;
	@Autowired
	FluentWebDriver fluent;

	@Before
	public void tearDown() throws Throwable {
		logged_out();
	}

	@Given("^logged out$")
	public void logged_out() throws Throwable {
		try {
			fluent.link(By.linkText("Logout")).click();
		} catch (FluentExecutionStopped ignored) {
		}
	}

	@When("^I login in as a (.+)$")
	public void I_login_in_as_a(String role) throws Throwable {
		fluent.input(By.name("j_username")).sendKeys(role);
		fluent.input(By.name("j_password")).sendKeys("123456");
		fluent.input(By.name("submit")).click();
	}

	@Then("^I can access (.+)$")
	public void I_can_access(String page) throws Throwable {
		driver.get("http://localhost:8080/release-manager-web/" + page);
		fluent.h1().getText().shouldNotBe("Login");
		fluent.h1().getText().shouldNotBe("Access Denied");
	}

	@But("^I cannot access (.*)$")
	public void I_cannot_access(String page) throws Throwable {
		driver.get("http://localhost:8080/release-manager-web/" + page);
		fluent.h1().getText().shouldMatch("Login|Access Denied");
	}

	@Then("^I logout$")
	public void I_logout() throws Throwable {
		fluent.link(By.linkText("Logout")).click();
	}
}
