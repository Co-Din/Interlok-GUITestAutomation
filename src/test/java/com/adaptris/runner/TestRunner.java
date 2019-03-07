package com.adaptris.runner;

import com.adaptris.stepdefs.GUIDetailsMatcher;
import com.adaptris.stepdefs.GUIDetailsMatcher.*;
import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
        tags = "@FunctionalTest",
        features = "src/test/resources/com/adaptris/features/",
        glue = { "com/adaptris/stepdefs/" },
        plugin = { "json:target/htmlreports/cucumber.json" }
)

public class TestRunner {



}