/*
 * Copyright 2019 Adaptris Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.adaptris.runner;

import com.adaptris.stepdefs.GUIDirectoryTools;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

import java.io.IOException;

import static com.adaptris.stepdefs.ENVVAR.ADAPTER_DB_DIRECTORY;

//TODO Update compatibility with Linux
//TODO Create Generic ENVVAR as template
//TODO Update Linux Docs
//TODO Add configs to resources' folder
//TODO Refactor strings from StepDefinitions 'Welcome Page' function

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
    Thread.sleep(45000);
  }

}
