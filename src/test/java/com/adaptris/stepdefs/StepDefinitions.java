package com.adaptris.stepdefs;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en_scouse.An;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
//For FireFox driver uncomment below
//import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

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

    // Given section

    @Given("^on a login page$")
    public void given_on_a_login() {
        driverInit();
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
    public void reaches_failed_login_page() throws Throwable {
        String ExpectedHeader = "Failed to authenticate user";
        Assert.assertEquals(ExpectedHeader, driver.findElement(By.xpath("//*[@id=\"login_container\"]/div[1]/div/section/div/p")).getText());
        Assert.assertEquals("http://localhost:8080/interlok/login.html?failed=", driver.getCurrentUrl());

    }

    @Then("^the user reaches Dashboard$")
    public void reaches_Dashboard() throws Throwable {
        String ExpectedHeader = "Interlok Dashboard";
        Assert.assertEquals(ExpectedHeader, driver.findElement(By.xpath("/html/body/section/div[1]/h2")).getText());

    }

    @Then("^sees the auto discovered adapter$")
    public void auto_discovered_adapter() throws InterruptedException {
        Assert.assertEquals("http://localhost:8080/interlok/dashboard/dashboard.html", driver.getCurrentUrl());
    }

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

    @And("sees {int} channels")
    public void sees_channels(int channels) throws InterruptedException {

        Thread.sleep(2000);
        Assert.assertEquals(Integer.toString(channels), driver.findElement(By.cssSelector("#channels-state-gauge_1 > svg:nth-child(1) > text:nth-child(5) > tspan:first-child")).getText());
    }

    @After
    public void after(Scenario scenario){
        driver.quit();
    }

}
