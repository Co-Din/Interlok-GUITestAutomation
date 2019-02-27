package com.adaptris.stepdefs;

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

public class FailedLoginDefinition {

    public static WebDriver driver;

    public void driverInit() {
        //	For FireFox driver uncomment below
//	System.setProperty("webdriver.gecko.driver", "C:\\Workspace\\ProjectHome\\selenium\\geckodriver-v0.24.0-win64\\geckodriver.exe");

//	For Chrome leave the below uncommented, significantly fewer bugs.
        System.setProperty("webdriver.chrome.driver", "C:\\Workspace\\ProjectHome\\selenium\\chromedriver_win32\\chromedriver.exe");

//	For FireFox driver uncomment below
//	driver = new FirefoxDriver();

//	For Chrome leave the below uncommented, significantly fewer bugs.
        driver = new ChromeDriver();

//	Opens page in full size and waits 10 secs before t-out
        driver.get("http://localhost:8080/interlok/login.html");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Given("^on a login page$")
    public void given_on_a_login() {
        driverInit();
    }

    @When("^the user enters an incorrect username or password$")
    public void the_user_enters_an_incorrect_username_or_password() {
        driver.findElement(By.name("username")).sendKeys("Foo");
        driver.findElement(By.name("password")).sendKeys("Bar");
        driver.findElement(By.xpath("/html/body/section/div[2]/div[2]/section/div[2]/div/div/form/fieldset/div/div[3]/div/div[1]/button")).click();
    }

    @Then("^the user reaches the failed login page$")
    public void the_user_reaches_the_failed_login_page() throws Throwable {
        String ExpectedHeader = "Failed to authenticate user";
        Assert.assertEquals(ExpectedHeader, driver.findElement(By.xpath("//*[@id=\"login_container\"]/div[1]/div/section/div/p")).getText());
        Assert.assertEquals("http://localhost:8080/interlok/login.html?failed=", driver.getCurrentUrl());
        driver.quit();
    }

}

