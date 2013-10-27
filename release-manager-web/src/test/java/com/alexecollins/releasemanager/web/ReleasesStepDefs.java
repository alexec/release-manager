package com.alexecollins.releasemanager.web;

import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.seleniumhq.selenium.fluent.FluentExecutionStopped;
import org.seleniumhq.selenium.fluent.FluentMatcher;
import org.seleniumhq.selenium.fluent.FluentWebDriver;
import org.seleniumhq.selenium.fluent.FluentWebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.Assert.fail;
import static org.openqa.selenium.By.*;

/**
 * @author alexec (alex.e.c@gmail.com)
 */
@ContextConfiguration(locations = "cucumber.xml")
public class ReleasesStepDefs {
	@Autowired
	WebDriver driver;
	@Autowired
	FluentWebDriver fluent;
	@Autowired
	Releases releases;

	@Before
	public void setUp() throws Exception {
		releases.name = null;
	}

	@Given("^the create releases page$")
	public void the_create_releases_page() throws Throwable {
		driver.get("http://localhost:8080/release-manager-web/releases.html");
		fluent.link(linkText("Create")).click();
	}

	@When("^I input a release for (.*)$")
	public void I_input_a_release_for(String when) throws Throwable {
		
		releases.name = "Test Release " + System.currentTimeMillis();

		fluent.input(name("name")).sendKeys(releases.name);
		fluent.input(name("when")).sendKeys(when);

		fluent.input(name("duration")).sendKeys("2 hours");
		fluent.textarea(name("desc")).sendKeys("Test Release Desc\n---\n" + ExamplesLoader.LIPSUM);
	}

	@And("^I see my release$")
	public void I_see_my_release() throws Throwable {
		fluent.h1().getText().shouldBe(releases.name);
	}

	@Then("^I see an error$")
	public void I_see_an_error() throws Throwable {
		fluent.h1().getText().shouldBe("Error");
	}

	@When("^I create a new release$")
	public void I_create_a_new_release() throws Throwable {
		a_new_release();
	}
	@Given("^a new release$")
	public void a_new_release() throws Throwable {
		the_create_releases_page();
		I_input_a_release_for("tomorrow");
		fluent.input(name("submit")).click();
		I_see_my_release();
	}

	@When("^I open the edit release page$")
	public void I_open_the_edit_release_page() throws Throwable {
		I_open_the_releases_page();
		fluent.link(linkText(releases.name)).click();
		fluent.link(linkText("Edit")).click();
	}

	@And("^I set when to (.+)$")
	public void I_set_when_to(String when) throws Throwable {
		fluent.input(name("when")).clearField().sendKeys(when);
	}

	@When("^I open the releases page$")
	public void I_open_the_releases_page() throws Throwable {
		driver.get("http://localhost:8080/release-manager-web/releases.html");
	}

	@And("^I click my release's remove button$")
	public void I_click_my_release_s_remove_button() throws Throwable {
		fluent.trs().first(new FluentMatcher() {
			@Override
			public boolean matches(WebElement webElement) {
				return webElement.getText().contains(releases.name);
			}
		}).input().click();
	}

	@And("^I am on the releases page$")
	public void I_am_on_the_releases_page() throws Throwable {
		fluent.h1().getText().shouldBe("Releases");
	}

	@And("^I do not see my release$")
	public void I_do_not_see_my_release() throws Throwable {
		try {
			fluent.link(name(releases.name));
			fail();
		} catch (FluentExecutionStopped ignored) {
		}
	}

	@And("^I delete that release$")
	public void I_delete_that_release() throws Throwable {
		I_open_the_releases_page();
		I_click_my_release_s_remove_button();
	}

	@And("^I go to edit my new release$")
	public void I_go_to_edit_my_new_release() throws Throwable {
		I_open_the_releases_page();
		fluent.link(linkText(releases.name)).click();
		fluent.link(linkText("Edit")).click();
	}

	@And("^I add a component to the release$")
	public void I_add_a_component_to_the_release() throws Throwable {
		final FluentWebElement form = fluent.form(id("add-component"));
		form.input(name("version")).sendKeys("1.0.0");
		form.input(name("submit")).click();
	}

	@And("^I remove a component from the release$")
	public void I_remove_a_component_from_the_release() throws Throwable {
		final FluentWebElement form = fluent.form(cssSelector(".remove-component"));
		form.input(name("submit")).click();
	}

	@And("^I add a sign-off to the release$")
	public void I_add_a_sign_off_to_the_release() throws Throwable {
		fluent.form(id("add-sign-off")).input(name("submit")).click();
	}

	@And("^I remove a sign-off to the release$")
	public void I_remove_a_sign_off_to_the_release() throws Throwable {
		fluent.form(cssSelector(".remove-sign-off")).input(name("submit")).click();
	}

	@And("^I update a sign-off to the release to \"([^\"]*)\"$")
	public void I_update_a_sign_off_to_the_release_to(String status) throws Throwable {
		fluent.input(cssSelector("*[value~=" + status + "]")).click();
	}

}
