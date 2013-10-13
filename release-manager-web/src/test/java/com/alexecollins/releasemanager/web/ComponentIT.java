package com.alexecollins.releasemanager.web;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.safari.SafariDriver;
import org.seleniumhq.selenium.fluent.FluentMatcher;
import org.seleniumhq.selenium.fluent.FluentWebDriver;

/**
 * @author alexec (alex.e.c@gmail.com)
 */
public class ComponentIT {

	private FluentWebDriver fluent;
	private WebDriver driver;

	@Before
	public void setUp() throws Exception {
		driver = new SafariDriver();
		driver.get("http://localhost:8080/release-manager-web");
		Thread.sleep(1000);
		fluent = new FluentWebDriver(driver);
	}

	@After
	public void tearDown() throws Exception {
		Thread.sleep(10000);
		driver.quit();
	}

	@Test
	public void createANewComponent() throws Exception {

		fluent
				.link(By.partialLinkText("Components")).click()
		        .link(By.partialLinkText("Create")).click()
				.h1().getText().shouldBe("New Component");
		fluent
				.input(By.name("name")).sendKeys("Test Component")
				.button().click()
				.tds().first(new FluentMatcher() {
			@Override
			public boolean matches(WebElement webElement) {
				return webElement.getText().contains("Test Component");
			}
		});


	}
}
