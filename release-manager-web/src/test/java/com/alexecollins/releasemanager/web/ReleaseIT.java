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
		verifyCreated(newRelease("tomorrow"));
	}

	private void verifyCreated(final String name) {
		fluent.link(linkText("Releases")).click();
		fluent.tds().first(new FluentMatcher() {
			@Override
			public boolean matches(WebElement webElement) {
				return webElement.getText().contains(name);
			}
		});
	}

	@Test(expected = Exception.class)
	public void givenABadDateWhenICreateAReleaseThenIGetAnError() throws Exception {
		verifyCreated(newRelease("balls"));
	}

	private String newRelease(String when) {
		fluent.link(linkText("Releases")).click();
		fluent.link(linkText("Create")).click();
		final String name = "Test Release " + System.currentTimeMillis();
		fluent.input(name("name")).sendKeys(name);
		fluent.input(name("when")).sendKeys(when);
		fluent.textarea(name("desc")).sendKeys("Test Release Desc\n---\n" + ExamplesLoader.LIPSUM);
		fluent.button().click();
		return name;
	}
}
