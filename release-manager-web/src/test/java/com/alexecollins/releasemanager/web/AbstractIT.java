package com.alexecollins.releasemanager.web;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.seleniumhq.selenium.fluent.FluentWebDriver;

/**
 * @author alexec (alex.e.c@gmail.com)
 */
public abstract class AbstractIT {

	protected FluentWebDriver fluent;
	private WebDriver driver;

	@Before
	public void setUp() throws Exception {
		driver = new HtmlUnitDriver();
		driver.get("http://localhost:8080/release-manager-web");
		fluent = new FluentWebDriver(driver);
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}
}
