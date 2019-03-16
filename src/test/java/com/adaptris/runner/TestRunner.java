package com.adaptris.runner;

import com.adaptris.stepdefs.GUIDirectoryTools;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

import java.io.IOException;

import static com.adaptris.stepdefs.EnvVar.ADAPTER_DB_DIRECTORY;

@RunWith(Cucumber.class)
@CucumberOptions(
        tags = "@FunctionalTests",
        features = "src/test/resources/com/adaptris/features/",
        glue = { "com/adaptris/stepdefs/" },
        plugin = { "json:src/resources/htmlreports/cucumber.json" }
)

public class TestRunner {

  @BeforeClass
  public static void setup() throws IOException, InterruptedException {
    GUIDirectoryTools.adapterIdReset(ADAPTER_DB_DIRECTORY);
    Thread.sleep(2000);
    GUIDirectoryTools.InterlokBoot();
    Thread.sleep(40000);
  }

}
