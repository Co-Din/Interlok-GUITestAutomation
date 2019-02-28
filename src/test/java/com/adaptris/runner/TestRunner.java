package com.adaptris.runner;

import com.cucumber.listener.Reporter;
import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.testng.annotations.AfterClass;

import java.io.File;

@RunWith(Cucumber.class)
@CucumberOptions(
        tags = "@FunctionalTest",
        features = "src/test/resources/com/adaptris/features/",
        glue = { "com/adaptris/stepdefs/" },
        plugin = { "json:target/htmlreports/cucumber.json" }
)

public class TestRunner {

}
