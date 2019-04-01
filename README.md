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

## Setup

<br />

   * #### Setup a compatible version of a Java 8 Open JDK
   
     * Begin by opening your command terminal and entering the command **`java -version`** <br />
       This should either return that you have no version of Java installed or any version that you have installed. <br />
     * If this has returned a version which is not Java 8 and you are running Windows I highly recommend removing the other version and starting with a fresh installation the same goes for any Oracle copies of Java 8. <br />
     * Follow the steps provided install install **Zulu** the **[Azul Open JDK](https://www.azul.com/downloads/zulu)**.
     
     * ##### Windows
       
        Following the download of your JDK you'll need to supply windows a JAVA_HOME and a JAVA_PATH <br />
        To do so open your file browser and navigate to: **`Control Panel\System and Security\System`** using the address bar. <br />
        Once there select the **'Advanced system settings'** which should open a separate window _(System Properties)_ on the 'Advanced' tab. <br />
        Select the 'Environmental Variables...' button at the bottom right hand of the window. This should open yet another window for your 'Environment Variables' you'll then want to do the following in the **'System Variables'** Segment. <br />
        Double click on the **Path** variable and then select 'new' and enter the location of your Java JDK i.e **`C:\Java\zulu8.33.0.1-jdk8.0.192-win_x64\bin`** making sure to include the 'bin' in the location and then dismiss the window by hitting the 'OK' button. <br />
        Then click on the **New...** button in the **'System Variables'** segment and in the 'Variable Name' input `JAVA_HOME` and the JDKs location as the 'Variable Value'(_this time without 'bin'_). <br /> 
        Finally we need to confirm that our installation has worked. Open the command terminal and once again enter the command **`java -version`** this should return the Java version you've installed or **`Java is not recognized as an internal or external command`** if the latter is the case please scroll to the bottom of this guide and follow the links supplied. <br />
                     
     * ##### Mac
     
        For Mac OSs start by running the command **`java -version`** to see if a Java JDK is already installed if one is you should still install the 'Azul Zulu' JDK and then switch to that JDK version. <br />
        To switch to the 'Zulu JDK' ope you command terminal and run the following **`/usr/libexec/java_home -V`** this should show you which Java versions you have installed. <br />
        Then you can select which version of Java you want using: <br />
        **`export JAVA_HOME='/usr/libexec/java_home -v *Replace with Java Version Here*'`** this needn't be the exact version it can be a major version. <br />
        Finally confirm which version is running with **`java -version`** <br />
                
     * ##### Linux
        
        For the Linux start by opening the command terminal and running the command **`sudo apt update`** then once this has completed **`java -version`** to confirm whether or not there is a version of Java already installed. <br />
        If one has already been installed and is not an open copy of the Java 8 JDK install the following JDK and then switch the version default. <br />
        Run the **`sudo apt-get install zulu-8`** command to install the JDK finally check the version by running the command **`java -version`** if it is not the openJDK you can switch using the following: <br />
        **`sudo update-alternatives --display java`** shows you which alternatives are available and then **`sudo update-alternatives --config java`** which should give you the JDK options to select from. <br />
        Finally confirm that the correct version of Java is being used by running the command **`java -version`**. <br />
         
   <br />
    
   * #### Install one of the following IDEs following the instructions provided on their sites:
     *(Both are compatible with Linux, Mac & Windows)*
     * Intellij: [Download IntelliJ IDEA](https://www.jetbrains.com/idea/download/)
     * Eclipse: [Eclipse IDE](https://www.eclipse.org/downloads/)
     
   <br />
        
   * #### Install Maven
    
        To run this project we also need to install **[Apache Maven](https://maven.apache.org/download.cgi)**. <br />
        Start by opening your command terminal and running **`mvn -version`** this should either return a version which may have been already installed or **`mvn is not recognized as an internal or external command`**. <br />
        Download the appropriate format of 'Apache Maven 3.6.0' and then follow the instructions below for installation and config. <br />
        Once downloaded extract the Zip or Tar.gz file to its final destination. <br />
   
   * ##### Windows
        
        After finishing the extraction open your file browser and navigate to: **`Control Panel\System and Security\System`** using the address bar. <br />
        Once there select the **'Advanced system settings'** which should open a separate window _(System Properties)_ on the 'Advanced' tab. <br />
        Select the 'Environmental Variables...' button at the bottom right hand of the window. This should open yet another window for your 'Environment Variables' you'll then want to do the following in the **'System Variables'** Segment. <br />
        Double click on the **Path** variable and then select 'new' and enter the location of your Apache Maven i.e **`C:\Programs\Apache\Maven\apache-maven-3.6.0\bin`** making sure to include the 'bin' in the location and then dismiss the window by hitting the 'OK' button. <br />
        Then click on the **New...** button in the **'System Variables'** segment and in the 'Variable Name' input `M2_HOME` and Maven's location as the 'Variable Value'(_this time without 'bin'_). <br />
        Repeat the above step and create another **'System Variables'** this time with the 'Variable Name' `M2` and yet again Maven's location as the 'Variable Value' i.e **`C:\Programs\Apache\Maven\apache-maven-3.6.0`**. <br />
        Finally check the setup has succeeded by running **`mvn -version`** this should now return the 'Maven' version and its location. <br />
   
   * ##### Mac
   
        Open the command terminal and navigate to where the files were extracted to and then login as Super – User.<br />
        Run **`rm Downloads/apache-maven*bin.tar.gz`** and then ammend the permissions: **`chown -R root:wheel Downloads/apache-maven*`**. <br /> 
        Switch the Maven contents: **`mv Downloads/apache-maven* /opt/apache-maven`** and archive the Admin session by running: **`exit`** <br /> 
        Add Maven binaries to the path and append using **`nano $HOME/.profile`** and then **`export PATH=$PATH:/opt/apache-maven/bin`** then hit **`Ctrl+x`** to **Save and Exit from 'nano'*. <br />
        Finally check the setup has been successfully completed by running **`mvn -version`** this should now return the 'Maven' version and its location. <br />
   
   * ##### Linux


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

### Java JDK
  > ##### Azul Docs
   * __[Azul Installation Guide](https://docs.azul.com/zulu/zuludocs/)__

  > ##### Windows
   * __[Setting the JAVA_HOME Variable in Windows](https://confluence.atlassian.com/conf60/setting-the-java_home-variable-in-windows-852732596.html)__
   * __[How to set JAVA_HOME on Windows 10](https://www.mkyong.com/java/how-to-set-java_home-on-windows-10/)__
   * __[Java Not Recognized StackOverflow](https://stackoverflow.com/questions/15796855/java-is-not-recognized-as-an-internal-or-external-command)__
   
   > ##### Unix systems
   * __[Setting JAVA_HOME](https://confluence.atlassian.com/crowd030beta/setting-java_home-907281613.html)__
   * __[How to set or change the default Java (JDK) version on OS X?](https://stackoverflow.com/questions/21964709/how-to-set-or-change-the-default-java-jdk-version-on-os-x)__
   * __[How To Install Java with 'apt'](https://www.digitalocean.com/community/tutorials/how-to-install-java-with-apt-on-ubuntu-18-04)__
   * __[How to change default Java version on Linux](http://ask.xmodulo.com/change-default-java-version-linux.html)__
<br />

### Maven

   * __[How to install Maven on Windows, Linux, Mac](https://www.baeldung.com/install-maven-on-windows-linux-mac)__

### Syntactic Cheat Sheets
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
  