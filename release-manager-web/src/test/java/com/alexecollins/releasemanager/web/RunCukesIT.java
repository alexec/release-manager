package com.alexecollins.releasemanager.web;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(glue = "com.alexecollins.releasemanager.web",
		format = {"html:target/cucumber-html-report", "json:target/cucumber-json-report.json"},
		tags = {"~@ignore"})
public class RunCukesIT {
}
