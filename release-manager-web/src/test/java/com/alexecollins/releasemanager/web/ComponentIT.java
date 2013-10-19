package com.alexecollins.releasemanager.web;

import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.seleniumhq.selenium.fluent.FluentMatcher;

import static org.openqa.selenium.By.partialLinkText;

/**
 * @author alexec (alex.e.c@gmail.com)
 */
public class ComponentIT extends AbstractIT {

	@Test
	public void createANewComponent() throws Exception {

		fluent.link(partialLinkText("Components")).click();
		fluent.link(partialLinkText("Create")).click();
		fluent.h1().getText().shouldBe("New Component");
		final String name = "Test Component " + System.currentTimeMillis();
		fluent.input().sendKeys(name);
		fluent.button().click();
		fluent.tds().first(new FluentMatcher() {
			@Override
			public boolean matches(WebElement webElement) {
				return webElement.getText().contains(name);
			}
		});
	}
}
