package com.adaptris.runner;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
        tags = "@FunctionalTest",
        features = "src/test/resources/com/adaptris/features/",
        glue = {"com/adaptris/stepdefs/"},
        plugin = { "pretty", "html:target/htmlreports" }
)

public class testrunner {

}

