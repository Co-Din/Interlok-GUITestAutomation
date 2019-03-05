package com.adaptris.stepdefs;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//For FireFox driver uncomment below
//import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.springframework.boot.test.context.assertj.AssertableReactiveWebApplicationContext;

public class StepDefinitions {

    public static WebDriver driver;

    public void driverInit() {
//	For FireFox driver uncomment below
//	    System.setProperty("webdriver.gecko.driver", "C:\\Workspace\\ProjectHome\\selenium\\geckodriver-v0.24.0-win64\\geckodriver.exe");

//	For Chrome leave the below uncommented, significantly fewer bugs.
        System.setProperty("webdriver.chrome.driver", "C:\\Workspace\\ProjectHome\\selenium\\chromedriver_win32\\chromedriver.exe");

//	For FireFox driver uncomment below
//	    driver = new FirefoxDriver();

//	For Chrome leave the below uncommented, significantly fewer bugs.
        driver = new ChromeDriver();

//	Opens page in full size and waits 10 secs before t-out
        driver.get("http://localhost:8080/interlok/login.html");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Before
    public void before(Scenario scenario) {
        driverInit();
    }

    @After
    public void after(Scenario scenario) {
        driver.quit();
    }

    // Given section

    @Given("^on a login page$")
    public void given_on_a_login() {
        Assert.assertEquals("http://localhost:8080/interlok/login.html", driver.getCurrentUrl());
    }

    // When section

    @When("^the user enters username and password$")
    public void enters_username_and_password() {
        driver.findElement(By.name("username")).sendKeys(EnvVar.USERNAME);
        driver.findElement(By.name("password")).sendKeys(EnvVar.PASSWORD);
        driver.findElement(By.xpath("/html/body/section/div[2]/div[2]/section/div[2]/div/div/form/fieldset/div/div[3]/div/div[1]/button")).click();
    }

    @When("^the user enters an incorrect username or password$")
    public void enters_an_incorrect_username_or_password() {
        driver.findElement(By.name("username")).sendKeys("Foo");
        driver.findElement(By.name("password")).sendKeys("Bar");
        driver.findElement(By.xpath("/html/body/section/div[2]/div[2]/section/div[2]/div/div/form/fieldset/div/div[3]/div/div[1]/button")).click();
    }

    // Then Section

    @Then("^the user reaches the failed login page$")
    public void reaches_failed_login_page() {
        String ExpectedHeader = "Failed to authenticate user";
        Assert.assertEquals(ExpectedHeader, driver.findElement(By.xpath("//*[@id=\"login_container\"]/div[1]/div/section/div/p")).getText());
        Assert.assertEquals("http://localhost:8080/interlok/login.html?failed=", driver.getCurrentUrl());

    }

    @Then("^the user is on the Dashboard$")
    public void reaches_Dashboard() {
        Assert.assertEquals("http://localhost:8080/interlok/dashboard/dashboard.html", driver.getCurrentUrl());
    }

    @Then("^sees the auto discovered adapter$")
    public void auto_discovered_adapter() {
        Assert.assertTrue(driver.findElement(By.cssSelector(".panel-adapter")).isDisplayed());
    }

    @Then("^clicks the stop button$")
    public void controlBar_stopButton_click() {
        driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[1]/div/div[2]/div")).click();
    }

    @Then("^clicks the start button$")
    public void controlBar_startButton_click() {
        driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[1]/div/div[2]/button[3]")).click();
    }

    @Then("^clicks the pause button$")
    public void controlBar_pauseButton_click() {
        driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[1]/div/div[2]/button[4]")).click();
    }

    @Then("^clicks the control bar drop down menu$")
    public void controlBar_dropDown_click() {
        driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[1]/div/div[2]/div/button[2]")).click();
    }

    @Then("^clicks the 'Force-Stop' button$")
    public void controlBar_forceStopButton_click() {
        driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[1]/div/div[2]/div/ul/li[1]/a")).click();
    }

    // And Section

    @And("^sees the adapters unique id$")
    public void adapters_unique_id() {
        Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"adapter-data-areas\"]/div[1]/div/div[1]/h4/span[2]")).getText().contains("Local Adapter - "));
    }

    @And("^sees the JMX URL address$")
    public void JMX_URL_address() {
        Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[1]/h4/span[4]/span")).getText().contains("service:jmx:jmxmp://"));

    }

    @And("^sees the adapter in 'Started' state$")
    public void adapter_in_started_state() {
        Assert.assertTrue(driver.findElement(By.id("container-state-label")).getText().contains("STARTED"));
    }

    @And("^sees a check icon$")
    public void check_icon() {
        Assert.assertEquals("fa fa-check text-success", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[3]/div/div[1]/div[1]/div/div[1]/span/span/span/i")).getAttribute("class"));

    }

    @And("^sees (.*) started channels$")
    public void sees_started_channels(String channels) throws InterruptedException {
        //Element refreshes and requires a wait
        Thread.sleep(2000);
        //Cannot select via xpath/ changes name each time a new adapter is created
        Assert.assertEquals(channels, driver.findElement(By.cssSelector("#channels-state-gauge_401 > svg:nth-child(1) > text:nth-child(5) > tspan:first-child")).getText());
    }

    @And("^sees the total number of (.*) adapter channels expected$")
    public void total_adapted_channels(String channels) throws InterruptedException {
        Thread.sleep(1000);
        Assert.assertEquals(channels, driver.findElement(By.cssSelector("#channels-state-gauge_401 > svg:nth-child(1) > text:nth-child(8) > tspan:first-child")).getText());
    }

    @And("^sees the Up Time section$")
    public void upTime_container() {
        Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[3]/div/div[1]/div[6]/div/div[1]/div")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[3]/div/div[1]/div[6]/div/div[1]/div")).getText().contains("Up Time"));
    }

    @And("^sees the Last Started section$")
    public void lastStarted_container() {
        Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[3]/div/div[1]/div[6]/div/div[2]/div")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[3]/div/div[1]/div[6]/div/div[2]/div")).getText().contains("Last Started"));

    }

    @And("^sees UI version (.*)$")
    public void ui_version(String uiVersion) {
        Assert.assertEquals(uiVersion, driver.findElement(By.xpath("/html/body/footer/span/span[1]/a")).getText());
    }

     /* @And("^sees correct copyright date (.*)$")
        public void correct_copyright_date(String date) throws InterruptedException {
          Thread.sleep(2000);
          Assert.assertEquals(date, driver.findElement(By.cssSelector("span.pull-left > span:nth-child(5) > a:nth-child(1) span:only-child")).getText());
        }
    */

     @And("^sees (.*) failed messages$")
     public void failed_messages(String failedMessages) {
         Assert.assertEquals(failedMessages, driver.findElement(By.cssSelector("div.container-info-box:nth-child(4) > div:nth-child(1) > div:nth-child(1) > span:nth-child(1) > span:nth-child(2)")).getText());
     }

    @And("^sees (.*) in-flight messages$")
    public void inFlight_messages(String msgsInFlight) {
        Assert.assertEquals(msgsInFlight, driver.findElement(By.cssSelector(".badge-info")).getText());
    }

    @And("^sees the table mode button$")
    public void table_mode_btn() {
        Assert.assertTrue(driver.findElement(By.cssSelector(".col-md-12 > div:nth-child(1) > button:nth-child(1)")).isDisplayed());
    }

    @And("^sees the add adapter button$")
    public void add_adapter_btn() {
        Assert.assertTrue(driver.findElement(By.id("control-add-btn")).isDisplayed());
    }

    @And("^sees the 'Refresh' button$")
    public void refresh_adapter_btn() {
        Assert.assertTrue(driver.findElement(By.id("control-reload-btn")).isDisplayed());
    }

    @And("^sees the control bar$")
    public void adapter_controlBar() {
        Assert.assertTrue(driver.findElement(By.id("adapter-control-panel")).isDisplayed());
        }

    @And("^sees the control-bar buttons$")
    public void controlBar_btns() {
        Assert.assertTrue(driver.findElement(By.id("show-channel-label")).isDisplayed());
        Assert.assertEquals("fa fa-fw fa-square-o", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[1]/div/div[1]/label/i[2]")).getAttribute("class"));
        Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[1]/div/div[2]/button[1]")).isDisplayed());
        Assert.assertEquals("fa fa-info-circle", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[1]/div/div[2]/button[1]/i")).getAttribute("class"));
        Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[1]/div/div[2]/button[2]")).isDisplayed());
        Assert.assertEquals("fa fa-code", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[1]/div/div[2]/button[2]/i")).getAttribute("class"));
        Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[1]/div/div[2]/a")).isDisplayed());
        Assert.assertEquals("fa fa-wrench", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[1]/div/div[2]/a/i")).getAttribute("class"));
        Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[1]/div/div[2]/button[3]")).isDisplayed());
        Assert.assertEquals("fa fa-play", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[1]/div/div[2]/button[3]/i")).getAttribute("class"));
        Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[1]/div/div[2]/button[4]")).isDisplayed());
        Assert.assertEquals("fa fa-pause", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[1]/div/div[2]/button[4]/i")).getAttribute("class"));
        Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[1]/div/div[2]/div/button[1]")).isDisplayed());
        Assert.assertEquals("fa fa-stop", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[1]/div/div[2]/div/button[1]/i")).getAttribute("class"));
        Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[1]/div/div[2]/div/button[2]")).isDisplayed());
        Assert.assertEquals("caret", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[1]/div/div[2]/div/button[2]/span")).getAttribute("class"));
        Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[1]/div/div[2]/button[5]")).isDisplayed());
        Assert.assertEquals("fa fa-refresh", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[1]/div/div[2]/button[5]/i")).getAttribute("class"));
        Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[1]/div/div[2]/button[6]")).isDisplayed());
        Assert.assertEquals("fa fa-times", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[1]/div/div[2]/button[6]/i")).getAttribute("class"));

        //Not displayed as dropdown isn't clicked
        Assert.assertFalse(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[1]/div/div[2]/div/ul/li[1]/a")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[1]/div/div[2]/div/ul/li[2]/a")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[1]/div/div[2]/div/ul/li[3]/a")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[1]/div/div[2]/div/ul/li[5]/a")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[1]/div/div[2]/div/ul/li[6]/a")).isDisplayed());

    }

    @And("^sees the error bar is blank$")
    public void blank_errorBar() {
         Assert.assertTrue(driver.findElement(By.cssSelector(".alert-box")).isDisplayed());
         Assert.assertEquals("", driver.findElement(By.cssSelector(".alert-box")).getText());
    }

    @And("^sees the (.*) label$")
    public void page_label(String pageLabel) {
        Assert.assertEquals(pageLabel, driver.findElement(By.xpath("/html/body/section/div[1]/h2")).getText());
    }

    @And("^sees the Down Time section$")
    public void downTime_container() {
        Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[3]/div/div[1]/div[7]/div/div[1]/div")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[3]/div/div[1]/div[7]/div/div[1]/div")).getText().contains("Down Time"));
    }

    @And("^sees the Last Stopped section$")
    public void lastStopped_container() {
        Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[3]/div/div[1]/div[7]/div/div[2]/div")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[3]/div/div[1]/div[7]/div/div[2]/div")).getText().contains("Last Stopped"));
    }

    @And("^sees the dropdown menu buttons and icons$")
    public void controlBar_dropDownMenu_btns_icons() {
        Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[1]/div/div[2]/div/ul/li[1]/a")).isDisplayed());
        Assert.assertEquals("fa fa-stop", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[1]/div/div[2]/div/ul/li[1]/a/i")).getAttribute("class"));
        Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[1]/div/div[2]/div/ul/li[2]/a")).isDisplayed());
        Assert.assertEquals("fa fa-recycle", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[1]/div/div[2]/div/ul/li[2]/a/i")).getAttribute("class"));
        Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[1]/div/div[2]/div/ul/li[3]/a")).isDisplayed());
        Assert.assertEquals("fa fa-bolt", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[1]/div/div[2]/div/ul/li[3]/a/i")).getAttribute("class"));
        Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[1]/div/div[2]/div/ul/li[5]/a")).isDisplayed());
        Assert.assertEquals("fa fa-life-ring", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[1]/div/div[2]/div/ul/li[5]/a/i")).getAttribute("class"));
        Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[1]/div/div[2]/div/ul/li[6]/a")).isDisplayed());
        Assert.assertEquals("fa fa-pencil-square-o", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[1]/div/div[2]/div/ul/li[6]/a/i")).getAttribute("class"));
    }

}
