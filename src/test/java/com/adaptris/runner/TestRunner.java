package com.adaptris.runner;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
        tags = "@FunctionalTests",
        features = "src/test/resources/com/adaptris/features/",
        glue = { "com/adaptris/stepdefs/" },
        plugin = { "json:src/resources/htmlreports/cucumber.json" }
)

public class TestRunner {



}