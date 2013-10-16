package com.alexecollins.releasemanager.web;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.seleniumhq.selenium.fluent.FluentMatcher;
import org.seleniumhq.selenium.fluent.FluentWebDriver;

import static org.openqa.selenium.By.partialLinkText;

/**
 * @author alexec (alex.e.c@gmail.com)
 */
public class ComponentIT {

	private FluentWebDriver fluent;
	private WebDriver driver;

	@Before
	public void setUp() throws Exception {
		driver = new HtmlUnitDriver();
		driver.get("http://localhost:8080/release-manager-web");
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
				.link(partialLinkText("Components")).click();
		fluent
		        .link(partialLinkText("Create")).click();
		fluent
				.h1().getText().shouldBe("New Component");
		final String name = "Test Component " + System.currentTimeMillis();
		fluent
				.input().sendKeys(name);
		fluent
				.button().click();
		fluent
				.tds().first(new FluentMatcher() {
			@Override
			public boolean matches(WebElement webElement) {
				return webElement.getText().contains(name);
			}
		});
	}
}
