package com.alexecollins.releasemanager.web;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.But;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
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
	public void setUp() throws Throwable {
		driver.manage().deleteAllCookies();
		driver.get("http://localhost:8080/release-manager-web");
	}

	@After
	public void tearDown() throws Throwable {
		logged_out();
	}

	@Given("^logged out$")
	public void logged_out() throws Throwable {
		driver.get("http://localhost:8080/release-manager-web/auth/logout.html");
	}

	@When("^I login in as a (.+)$")
	public void I_login_in_as_a(String role) throws Throwable {
		System.out.println("logging in");
		fluent.h1().getText().shouldBe("Login");
		fluent.input(By.name("j_username")).clearField().sendKeys(role);
		fluent.input(By.name("j_password")).clearField().sendKeys("123456");
		fluent.input(By.name("submit")).click();
		fluent.h1().getText().shouldNotBe("Error|Login|Access Denied");
		System.out.println(fluent.h1().getText());
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
		driver.get("http://localhost:8080/release-manager-web/auth/logout.html");
	}
}
