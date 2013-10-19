package com.alexecollins.releasemanager.web;

import com.alexecollins.web.ExamplesLoader;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.seleniumhq.selenium.fluent.FluentMatcher;

import static org.openqa.selenium.By.linkText;
import static org.openqa.selenium.By.name;

/**
 * @author alexec (alex.e.c@gmail.com)
 */
public class ReleaseIT extends AbstractIT {

	@Test
	public void createARelease() throws Exception {
		fluent.link(linkText("Releases")).click();
		fluent.link(linkText("Create")).click();
		final String name = "Test Release " + System.currentTimeMillis();
		fluent.input(name("name")).sendKeys(name);
		fluent.input(name("when")).sendKeys("tomorrow");
		fluent.textarea(name("desc")).sendKeys("Test Release Desc\n---\n" + ExamplesLoader.LIPSUM);
		fluent.button().click();
		fluent.link(linkText("Releases")).click();
		fluent.tds().first(new FluentMatcher() {
			@Override
			public boolean matches(WebElement webElement) {
				return webElement.getText().contains(name);
			}
		});
	}
}
