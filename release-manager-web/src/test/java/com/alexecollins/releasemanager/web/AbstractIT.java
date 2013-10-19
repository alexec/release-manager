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
	public static String LIPSUM = "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";

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
