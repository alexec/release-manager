package com.alexecollins.releasemanager.web;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.seleniumhq.selenium.fluent.FluentWebDriver;
import org.seleniumhq.selenium.fluent.monitors.CompositeMonitor;
import org.seleniumhq.selenium.fluent.monitors.HighlightOnError;
import org.seleniumhq.selenium.fluent.monitors.ScreenShotOnError;

/**
 * @author alexec (alex.e.c@gmail.com)
 */
public abstract class BasePage extends FluentWebDriver {

	public BasePage(FirefoxDriver delegate) {
		super(delegate,
				new CompositeMonitor(
						new HighlightOnError(delegate),
						new ScreenShotOnError.WithUnitTestFrameWorkContext(delegate, BasePage.class, "test-classes", "surefire-reports")));
	}
}
