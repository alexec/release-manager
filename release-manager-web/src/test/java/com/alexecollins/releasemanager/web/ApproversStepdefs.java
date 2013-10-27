package com.alexecollins.releasemanager.web;

import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.seleniumhq.selenium.fluent.FluentMatcher;
import org.seleniumhq.selenium.fluent.FluentWebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import static org.openqa.selenium.By.name;
import static org.openqa.selenium.By.partialLinkText;

/**
 * @author alex.collins
 */
@ContextConfiguration(locations = "cucumber.xml")
public class ApproversStepdefs {
    @Autowired
    WebDriver driver;
    @Autowired
    FluentWebDriver fluent;

    String name;

    @Before
    public void before() {
        name = null;
    }
    @Given("^the create approver page$")
    public void the_create_approver_page() throws Throwable {
        driver.get("http://localhost:8080/release-manager-web/approvers.html");
        fluent.link(partialLinkText("Create")).click();
    }

    @And("^I input a approver$")
    public void I_input_a_approver() throws Throwable {
        name = "Approver " + System.currentTimeMillis();
        fluent.input(name("name")).sendKeys(name);
    }

    @And("^I see my new approver$")
    public void I_see_my_new_approver() throws Throwable {
        fluent.tds().first(new FluentMatcher() {
            @Override
            public boolean matches(WebElement webElement) {
                return webElement.getText().contains(name);
            }
        });
    }

	@Given("^I create a new approver$")
	public void I_create_a_new_approver() throws Throwable {
		the_create_approver_page();
		I_input_a_approver();
		fluent.input(name("submit")).click();
		I_see_my_new_approver();
	}
}
