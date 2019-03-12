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

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;

import static com.adaptris.stepdefs.EnvVar.*;

public class StepDefinitions {

    public static WebDriver driver;
    private String configAddress = "interlok/config/config.html";

    public void driverInit(String domainUrl) {
//	For FireFox driver uncomment below
//	    System.setProperty("webdriver.gecko.driver", "src/resources/geckodriver.exe");

//	For Chrome leave the below uncommented, significantly fewer bugs.
        System.setProperty("webdriver.chrome.driver", "src/resources/chromedriver.exe");

//	For FireFox driver uncomment below
//	    driver = new FirefoxDriver();

//	For Chrome leave the below uncommented, significantly fewer bugs.
        driver = new ChromeDriver();

//	Opens page in full size and waits 10 secs before t-out
        driver.get(domainUrl);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }



    @Before
    public void before(Scenario scenario) {
      driverInit(LOGIN_PAGE);
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
    public void controlBar_stopBtn_click() {
        driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[1]/div/div[2]/div")).click();
    }

    @Then("^clicks the start button$")
    public void controlBar_startBtn_click() {
        driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[1]/div/div[2]/button[3]")).click();
    }

    @Then("^clicks the pause button$")
    public void controlBar_pauseBtn_click() {
        driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[1]/div/div[2]/button[4]")).click();
    }

    @Then("^clicks the control bar drop down menu$")
    public void controlBar_dropDown_click() {
        driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[1]/div/div[2]/div/button[2]")).click();
    }

    @Then("^clicks the 'Force-Stop' button$")
    public void controlBar_forceStopBtn_click() {
        driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[1]/div/div[2]/div/ul/li[1]/a")).click();
    }

    @Then("^clicks the information button$")
    public void controlBar_infoBtn() {
        driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[1]/div/div[2]/button[1]")).click();
    }

    @Then("^clicks the close button to shut the 'Adapter Information' modal$")
    public void infoModal_shut() throws InterruptedException {
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/div[5]/div/div/div[3]/button")).click();
    }

    @Then("^clicks the dismiss button to shut the 'Adapter Information' modal$")
    public void infoModal_dismiss() throws InterruptedException {
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/div[5]/div/div/div[1]/a")).click();
    }

    @Then("^navigates to the config page$")
    public void nav_toConfigPage() throws InterruptedException {
        Thread.sleep(2000);
        driver.findElement(By.xpath("/html/body/header/div[2]/ul/li[3]/a[1]")).click();
    }

    @Then("^clicks the 'Open Config' button$")
    public void openConfig_btn_click() {
        driver.findElement(By.xpath("/html/body/section/div[2]/section[5]/div[1]/div[2]/div/div[1]/button[1]")).click();
    }

    @Then("^clicks the 'Saved Project' tile$")
    public void savedProject_click() {
        driver.findElement(By.xpath("//*[@id=\"open-config\"]/div/div[2]/div[1]/div[4]/div")).click();
    }

    @Then("^selects the 'config-002-generates-dumb-data-with-fs' config$")
    public void selects_config() throws InterruptedException {
        Thread.sleep(2000);
        driver.findElement(By.xpath("/html/body/div[3]/div/div/div[2]/div[1]/div/div[3]/div[3]/div[4]/div/a[2]")).click();

    }

    @Then("^selects option (.*) in 'Adapter Instance' in the 'Active Adapters' modal to apply the config to$")
    public void select_adapterInstance(String option) {
        String selectOption = String.format("/html/body/div[5]/div/div/div[2]/div[2]/select/option[text() = '%s']", option);
        driver.findElement(By.xpath("/html/body/div[5]/div/div/div[2]/div[2]/select")).click();
        driver.findElement(By.xpath(selectOption)).click();

    }

    @Then("^selects the (.*) 'Variable-Set' in the 'Active Adapters' modal to apply with the config$")
    public void select_variableSet(String option) {
        String selectOption = String.format("/html/body/div[5]/div/div/div[2]/div[3]/div/div/div[2]/div[1]/select/option[text() = '%s']", option);
        driver.findElement(By.xpath("/html/body/div[5]/div/div/div[2]/div[3]/div/div/div[2]/div[1]/select")).click();
        driver.findElement(By.xpath(selectOption)).click();

    }

    @Then("^clicks Ok to apply the config$")
    public void clicks_ok_to_applyConfig() {
        driver.findElement(By.xpath("/html/body/div[5]/div/div/div[3]/div/button[2]")).click();
    }


    // And Section

    @And("^sees the adapters unique id$")
    public void adapters_unique_id() throws IOException, SAXException, ParserConfigurationException {
        Assert.assertEquals(" " + GUIDetailsMatcher.searchFileDetails(ADAPTRIS_INSTANCE_PATH, "adapter.xml", "unique-id"), driver.findElement(By.xpath("//*[@id=\"adapter-data-areas\"]/div[1]/div/div[1]/h4/span[2]")).getText());
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
        Assert.assertEquals(channels, driver.findElement(By.cssSelector("#channels-state-gauge_504 > svg:nth-child(1) > text:nth-child(5) > tspan:first-child")).getText());
    }

    @And("^sees the total number of (.*) adapter channels expected$")
    public void total_adapted_channels(String channels) throws InterruptedException {
        Thread.sleep(1000);
        Assert.assertEquals(channels, driver.findElement(By.cssSelector("#channels-state-gauge_504 > svg:nth-child(1) > text:nth-child(8) > tspan:first-child")).getText());
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
     public void failed_msgs(String failedMessages) {
         Assert.assertEquals(failedMessages, driver.findElement(By.cssSelector("div.container-info-box:nth-child(4) > div:nth-child(1) > div:nth-child(1) > span:nth-child(1) > span:nth-child(2)")).getText());
     }

    @And("^sees (.*) in-flight messages$")
    public void inFlight_msgs(String msgsInFlight) {
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

    @And("^sees the 'Adapter Information' modal$")
    public void adapterInfo_modal() throws InterruptedException {
         Thread.sleep(2000);
         Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[5]/div")).isDisplayed());
    }

    @And("^sees the Adapter details$")
    public void adapterInfo_modal_details() throws IOException, SAXException, ParserConfigurationException {
      Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[5]/div/div/div[1]/h3")).isDisplayed());
      Assert.assertEquals(" Adapter Information", driver.findElement(By.xpath("/html/body/div[5]/div/div/div[1]/h3")).getText());
      Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[5]/div/div/div[1]/h3/i")).isDisplayed());
      Assert.assertEquals("fa fa-info-circle", driver.findElement(By.xpath("/html/body/div[5]/div/div/div[1]/h3/i")).getAttribute("class"));

      Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[5]/div/div/div[2]/div/table/tbody/tr[1]/td[1]/span/b")).isDisplayed());
      Assert.assertEquals("Adapter Unique Id:", driver.findElement(By.xpath("/html/body/div[5]/div/div/div[2]/div/table/tbody/tr[1]/td[1]/span/b")).getText());
      Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[5]/div/div/div[2]/div/table/tbody/tr[1]/td[2]/span")).isDisplayed());
      Assert.assertEquals(GUIDetailsMatcher.searchFileDetails(ADAPTRIS_INSTANCE_PATH, "adapter.xml", "unique-id"), driver.findElement(By.xpath("/html/body/div[5]/div/div/div[2]/div/table/tbody/tr[1]/td[2]/span")).getText());

      Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[5]/div/div/div[2]/div/table/tbody/tr[2]/td[1]/span/b")).isDisplayed());
      Assert.assertEquals("Adapter Build Version:", driver.findElement(By.xpath("/html/body/div[5]/div/div/div[2]/div/table/tbody/tr[2]/td[1]/span/b")).getText());
//      Assert.assertTrue(driver.findElement(By.xpath("")).isDisplayed());
//      Assert.assertEquals(, driver.findElement(By.xpath("")).getText());

      Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[5]/div/div/div[2]/div/table/tbody/tr[3]/td[1]/span/b")).isDisplayed());
      Assert.assertEquals("Adapter Module Versions:", driver.findElement(By.xpath("/html/body/div[5]/div/div/div[2]/div/table/tbody/tr[3]/td[1]/span/b")).getText());
//      Assert.assertTrue(driver.findElement(By.xpath("")).isDisplayed());
//      Assert.assertEquals(, driver.findElement(By.xpath("")).getText());

      Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[5]/div/div/div[2]/div/table/tbody/tr[4]/td[1]/span/b")).isDisplayed());
      Assert.assertEquals("Java Version:", driver.findElement(By.xpath("/html/body/div[5]/div/div/div[2]/div/table/tbody/tr[4]/td[1]/span/b")).getText());
      Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[5]/div/div/div[2]/div/table/tbody/tr[4]/td[2]/span")).isDisplayed());
      Assert.assertEquals(System.getProperty("java.version"), driver.findElement(By.xpath("/html/body/div[5]/div/div/div[2]/div/table/tbody/tr[4]/td[2]/span")).getText());

      Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[5]/div/div/div[2]/div/table/tbody/tr[5]/td[1]/span/b")).isDisplayed());
      Assert.assertEquals("Java VM Vendor:", driver.findElement(By.xpath("/html/body/div[5]/div/div/div[2]/div/table/tbody/tr[5]/td[1]/span/b")).getText());
      Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[5]/div/div/div[2]/div/table/tbody/tr[5]/td[2]/span")).isDisplayed());
      Assert.assertEquals(System.getProperty("java.vm.vendor"), driver.findElement(By.xpath("/html/body/div[5]/div/div/div[2]/div/table/tbody/tr[5]/td[2]/span")).getText());

      Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[5]/div/div/div[2]/div/table/tbody/tr[6]/td[1]/span/b")).isDisplayed());
      Assert.assertEquals("Java VM Name:", driver.findElement(By.xpath("/html/body/div[5]/div/div/div[2]/div/table/tbody/tr[6]/td[1]/span/b")).getText());
      Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[5]/div/div/div[2]/div/table/tbody/tr[6]/td[2]/span")).isDisplayed());
      Assert.assertEquals(System.getProperty("java.vm.name"), driver.findElement(By.xpath("/html/body/div[5]/div/div/div[2]/div/table/tbody/tr[6]/td[2]/span")).getText());

      Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[5]/div/div/div[2]/div/table/tbody/tr[7]/td[1]/span/b")).isDisplayed());
      Assert.assertEquals("Java VM Runtime Arguments:", driver.findElement(By.xpath("/html/body/div[5]/div/div/div[2]/div/table/tbody/tr[7]/td[1]/span/b")).getText());
//      Assert.assertTrue(driver.findElement(By.xpath("")).isDisplayed());
//      Assert.assertEquals(, driver.findElement(By.xpath("")).getText());

      Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[5]/div/div/div[2]/div/table/tbody/tr[8]/td[1]/span/b")).isDisplayed());
      Assert.assertEquals("Operating System Architecture:", driver.findElement(By.xpath("/html/body/div[5]/div/div/div[2]/div/table/tbody/tr[8]/td[1]/span/b")).getText());
      Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[5]/div/div/div[2]/div/table/tbody/tr[8]/td[2]/span")).isDisplayed());
      Assert.assertEquals(System.getProperty("os.arch"), driver.findElement(By.xpath("/html/body/div[5]/div/div/div[2]/div/table/tbody/tr[8]/td[2]/span")).getText());

      Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[5]/div/div/div[2]/div/table/tbody/tr[9]/td[1]/span/b")).isDisplayed());
      Assert.assertEquals("Operating System Name:", driver.findElement(By.xpath("/html/body/div[5]/div/div/div[2]/div/table/tbody/tr[9]/td[1]/span/b")).getText());
      Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[5]/div/div/div[2]/div/table/tbody/tr[9]/td[2]/span")).isDisplayed());
      Assert.assertEquals(System.getProperty("os.name"), driver.findElement(By.xpath("/html/body/div[5]/div/div/div[2]/div/table/tbody/tr[9]/td[2]/span")).getText());

      Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[5]/div/div/div[2]/div/table/tbody/tr[10]/td[1]/span/b")).isDisplayed());
      Assert.assertEquals("Operating System Version:", driver.findElement(By.xpath("/html/body/div[5]/div/div/div[2]/div/table/tbody/tr[10]/td[1]/span/b")).getText());
      Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[5]/div/div/div[2]/div/table/tbody/tr[10]/td[2]/span")).isDisplayed());
      Assert.assertEquals(System.getProperty("os.version"), driver.findElement(By.xpath("/html/body/div[5]/div/div/div[2]/div/table/tbody/tr[10]/td[2]/span")).getText());

      Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[5]/div/div/div[2]/div/table/tbody/tr[11]/td[1]/span/b")).isDisplayed());
      Assert.assertEquals("Time Zone:", driver.findElement(By.xpath("/html/body/div[5]/div/div/div[2]/div/table/tbody/tr[11]/td[1]/span/b")).getText());
      Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[5]/div/div/div[2]/div/table/tbody/tr[11]/td[2]/span")).isDisplayed());
      Assert.assertEquals("Europe/London", driver.findElement(By.xpath("/html/body/div[5]/div/div/div[2]/div/table/tbody/tr[11]/td[2]/span")).getText());

      Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[5]/div/div/div[1]/a")).isDisplayed());
      Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[5]/div/div/div[3]/button")).isDisplayed());
      Assert.assertEquals("Close", driver.findElement(By.xpath("/html/body/div[5]/div/div/div[3]/button")).getText());

    }

    @And("^sees the 'Adapter Information' modal has been shut$")
    public void adapterInfo_modal_shut() throws InterruptedException {
        Thread.sleep(2000);
        Assert.assertFalse(driver.findElement(By.xpath("/html/body/div[5]/div")).isDisplayed());
    }

    @And("^reaches the config page$")
    public void reached_configPage(){
        Assert.assertTrue(driver.getCurrentUrl().contains(configAddress));
    }

    @And("^sees the Open config button$")
    public void openConfig_btn() throws InterruptedException {
        Thread.sleep(2000);
        Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[5]/div[1]/div[2]/div/div[1]/button[1]")).isDisplayed());
        Assert.assertEquals("Open config", driver.findElement(By.xpath("/html/body/section/div[2]/section[5]/div[1]/div[2]/div/div[1]/button[1]")).getText());
    }

    @And("^sees the 'Config Modal' and its options$")
    public void sees_openConfig_modal() throws InterruptedException {
         Thread.sleep(2000);
         Assert.assertTrue(driver.findElement(By.id("open-config")).isDisplayed());
         Assert.assertEquals("Open Interlok Container Config", driver.findElement(By.xpath("//*[@id=\"open-config\"]/div/div[1]/h3/span")).getText());

        //Active Adapter
        Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[1]")).isDisplayed());
        Assert.assertEquals("Active Adapter", driver.findElement(By.xpath("//*[@id=\"open-config\"]/div/div[2]/div[1]/div[1]/div/div[2]/h4")).getText());
        Assert.assertEquals("card-header card-img-top choice-card-img", driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[1]/div/div[1]")).getAttribute("class"));

        //New
        Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[2]")).isDisplayed());
        Assert.assertEquals("New", driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[2]/div/div[2]/h4")).getText());
        Assert.assertEquals("card-header card-img-top choice-card-img", driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[2]/div/div[1]")).getAttribute("class"));

        //File System
        Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]")).isDisplayed());
        Assert.assertEquals("File System", driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div/div[2]/h4")).getText());
        Assert.assertEquals("card-header card-img-top choice-card-img", driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div/div[1]")).getAttribute("class"));

        //Saved Project
        Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[4]")).isDisplayed());
        Assert.assertEquals("Saved Project", driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[4]/div/div[2]/h4")).getText());
        Assert.assertEquals("card-header card-img-top choice-card-img", driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[4]/div/div[1]")).getAttribute("class"));

        //Auto Saved
        Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[5]")).isDisplayed());
        Assert.assertEquals("Auto Saved", driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[5]/div/div[2]/h4")).getText());
        Assert.assertEquals("card-header card-img-top choice-card-img", driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[5]/div/div[1]")).getAttribute("class"));

        //Use Template
        Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[6]")).isDisplayed());
        Assert.assertEquals("Use Template", driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[6]/div/div[2]/h4")).getText());
        Assert.assertEquals("card-header card-img-top choice-card-img", driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[6]/div/div[1]")).getAttribute("class"));

        //Swagger
        Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[7]")).isDisplayed());
        Assert.assertEquals("Swagger", driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[7]/div/div[2]/h4")).getText());
        Assert.assertEquals("card-header card-img-top choice-card-img", driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[7]/div/div[1]")).getAttribute("class"));

        //Version Control
        Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[8]")).isDisplayed());
        Assert.assertEquals("Version Control", driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[8]/div/div[2]/h4")).getText());
        Assert.assertEquals("card-header card-img-top choice-card-img", driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[8]/div/div[1]")).getAttribute("class"));

    }

    @And("^sees the 'Interlok Container Saved Config Projects' modal$")
    public void interlok_savedConfig_projectsModal() throws InterruptedException {
         Thread.sleep(2000);

         Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"modal-list-configs-modal\"]")).isDisplayed());
         Assert.assertEquals("fa fa-th-list", driver.findElement(By.xpath("//*[@id=\"open-config\"]/div/div[1]/h3/i")).getAttribute("class"));

         Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[3]/div/div/div[1]/h3/span")).isDisplayed());
         Assert.assertEquals("Interlok Container Saved Config Projects", driver.findElement(By.xpath("/html/body/div[3]/div/div/div[1]/h3/span")).getText());

         Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[3]/div/div/div[1]/a")).isDisplayed());
         Assert.assertEquals("×", driver.findElement(By.xpath("/html/body/div[3]/div/div/div[1]/a")).getText());

         Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"upload-config\"]")).isDisplayed());
         Assert.assertEquals("fa fa-upload action-button-normal", driver.findElement(By.xpath("/html/body/div[3]/div/div/div[3]/a/i[1]")).getAttribute("class"));
         Assert.assertEquals("Upload", driver.findElement(By.xpath("/html/body/div[3]/div/div/div[3]/a/span")).getText());

        Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[3]/div/div/div[3]/button")).isDisplayed());
        Assert.assertEquals("Close", driver.findElement(By.xpath("/html/body/div[3]/div/div/div[3]/button")).getText());

    }

    @And("^sees the 'Active Adapters' modal$")
    public void activeAdapter_modal() throws InterruptedException {
        Thread.sleep(3000);

        String varInfo = " If you select a variable set, the Interlok UI will do the replacement before applying the XML. The Adapter variable substitution pre-processors is not used.\n" +
                            "If you don't select any variable set the default values from the configuration will be used.";

      //Modal appears
      Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[5]/div/div")).isDisplayed());

      //Active Adapter header and icon
      Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[5]/div/div/div[1]/h3/span")).isDisplayed());
      Assert.assertEquals("Active Adapters", driver.findElement(By.xpath("/html/body/div[5]/div/div/div[1]/h3/span")).getText());
      Assert.assertEquals("fa fa-adapter", driver.findElement(By.xpath("/html/body/div[5]/div/div/div[1]/h3/i")).getAttribute("class"));

      //Select an Adapter Instance and icons
      Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[5]/div/div/div[2]/div[2]/label")).isDisplayed());
      Assert.assertEquals(" Select an Adapter Instance", driver.findElement(By.xpath("/html/body/div[5]/div/div/div[2]/div[2]/label")).getText());
      Assert.assertEquals("fa fa-adapter", driver.findElement(By.xpath("/html/body/div[5]/div/div/div[2]/div[2]/label/i")).getAttribute("class"));
      Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[5]/div/div/div[2]/div[2]/select")).isDisplayed());
      Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[5]/div/div/div[2]/div[2]/select")).getText().contains("Select an Adapter Instance..."));
      Assert.assertEquals(" Select an adapter to apply the configuration to and click Ok to confirm.", driver.findElement(By.xpath("/html/body/div[5]/div/div/div[2]/p[1]")).getText());
      Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[5]/div/div/div[2]/p[1]/span")).isDisplayed());
      Assert.assertEquals("fa fa-info-circle", driver.findElement(By.xpath("/html/body/div[5]/div/div/div[2]/p[1]/span/i")).getAttribute("class"));

      //Variable Sets and icons
      Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[5]/div/div/div[2]/div[3]/label")).isDisplayed());
      Assert.assertEquals("Variable Sets - Optional", driver.findElement(By.xpath("/html/body/div[5]/div/div/div[2]/div[3]/label")).getText());

      Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[5]/div/div/div[2]/div[3]/div/div/div[2]/div[1]/select")).isDisplayed());
      Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[5]/div/div/div[2]/div[3]/div/div/div[2]/div[1]/select")).getText().contains("No Variable Set"));
      Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[5]/div/div/div[2]/div[3]/div/div/div[2]/div[1]/select")).getText().contains("default"));

      Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[5]/div/div/div[2]/p[3]/span/i")).isDisplayed());
      Assert.assertEquals("fa fa-info-circle", driver.findElement(By.xpath("/html/body/div[5]/div/div/div[2]/p[3]/span/i")).getAttribute("class"));
      Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[5]/div/div/div[2]/p[3]/span")).isDisplayed());
      Assert.assertEquals(varInfo, driver.findElement(By.xpath("/html/body/div[5]/div/div/div[2]/p[3]/span")).getText());

      //Cancel Button
      Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[5]/div/div/div[3]/div/button[1]")).isDisplayed());
      Assert.assertEquals(" Cancel", driver.findElement(By.xpath("/html/body/div[5]/div/div/div[3]/div/button[1]")).getText());
      Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[5]/div/div/div[3]/div/button[1]/i")).isDisplayed());
      Assert.assertEquals("fa fa-times", driver.findElement(By.xpath("/html/body/div[5]/div/div/div[3]/div/button[1]/i")).getAttribute("class"));

      //Ok Button
      Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[5]/div/div/div[3]/div/button[2]")).isDisplayed());
      Assert.assertEquals(" Ok", driver.findElement(By.xpath("/html/body/div[5]/div/div/div[3]/div/button[2]")).getText());
      Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[5]/div/div/div[3]/div/button[2]/i")).isDisplayed());
      Assert.assertEquals("fa fa-check", driver.findElement(By.xpath("/html/body/div[5]/div/div/div[3]/div/button[2]/i")).getAttribute("class"));

    }


//    Using this method as a testing ground
//    @And("^testing search details$")
//    public void please_search() {
//    }

}
