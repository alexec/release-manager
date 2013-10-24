package com.alexecollins.releasemanager.web;

import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.seleniumhq.selenium.fluent.FluentMatcher;
import org.seleniumhq.selenium.fluent.FluentWebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import static org.openqa.selenium.By.partialLinkText;

/**
 * @author alexec (alex.e.c@gmail.com)
 */
@ContextConfiguration(locations = "cucumber.xml")
public class ComponentsStepDefs {

	@Autowired
	WebDriver driver;
	@Autowired
	FluentWebDriver fluent;

	String name;

	@Before
	public void before() {
		name = null;
	}

	@Given("^the create component page$")
	public void the_create_component_page() throws Throwable {
		driver.get("http://localhost:8080/release-manager-web/components.html");
		fluent.link(partialLinkText("Create")).click();
	}

	@And("^I input a component$")
	public void I_input_a_component() throws Throwable {
		fluent.h1().getText().shouldBe("New Component");
		name = "Test Component " + System.currentTimeMillis();
		fluent.input().sendKeys(name);
	}

	@And("^I click submit$")
	public void I_click_submit() throws Throwable {
		fluent.input(By.name("submit")).click();
	}

	@Then("^I do not see an error$")
	public void I_do_not_see_an_error() throws Throwable {
		fluent.h1().getText().shouldNotBe("Error");
	}

	@And("^I see my new component$")
	public void I_see_my_new_component() throws Throwable {
		fluent.tds().first(new FluentMatcher() {
			@Override
			public boolean matches(WebElement webElement) {
				return webElement.getText().contains(name);
			}
		});
	}
}
