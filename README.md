![Interlok](https://img.shields.io/github/tag/adaptris/interlok-filesystem.svg) 
![Maven Central](https://img.shields.io/maven-central/v/org.apache.maven/apache-maven.svg?label=Maven%20Central)
![](https://img.shields.io/github/languages/top/Co-Din/ASaladOfAutomation.svg)
# __A Salad Of Automation__

## Outline

This project has been designed and implemented to learn the basics of Cucumber, Gherkin & Selenium in order to test the 'Interlok' GUI. 
The initial test plan to be followed can be found at this URL: 
__[Interlok UI Test Plan Documents](https://reedelsevier.sharepoint.com/sites/OG-RBIAdaptris/Shared%20Documents/Forms/AllItems.aspx?id=%2Fsites%2FOG%2DRBIAdaptris%2FShared%20Documents%2FInterlok%20UI%20Test%20Plan%20Documents)__
which requires appropriate credentials for access.
The documents found at this site are __authored__ by __[HiggyFella](https://github.com/higgyfella)__.
<br />
***
***

## Tech Stack


*_This Project was developed on Windows 10 using the [Intellij IDE](https://www.jetbrains.com/idea/download/#section=windows) and the following tech; <br />_*
* *[Apache Maven 3.6.0](https://maven.apache.org/download.cgi)*<br />
* *[Chrome Driver](http://chromedriver.chromium.org/)*<br />
* *[Cluecumber](https://github.com/trivago/cluecumber-report-plugin)*<br />
* *[Cucumber](https://cucumber.io/)*<br />
* *[Cucumber Github](https://github.com/cucumber/cucumber)*<br />
* *[Interlok 3.8.3](https://development.adaptris.net/installers/Interlok/3.8.3/)*<br />
* *[Java 8 Open JDK 64-bit](https://www.azul.com/downloads/zulu/zulu-windows/)*<br />
* *[Selenium](https://www.seleniumhq.org/)*<br />

<br />

## Summary
<br />

* __What is Selenium__
 <br /><p>
 Selenium is an open-source testing suite designed to automate the testing of both websites and web applications.
 It's compatible with a multitude of languages and is compatible with both Windows and Unix systems.
 It runs through a number of different web-drivers(Chrome, Firefox, Opera and the timeless classic IE) thanks to its Remote Control Server.
 Selenium has unfortunately not got its own reporting capabilities nor has it got the functionality to run 'Snapshot' or Image testing of it's own accord 
 however these issues are all addressed via the integration of tools such as 'Sikuli', 'Selenium Visual Difference' and 'Fighting Layout Bugs'
 for the graphical('Snapshot') testing and Jenkins, TestNG, Maven and J-unit for reporting and more accurate tests.
 </p>
  
* __What is Cucumber__
<br /><p>
According to IBM 
    > "Cucumber is a BDD framework designed to bridge the gap between business managers and software developers."
    
    It's main aim is to facilitate the writing of plain language code that is human readable and approachable to someone without a technical background.
    This is achieved through Cucumber's domain specific language 'Gherkin' and it's simple to understand commands which build '_Features_' and '_Scenarios_' 
    we'd like to test 
    using steps defined and called upon through the '_Given_, _When_, _Then_ & _And_' commands.
    
</p>

* __What is Gherkin, is it a translation tool?__
<br /><p>
Gherkin is the language used in the '.feature files'. It's only based on a simple set of commands covered in
the above section about Cucumber.
These commands are useless on their own and require a '_Step Definitions_' file known to Cucumber as the '_glue_'
This file is written in the programming language (in our case Java) and using Cucumber's annotations allows us to
develop the translation for simple sentences we'd like to see execute specific scripts:

> The below is the step definition written to allow us to search on our active page and 
confirm there is a blank error bar to begin with.

 ```
 @And("^sees the error bar is blank$")
 public void blank_errorBar() {
   Assert.assertTrue(driver.findElement(By.cssSelector(".alert-box")).isDisplayed());
   Assert.assertEquals("", driver.findElement(By.cssSelector(".alert-box")).getText());
 }
 ```
 
 > This is the techincal side, the non-technical/ human readable side that will be used in the feature file
 is whatever we place in the first set of brackets after the annotation so the feature file will contain a 
 simple command such as _"**And** sees the error bar is blank"_.
 
 The final piece of the puzzle is the '_Runner_' file this is where Cucumber is run from and options 
 are set so that Cucumber knows where in the project to look for the corresponding files as well as 
 which tests and plugins we would like to use while doing so.
 <br /> Example:
 ```
@RunWith(Cucumber.class)
@CucumberOptions(
        tags = "@FunctionalTests",
        features = "src/test/resources/com/adaptris/features/",
        glue = { "com/adaptris/stepdefs/" },
        plugin = { "json:src/resources/htmlreports/cucumber.json" }
)
 ```

</p>

* __* __Limitations__ *__
<br /><p>
    * Neither Cucumber nor Selenium have their own ability to perform snapshot testing
    * Selenium's web-drivers are strictly limited to the manner in which we target a web element and that in itself can prove finnicky. 
    CssSelectors and Xpaths were not always accessible to selenium and manual deletion and creation of the adapters' would change the elements path/location.
    * Support for Firefox's web driver in Selenium is now provided by third-party and flagged numerous bugs while running.
    * Selenium's wait function was not functional and numerous element need time to render before being interacted with, the fallback is Thread.sleep.
    * Cucumber's native reporting was over simplified even though it stored all the data from tests however this was improved by using Trivagos '_Cluecumber_' reporting tool.

</p>
    

<br />

## Related Reading Materials
<br />

* __[Gherkin Syntax](https://docs.cucumber.io/gherkin/)__
* __[Selenium cheatsheet for: CSS, DOM, and XPATH](https://www.red-gate.com/simple-talk/dotnet/.net-framework/xpath,-css,-dom-and-selenium-the-rosetta-stone/)__

<br />

* #### References

    * __[IBM: Automated testing with Selenium and Cucumber](https://www.ibm.com/developerworks/library/a-automating-ria/index.html)__
    * __[Wikipedia: Cucumber](https://en.wikipedia.org/wiki/Cucumber_\(software\))__
    * __[What is Selenium](https://www.edureka.co/blog/what-is-selenium/)__
<br />

***
## Project Developers

  * __[Co-Din](https://github.com/Co-Din)__
  <br />
  
***
  