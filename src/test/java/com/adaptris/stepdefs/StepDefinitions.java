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


package com.adaptris.stepdefs;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import org.openqa.selenium.By;

import java.io.IOException;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.interactions.Actions;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;

import static com.adaptris.stepdefs.ENVVAR.*;
import static com.adaptris.stepdefs.GUIDirectoryTools.*;

//TODO Separate into multiple readable files

public class StepDefinitions {

  @Before
  public void before(Scenario scenario) {
    driverInit(LOGIN_PAGE);
  }

  @After
  public void after(Scenario scenario) {
    driver.quit();
  }

  //    @And("^waits$")
  //    public void hold_fire() throws InterruptedException {
  //      Thread.sleep(3000);
  //    }


  // Given section

  @Given("^on a splash page$")
  public void given_on_a_splash() {
    Assert.assertEquals("http://localhost:8080/", driver.getCurrentUrl());
  }

  @Given("^the user clicks the 'Widgets' button$")
  public void clicks_widgetsBtn() throws InterruptedException {
    Thread.sleep(2000);
    driver.findElement(By.xpath("/html/body/header/div[2]/ul/li[2]/a[1]")).click();
  }

  @Given("^the user navigates to the config page$")
  public void nav_toConfigPage() throws InterruptedException {
    Thread.sleep(2000);
    driver.findElement(By.xpath("/html/body/header/div[2]/ul/li[3]/a[1]")).click();
  }

  // When section

  @When("^the user enters username and password$")
  public void enters_username_and_password() {
    driver.findElement(By.name("username")).sendKeys(ENVVAR.USERNAME);
    driver.findElement(By.name("password")).sendKeys(ENVVAR.PASSWORD);
    driver.findElement(By.xpath("/html/body/section/div[2]/div[2]/section/div[2]/div/div/form/fieldset/div/div[3]/div/div[1]/button")).click();
  }

  @When("^the user enters an incorrect username or password$")
  public void enters_an_incorrect_username_or_password() {
    driver.findElement(By.name("username")).sendKeys("Foo");
    driver.findElement(By.name("password")).sendKeys("Bar");
    driver.findElement(By.xpath("/html/body/section/div[2]/div[2]/section/div[2]/div/div/form/fieldset/div/div[3]/div/div[1]/button")).click();
  }

  @When("^the user lands on the 'Widgets' page$")
  public void lands_on_widgetsPage() {
    Assert.assertEquals("http://localhost:8080/interlok/runtime/runtime.html", driver.getCurrentUrl());
  }

  @When("^the user reaches the config page$")
  public void reached_configPage(){
    Assert.assertTrue(driver.getCurrentUrl().contains(configAddress));
  }

  // Then Section

  @Then("^the user sees 'Welcome Page'")
  public void welcome_Page() {
    //Image header
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[1]/div/img")).isDisplayed());
    Assert.assertEquals(LOGIN_PAGE + "/welcome.png", driver.findElement(By.xpath("/html/body/div[1]/div/img")).getAttribute("src"));

    //Text
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/h1")).isDisplayed());
    Assert.assertEquals("Welcome to the Interlok Framework", driver.findElement(By.xpath("/html/body/div[2]/div/h1")).getText());
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/p")).isDisplayed());
    Assert.assertEquals("If you're already familiar with the adapter and just want to get going, then you can do that; otherwise you might want to have a look at the links and checkout the documentation.", driver.findElement(By.xpath("/html/body/div[2]/div/p")).getText());


    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[3]/div/div[1]/h2")).isDisplayed());
    Assert.assertEquals("Web UI", driver.findElement(By.xpath("/html/body/div[3]/div/div[1]/h2")).getText());
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[3]/div/div[1]/p[1]")).isDisplayed());
    Assert.assertEquals("The Local Interlok UI allows you to control, configure and monitor your local Interlok instance, all within your web browser.", driver.findElement(By.xpath("/html/body/div[3]/div/div[1]/p[1]")).getText());
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[3]/div/div[1]/p[2]/a")).isDisplayed());
    Assert.assertEquals("View >>", driver.findElement(By.xpath("/html/body/div[3]/div/div[1]/p[2]/a")).getText());
    Assert.assertEquals("btn btn-default", driver.findElement(By.xpath("/html/body/div[3]/div/div[1]/p[2]/a")).getAttribute("class"));

    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/h2")).isDisplayed());
    Assert.assertEquals("Documentation", driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/h2")).getText());
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/p[1]")).isDisplayed());
    Assert.assertEquals("Enjoy our documentation website which contains information on all things related to the Interlok container. Getting started guides, advanced topics, developer guide & various cookbooks covering specifics for a host of technologies.", driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/p[1]")).getText());
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/p[2]/a")).isDisplayed());
    Assert.assertEquals("View >>", driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/p[2]/a")).getText());
    Assert.assertEquals("btn btn-default", driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/p[2]/a")).getAttribute("class"));

    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[3]/div/div[3]/h2")).isDisplayed());
    Assert.assertEquals("Javadocs", driver.findElement(By.xpath("/html/body/div[3]/div/div[3]/h2")).getText());
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[3]/div/div[3]/p[1]")).isDisplayed());
    Assert.assertEquals("API documentation in HTML format for all our Java source code. Need to know every little detail? you'll find it in here.", driver.findElement(By.xpath("/html/body/div[3]/div/div[3]/p[1]")).getText());
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[3]/div/div[3]/p[2]/a")).isDisplayed());
    Assert.assertEquals("View >>", driver.findElement(By.xpath("/html/body/div[3]/div/div[3]/p[2]/a")).getText());
    Assert.assertEquals("btn btn-default", driver.findElement(By.xpath("/html/body/div[3]/div/div[3]/p[2]/a")).getAttribute("class"));

    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[3]/div/div[4]/h2")).isDisplayed());
    Assert.assertEquals("Extras", driver.findElement(By.xpath("/html/body/div[3]/div/div[4]/h2")).getText());
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[3]/div/div[4]/p[1]")).isDisplayed());
    Assert.assertEquals("As well as all our core components, we also have lots of documentation regarding optional components. Looking for information regarding Websphere MQ or Interfax? etc, look no further.", driver.findElement(By.xpath("/html/body/div[3]/div/div[4]/p[1]")).getText());
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[3]/div/div[4]/p[2]/a")).isDisplayed());
    Assert.assertEquals("View >>", driver.findElement(By.xpath("/html/body/div[3]/div/div[4]/p[2]/a")).getText());
    Assert.assertEquals("btn btn-default", driver.findElement(By.xpath("/html/body/div[3]/div/div[4]/p[2]/a")).getAttribute("class"));

    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[3]/footer/p")).isDisplayed());
    Assert.assertEquals("© Adaptris Limited", driver.findElement(By.xpath("/html/body/div[3]/footer/p")).getText());
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[3]/footer/p/a")).isDisplayed());
  }

  @Then("^the user reaches the failed login page$")
  public void reaches_failed_login_page() {
    String ExpectedHeader = "Failed to authenticate user";
    Assert.assertEquals(ExpectedHeader, driver.findElement(By.xpath("//*[@id=\"login_container\"]/div[1]/div/section/div/p")).getText());
    Assert.assertEquals("http://localhost:8080/interlok/login.html?failed=", driver.getCurrentUrl());
  }

  @Then("^clicks the 'Web UI' view button$")
  public void clicks_webUI_btn() {
    driver.findElement(By.xpath("/html/body/div[3]/div/div[1]/p[2]/a")).click();
  }

  @Then("^the user sees the 'Welcome Modal'$")
  public void welcome_one_and_all_to_the_show() throws InterruptedException {
    Thread.sleep(2000);
    Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"modal-welcome-splash\"]")).isDisplayed());

    //Header
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[8]/div/div/div[1]/div/h1")).isDisplayed());
    Assert.assertEquals("welcome", driver.findElement(By.xpath("/html/body/div[8]/div/div/div[1]/div")).getAttribute("class"));

    //Sections
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[8]/div/div/div[2]/div/div/div[2]/div/div/div")).isDisplayed());
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[8]/div/div/div[2]/div/div/div[1]/div/div[1]/span[1]/i")).isDisplayed());
    Assert.assertEquals("fa fa-home", driver.findElement(By.xpath("/html/body/div[8]/div/div/div[2]/div/div/div[1]/div/div[1]/span[1]/i")).getAttribute("class"));
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[8]/div/div/div[2]/div/div/div[1]/div/div[1]/span[1]")).isDisplayed());
    Assert.assertEquals("Manage", driver.findElement(By.xpath("/html/body/div[8]/div/div/div[2]/div/div/div[1]/div/div[1]/span[1]")).getText());
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[8]/div/div/div[2]/div/div/div[1]/div/div[2]/span[1]/i")).isDisplayed());
    Assert.assertEquals("fa fa-tasks", driver.findElement(By.xpath("/html/body/div[8]/div/div/div[2]/div/div/div[1]/div/div[2]/span[1]/i")).getAttribute("class"));
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[8]/div/div/div[2]/div/div/div[1]/div/div[2]/span[1]")).isDisplayed());
    Assert.assertEquals("Monitor", driver.findElement(By.xpath("/html/body/div[8]/div/div/div[2]/div/div/div[1]/div/div[2]/span[1]")).getText());
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[8]/div/div/div[2]/div/div/div[1]/div/div[3]/span[1]/i")).isDisplayed());
    Assert.assertEquals("fa fa-wrench", driver.findElement(By.xpath("/html/body/div[8]/div/div/div[2]/div/div/div[1]/div/div[3]/span[1]/i")).getAttribute("class"));
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[8]/div/div/div[2]/div/div/div[1]/div/div[3]/span[1]")).isDisplayed());
    Assert.assertEquals("Config", driver.findElement(By.xpath("/html/body/div[8]/div/div/div[2]/div/div/div[1]/div/div[3]/span[1]")).getText());

    //Buttons
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[8]/div/div/div[3]/button[1]")).isDisplayed());
    Assert.assertEquals("Give me a Tour", driver.findElement(By.xpath("/html/body/div[8]/div/div/div[3]/button[1]")).getText());
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[8]/div/div/div[3]/button[2]")).isDisplayed());
    Assert.assertEquals("Got it, Thanks", driver.findElement(By.xpath("/html/body/div[8]/div/div/div[3]/button[2]")).getText());

    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[8]/div/div/div[3]/div/label/i[2]")).isDisplayed());
    Assert.assertEquals("fa fa-fw fa-square-o", driver.findElement(By.xpath("/html/body/div[8]/div/div/div[3]/div/label/i[2]")).getAttribute("class"));
    Assert.assertTrue(driver.findElement(By.id("welcome-dismis-label")).isDisplayed());
    Assert.assertEquals(" Don't show me this again",driver.findElement(By.id("welcome-dismis-label")).getText());
  }

  @Then("^the user dismisses the 'Welcome Modal'$")
  public void dismiss_welcomeModal() throws InterruptedException {
    Thread.sleep(2000);
    driver.findElement(By.xpath("/html/body/div[8]/div/div/div[3]/div")).click();
    driver.findElement(By.xpath("/html/body/div[8]/div/div/div[3]/button[2]")).click();
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
  public void controlBar_stopBtn_click() throws InterruptedException {
    Thread.sleep(1000);
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

  @Then("^clicks the control-bar drop down menu$")
  public void controlBar_dropDown_click() {
    driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[1]/div/div[2]/div/button[2]")).click();
  }

  @Then("^clicks the 'Force-Stop' button$")
  public void controlBar_forceStopBtn_click() {
    driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[1]/div/div[2]/div/ul/li[1]/a")).click();
  }

  @Then("^dismisses the 'Interrupted Exception' alert box$")
  public void interruptedException_alertBox_dismiss() {
    driver.findElement(By.xpath("//*[@id=\"content\"]/section[1]/div[1]/button")).click();
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

  @Then("^clicks the 'Thread Dump' button$")
  public void clicks_threadDump() throws InterruptedException {
    Thread.sleep(2000);
    driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[1]/div/div[2]/div/ul/li[3]/a")).click();
  }

  @Then("^clicks the close button to shut the 'Thread Dump' modal$")
  public void thread_dumpModal_shut() throws InterruptedException {
    Thread.sleep(1000);
    driver.findElement(By.xpath("/html/body/div[7]/div/div/div[3]/button")).click();
  }

  @Then("^clicks the dismiss button to shut the 'Thread Dump' modal$")
  public void thread_dumpModal_dismiss() throws InterruptedException {
    Thread.sleep(1000);
    driver.findElement(By.xpath("/html/body/div[7]/div/div/div[1]/a")).click();
  }

  @Then("^clicks the control-bar 'Config' button$")
  public void cntrlBar_configBtn() {
    driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[1]/div/div[2]/button[2]")).click();
  }

  @Then("^expands the config xml")
  public void configXml_expand() throws InterruptedException {
    Thread.sleep(2000);
    //Channel 4
    driver.findElement(By.xpath("/html/body/div[6]/div/div/div[2]/div[2]/div/div[1]/div/div[6]/div[1]/div/div/div/div[5]/div[24]/pre/span/span[4]/span")).click();
    driver.findElement(By.xpath("/html/body/div[6]/div/div/div[2]/div[2]/div/div[1]/div/div[6]/div[1]/div")).click();
    Thread.sleep(1000);
    //Channel 3
    driver.findElement(By.xpath("/html/body/div[6]/div/div/div[2]/div[2]/div/div[1]/div/div[6]/div[1]/div/div/div/div[5]/div[23]/pre/span/span[4]/span")).click();
    driver.findElement(By.xpath("/html/body/div[6]/div/div/div[2]/div[2]/div/div[1]/div/div[6]/div[1]/div")).click();
    Thread.sleep(2000);
    //Channel 2
    driver.findElement(By.xpath("/html/body/div[6]/div/div/div[2]/div[2]/div/div[1]/div/div[6]/div[1]/div/div/div/div[5]/div[22]/pre/span/span[4]/span")).click();
    driver.findElement(By.xpath("/html/body/div[6]/div/div/div[2]/div[2]/div/div[1]/div/div[6]/div[1]/div")).click();
    Thread.sleep(2000);
    //Channel 1
    driver.findElement(By.xpath("/html/body/div[6]/div/div/div[2]/div[2]/div/div[1]/div/div[6]/div[1]/div/div/div/div[5]/div[21]/pre/span/span[4]/span")).click();
    driver.findElement(By.xpath("/html/body/div[6]/div/div/div[2]/div[2]/div/div[1]/div/div[6]/div[1]/div")).click();
    Thread.sleep(2000);
    //Failed Message Retrier
    driver.findElement(By.xpath("/html/body/div[6]/div/div/div[2]/div[2]/div/div[1]/div/div[6]/div[1]/div/div/div/div[5]/div[19]/pre/span/span[6]/span")).click();
    driver.findElement(By.xpath("/html/body/div[6]/div/div/div[2]/div[2]/div/div[1]/div/div[6]/div[1]/div")).click();
    Thread.sleep(2000);
    //Message Error Handler
    driver.findElement(By.xpath("/html/body/div[6]/div/div/div[2]/div[2]/div/div[1]/div/div[6]/div[1]/div/div/div/div[5]/div[18]/pre/span/span[6]/span")).click();
    driver.findElement(By.xpath("/html/body/div[6]/div/div/div[2]/div[2]/div/div[1]/div/div[6]/div[1]/div")).click();
  }

  @Then("^clicks the close button to shut the control-bar'Config' modal$")
  public void cntrlBar_configXml_modalShut() throws InterruptedException {
    Thread.sleep(1000);
    driver.findElement(By.xpath("/html/body/div[6]/div/div/div[3]/button")).click();
  }

  @Then("^clicks the dismiss button to dismiss the control-bar'Config' modal$")
  public void cntrlBar_configXml_modalDismiss() throws InterruptedException {
    Thread.sleep(1000);
    driver.findElement(By.xpath("/html/body/div[6]/div/div/div[1]/a")).click();
  }

  @Then("^clicks the arrow to collapse the 'config unique-id' tag$")
  public void clicks_configUID_collapseBtn() {
    driver.findElement(By.xpath("/html/body/div[6]/div/div/div[2]/div[2]/div/div[1]/div/div[6]/div[1]/div/div/div/div[5]/div[2]/div/div[2]/div")).click();
  }

  @Then("^clicks the 'Run Garbage Collector' button$")
  public void clicks_garbageCollector_cntrlBar_btn() throws InterruptedException {
    Thread.sleep(1000);
    driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[1]/div/div[2]/div/ul/li[2]/a")).click();
  }

  @Then("^clicks the dismiss button to shut the 'Garbage Collector' alert box$")
  public void garbageCollector_dismiss() {
    driver.findElement(By.xpath("/html/body/section/div[2]/section[1]/div[1]/button")).click();
  }

  @Then("^the user sees the 'Widgets Modal'$")
  public void widgets_modal_isDisplayed() throws InterruptedException {
    Thread.sleep(2000);

    //Modal itself
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div")).isDisplayed());

    //Page header
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[1]/h3/i")).isDisplayed());
    Assert.assertEquals("fa fa-bar-chart-o", driver.findElement(By.xpath("/html/body/div[2]/div/div/div[1]/h3/i")).getAttribute("class"));
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[1]/h3")).isDisplayed());
    Assert.assertEquals("Add a Runtime Widget", driver.findElement(By.xpath("/html/body/div[2]/div/div/div[1]/h3")).getText());

    //Aggregated Adapter Widgets option
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[1]/div[1]/div/div/label")).isDisplayed());
    Assert.assertEquals("checkbox-label", driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[1]/div[1]/div/div/label")).getAttribute("class"));

    //Adapter selector form
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[1]/div[3]/div[1]/div/span/i")).isDisplayed());
    Assert.assertEquals("fa fa-adapter", driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[1]/div[3]/div[1]/div/span/i")).getAttribute("class"));
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[1]/div[3]/div[1]/div/select")).isDisplayed());

    //Custom tile
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div/div")).isDisplayed());
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div/div/div[1]/div/i")).isDisplayed());
    Assert.assertEquals("fa fa-code", driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div/div/div[1]/div/i")).getAttribute("class"));
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div/div/div[3]/a/i")).isDisplayed());
    Assert.assertEquals("fa fa-info-circle action-icon component-action", driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div/div/div[3]/a/i")).getAttribute("class"));
    Assert.assertEquals("Custom", driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div/div/div[2]/h4")).getText());
    Assert.assertEquals("Platform", driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div/div/div[2]/h5/span")).getText());

    //Close, dismiss and need help buttons
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[3]/div/a")).isDisplayed());
    Assert.assertEquals("Need Help?", driver.findElement(By.xpath("/html/body/div[2]/div/div/div[3]/div/a")).getText());
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[3]/button")).isDisplayed());
    Assert.assertEquals("Close", driver.findElement(By.xpath("/html/body/div[2]/div/div/div[3]/button")).getText());
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[1]/a")).isDisplayed());
    Assert.assertEquals("×", driver.findElement(By.xpath("/html/body/div[2]/div/div/div[1]/a")).getText());
  }

  @Then("^selects the (.*) adapter in the 'Widgets Modal'$")
  public void select_adapter_for_widgets(String adapterName) throws InterruptedException {
    Thread.sleep(2000);
    String selectOption = String.format("/html/body/div[2]/div/div/div[2]/div[1]/div[1]/div[3]/div[1]/div/select/option[text() = '%s']", adapterName);
    driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[1]/div[3]/div[1]/div/select")).click();
    Thread.sleep(2000);
    driver.findElement(By.xpath(selectOption)).click();
  }

  //Adapter Widget tiles

  @Then("^clicks the 'Add Widget' button$")
  public void add_widget_btn() throws InterruptedException {
    Thread.sleep(2000);
    driver.findElement(By.xpath("//*[@id=\"control-add-btn\"]")).click();
  }

  @Then("^bulk adds all the widgets bar 'Consumer Messages Remaining'$")
  public void adds_all_widgets_except_consumerMessages_remaining() throws InterruptedException {
    //TODO refactor create list of widgets for each/ has next click the add and wait then add and wait
    Thread.sleep(2000);
    driver.findElement(By.xpath("//*[@id=\"control-add-btn\"]")).click();
    Thread.sleep(1000);
    driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[1]/div")).click();
    Thread.sleep(2000);
    driver.findElement(By.xpath("//*[@id=\"control-add-btn\"]")).click();
    Thread.sleep(1000);
    driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[1]/div")).click();Thread.sleep(2000);
    Thread.sleep(2000);
    driver.findElement(By.xpath("//*[@id=\"control-add-btn\"]")).click();
    Thread.sleep(1000);
    driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[1]/div")).click();Thread.sleep(2000);
    Thread.sleep(2000);
    driver.findElement(By.xpath("//*[@id=\"control-add-btn\"]")).click();
    Thread.sleep(1000);
    driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[1]/div")).click();Thread.sleep(2000);
    Thread.sleep(2000);
    driver.findElement(By.xpath("//*[@id=\"control-add-btn\"]")).click();
    Thread.sleep(1000);
    driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[1]/div")).click();Thread.sleep(2000);
    Thread.sleep(2000);
    driver.findElement(By.xpath("//*[@id=\"control-add-btn\"]")).click();
    Thread.sleep(1000);
    driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[1]/div")).click();Thread.sleep(2000);
    Thread.sleep(2000);
    driver.findElement(By.xpath("//*[@id=\"control-add-btn\"]")).click();
    Thread.sleep(1000);
    driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[1]/div")).click();Thread.sleep(2000);
    Thread.sleep(2000);
    driver.findElement(By.xpath("//*[@id=\"control-add-btn\"]")).click();
    Thread.sleep(1000);
    driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[1]/div")).click();Thread.sleep(2000);
    Thread.sleep(2000);
    driver.findElement(By.xpath("//*[@id=\"control-add-btn\"]")).click();
    Thread.sleep(1000);
    driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[1]/div")).click();Thread.sleep(2000);
    Thread.sleep(2000);
    driver.findElement(By.xpath("//*[@id=\"control-add-btn\"]")).click();
    Thread.sleep(1000);
    driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[1]/div")).click();Thread.sleep(2000);
    Thread.sleep(2000);
    driver.findElement(By.xpath("//*[@id=\"control-add-btn\"]")).click();
    Thread.sleep(1000);
    driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[1]/div")).click();Thread.sleep(2000);
    Thread.sleep(2000);
    driver.findElement(By.xpath("//*[@id=\"control-add-btn\"]")).click();
    Thread.sleep(1000);
    driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[1]/div")).click();Thread.sleep(2000);
    Thread.sleep(2000);
    driver.findElement(By.xpath("//*[@id=\"control-add-btn\"]")).click();
    Thread.sleep(1000);
    driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[1]/div")).click();Thread.sleep(2000);
    Thread.sleep(2000);
    driver.findElement(By.xpath("//*[@id=\"control-add-btn\"]")).click();
    Thread.sleep(1000);
    driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[1]/div")).click();Thread.sleep(2000);
    Thread.sleep(2000);
    driver.findElement(By.xpath("//*[@id=\"control-add-btn\"]")).click();
    Thread.sleep(1000);
    driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[1]/div")).click();Thread.sleep(2000);
    Thread.sleep(2000);
    driver.findElement(By.xpath("//*[@id=\"control-add-btn\"]")).click();
    Thread.sleep(1000);
    driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[1]/div")).click();Thread.sleep(2000);
    Thread.sleep(2000);
    driver.findElement(By.xpath("//*[@id=\"control-add-btn\"]")).click();
    Thread.sleep(1000);
    driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[1]/div")).click();Thread.sleep(2000);
    Thread.sleep(2000);
    driver.findElement(By.xpath("//*[@id=\"control-add-btn\"]")).click();
    Thread.sleep(1000);
    driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[1]/div")).click();Thread.sleep(2000);
    Thread.sleep(2000);
    driver.findElement(By.xpath("//*[@id=\"control-add-btn\"]")).click();
    Thread.sleep(1000);
    driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[1]/div")).click();Thread.sleep(2000);
    Thread.sleep(2000);
    driver.findElement(By.xpath("//*[@id=\"control-add-btn\"]")).click();
    Thread.sleep(1000);
    driver.findElement(By.cssSelector(".widget-component-table")).click();
  }

  @Then("^selects the 'Summary Details' tile$")
  public void select_summaryDetail_tile() throws InterruptedException {
    Thread.sleep(1000);
    driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[1]/div")).click();
  }

  @Then("^selects the 'Summary Details Carousel' tile$")
  public void select_summaryDetails_carousel_tile() throws InterruptedException {
    Thread.sleep(1000);
    driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[2]/div")).click();
  }

  @Then("^selects the 'Control Panel' tile$")
  public void select_controlPanel_tile() throws InterruptedException {
    Thread.sleep(1000);
    driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[3]/div")).click();
  }

  @Then("^selects the 'In Flight' tile$")
  public void select_inFlight_tile() throws InterruptedException {
    Thread.sleep(1000);
    driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[4]/div")).click();
  }

  @Then("^selects the 'Component Counts' tile$")
  public void select_componentCounts_tile() throws InterruptedException {
    Thread.sleep(1000);
    driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[5]/div")).click();
  }

  @Then("^selects the 'Message Counts Chart' tile$")
  public void select_messageCount_charts_tile() throws InterruptedException {
    Thread.sleep(1000);
    driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[6]/div")).click();
  }

  @Then("^selects the 'Daily Message Counts Chart' tile$")
  public void select_dailyMessage_countsChart_tile() throws InterruptedException {
    Thread.sleep(1000);
    driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[7]/div")).click();
  }

  @Then("^selects the 'Message Counts Pie Chart' tile$")
  public void select_messageCounts_pieChart_tile() throws InterruptedException {
    Thread.sleep(1000);
    driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[8]/div")).click();
  }

  @Then("^selects the 'Failed Messages Table' tile$")
  public void select_failedMessage_table_tile() throws InterruptedException {
    Thread.sleep(1000);
    driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[9]/div")).click();
  }

  @Then("^selects the 'Platform Heap Memory Details' tile$")
  public void select_platformHeap_memoryDetails_tile() throws InterruptedException {
    Thread.sleep(1000);
    driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[10]/div")).click();
  }

  @Then("^selects the 'Platform Non Heap Memory Details' tile$")
  public void select_platformNon_heapMemory_details_tile() throws InterruptedException {
    Thread.sleep(1000);
    driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[11]/div")).click();
  }

  @Then("^selects the 'Platform Memory Heap Chart' tile$")
  public void select_platformMemory_heapChart_tile() throws InterruptedException {
    Thread.sleep(1000);
    driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[12]/div")).click();
  }

  @Then("^selects the 'Platform Memory Non Heap Chart' tile$")
  public void select_platformMemory_nonHeap_chart_tile() throws InterruptedException {
    Thread.sleep(1000);
    driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[13]/div")).click();
  }

  @Then("^selects the 'Platform Runtime Path Details' tile$")
  public void select_platformRuntime_pathDetails_tile() throws InterruptedException {
    Thread.sleep(1000);
    driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[14]/div")).click();
  }

  @Then("^selects the 'Platform Runtime Details' tile$")
  public void select_platformRuntime_details_tile() throws InterruptedException {
    Thread.sleep(1000);
    driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[15]/div")).click();
  }

  @Then("^selects the 'Platform Runtime System Details' tile$")
  public void select_platformRuntime_systemDetails_tile() throws InterruptedException {
    Thread.sleep(1000);
    driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[16]/div")).click();
  }

  @Then("^selects the 'Platform Operating System Details' tile$")
  public void select_platformOperating_systemDetails_tile() throws InterruptedException {
    Thread.sleep(1000);
    driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[17]/div")).click();
  }

  @Then("^selects the 'Platform JVM Process Load Chart' tile$")
  public void select_platformJVM_processLoad_chart_tile() throws InterruptedException {
    Thread.sleep(1000);
    driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[18]/div")).click();
  }

  @Then("^selects the 'Platform System CPU Load Chart' tile$")
  public void select_platformSystem_cpuLoad_chart_tile() throws InterruptedException {
    Thread.sleep(1000);
    driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[19]/div")).click();
  }

  @Then("^selects the 'Platform Thread Details' tile$")
  public void select_platformThread_details_tile() throws InterruptedException {
    Thread.sleep(1000);
    driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[20]/div")).click();
  }

  @Then("^selects the 'Consumer Messages Remaining' tile$")
  public void select_consumerMessages_remaining_tile() throws InterruptedException {
    Thread.sleep(1000);
    driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[21]/div")).click();
  }

  @Then("^selects the 'Logs Table - JMX Log Appender' tile$")
  public void select_logsTable_jmxLog_appender_tile() throws InterruptedException {
    Thread.sleep(1000);
    driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[22]/div")).click();
  }

  @Then("^clicks the 'Remove Widget' button$")
  public void remove_single_widgetBtn() {
    Actions action = new Actions(driver);
    action.moveToElement(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[1]/div[1]/div/div[2]/div[2]/div"))).build().perform();
    driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[1]/div[1]/div/div[3]/div/div[2]/div/button[2]/i")).click();
  }

  @Then("^clicks the dismiss button on the 'Remove Widget' modal$")
  public void removeWidget_dismissBtn() {
    driver.findElement(By.xpath("/html/body/div[7]/div/div/div[1]/button")).click();
  }

  @Then("^clicks the Cancel button on the 'Remove Widget' modal$")
  public void removeWidget_cancelBtn() {
    driver.findElement(By.xpath("/html/body/div[7]/div/div/div[2]/button[1]")).click();
  }

  @Then("^clicks the OK button on the 'Remove Widget' modal$")
  public void removeWidget_okBtn() {
    driver.findElement(By.xpath("/html/body/div[7]/div/div/div[2]/button[2]")).click();
  }

  @Then("^then removes all 20 widgets remaining widgets$")
  public void remove_allWidgets() throws InterruptedException {
    Actions action = new Actions(driver);
    Thread.sleep(2000);

    action.moveToElement(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[1]/div[1]/div"))).build().perform();
    Thread.sleep(2000);
    driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[1]/div[1]/div/div[3]/div/div[2]/div/button[2]")).click();
    Thread.sleep(1000);
    driver.findElement(By.xpath("/html/body/div[7]/div/div/div[2]/button[2]")).click();
    Thread.sleep(1000);

    action.moveToElement(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[1]/div[1]/div"))).build().perform();
    Thread.sleep(2000);
    driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[1]/div[1]/div/div[3]/div/div[2]/div/button[2]")).click();
    Thread.sleep(1000);
    driver.findElement(By.xpath("/html/body/div[7]/div/div/div[2]/button[2]")).click();
    Thread.sleep(1000);

    action.moveToElement(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[2]/div[1]/div"))).build().perform();
    Thread.sleep(2000);
    driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[2]/div[1]/div/div[3]/div/div[2]/div/button[2]")).click();
    Thread.sleep(1000);
    driver.findElement(By.xpath("/html/body/div[7]/div/div/div[2]/button[2]")).click();
    Thread.sleep(1000);

    action.moveToElement(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[1]/div[1]/div"))).build().perform();
    Thread.sleep(2000);
    driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[1]/div[1]/div/div[3]/div/div[2]/div/button[2]")).click();
    Thread.sleep(1000);
    driver.findElement(By.xpath("/html/body/div[7]/div/div/div[2]/button[2]")).click();
    Thread.sleep(1000);

    action.moveToElement(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[2]/div[1]/div"))).build().perform();
    Thread.sleep(2000);
    driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[2]/div[1]/div/div[3]/div/div[2]/div/button[2]")).click();
    Thread.sleep(1000);
    driver.findElement(By.xpath("/html/body/div[7]/div/div/div[2]/button[2]")).click();
    Thread.sleep(1000);

    action.moveToElement(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[1]/div[1]/div"))).build().perform();
    Thread.sleep(2000);
    driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[1]/div[1]/div/div[3]/div/div[2]/div/button[3]")).click();
    Thread.sleep(1000);
    driver.findElement(By.xpath("/html/body/div[7]/div/div/div[2]/button[2]")).click();
    Thread.sleep(1000);

    action.moveToElement(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[3]/div[1]/div"))).build().perform();
    Thread.sleep(2000);
    driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[3]/div[1]/div/div[3]/div/div[2]/div/button[3]")).click();
    Thread.sleep(1000);
    driver.findElement(By.xpath("/html/body/div[7]/div/div/div[2]/button[2]")).click();
    Thread.sleep(1000);

    action.moveToElement(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[1]/div[1]/div"))).build().perform();
    Thread.sleep(2000);
    driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[1]/div[1]/div/div[3]/div/div[2]/div/button[3]")).click();
    Thread.sleep(1000);
    driver.findElement(By.xpath("/html/body/div[7]/div/div/div[2]/button[2]")).click();
    Thread.sleep(1000);

    action.moveToElement(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[7]/div[1]/div"))).build().perform();
    Thread.sleep(2000);
    driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[7]/div[1]/div/div[3]/div/div[2]/div/button[2]")).click();
    Thread.sleep(1000);
    driver.findElement(By.xpath("/html/body/div[7]/div/div/div[2]/button[2]")).click();
    Thread.sleep(1000);

    action.moveToElement(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[4]/div[1]/div"))).build().perform();
    Thread.sleep(2000);
    driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[4]/div[1]/div/div[3]/div/div[2]/div/button[2]")).click();
    Thread.sleep(1000);
    driver.findElement(By.xpath("/html/body/div[7]/div/div/div[2]/button[2]")).click();
    Thread.sleep(1000);

    action.moveToElement(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[8]/div[1]/div"))).build().perform();
    Thread.sleep(2000);
    driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[8]/div[1]/div/div[3]/div/div[2]/div/button[2]")).click();
    Thread.sleep(1000);
    driver.findElement(By.xpath("/html/body/div[7]/div/div/div[2]/button[2]")).click();
    Thread.sleep(1000);

    action.moveToElement(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[2]/div[1]/div"))).build().perform();
    Thread.sleep(2000);
    driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[2]/div[1]/div/div[3]/div/div[2]/div/button[3]")).click();
    Thread.sleep(1000);
    driver.findElement(By.xpath("/html/body/div[7]/div/div/div[2]/button[2]")).click();
    Thread.sleep(1000);

    action.moveToElement(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[3]/div[1]/div"))).build().perform();
    Thread.sleep(2000);
    driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[3]/div[1]/div/div[3]/div/div[2]/div/button[2]")).click();
    Thread.sleep(1000);
    driver.findElement(By.xpath("/html/body/div[7]/div/div/div[2]/button[2]")).click();
    Thread.sleep(1000);

    action.moveToElement(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[4]/div[1]/div"))).build().perform();
    Thread.sleep(2000);
    driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[4]/div[1]/div/div[3]/div/div[2]/div/button[2]")).click();
    Thread.sleep(1000);
    driver.findElement(By.xpath("/html/body/div[7]/div/div/div[2]/button[2]")).click();
    Thread.sleep(1000);

    action.moveToElement(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[5]/div[1]/div"))).build().perform();
    Thread.sleep(2000);
    driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[5]/div[1]/div/div[3]/div/div[2]/div/button[3]")).click();
    Thread.sleep(1000);
    driver.findElement(By.xpath("/html/body/div[7]/div/div/div[2]/button[2]")).click();
    Thread.sleep(1000);

    action.moveToElement(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[1]/div[1]/div"))).build().perform();
    Thread.sleep(2000);
    driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[1]/div[1]/div/div[3]/div/div[2]/div/button[2]")).click();
    Thread.sleep(1000);
    driver.findElement(By.xpath("/html/body/div[7]/div/div/div[2]/button[2]")).click();
    Thread.sleep(1000);

    action.moveToElement(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[1]/div[1]/div"))).build().perform();
    Thread.sleep(2000);
    driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[1]/div[1]/div/div[3]/div/div[2]/div/button[3]")).click();
    Thread.sleep(1000);
    driver.findElement(By.xpath("/html/body/div[7]/div/div/div[2]/button[2]")).click();
    Thread.sleep(1000);

    action.moveToElement(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[1]/div[1]/div"))).build().perform();
    Thread.sleep(2000);
    driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[1]/div[1]/div/div[3]/div/div[2]/div/button[2]")).click();
    Thread.sleep(1000);
    driver.findElement(By.xpath("/html/body/div[7]/div/div/div[2]/button[2]")).click();
    Thread.sleep(1000);

    action.moveToElement(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[1]/div[1]/div"))).build().perform();
    Thread.sleep(2000);
    driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[1]/div[1]/div/div[3]/div/div[2]/div/button[2]")).click();
    Thread.sleep(1000);
    driver.findElement(By.xpath("/html/body/div[7]/div/div/div[2]/button[2]")).click();
    Thread.sleep(1000);

    action.moveToElement(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[1]/div"))).build().perform();
    Thread.sleep(2000);
    driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[1]/div/div[3]/div/div[2]/div/form/div[2]/div/button[2]")).click();
    Thread.sleep(1000);
    driver.findElement(By.xpath("/html/body/div[7]/div/div/div[2]/button[2]")).click();
    Thread.sleep(1000);

  }

  @Then("^the user sees the Open config button$")
  public void openConfig_btn() throws InterruptedException {
    Thread.sleep(2000);
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[5]/div[1]/div[2]/div/div[1]/button[1]")).isDisplayed());
    Assert.assertEquals("Open config", driver.findElement(By.xpath("/html/body/section/div[2]/section[5]/div[1]/div[2]/div/div[1]/button[1]")).getText());
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
  public void select_variableSet(String option) throws InterruptedException {
    Thread.sleep(2000);
    String selectOption = String.format("/html/body/div[5]/div/div/div[2]/div[4]/div[1]/div/div/div[2]/div[1]/select/option[text() = '%s']", option);
    driver.findElement(By.xpath("/html/body/div[5]/div/div/div[2]/div[4]/div[1]/div/div/div[2]/div[1]/select")).click();
    driver.findElement(By.xpath(selectOption)).click();
  }

  @Then("^clicks Ok to apply the config$")
  public void clicks_ok_to_applyConfig() {
    driver.findElement(By.xpath("/html/body/div[5]/div/div/div[3]/div/button[2]")).click();
  }


  // And Section

  @And("^sees the adapters unique id: (.*)$")
  public void adapters_unique_id(String adapterID) {
    Assert.assertEquals(adapterID, driver.findElement(By.xpath("//*[@id=\"adapter-data-areas\"]/div[1]/div/div[1]/h4/span[2]")).getText());
  }

  @And("^sees the JMX URL address: (.*)$")
  public void JMX_URL_address(String jmxURL) {
    Assert.assertEquals(jmxURL, driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[1]/h4/span[4]/span")).getText());
  }

  @And("^sees the adapter in 'Started' state$")
  public void adapter_in_started_state() {
    Assert.assertTrue(driver.findElement(By.id("container-state-label")).getText().contains("STARTED"));
  }

  @And("^sees a check icon$")
  public void check_icon() throws InterruptedException {
    Thread.sleep(3500);
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[1]/h4/span[3]/span/span/i")).isDisplayed());
    Assert.assertEquals("fa fa-check text-success", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[1]/h4/span[3]/span/span/i")).getAttribute("class"));
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[3]/div/div[1]/div[1]/div/div[1]/span/span/span/i")).isDisplayed());
    Assert.assertEquals("fa fa-check text-success", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[3]/div/div[1]/div[1]/div/div[1]/span/span/span/i")).getAttribute("class"));
  }

  @And("^sees the adapter in 'Stopped' state$")
  public void adapter_in_stopped_state() throws InterruptedException {
    Thread.sleep(3000);
    Assert.assertTrue(driver.findElement(By.id("container-state-label")).getText().contains("STOPPED"));
  }

  @And("^sees a stop icon$")
  public void stop_icon() throws InterruptedException {
    Thread.sleep(3500);
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[1]/h4/span[3]/span/span/i")).isDisplayed());
    Assert.assertEquals("fa fa-minus-circle text-warning", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[1]/h4/span[3]/span/span/i")).getAttribute("class"));
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[1]/h4/span[3]/span/span/i")).isDisplayed());
    Assert.assertEquals("fa fa-minus-circle text-warning", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[1]/h4/span[3]/span/span/i")).getAttribute("class"));
  }

  @And("^sees the adapter in 'Closed' state$")
  public void adapter_in_closed_state() throws InterruptedException {
    Thread.sleep(3000);
    Assert.assertTrue(driver.findElement(By.id("container-state-label")).getText().contains("CLOSED"));
  }

  @And("^sees a closed icon$")
  public void closed_icon() throws InterruptedException {
    Thread.sleep(3500);
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[1]/h4/span[3]/span/span/i")).isDisplayed());
    Assert.assertEquals("fa fa-ban text-warning", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[1]/h4/span[3]/span/span/i")).getAttribute("class"));
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[1]/h4/span[3]/span/span/i")).isDisplayed());
    Assert.assertEquals("fa fa-ban text-warning", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[1]/h4/span[3]/span/span/i")).getAttribute("class"));
  }

  @And("^sees a low Heap Memory$")
  public void low_heap_Memory() throws InterruptedException {
    Thread.sleep(2000);
    Integer memoryHeapValue = Integer.parseInt(driver.findElement(By.cssSelector("#heap-memory-gauge_1 > svg:nth-child(1) > text:nth-child(5) > tspan:nth-child(1)")).getText());
    Thread.sleep(1000);
    Assert.assertTrue(0 <= memoryHeapValue);
    Assert.assertTrue(memoryHeapValue <= 999);
  }

  @And("^sees (.*) started channels$")
  public void sees_started_channels(String channels) throws InterruptedException {
    //Element refreshes and requires a wait
    Thread.sleep(8000);
    Assert.assertEquals(channels, driver.findElement(By.cssSelector("#channels-state-gauge_1 > svg:nth-child(1) > text:nth-child(5) > tspan:first-child")).getText());
  }

  @And("^sees the total number of (.*) adapter channels expected$")
  public void total_adapted_channels(String channels) throws InterruptedException {
    Thread.sleep(1000);
    Assert.assertEquals(channels, driver.findElement(By.cssSelector("#channels-state-gauge_1 > svg:nth-child(1) > text:nth-child(8) > tspan:first-child")).getText());
  }

  @And("^sees the 'Up Time' section$")
  public void upTime_container() {
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[3]/div/div[1]/div[6]/div/div[1]/div")).isDisplayed());
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[3]/div/div[1]/div[6]/div/div[1]/div")).getText().contains("Up Time"));
  }

  @And("^sees the 'Last Started' section$")
  public void lastStarted_container() {
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[3]/div/div[1]/div[6]/div/div[2]/div")).isDisplayed());
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[3]/div/div[1]/div[6]/div/div[2]/div")).getText().contains("Last Started"));
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[3]/div/div[1]/div[6]/div/div[2]/span")).isDisplayed());
  }

  @And("^sees UI version (.*)$")
  public void ui_version(String uiVersion) {
    Assert.assertEquals(uiVersion, driver.findElement(By.xpath("/html/body/footer/span/span[1]/a")).getText());
  }

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

  @And("^sees the control-bar$")
  public void adapter_controlBar() {
    Assert.assertTrue(driver.findElement(By.id("adapter-control-panel")).isDisplayed());
  }

  @And("^sees the control-bar buttons$")
  public void controlBar_btns() {
    //TODO create an Assert icon method, takes icon name and location as args
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

  @And("^sees the 'Interrupted Exception' alert box$")
  public void interruptedException_alertBox_isVisible() throws InterruptedException {
    Thread.sleep(3000);
    //Alertbox
    Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"content\"]/section[1]")).isDisplayed());
    //Header
    //Icon
    Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"content\"]/section[1]/div[1]/i")).isDisplayed());
    Assert.assertEquals("fa fa-times-circle", driver.findElement(By.xpath("//*[@id=\"content\"]/section[1]/div[1]/i")).getAttribute("class"));
    //Text
    Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"content\"]/section[1]/div[1]/strong")).isDisplayed());
    Assert.assertEquals("An error has occurred ", driver.findElement(By.xpath("//*[@id=\"content\"]/section[1]/div[1]/strong")).getText());
    //Message
    Assert.assertEquals("java.lang.InterruptedException", driver.findElement(By.xpath("//*[@id=\"content\"]/section[1]/div[1]/p")).getText());
    //Further Info btn
    Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"content\"]/section[1]/div[1]/a")).isDisplayed());
    Assert.assertEquals("Show more…", driver.findElement(By.xpath("//*[@id=\"content\"]/section[1]/div[1]/a")).getText());
    //Dismiss btn
    Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"content\"]/section[1]/div[1]/button")).isDisplayed());
    Assert.assertEquals("×", driver.findElement(By.xpath("//*[@id=\"content\"]/section[1]/div[1]/button")).getText());

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
    Assert.assertEquals(GUIDirectoryTools.searchFileDetails(ADAPTRIS_INSTANCE_PATH, "adapter.xml", "unique-id"), driver.findElement(By.xpath("/html/body/div[5]/div/div/div[2]/div/table/tbody/tr[1]/td[2]/span")).getText());

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

  @And("^sees the 'Thread Dump' Modal$")
  public void sees_threadDump_modal() throws InterruptedException {
    Thread.sleep(2000);
    //Modal Window
    Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"modal-thread-dump\"]")).isDisplayed());

    //Header
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[7]/div/div/div[1]/h3/i")).isDisplayed());
    Assert.assertEquals("fa fa-bolt", driver.findElement(By.xpath("/html/body/div[7]/div/div/div[1]/h3/i")).getAttribute("class"));
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[7]/div/div/div[1]/h3")).isDisplayed());
    Assert.assertEquals("Thread dump", driver.findElement(By.xpath("/html/body/div[7]/div/div/div[1]/h3")).getText());

    //Action Buttons
    //Close
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[7]/div/div/div[3]/button")).isDisplayed());
    Assert.assertEquals("Close", driver.findElement(By.xpath("/html/body/div[7]/div/div/div[3]/button")).getText());
    //Dismiss
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[7]/div/div/div[1]/a")).isDisplayed());
    Assert.assertEquals("×", driver.findElement(By.xpath("/html/body/div[7]/div/div/div[1]/a")).getText());
    Thread.sleep(2000);
    //Thread Dump Data
    Assert.assertTrue(driver.findElement(By.cssSelector("#thread-dump-text")).getText().contains(threadDumpDate()));
  }

  @And("^sees the 'Thread Dump' modal has been shut$")
  public void thread_dumpModal_isShut() throws InterruptedException {
    Thread.sleep(2000);
    Assert.assertFalse(driver.findElement(By.xpath("//*[@id=\"modal-thread-dump\"]")).isDisplayed());
  }

  @And("^sees the 'Config' modal$")
  public void config_xmlModal() throws InterruptedException {
    Thread.sleep(2000);
    //Modal
    Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"modal-config\"]")).isDisplayed());
    //Header
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[6]/div/div/div[1]/h3/i")).isDisplayed());
    Assert.assertEquals("fa fa-code", driver.findElement(By.xpath("/html/body/div[6]/div/div/div[1]/h3/i")).getAttribute("class"));
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[6]/div/div/div[1]/h3")).isDisplayed());
    Assert.assertEquals("Config", driver.findElement(By.xpath("/html/body/div[6]/div/div/div[1]/h3")).getText());
    //Modal Action Buttons
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[6]/div/div/div[1]/ul/li[1]/a")).isDisplayed());
    Assert.assertEquals("active", driver.findElement(By.xpath("/html/body/div[6]/div/div/div[1]/ul/li[1]")).getAttribute("class"));
    Assert.assertEquals("Xml", driver.findElement(By.xpath("/html/body/div[6]/div/div/div[1]/ul/li[1]/a")).getText());
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[6]/div/div/div[1]/ul/li[2]/a")).isDisplayed());
    Assert.assertEquals("Diagram", driver.findElement(By.xpath("/html/body/div[6]/div/div/div[1]/ul/li[2]/a")).getText());
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[6]/div/div/div[1]/a")).isDisplayed());
    Assert.assertEquals("×", driver.findElement(By.xpath("/html/body/div[6]/div/div/div[1]/a")).getText());
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[6]/div/div/div[3]/button")).isDisplayed());
    Assert.assertEquals("Close", driver.findElement(By.xpath("/html/body/div[6]/div/div/div[3]/button")).getText());
    //Xml Content
    //TODO Refactor to take xml arguements
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[6]/div/div/div[2]/div[2]/div/div[1]/div/div[6]/div[1]/div/div/div/div[5]")).getText().contains("</adapter>"));
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[6]/div/div/div[2]/div[2]/div/div[1]/div/div[6]/div[1]/div/div/div/div[5]")).getText().contains("<unique-id>basic-channel-4</unique-id>"));
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[6]/div/div/div[2]/div[2]/div/div[1]/div/div[6]/div[1]/div/div/div/div[5]")).getText().contains("<unique-id>basic-channel-3</unique-id>"));
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[6]/div/div/div[2]/div[2]/div/div[1]/div/div[6]/div[1]/div/div/div/div[5]")).getText().contains("<unique-id>basic-channel-2</unique-id>"));
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[6]/div/div/div[2]/div[2]/div/div[1]/div/div[6]/div[1]/div/div/div/div[5]")).getText().contains("<unique-id>basic-channel-1</unique-id>"));
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[6]/div/div/div[2]/div[2]/div/div[1]/div/div[6]/div[1]/div/div/div/div[5]")).getText().contains("<unique-id>basic-workflow-4e-InFlight</unique-id>"));
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[6]/div/div/div[2]/div[2]/div/div[1]/div/div[6]/div[1]/div/div/div/div[5]")).getText().contains("<unique-id>basic-workflow-2c-MessageMetrics</unique-id>"));
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[6]/div/div/div[2]/div[2]/div/div[1]/div/div[6]/div[1]/div/div/div/div[5]")).getText().contains("<unique-id>config-001-basic-components</unique-id>"));
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[6]/div/div/div[2]/div[2]/div/div[1]/div/div[6]/div[1]/div/div/div/div[5]")).getText().contains("  <start-up-event-imp>com.adaptris.core.event.StandardAdapterStartUpEvent</start-up-event-imp>\n"));
  }

  @And("^sees the control-bar 'Config' modal has been shut$")
  public void cntrlBar_configXml_modal_isShut() throws InterruptedException {
    Thread.sleep(2000);
    Assert.assertFalse(driver.findElement(By.xpath("/html/body/div[6]/div")).isDisplayed());
  }

  @And("^sees the 'config unique-id' tag is expanded$")
  public void config_uidTag_isVisible() throws IOException, SAXException, ParserConfigurationException, InterruptedException {
    Thread.sleep(1000);
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[6]/div/div/div[2]/div[2]/div/div[1]/div/div[6]/div[1]/div/div/div/div[5]/div[2]/pre/span")).isDisplayed());
    Assert.assertEquals("  <unique-id>" + searchFileDetails(ADAPTRIS_INSTANCE_PATH, "adapter.xml", "unique-id") + "</unique-id>", driver.findElement(By.xpath("/html/body/div[6]/div/div/div[2]/div[2]/div/div[1]/div/div[6]/div[1]/div/div/div/div[5]/div[2]/pre/span")).getText());
  }

  @And("^sees the 'config unique-id' has been collapsed$")
  public void config_uidTag_isCollapsed() throws IOException, SAXException, ParserConfigurationException, InterruptedException {
    Thread.sleep(1000);
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[6]/div/div/div[2]/div[2]/div/div[1]/div/div[6]/div[1]/div/div/div/div[5]/div[2]/pre/span/span[4]/span")).isDisplayed());
    Assert.assertEquals("↔", driver.findElement(By.xpath("/html/body/div[6]/div/div/div[2]/div[2]/div/div[1]/div/div[6]/div[1]/div/div/div/div[5]/div[2]/pre/span/span[4]/span")).getText());
    Assert.assertNotEquals("  <unique-id>" + searchFileDetails(ADAPTRIS_INSTANCE_PATH, "adapter.xml", "unique-id") + "</unique-id>", driver.findElement(By.xpath("/html/body/div[6]/div/div/div[2]/div[2]/div/div[1]/div/div[6]/div[1]/div/div/div/div[5]/div[2]/pre/span/span[4]/span")).getText());
  }

  @And("^sees the 'Garbage Collector' alert box$")
  public void garbageCollector_alertBox_isVisible() throws InterruptedException {
    Thread.sleep(3000);
    //Alertbox
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[1]/div[1]")).isDisplayed());
    //Header
    //Icon
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[1]/div[1]/i")).isDisplayed());
    Assert.assertEquals("fa fa-check-circle", driver.findElement(By.xpath("/html/body/section/div[2]/section[1]/div[1]/i")).getAttribute("class"));
    //Text
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[1]/div[1]/strong")).isDisplayed());
    Assert.assertEquals("Success ", driver.findElement(By.xpath("/html/body/section/div[2]/section[1]/div[1]/strong")).getText());
    //Message
    Assert.assertEquals("Garbage Collector request has been successfully sent", driver.findElement(By.xpath("/html/body/section/div[2]/section[1]/div[1]/p")).getText());
    //Dismiss btn
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[1]/div[1]/button")).isDisplayed());
    Assert.assertEquals("×", driver.findElement(By.xpath("/html/body/section/div[2]/section[1]/div[1]/button")).getText());
  }

  @And("^sees the 'Garbage Collector' alert box has dismissed$")
  public void garbageCollector_alertBox_isDismissed() throws InterruptedException {
    Thread.sleep(2000);
    //Alertbox
    Assert.assertFalse(driver.findElement(By.xpath("/html/body/section/div[2]/section[1]/div[1]")).isDisplayed());
  }

  @And("^sees the 'Config Modal' and its options$")
  public void open_configModal() throws InterruptedException {
    Thread.sleep(2000);
    Assert.assertTrue(driver.findElement(By.id("open-config")).isDisplayed());
    Assert.assertEquals("Open Interlok Container Config", driver.findElement(By.xpath("//*[@id=\"open-config\"]/div/div[1]/h3/span")).getText());

    //TODO Refactor into guiTool method args: div container, tile name,

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

//    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[5]/div/div/div[2]/div[3]/div/div/div[2]/div[1]/select")).isDisplayed());
//    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[5]/div/div/div[2]/div[3]/div/div/div[2]/div[1]/select")).getText().contains("No Variable Set"));
//    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[5]/div/div/div[2]/div[3]/div/div/div[2]/div[1]/select")).getText().contains("default"));

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

  //Adapter Widget tiles

  @And("^finds the 'Summary Details' tile$")
  public void finds_summaryDetail_tile() {
    driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[1]/div")).click();
  }

  @And("^sees the available widgets$")
  public void sees_all_widgets() {
    //Header and selection refinement
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[1]/div[3]/div[2]/div/select")).isDisplayed());
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[1]/div[3]/div[2]/div/span/i")).isDisplayed());
    Assert.assertEquals("fa fa-channel", driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[1]/div[3]/div[2]/div/span/i")).getAttribute("class"));
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[1]")).isDisplayed());
    Assert.assertEquals("Viewing Widgets for selected Adapter", driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[1]")).getText());

    //Sees all 21 selectable tiles and the 'Consumer Messages Remaining' disabled
    //TODO Refactor!

    //Summary Details
    //Tile
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[1]")).isDisplayed());
    //Icons
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[1]/div/div[1]/div/i")).isDisplayed());
    Assert.assertEquals("fa fa-th-large", driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[1]/div/div[1]/div/i")).getAttribute("class"));
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[1]/div/div[3]/a/i")).isDisplayed());
    Assert.assertEquals("fa fa-info-circle action-icon component-action", driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[1]/div/div[3]/a/i")).getAttribute("class"));
    //Text
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[1]/div/div[2]/h4")).isDisplayed());
    Assert.assertEquals("Summary Details", driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[1]/div/div[2]/h4")).getText());
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[1]/div/div[2]/h5/span")).isDisplayed());
    Assert.assertEquals("Details", driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[1]/div/div[2]/h5/span")).getText());

    //Summary Details(Carousel)
    //Tile
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[2]")).isDisplayed());
    //Icons
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[2]/div/div[1]/div/i")).isDisplayed());
    Assert.assertEquals("fa fa-th-large", driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[2]/div/div[1]/div/i")).getAttribute("class"));
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[2]/div/div[3]/a/i")).isDisplayed());
    Assert.assertEquals("fa fa-info-circle action-icon component-action", driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[2]/div/div[3]/a/i")).getAttribute("class"));
    //Text
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[2]/div/div[2]/h4")).isDisplayed());
    Assert.assertEquals("Summary Details (carousel)", driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[2]/div/div[2]/h4")).getText());
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[2]/div/div[2]/h5/span")).isDisplayed());
    Assert.assertEquals("Details", driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[2]/div/div[2]/h5/span")).getText());


    //Control Panel
    //Tile
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[3]")).isDisplayed());
    //Icons
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[3]/div/div[1]/div/i")).isDisplayed());
    Assert.assertEquals("fa fa-gamepad", driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[3]/div/div[1]/div/i")).getAttribute("class"));
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[3]/div/div[3]/a/i")).isDisplayed());
    Assert.assertEquals("fa fa-info-circle action-icon component-action", driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[3]/div/div[3]/a/i")).getAttribute("class"));
    //Text
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[3]/div/div[2]/h4")).isDisplayed());
    Assert.assertEquals("Control Panel", driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[3]/div/div[2]/h4")).getText());
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[3]/div/div[2]/h5/span")).isDisplayed());
    Assert.assertEquals("Dashboard", driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[3]/div/div[2]/h5/span")).getText());



    //In Flight
    //Tile
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[4]")).isDisplayed());
    //Icons
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[4]/div/div[1]/div/i")).isDisplayed());
    Assert.assertEquals("fa fa-paper-plane-o", driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[4]/div/div[1]/div/i")).getAttribute("class"));
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[4]/div/div[3]/a/i")).isDisplayed());
    Assert.assertEquals("fa fa-info-circle action-icon component-action", driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[4]/div/div[3]/a/i")).getAttribute("class"));
    //Text
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[4]/div/div[2]/h4")).isDisplayed());
    Assert.assertEquals("In Flight", driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[4]/div/div[2]/h4")).getText());
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[4]/div/div[2]/h5/span")).isDisplayed());
    Assert.assertEquals("Dashboard", driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[4]/div/div[2]/h5/span")).getText());

    //Component Counts
    //Tile
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[5]")).isDisplayed());
    //Icons
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[5]/div/div[1]/div/i")).isDisplayed());
    Assert.assertEquals("fa fa-dashboard", driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[5]/div/div[1]/div/i")).getAttribute("class"));
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[5]/div/div[3]/a/i")).isDisplayed());
    Assert.assertEquals("fa fa-info-circle action-icon component-action", driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[5]/div/div[3]/a/i")).getAttribute("class"));
    //Text
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[5]/div/div[2]/h4")).isDisplayed());
    Assert.assertEquals("Component Counts", driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[5]/div/div[2]/h4")).getText());
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[5]/div/div[2]/h5/span")).isDisplayed());
    Assert.assertEquals("Details", driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[5]/div/div[2]/h5/span")).getText());

    //Message Counts Chart
    //Tile
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[6]")).isDisplayed());
    //Icons
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[6]/div/div[1]/div/i")).isDisplayed());
    Assert.assertEquals("fa fa-line-chart", driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[6]/div/div[1]/div/i")).getAttribute("class"));
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[6]/div/div[3]/a/i")).isDisplayed());
    Assert.assertEquals("fa fa-info-circle action-icon component-action", driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[6]/div/div[3]/a/i")).getAttribute("class"));
    //Text
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[6]/div/div[2]/h4")).isDisplayed());
    Assert.assertEquals("Message Counts Chart", driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[6]/div/div[2]/h4")).getText());
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[6]/div/div[2]/h5/span")).isDisplayed());
    Assert.assertEquals("Chart", driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[6]/div/div[2]/h5/span")).getText());

    //Daily Message Counts Chart
    //Tile
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[7]")).isDisplayed());
    //Icons
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[7]/div/div[1]/div/i")).isDisplayed());
    Assert.assertEquals("fa fa-line-chart", driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[7]/div/div[1]/div/i")).getAttribute("class"));
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[7]/div/div[3]/a/i")).isDisplayed());
    Assert.assertEquals("fa fa-info-circle action-icon component-action", driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[7]/div/div[3]/a/i")).getAttribute("class"));
    //Text
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[7]/div/div[2]/h4")).isDisplayed());
    Assert.assertEquals("Daily Message Counts Chart", driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[7]/div/div[2]/h4")).getText());
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[7]/div/div[2]/h5/span")).isDisplayed());
    Assert.assertEquals("Chart", driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[7]/div/div[2]/h5/span")).getText());

    //Message Counts Pie Chart
    //Tile
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[8]")).isDisplayed());
    //Icons
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[8]/div/div[1]/div/i")).isDisplayed());
    Assert.assertEquals("fa fa-pie-chart", driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[8]/div/div[1]/div/i")).getAttribute("class"));
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[8]/div/div[3]/a/i")).isDisplayed());
    Assert.assertEquals("fa fa-info-circle action-icon component-action", driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[8]/div/div[3]/a/i")).getAttribute("class"));
    //Text
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[8]/div/div[2]/h4")).isDisplayed());
    Assert.assertEquals("Message Counts Pie Chart", driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[8]/div/div[2]/h4")).getText());
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[8]/div/div[2]/h5/span")).isDisplayed());
    Assert.assertEquals("Chart", driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[8]/div/div[2]/h5/span")).getText());

    //Failed Messages Table
    //Tile
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[9]")).isDisplayed());
    //Icons
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[9]/div/div[1]/div/i")).isDisplayed());
    Assert.assertEquals("fa fa-table", driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[9]/div/div[1]/div/i")).getAttribute("class"));
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[9]/div/div[3]/a/i")).isDisplayed());
    Assert.assertEquals("fa fa-info-circle action-icon component-action", driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[9]/div/div[3]/a/i")).getAttribute("class"));
    //Text
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[9]/div/div[2]/h4")).isDisplayed());
    Assert.assertEquals("Failed Messages Table", driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[9]/div/div[2]/h4")).getText());
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[9]/div/div[2]/h5/span")).isDisplayed());
    Assert.assertEquals("Table", driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[9]/div/div[2]/h5/span")).getText());

    //Platform Heap Memory Details
    //Tile
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[10]")).isDisplayed());
    //Icons
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[10]/div/div[1]/div/i")).isDisplayed());
    Assert.assertEquals("fa fa-th-large", driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[10]/div/div[1]/div/i")).getAttribute("class"));
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[10]/div/div[3]/a/i")).isDisplayed());
    Assert.assertEquals("fa fa-info-circle action-icon component-action", driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[10]/div/div[3]/a/i")).getAttribute("class"));
    //Text
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[10]/div/div[2]/h4")).isDisplayed());
    Assert.assertEquals("Platform Heap Memory Details", driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[10]/div/div[2]/h4")).getText());
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[10]/div/div[2]/h5/span")).isDisplayed());
    Assert.assertEquals("Platform", driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[10]/div/div[2]/h5/span")).getText());

    //Platform Non Heap Memory Details
    //Tile
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[11]")).isDisplayed());
    //Icons
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[11]/div/div[1]/div/i")).isDisplayed());
    Assert.assertEquals("fa fa-th-large", driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[11]/div/div[1]/div/i")).getAttribute("class"));
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[11]/div/div[3]/a/i")).isDisplayed());
    Assert.assertEquals("fa fa-info-circle action-icon component-action", driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[11]/div/div[3]/a/i")).getAttribute("class"));
    //Text
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[11]/div/div[2]/h4")).isDisplayed());
    Assert.assertEquals("Platform Non Heap Memory Details", driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[11]/div/div[2]/h4")).getText());
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[11]/div/div[2]/h5/span")).isDisplayed());
    Assert.assertEquals("Platform", driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[11]/div/div[2]/h5/span")).getText());

    //Platform Memory Heap Chart
    //Tile
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[12]")).isDisplayed());
    //Icons
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[12]/div/div[1]/div/i")).isDisplayed());
    Assert.assertEquals("fa fa-line-chart", driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[12]/div/div[1]/div/i")).getAttribute("class"));
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[12]/div/div[3]/a/i")).isDisplayed());
    Assert.assertEquals("fa fa-info-circle action-icon component-action", driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[12]/div/div[3]/a/i")).getAttribute("class"));
    //Text
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[12]/div/div[2]/h4")).isDisplayed());
    Assert.assertEquals("Platform Memory Heap Chart", driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[12]/div/div[2]/h4")).getText());
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[12]/div/div[2]/h5/span")).isDisplayed());
    Assert.assertEquals("Platform", driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[12]/div/div[2]/h5/span")).getText());

    //Platform Memory Non Heap Chart
    //Tile
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[13]")).isDisplayed());
    //Icons
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[13]/div/div[1]/div/i")).isDisplayed());
    Assert.assertEquals("fa fa-line-chart", driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[13]/div/div[1]/div/i")).getAttribute("class"));
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[13]/div/div[3]/a/i")).isDisplayed());
    Assert.assertEquals("fa fa-info-circle action-icon component-action", driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[13]/div/div[3]/a/i")).getAttribute("class"));
    //Text
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[13]/div/div[2]/h4")).isDisplayed());
    Assert.assertEquals("Platform Memory Non Heap Chart", driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[13]/div/div[2]/h4")).getText());
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[14]/div/div[2]/h5/span")).isDisplayed());
    Assert.assertEquals("Platform", driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[14]/div/div[2]/h5/span")).getText());

    //Platform Runtime Path Details
    //Tile
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[14]")).isDisplayed());
    //Icons
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[14]/div/div[1]/div/i")).isDisplayed());
    Assert.assertEquals("fa fa-list", driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[14]/div/div[1]/div/i")).getAttribute("class"));
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[14]/div/div[3]/a/i")).isDisplayed());
    Assert.assertEquals("fa fa-info-circle action-icon component-action", driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[14]/div/div[3]/a/i")).getAttribute("class"));
    //Text
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[14]/div/div[2]/h4")).isDisplayed());
    Assert.assertEquals("Platform Runtime Path Details", driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[14]/div/div[2]/h4")).getText());
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[14]/div/div[2]/h5/span")).isDisplayed());
    Assert.assertEquals("Platform", driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[14]/div/div[2]/h5/span")).getText());

    //Platform Runtime Details
    //Tile
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[15]")).isDisplayed());
    //Icons
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[15]/div/div[1]/div/i")).isDisplayed());
    Assert.assertEquals("fa fa-list", driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[15]/div/div[1]/div/i")).getAttribute("class"));
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[15]/div/div[3]/a/i")).isDisplayed());
    Assert.assertEquals("fa fa-info-circle action-icon component-action", driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[15]/div/div[3]/a/i")).getAttribute("class"));
    //Text
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[15]/div/div[2]/h4")).isDisplayed());
    Assert.assertEquals("Platform Runtime Details", driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[15]/div/div[2]/h4")).getText());
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[15]/div/div[2]/h5/span")).isDisplayed());
    Assert.assertEquals("Platform", driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[15]/div/div[2]/h5/span")).getText());

    //Platform Runtime System Details
    //Tile
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[16]")).isDisplayed());
    //Icons
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[16]/div/div[1]/div/i")).isDisplayed());
    Assert.assertEquals("fa fa-list", driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[16]/div/div[1]/div/i")).getAttribute("class"));
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[16]/div/div[3]/a/i")).isDisplayed());
    Assert.assertEquals("fa fa-info-circle action-icon component-action", driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[16]/div/div[3]/a/i")).getAttribute("class"));
    //Text
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[16]/div/div[2]/h4")).isDisplayed());
    Assert.assertEquals("Platform Runtime System Details", driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[16]/div/div[2]/h4")).getText());
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[16]/div/div[2]/h5/span")).isDisplayed());
    Assert.assertEquals("Platform", driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[16]/div/div[2]/h5/span")).getText());

    //Platform Operating System Details
    //Tile
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[17]")).isDisplayed());
    //Icons
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[17]/div/div[1]/div/i")).isDisplayed());
    Assert.assertEquals("fa fa-th-large", driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[17]/div/div[1]/div/i")).getAttribute("class"));
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[17]/div/div[3]/a/i")).isDisplayed());
    Assert.assertEquals("fa fa-info-circle action-icon component-action", driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[17]/div/div[3]/a/i")).getAttribute("class"));
    //Text
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[17]/div/div[2]/h4")).isDisplayed());
    Assert.assertEquals("Platform Operating System Details", driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[17]/div/div[2]/h4")).getText());
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[17]/div/div[2]/h5/span")).isDisplayed());
    Assert.assertEquals("Platform", driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[17]/div/div[2]/h5/span")).getText());

    //Platform JVM Process Load Chart
    //Tile
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[18]")).isDisplayed());
    //Icons
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[18]/div/div[1]/div/i")).isDisplayed());
    Assert.assertEquals("fa fa-line-chart", driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[18]/div/div[1]/div/i")).getAttribute("class"));
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[18]/div/div[3]/a/i")).isDisplayed());
    Assert.assertEquals("fa fa-info-circle action-icon component-action", driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[18]/div/div[3]/a/i")).getAttribute("class"));
    //Text
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[18]/div/div[2]/h4")).isDisplayed());
    Assert.assertEquals("Platform JVM Process Load Chart", driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[18]/div/div[2]/h4")).getText());
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[18]/div/div[2]/h5/span")).isDisplayed());
    Assert.assertEquals("Platform", driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[18]/div/div[2]/h5/span")).getText());

    //Platform System CPU Load Chart
    //Tile
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[19]")).isDisplayed());
    //Icons
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[19]/div/div[1]/div/i")).isDisplayed());
    Assert.assertEquals("fa fa-line-chart", driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[19]/div/div[1]/div/i")).getAttribute("class"));
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[19]/div/div[3]/a/i")).isDisplayed());
    Assert.assertEquals("fa fa-info-circle action-icon component-action", driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[19]/div/div[3]/a/i")).getAttribute("class"));
    //Text
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[19]/div/div[2]/h4")).isDisplayed());
    Assert.assertEquals("Platform System CPU Load Chart", driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[19]/div/div[2]/h4")).getText());
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[19]/div/div[2]/h5/span")).isDisplayed());
    Assert.assertEquals("Platform", driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[19]/div/div[2]/h5/span")).getText());

    //Platform Thread Details
    //Tile
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[20]")).isDisplayed());
    //Icons
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[20]/div/div[1]/div/i")).isDisplayed());
    Assert.assertEquals("fa fa-th-large", driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[20]/div/div[1]/div/i")).getAttribute("class"));
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[20]/div/div[3]/a/i")).isDisplayed());
    Assert.assertEquals("fa fa-info-circle action-icon component-action", driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[20]/div/div[3]/a/i")).getAttribute("class"));
    //Text
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[20]/div/div[2]/h4")).isDisplayed());
    Assert.assertEquals("Platform Thread Details", driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[20]/div/div[2]/h4")).getText());
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[20]/div/div[2]/h5/span")).isDisplayed());
    Assert.assertEquals("Platform", driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[20]/div/div[2]/h5/span")).getText());

    //Consumer Messages Remaining(Disabled)
    //Tile
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[21]")).isDisplayed());
    //Icons
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[21]/div/div[1]/div/i")).isDisplayed());
    Assert.assertEquals("fa fa-envelope-o", driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[21]/div/div[1]/div/i")).getAttribute("class"));
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[21]/div/div[3]/a/i")).isDisplayed());
    Assert.assertEquals("fa fa-exclamation-triangle", driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[21]/div/div[3]/a/i")).getAttribute("class"));
    //Text
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[21]/div/div[2]/h4")).isDisplayed());
    Assert.assertEquals("Consumer Messages Remaining", driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[21]/div/div[2]/h4")).getText());
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[21]/div/div[2]/h5/span")).isDisplayed());
    Assert.assertEquals("Dashboard", driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[21]/div/div[2]/h5/span")).getText());
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[21]/div/div[3]/a")).isDisplayed());
    Assert.assertEquals("Option Disabled", driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[21]/div/div[3]/a")).getText());
    //Disabled View
    Assert.assertEquals("card choice-card choice-card-disabled widget-component-dashboard", driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[21]/div")).getAttribute("class"));

    //Logs Table - JMX Log Appender
    //Tile
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[22]")).isDisplayed());
    //Icons
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[22]/div/div[1]/div/i")).isDisplayed());
    Assert.assertEquals("fa fa-table", driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[22]/div/div[1]/div/i")).getAttribute("class"));
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[22]/div/div[3]/a/i")).isDisplayed());
    Assert.assertEquals("fa fa-info-circle action-icon component-action", driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[22]/div/div[3]/a/i")).getAttribute("class"));
    //Text
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[22]/div/div[2]/h4")).isDisplayed());
    Assert.assertEquals("Logs Table - Jmx Log Appender", driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[22]/div/div[2]/h4")).getText());
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[22]/div/div[2]/h5/span")).isDisplayed());
    Assert.assertEquals("Table", driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div[22]/div/div[2]/h5/span")).getText());

  }

  @And("^sees the 21 widgets added to the adapter$")
  public void sees_added_widgets() throws InterruptedException {
    Actions action = new Actions(driver);

    Thread.sleep(3000);
    //Header and selection refinement
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[2]/div/div[1]/div/button/b/i")).isDisplayed());
    Assert.assertEquals("fa fa-object-group", driver.findElement(By.xpath("/html/body/section/div[2]/section[2]/div/div[1]/div/button/b/i")).getAttribute("class"));
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[2]/div/div[1]/div/button/b/span")).isDisplayed());
    Assert.assertEquals("Default", driver.findElement(By.xpath("/html/body/section/div[2]/section[2]/div/div[1]/div/button/b/span")).getText());
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[2]/div/div[1]/div/span")).isDisplayed());
    Assert.assertEquals("The default widget group", driver.findElement(By.xpath("/html/body/section/div[2]/section[2]/div/div[1]/div/span")).getText());


    //Summary Details
    //Tile is displayed
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[1]")).isDisplayed());
    //Tile header bar
    //Icon
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[1]/div[1]/div/div[1]/div[1]/span[1]/i")).isDisplayed());
    Assert.assertEquals("fa fa-adapter", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[1]/div[1]/div/div[1]/div[1]/span[1]/i")).getAttribute("class"));
    //Text
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[1]/div[1]/div/div[1]/div[1]/span[2]")).isDisplayed());
    Assert.assertEquals("Summary Details", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[1]/div[1]/div/div[1]/div[1]/span[2]")).getText());
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[1]/div[1]/div/div[1]/div[2]/span[2]")).isDisplayed());
    Assert.assertEquals("Local Adapter - config-001-basic-components", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[1]/div[1]/div/div[1]/div[2]/span[2]")).getText());
    //Tile content
    //Adapter Status
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[1]/div[1]/div/div[2]/div[2]/div/div/div[1]/div/div[1]/span/span/span/i")).isDisplayed());
//    Assert.assertEquals("fa fa-check text-success", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[1]/div[1]/div/div[2]/div[2]/div/div/div[1]/div/div[1]/span/span/span/i")).getAttribute("class"));
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[1]/div[1]/div/div[2]/div[2]/div/div/div[1]/div/div[2]/span")).isDisplayed());
    Assert.assertEquals("STARTED", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[1]/div[1]/div/div[2]/div[2]/div/div/div[1]/div/div[2]/span")).getText());
    //Channel(s) on adapter
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[1]/div[1]/div/div[2]/div[2]/div/div/div[2]/div/div[1]/span")).isDisplayed());
    Assert.assertEquals("4", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[1]/div[1]/div/div[2]/div[2]/div/div/div[2]/div/div[1]/span")).getText());
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[1]/div[1]/div/div[2]/div[2]/div/div/div[2]/div/div[2]/span")).isDisplayed());
    Assert.assertEquals("Channel(s)", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[1]/div[1]/div/div[2]/div[2]/div/div/div[2]/div/div[2]/span")).getText());
    //Failed Messages
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[1]/div[1]/div/div[2]/div[2]/div/div/div[3]/div/div[1]/span/i")).isDisplayed());
    Assert.assertEquals("fa fa-envelope-o text-muted", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[1]/div[1]/div/div[2]/div[2]/div/div/div[3]/div/div[1]/span/i")).getAttribute("class"));
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[1]/div[1]/div/div[2]/div[2]/div/div/div[3]/div/div[1]/span/span")).isDisplayed());
    Assert.assertEquals("0", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[1]/div[1]/div/div[2]/div[2]/div/div/div[3]/div/div[1]/span/span")).getText());
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[1]/div[1]/div/div[2]/div[2]/div/div/div[3]/div/div[2]")).isDisplayed());
    Assert.assertEquals("Failed Messages", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[1]/div[1]/div/div[2]/div[2]/div/div/div[3]/div/div[2]")).getText());
    //Last Started
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[1]/div[1]/div/div[2]/div[2]/div/div/div[4]/div/div[1]/span")).isDisplayed());
  //        Assert.assertEquals("", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[1]/div[1]/div/div[2]/div[2]/div/div/div[4]/div/div[1]/span")).getText());
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[1]/div[1]/div/div[2]/div[2]/div/div/div[4]/div/div[2]")).isDisplayed());
    Assert.assertEquals("Last Started", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[1]/div[1]/div/div[2]/div[2]/div/div/div[4]/div/div[2]")).getText());
    //Tile Action Buttons
    tileHiddenActions("1");
    tileBasicVisibleActions("1");


    //Summary Details(Carousel)
    //Tile is displayed
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[2]")).isDisplayed());
    //Tile header bar
    //Icon
    Assert.assertTrue(driver.findElement(By.cssSelector(".glyphicon-chevron-left")).isDisplayed());
    Assert.assertEquals("glyphicon glyphicon-chevron-left", driver.findElement(By.cssSelector(".glyphicon-chevron-left")).getAttribute("class"));
    Assert.assertTrue(driver.findElement(By.cssSelector(".glyphicon-chevron-right")).isDisplayed());
    Assert.assertEquals("glyphicon glyphicon-chevron-right", driver.findElement(By.cssSelector(".glyphicon-chevron-right")).getAttribute("class"));
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[2]/div[1]/div/div[1]/div[1]/span[1]/i")).isDisplayed());
    Assert.assertEquals("fa fa-adapter", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[2]/div[1]/div/div[1]/div[1]/span[1]/i")).getAttribute("class"));
    //Text
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[2]/div[1]/div/div[1]/div[1]/span[2]")).isDisplayed());
    Assert.assertEquals("Summary Details (carousel)", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[2]/div[1]/div/div[1]/div[1]/span[2]")).getText());
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[2]/div[1]/div/div[1]/div[2]/span[2]")).isDisplayed());
    Assert.assertEquals("Local Adapter - config-001-basic-components", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[2]/div[1]/div/div[1]/div[2]/span[2]")).getText());
    //Tile content
    //Adapter Status
    driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[2]/div[1]/div/div[2]/div[2]/div/div/ol/li[1]")).click();
    Thread.sleep(1000);
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[2]/div[1]/div/div[2]/div[2]/div/div/div/div[1]/span/span/span/i")).isDisplayed());
    Assert.assertEquals("fa fa-check text-success", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[2]/div[1]/div/div[2]/div[2]/div/div/div/div[1]/span/span/span/i")).getAttribute("class"));
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[2]/div[1]/div/div[2]/div[2]/div/div/div/div[1]/div/span")).isDisplayed());
    Assert.assertEquals("STARTED", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[2]/div[1]/div/div[2]/div[2]/div/div/div/div[1]/div/span")).getText());
    //Channel(s) on adapter
    driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[2]/div[1]/div/div[2]/div[2]/div/div/ol/li[2]")).click();
    Thread.sleep(1000);
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[2]/div[1]/div/div[2]/div[2]/div/div/div/div[2]/span")).isDisplayed());
    Assert.assertEquals("4", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[2]/div[1]/div/div[2]/div[2]/div/div/div/div[2]/span")).getText());
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[2]/div[1]/div/div[2]/div[2]/div/div/div/div[2]/div/span")).isDisplayed());
    Assert.assertEquals("Channel(s)", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[2]/div[1]/div/div[2]/div[2]/div/div/div/div[2]/div/span")).getText());
    //Failed Messages
    driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[2]/div[1]/div/div[2]/div[2]/div/div/ol/li[3]")).click();
    Thread.sleep(1000);
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[2]/div[1]/div/div[2]/div[2]/div/div/div/div[3]/i")).isDisplayed());
    Assert.assertEquals("fa fa-envelope-o text-muted", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[2]/div[1]/div/div[2]/div[2]/div/div/div/div[3]/i")).getAttribute("class"));
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[2]/div[1]/div/div[2]/div[2]/div/div/div/div[3]/span")).isDisplayed());
    Assert.assertEquals("0", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[2]/div[1]/div/div[2]/div[2]/div/div/div/div[3]/span")).getText());
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[2]/div[1]/div/div[2]/div[2]/div/div/div/div[3]/div")).isDisplayed());
    Assert.assertEquals("Failed Messages", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[2]/div[1]/div/div[2]/div[2]/div/div/div/div[3]/div")).getText());
    //Last Started
    driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[2]/div[1]/div/div[2]/div[2]/div/div/ol/li[4]")).click();
    Thread.sleep(1000);
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[2]/div[1]/div/div[2]/div[2]/div/div/div/div[4]/span[1]")).isDisplayed());
    //        Assert.assertEquals("", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[2]/div[1]/div/div[2]/div[2]/div/div/div/div[4]/span[1]")).getText());
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[2]/div[1]/div/div[2]/div[2]/div/div/div/div[4]/div/span[1]")).isDisplayed());
    Assert.assertEquals("Last Started", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[2]/div[1]/div/div[2]/div[2]/div/div/div/div[4]/div/span[1]")).getText());
    driver.findElement(By.xpath("/html/body/section/div[2]/section[2]/div/div[1]/div/span")).click();
    //Tile Action Buttons
    tileHiddenActions("2");
    tileBasicVisibleActions("2");


    //Control Panel
    //Tile is displayed
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[3]")).isDisplayed());
    //Tile header bar
    //Icon
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[3]/div[1]/div/div[1]/div[1]/span[1]/i")).isDisplayed());
    Assert.assertEquals("fa fa-adapter", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[3]/div[1]/div/div[1]/div[1]/span[1]/i")).getAttribute("class"));
    //Text
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[3]/div[1]/div/div[1]/div[1]/span[2]")).isDisplayed());
    Assert.assertEquals("Control Panel", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[3]/div[1]/div/div[1]/div[1]/span[2]")).getText());
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[3]/div[1]/div/div[1]/div[2]/span[2]")).isDisplayed());
    Assert.assertEquals("Local Adapter - config-001-basic-components", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[3]/div[1]/div/div[1]/div[2]/span[2]")).getText());
    //Tile content
    //Adapter Status
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[3]/div[1]/div/div[2]/div[2]/div/div[1]/span/span/span/i")).isDisplayed());
    Assert.assertEquals("fa fa-check text-success", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[3]/div[1]/div/div[2]/div[2]/div/div[1]/span/span/span/i")).getAttribute("class"));
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[3]/div[1]/div/div[2]/div[2]/div/div[1]/p/span")).isDisplayed());
    Assert.assertEquals("STARTED", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[3]/div[1]/div/div[2]/div[2]/div/div[1]/p/span")).getText());
    //Control Buttons
    //Start
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[3]/div[1]/div/div[2]/div[2]/div/div[2]/button[1]/i")).isDisplayed());
    Assert.assertEquals("fa fa-play", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[3]/div[1]/div/div[2]/div[2]/div/div[2]/button[1]/i")).getAttribute("class"));
    //Pause
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[3]/div[1]/div/div[2]/div[2]/div/div[2]/button[2]/i")).isDisplayed());
    Assert.assertEquals("fa fa-pause", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[3]/div[1]/div/div[2]/div[2]/div/div[2]/button[2]/i")).getAttribute("class"));
    //Stop
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[3]/div[1]/div/div[2]/div[2]/div/div[2]/button[3]/i")).isDisplayed());
    Assert.assertEquals("fa fa-stop", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[3]/div[1]/div/div[2]/div[2]/div/div[2]/button[3]/i")).getAttribute("class"));
    //Tile Action Buttons
    tileHiddenActions("3");
    tileBasicVisibleActions("3");


    //In Flight
    //Tile is displayed
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[4]")).isDisplayed());
    //Tile header bar
    //Icon
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[4]/div[1]/div/div[1]/div[1]/span[1]/i")).isDisplayed());
    Assert.assertEquals("fa fa-adapter", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[4]/div[1]/div/div[1]/div[1]/span[1]/i")).getAttribute("class"));
    //Text
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[4]/div[1]/div/div[1]/div[1]/span[2]")).isDisplayed());
    Assert.assertEquals("In Flight", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[4]/div[1]/div/div[1]/div[1]/span[2]")).getText());
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[4]/div[1]/div/div[1]/div[2]/span[2]")).isDisplayed());
    Assert.assertEquals("Local Adapter - config-001-basic-components", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[4]/div[1]/div/div[1]/div[2]/span[2]")).getText());
    //Tile content
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[4]/div[1]/div/div[2]/div[2]/div/i")).isDisplayed());
    Assert.assertEquals("fa fa-paper-plane-o text-muted", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[4]/div[1]/div/div[2]/div[2]/div/i")).getAttribute("class"));
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[4]/div[1]/div/div[2]/div[2]/div/span")).isDisplayed());
    Assert.assertEquals("0", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[4]/div[1]/div/div[2]/div[2]/div/span")).getText());
    //Tile Action Buttons
    tileHiddenActions("4");
    tileBasicVisibleActions("4");


    //Component Counts
    //Tile is displayed
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[5]")).isDisplayed());
    //Tile header bar
    //Icon
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[5]/div[1]/div/div[1]/div[1]/span[1]/i")).isDisplayed());
    Assert.assertEquals("fa fa-adapter", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[5]/div[1]/div/div[1]/div[1]/span[1]/i")).getAttribute("class"));
    //Text
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[5]/div[1]/div/div[1]/div[1]/span[2]")).isDisplayed());
    Assert.assertEquals("Component Counts", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[5]/div[1]/div/div[1]/div[1]/span[2]")).getText());
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[5]/div[1]/div/div[1]/div[2]/span[2]")).isDisplayed());
    Assert.assertEquals("Local Adapter - config-001-basic-components", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[5]/div[1]/div/div[1]/div[2]/span[2]")).getText());
    //Tile content
    //Tile Action Buttons
    tileHiddenActions("5");
    tileBasicVisibleActions("5");


    //Message Counts Chart
    //Tile is displayed
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[6]")).isDisplayed());
    //Tile header bar
    //Icon
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[6]/div[1]/div/div[1]/div[1]/span[1]/i")).isDisplayed());
    Assert.assertEquals("fa fa-adapter", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[6]/div[1]/div/div[1]/div[1]/span[1]/i")).getAttribute("class"));
    //Text
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[6]/div[1]/div/div[1]/div[1]/span[2]")).isDisplayed());
    Assert.assertEquals("Message Counts Chart", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[6]/div[1]/div/div[1]/div[1]/span[2]")).getText());
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[6]/div[1]/div/div[1]/div[2]/span[2]")).isDisplayed());
    Assert.assertEquals("Local Adapter - config-001-basic-components", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[6]/div[1]/div/div[1]/div[2]/span[2]")).getText());
    //Tile content
    // Tile Action Buttons
    tileHiddenActions("6");
    tileAdvancedVisibleActions("6", "fa fa-eraser", "fa fa-refresh", "fa fa-times");


    //Daily Message Counts Chart
    //Tile is displayed
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[7]")).isDisplayed());
    //Tile header bar
      //Icon
      Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[7]/div[1]/div/div[1]/div[1]/span[1]/i")).isDisplayed());
      Assert.assertEquals("fa fa-adapter", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[7]/div[1]/div/div[1]/div[1]/span[1]/i")).getAttribute("class"));
      //Text
      Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[7]/div[1]/div/div[1]/div[1]/span[2]")).isDisplayed());
      Assert.assertEquals("Daily Message Counts Chart", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[7]/div[1]/div/div[1]/div[1]/span[2]")).getText());
      Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[8]/div[1]/div/div[1]/div[2]/span[2]")).isDisplayed());
      Assert.assertEquals("Local Adapter - config-001-basic-components", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[8]/div[1]/div/div[1]/div[2]/span[2]")).getText());
    //Tile content
    //Tile Action Buttons
    tileHiddenActions("7");
    tileAdvancedVisibleMirroredActions("7", "fa fa-calendar", "fa fa-refresh", "fa fa-times");


    //Message Counts Pie Chart
    //Tile is displayed
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[8]")).isDisplayed());
    //Tile header bar
    //Icon
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[8]/div[1]/div/div[1]/div[1]/span[1]/i")).isDisplayed());
    Assert.assertEquals("fa fa-adapter", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[8]/div[1]/div/div[1]/div[1]/span[1]/i")).getAttribute("class"));
    //Text
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[8]/div[1]/div/div[1]/div[1]/span[2]")).isDisplayed());
    Assert.assertEquals("Message Counts Pie Chart", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[8]/div[1]/div/div[1]/div[1]/span[2]")).getText());
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[8]/div[1]/div/div[1]/div[2]/span[2]")).isDisplayed());
    Assert.assertEquals("Local Adapter - config-001-basic-components", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[8]/div[1]/div/div[1]/div[2]/span[2]")).getText());
    //Tile content
    //Tile Action Buttons
    tileHiddenActions("8");
    tileAdvancedVisibleActions("8", "fa fa-eraser", "fa fa-refresh", "fa fa-times");


    //Failed Messages Table
    //Tile is displayed
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[9]")).isDisplayed());
    //Tile header bar
    //Icon
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[9]/div[1]/div/div[1]/div[1]/span[1]/i")).isDisplayed());
    Assert.assertEquals("fa fa-adapter", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[9]/div[1]/div/div[1]/div[1]/span[1]/i")).getAttribute("class"));
    //Text
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[9]/div[1]/div/div[1]/div[1]/span[2]")).isDisplayed());
    Assert.assertEquals("Failed Messages Table", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[9]/div[1]/div/div[1]/div[1]/span[2]")).getText());
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[9]/div[1]/div/div[1]/div[2]/span[2]")).isDisplayed());
    Assert.assertEquals("Local Adapter - config-001-basic-components", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[9]/div[1]/div/div[1]/div[2]/span[2]")).getText());
    //Tile content
    //Tile Action Buttons
    tileHiddenActions("9");
    tileBasicVisibleActions("9");


    //Platform Heap Memory Details
    //Tile is displayed
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[10]/div[1]/div")).isDisplayed());
    //Tile header bar
    //Icon
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[10]/div[1]/div/div[1]/div[1]/span[1]/i")).isDisplayed());
    Assert.assertEquals("fa fa-adapter", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[10]/div[1]/div/div[1]/div[1]/span[1]/i")).getAttribute("class"));
    //Text
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[10]/div[1]/div/div[1]/div[1]/span[2]")).isDisplayed());
    Assert.assertEquals("Platform Heap Memory Details", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[10]/div[1]/div/div[1]/div[1]/span[2]")).getText());
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[10]/div[1]/div/div[1]/div[2]/span[2]")).isDisplayed());
    Assert.assertEquals("Local Adapter - config-001-basic-components", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[10]/div[1]/div/div[1]/div[2]/span[2]")).getText());
    //Tile content
    //Tile Action Buttons
    tileHiddenActions("10");
    tileAdvancedVisibleActions("10", "fa fa-recycle", "fa fa-refresh", "fa fa-times");


    //Platform Non Heap Memory Details
    //Tile is displayed
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[11]")).isDisplayed());
    //Tile header bar
    //Icon
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[11]/div[1]/div/div[1]/div[1]/span[1]/i")).isDisplayed());
    Assert.assertEquals("fa fa-adapter", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[11]/div[1]/div/div[1]/div[1]/span[1]/i")).getAttribute("class"));
    //Text
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[11]/div[1]/div/div[1]/div[1]/span[2]")).isDisplayed());
    Assert.assertEquals("Platform Non Heap Memory Details", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[11]/div[1]/div/div[1]/div[1]/span[2]")).getText());
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[11]/div[1]/div/div[1]/div[2]/span[2]")).isDisplayed());
    Assert.assertEquals("Local Adapter - config-001-basic-components", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[11]/div[1]/div/div[1]/div[2]/span[2]")).getText());
    //Tile content
    //Tile Action Buttons
    tileHiddenActions("11");
    tileAdvancedVisibleActions("11", "fa fa-recycle", "fa fa-refresh", "fa fa-times");


    //Platform Memory Heap Chart
    //Tile is displayed
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[12]")).isDisplayed());
    //Tile header bar
    //Icon
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[12]/div[1]/div/div[1]/div[1]/span[1]/i")).isDisplayed());
    Assert.assertEquals("fa fa-adapter", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[12]/div[1]/div/div[1]/div[1]/span[1]/i")).getAttribute("class"));
    //Text
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[12]/div[1]/div/div[1]/div[1]/span[2]")).isDisplayed());
    Assert.assertEquals("Platform Memory Heap Chart", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[12]/div[1]/div/div[1]/div[1]/span[2]")).getText());
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[12]/div[1]/div/div[1]/div[2]/span[2]")).isDisplayed());
    Assert.assertEquals("Local Adapter - config-001-basic-components", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[12]/div[1]/div/div[1]/div[2]/span[2]")).getText());
    //Tile content
    //Tile Action Buttons
    tileHiddenActions("12");
    //Visible
    action.moveToElement(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[12]/div[1]/div/div[2]/div[2]/div"))).build().perform();
    Thread.sleep(2000);
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[12]/div[1]/div/div[3]/div")).isDisplayed());
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[12]/div[1]/div/div[3]/div/div[1]/a/i")).isDisplayed());
    Assert.assertEquals("fa fa-ellipsis-h", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[12]/div[1]/div/div[3]/div/div[1]/a/i")).getAttribute("class"));
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[12]/div[1]/div/div[3]/div/div[2]/div/div/button/i")).isDisplayed());
    Assert.assertEquals("fa fa-calendar", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[12]/div[1]/div/div[3]/div/div[2]/div/div/button/i")).getAttribute("class"));
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[12]/div[1]/div/div[3]/div/div[2]/div/button[1]/i")).isDisplayed());
    Assert.assertEquals("fa fa-recycle", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[12]/div[1]/div/div[3]/div/div[2]/div/button[1]/i")).getAttribute("class"));
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[12]/div[1]/div/div[3]/div/div[2]/div/button[2]/i")).isDisplayed());
    Assert.assertEquals("fa fa-refresh", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[12]/div[1]/div/div[3]/div/div[2]/div/button[2]/i")).getAttribute("class"));
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[12]/div[1]/div/div[3]/div/div[2]/div/button[3]/i")).isDisplayed());
    Assert.assertEquals("fa fa-times", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[12]/div[1]/div/div[3]/div/div[2]/div/button[3]/i")).getAttribute("class"));
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[12]/div[3]")).isDisplayed());
    Assert.assertEquals("ui-resizable-handle ui-resizable-sw", driver.findElement(By.xpath(("/html/body/section/div[2]/section[3]/div[1]/div[12]/div[3]"))).getAttribute("class"));
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[12]/div[2]")).isDisplayed());
    Assert.assertEquals("ui-resizable-handle ui-resizable-se ui-icon ui-icon-gripsmall-diagonal-se", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[12]/div[2]")).getAttribute("class"));


    //Platform Memory Non Heap Chart
    //Tile is displayed
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[13]")).isDisplayed());
    //Tile header bar
    //Icon
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[13]/div[1]/div/div[1]/div[1]/span[1]/i")).isDisplayed());
    Assert.assertEquals("fa fa-adapter", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[13]/div[1]/div/div[1]/div[1]/span[1]/i")).getAttribute("class"));
    //Text
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[13]/div[1]/div/div[1]/div[1]/span[2]")).isDisplayed());
    Assert.assertEquals("Platform Memory Non Heap Chart", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[13]/div[1]/div/div[1]/div[1]/span[2]")).getText());
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[13]/div[1]/div/div[1]/div[2]/span[2]")).isDisplayed());
    Assert.assertEquals("Local Adapter - config-001-basic-components", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[13]/div[1]/div/div[1]/div[2]/span[2]")).getText());
    //Tile content
    //Tile Action Buttons
    tileHiddenActions("13");
    tileAdvancedVisibleMirroredActions("13", "fa fa-calendar", "fa fa-refresh", "fa fa-times");


    //Platform Runtime Path Details
    //Tile is displayed
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[14]")).isDisplayed());
    //Tile header bar
    //Icon
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[14]/div[1]/div/div[1]/div[1]/span[1]/i")).isDisplayed());
    Assert.assertEquals("fa fa-adapter", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[14]/div[1]/div/div[1]/div[1]/span[1]/i")).getAttribute("class"));
    //Text
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[14]/div[1]/div/div[1]/div[1]/span[2]")).isDisplayed());
    Assert.assertEquals("Platform Runtime Path Details", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[14]/div[1]/div/div[1]/div[1]/span[2]")).getText());
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[14]/div[1]/div/div[1]/div[2]/span[2]")).isDisplayed());
    Assert.assertEquals("Local Adapter - config-001-basic-components", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[14]/div[1]/div/div[1]/div[2]/span[2]")).getText());
    //Tile content
    //Tile Action Buttons
    tileHiddenActions("14");
    tileBasicVisibleActions("14");


    //Platform Runtime Details
    //Tile is displayed
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[15]")).isDisplayed());
    //Tile header bar
    //Icon
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[15]/div[1]/div/div[1]/div[1]/span[1]/i")).isDisplayed());
    Assert.assertEquals("fa fa-adapter", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[15]/div[1]/div/div[1]/div[1]/span[1]/i")).getAttribute("class"));
    //Text
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[15]/div[1]/div/div[1]/div[1]/span[2]")).isDisplayed());
    Assert.assertEquals("Platform Runtime Details", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[15]/div[1]/div/div[1]/div[1]/span[2]")).getText());
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[15]/div[1]/div/div[1]/div[2]/span[2]")).isDisplayed());
    Assert.assertEquals("Local Adapter - config-001-basic-components", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[15]/div[1]/div/div[1]/div[2]/span[2]")).getText());
    //Tile content
    //Tile Action Buttons
    tileHiddenActions("15");
    tileBasicVisibleActions("15");



    //Platform Runtime System Details
    //Tile is displayed
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[16]")).isDisplayed());
    //Tile header bar
    //Icon
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[16]/div[1]/div/div[1]/div[1]/span[1]/i")).isDisplayed());
    Assert.assertEquals("fa fa-adapter", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[16]/div[1]/div/div[1]/div[1]/span[1]/i")).getAttribute("class"));
    //Text
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[16]/div[1]/div/div[1]/div[1]/span[2]")).isDisplayed());
    Assert.assertEquals("Platform Runtime System Details", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[16]/div[1]/div/div[1]/div[1]/span[2]")).getText());
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[16]/div[1]/div/div[1]/div[2]/span[2]")).isDisplayed());
    Assert.assertEquals("Local Adapter - config-001-basic-components", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[16]/div[1]/div/div[1]/div[2]/span[2]")).getText());
    //Tile content
    //Tile Action Buttons
    tileHiddenActions("16");
    //Visible
    action.moveToElement(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[16]/div[1]/div/div[2]/div[2]/div"))).build().perform();
    Thread.sleep(2000);
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[16]/div[1]/div/div[3]/div")).isDisplayed());
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[16]/div[1]/div/div[3]/div/div[1]/a/i")).isDisplayed());
    Assert.assertEquals("fa fa-ellipsis-h", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[16]/div[1]/div/div[3]/div/div[1]/a/i")).getAttribute("class"));
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[16]/div[1]/div/div[3]/div/div[2]/div/div/button/span")).isDisplayed());
    Assert.assertEquals("caret", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[16]/div[1]/div/div[3]/div/div[2]/div/div/button/span")).getAttribute("class"));
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[16]/div[1]/div/div[3]/div/div[2]/div/button[1]/i")).isDisplayed());
    Assert.assertEquals("fa fa-refresh", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[16]/div[1]/div/div[3]/div/div[2]/div/button[1]/i")).getAttribute("class"));
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[16]/div[1]/div/div[3]/div/div[2]/div/button[2]/i")).isDisplayed());
    Assert.assertEquals("fa fa-times", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[16]/div[1]/div/div[3]/div/div[2]/div/button[2]/i")).getAttribute("class"));
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[16]/div[3]")).isDisplayed());
    Assert.assertEquals("ui-resizable-handle ui-resizable-sw", driver.findElement(By.xpath(("/html/body/section/div[2]/section[3]/div[1]/div[16]/div[3]"))).getAttribute("class"));
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[16]/div[2]")).isDisplayed());
    Assert.assertEquals("ui-resizable-handle ui-resizable-se ui-icon ui-icon-gripsmall-diagonal-se", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[16]/div[2]")).getAttribute("class"));


    //Platform Operating System Details
    //Tile is displayed
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[17]")).isDisplayed());
    //Tile header bar
    //Icon
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[17]/div[1]/div/div[1]/div[1]/span[1]/i")).isDisplayed());
    Assert.assertEquals("fa fa-adapter", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[17]/div[1]/div/div[1]/div[1]/span[1]/i")).getAttribute("class"));
    //Text
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[17]/div[1]/div/div[1]/div[1]/span[2]")).isDisplayed());
    Assert.assertEquals("Platform Operating System Details", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[17]/div[1]/div/div[1]/div[1]/span[2]")).getText());
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[17]/div[1]/div/div[1]/div[2]/span[2]")).isDisplayed());
    Assert.assertEquals("Local Adapter - config-001-basic-components", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[17]/div[1]/div/div[1]/div[2]/span[2]")).getText());
    //Tile content
    //Tile Action Buttons
    tileHiddenActions("17");
    tileBasicVisibleActions("17");


    //Platform JVM Process Load Chart
    //Tile is displayed
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[18]")).isDisplayed());
    //Tile header bar
    //Icon
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[18]/div[1]/div/div[1]/div[1]/span[1]/i")).isDisplayed());
    Assert.assertEquals("fa fa-adapter", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[18]/div[1]/div/div[1]/div[1]/span[1]/i")).getAttribute("class"));
    //Text
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[18]/div[1]/div/div[1]/div[1]/span[2]")).isDisplayed());
    Assert.assertEquals("Platform JVM Process Load Chart", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[18]/div[1]/div/div[1]/div[1]/span[2]")).getText());
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[18]/div[1]/div/div[1]/div[2]/span[2]")).isDisplayed());
    Assert.assertEquals("Local Adapter - config-001-basic-components", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[18]/div[1]/div/div[1]/div[2]/span[2]")).getText());
    //Tile content
    //Tile Action Buttons
    tileHiddenActions("18");
    tileAdvancedVisibleMirroredActions("18", "fa fa-calendar", "fa fa-refresh", "fa fa-times");


    //Platform System CPU Load Chart
    //Tile is displayed
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[19]")).isDisplayed());
    //Tile header bar
    //Icon
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[19]/div[1]/div/div[1]/div[1]/span[1]/i")).isDisplayed());
    Assert.assertEquals("fa fa-adapter", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[19]/div[1]/div/div[1]/div[1]/span[1]/i")).getAttribute("class"));
    //Text
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[19]/div[1]/div/div[1]/div[1]/span[2]")).isDisplayed());
    Assert.assertEquals("Platform System CPU Load Chart", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[19]/div[1]/div/div[1]/div[1]/span[2]")).getText());
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[19]/div[1]/div/div[1]/div[2]/span[2]")).isDisplayed());
    Assert.assertEquals("Local Adapter - config-001-basic-components", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[19]/div[1]/div/div[1]/div[2]/span[2]")).getText());
    //Tile content
    //Tile Action Buttons
    tileHiddenActions("19");
    tileAdvancedVisibleMirroredActions("19", "fa fa-calendar", "fa fa-refresh", "fa fa-times");


    //Platform Thread Details
    //Tile is displayed
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[20]")).isDisplayed());
    //Tile header bar
    //Icon
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[20]/div[1]/div/div[1]/div[1]/span[1]/i")).isDisplayed());
    Assert.assertEquals("fa fa-adapter", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[20]/div[1]/div/div[1]/div[1]/span[1]/i")).getAttribute("class"));
    //Text
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[20]/div[1]/div/div[1]/div[1]/span[2]")).isDisplayed());
    Assert.assertEquals("Platform Thread Details", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[20]/div[1]/div/div[1]/div[1]/span[2]")).getText());
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[20]/div[1]/div/div[1]/div[2]/span[2]")).isDisplayed());
    Assert.assertEquals("Local Adapter - config-001-basic-components", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[20]/div[1]/div/div[1]/div[2]/span[2]")).getText());
    //Tile content
    //Tile Action Buttons
    tileHiddenActions("20");
    //Visible
    action.moveToElement(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[20]/div[1]/div/div[2]/div[2]/div"))).build().perform();
    Thread.sleep(2000);
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[20]/div[1]/div/div[3]/div")).isDisplayed());
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[20]/div[1]/div/div[3]/div/div[1]/a/i")).isDisplayed());
    Assert.assertEquals("fa fa-ellipsis-h", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[20]/div[1]/div/div[3]/div/div[1]/a/i")).getAttribute("class"));
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[20]/div[1]/div/div[3]/div/div[2]/div/button[1]/i")).isDisplayed());
    Assert.assertEquals("fa fa-bolt", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[20]/div[1]/div/div[3]/div/div[2]/div/button[1]/i")).getAttribute("class"));
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[20]/div[1]/div/div[3]/div/div[2]/div/button[2]/i")).isDisplayed());
    Assert.assertEquals("fa fa-refresh", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[20]/div[1]/div/div[3]/div/div[2]/div/button[2]/i")).getAttribute("class"));
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[20]/div[1]/div/div[3]/div/div[2]/div/button[3]/i")).isDisplayed());
    Assert.assertEquals("fa fa-times", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[20]/div[1]/div/div[3]/div/div[2]/div/button[3]/i")).getAttribute("class"));
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[20]/div[4]")).isDisplayed());
    Assert.assertEquals("ui-resizable-handle ui-resizable-sw", driver.findElement(By.xpath(("/html/body/section/div[2]/section[3]/div[1]/div[20]/div[4]"))).getAttribute("class"));
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[20]/div[3]")).isDisplayed());
    Assert.assertEquals("ui-resizable-handle ui-resizable-se ui-icon ui-icon-gripsmall-diagonal-se", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[20]/div[3]")).getAttribute("class"));


    //Logs Table - JMX Log Appender
    //Tile is displayed
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[21]")).isDisplayed());
    //Tile header bar
    //Icon
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[21]/div[1]/div/div[1]/div[1]/span[1]/i")).isDisplayed());
    Assert.assertEquals("fa fa-adapter", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[21]/div[1]/div/div[1]/div[1]/span[1]/i")).getAttribute("class"));
    //Text
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[21]/div[1]/div/div[1]/div[1]/span[2]")).isDisplayed());
    Assert.assertEquals("Logs Table - Jmx Log Appender", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[21]/div[1]/div/div[1]/div[1]/span[2]")).getText());
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[21]/div[1]/div/div[1]/div[2]/span[2]")).isDisplayed());
    Assert.assertEquals("Local Adapter - config-001-basic-components", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[21]/div[1]/div/div[1]/div[2]/span[2]")).getText());
    //Tile content
    //Tile Action Buttons
    tileHiddenActions("21");
    //Visible
    action.moveToElement(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[21]/div[1]/div/div[2]/div[2]/div"))).build().perform();
    Thread.sleep(2000);
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[21]/div[1]/div/div[3]/div")).isDisplayed());
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[21]/div[1]/div/div[3]/div/div[1]/a/i")).isDisplayed());
    Assert.assertEquals("fa fa-ellipsis-h", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[21]/div[1]/div/div[3]/div/div[1]/a/i")).getAttribute("class"));
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[21]/div[1]/div/div[3]/div/div[2]/div/form/div[1]/input")).isDisplayed());
    Assert.assertEquals("form-control", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[21]/div[1]/div/div[3]/div/div[2]/div/form/div[1]/input")).getAttribute("class"));
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[21]/div[1]/div/div[3]/div/div[2]/div/form/div[2]/div/button[1]/i")).isDisplayed());
    Assert.assertEquals("fa fa-refresh", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[21]/div[1]/div/div[3]/div/div[2]/div/form/div[2]/div/button[1]/i")).getAttribute("class"));
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[21]/div[1]/div/div[3]/div/div[2]/div/form/div[2]/div/button[2]/i")).isDisplayed());
    Assert.assertEquals("fa fa-times", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[21]/div[1]/div/div[3]/div/div[2]/div/form/div[2]/div/button[2]/i")).getAttribute("class"));
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[21]/div[3]")).isDisplayed());
    Assert.assertEquals("ui-resizable-handle ui-resizable-sw", driver.findElement(By.xpath(("/html/body/section/div[2]/section[3]/div[1]/div[21]/div[3]"))).getAttribute("class"));
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[21]/div[2]")).isDisplayed());
    Assert.assertEquals("ui-resizable-handle ui-resizable-se ui-icon ui-icon-gripsmall-diagonal-se", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[21]/div[2]")).getAttribute("class"));

  }

  @And("^sees the 'Remove this Widget' modal$")
  public void sees_remove_widgetModal() throws InterruptedException {
    Thread.sleep(2000);
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[7]")).isDisplayed());
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[7]/div/div/div[1]/div")).isDisplayed());
    Assert.assertEquals("Are you sure you want to remove this Widget?", driver.findElement(By.xpath("/html/body/div[7]/div/div/div[1]/div")).getText());
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[7]/div/div/div[1]/button")).isDisplayed());
    Assert.assertEquals("×", driver.findElement(By.xpath("/html/body/div[7]/div/div/div[1]/button")).getText());
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[7]/div/div/div[2]/button[1]")).isDisplayed());
    Assert.assertEquals("Cancel", driver.findElement(By.xpath("/html/body/div[7]/div/div/div[2]/button[1]")).getText());
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[7]/div/div/div[2]/button[2]")).isDisplayed());
    Assert.assertEquals("OK", driver.findElement(By.xpath("/html/body/div[7]/div/div/div[2]/button[2]")).getText());
  }

  @And("^sees only 20 widgets$")
  public void sees_twentyWidgets() throws InterruptedException {
    Thread.sleep(2000);
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[1]/div[1]")).isDisplayed());
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[2]/div[1]/div")).isDisplayed());
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[3]/div[1]/div")).isDisplayed());
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[4]/div[1]/div")).isDisplayed());
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[5]/div[1]/div")).isDisplayed());
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[6]/div[1]/div")).isDisplayed());
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[7]/div[1]/div")).isDisplayed());
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[8]/div[1]/div")).isDisplayed());
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[9]/div[1]/div")).isDisplayed());
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[10]/div[1]/div")).isDisplayed());
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[11]/div[1]/div")).isDisplayed());
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[12]/div[1]/div")).isDisplayed());
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[13]/div[1]/div")).isDisplayed());
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[14]/div[1]/div")).isDisplayed());
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[15]/div[1]/div")).isDisplayed());
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[16]/div[1]/div")).isDisplayed());
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[17]/div[1]/div")).isDisplayed());
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[18]/div[1]/div")).isDisplayed());
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[19]/div[1]/div")).isDisplayed());
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div[20]/div[1]/div")).isDisplayed());
  }

  @And("^sees no widgets$")
  public void sees_no_widgets() {

  }

  //*******************************************************************************************
  //*******************************************************************************************

  //                    Show channels and workflow stop start

  @Then("^checks the 'Show Channels box'$")
  public void tick_showChannels() {
    driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[1]/div/div[1]/label/i[2]")).click();
  }

  @Then("^unchecks the 'Show Channels box'$")
  public void untick_showChannels() {
    driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[1]/div/div[1]/label/i[1]")).click();
  }

  @Then("^checks the 'Show Workflows' box for Basic channel: 2 and 4$")
  public void tick_showWorkflows_channelTwoFour() throws InterruptedException {
    driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[1]/div/div[1]/div[2]/div[1]/label/i[2]")).click();
    Thread.sleep(1000);
    driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[4]/div/div[1]/div[2]/div[1]/label/i[2]")).click();
  }

  @Then("^unchecks the 'Show Workflows' box for Basic channel: 2 and 4$")
  public void untick_showWorkflows_channelTwoFour() {
    driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[4]/div/div[1]/div[2]/div[1]/label/i[1]")).click();
    driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[1]/div/div[1]/div[2]/div[1]/label/i[1]")).click();
  }

  @Then("^clicks the 'Diagram' tab$")
  public void config_diagram_tabClick() {
    driver.findElement(By.xpath("/html/body/div[6]/div/div/div[1]/ul/li[2]/a")).click();
  }


  @And("^sees all four channels$")
  public void fourChannels_channels_isVisible() {
    //Checkbox is ticked
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[1]/div/div[1]/label/i[1]")).isDisplayed());
    Assert.assertEquals("fa fa-fw fa-check-square-o", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[1]/div/div[1]/label/i[1]")).getAttribute("class"));
    //Channel panel
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[1]")).isDisplayed());
    //Channel Icon
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[1]/div/div[1]/div[1]/i")).isDisplayed());
    Assert.assertEquals("fa fa-channel channel-title-name-icon", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[1]/div/div[1]/div[1]/i")).getAttribute("class"));
    //Channel Title/Name
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[1]/div/div[1]/div[1]")).isDisplayed());
    Assert.assertEquals(" Channel :  basic-channel-2", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[1]/div/div[1]/div[1]")).getText());
    //Last started
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[1]/div/div[1]/div[4]/div[1]/div[1]/span[1]")).isDisplayed());
    Assert.assertEquals("Last Started: ", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[1]/div/div[1]/div[4]/div[1]/div[1]/span[1]")).getText());
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[1]/div/div[1]/div[4]/div[1]/div[1]/span[2]")).isDisplayed());
    //Uptime
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[1]/div/div[1]/div[4]/div[2]/div[1]/span[1]")).isDisplayed());
    Assert.assertEquals("Up Time: ", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[1]/div/div[1]/div[4]/div[2]/div[1]/span[1]")).getText());
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[1]/div/div[1]/div[4]/div[2]/div[1]/span[2]")).isDisplayed());
    //Failed Messages Icon and Count
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[1]/div/div[1]/div[4]/div[3]/div/span[1]/i")).isDisplayed());
    Assert.assertEquals("fa fa-envelope-o", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[1]/div/div[1]/div[4]/div[3]/div/span[1]/i")).getAttribute("class"));
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[1]/div/div[1]/div[4]/div[3]/div/span[2]")).isDisplayed());
    Assert.assertEquals("0", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[1]/div/div[1]/div[4]/div[3]/div/span[2]")).getText());
    //InFlight Messages Icon and Count
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[1]/div/div[1]/div[4]/div[4]/div/span[1]/i")).isDisplayed());
    Assert.assertEquals("fa fa-paper-plane-o", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[1]/div/div[1]/div[4]/div[4]/div/span[1]/i")).getAttribute("class"));
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[1]/div/div[1]/div[4]/div[4]/div/span[2]")).isDisplayed());
    Assert.assertEquals("0", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[1]/div/div[1]/div[4]/div[4]/div/span[2]")).getText());
    //Action buttons
      //Show Workflows
      Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[1]/div/div[1]/div[2]/div[1]/label")).isDisplayed());
      Assert.assertEquals("Show Workflows (3)", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[1]/div/div[1]/div[2]/div[1]/label")).getText());
      Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[1]/div/div[1]/div[2]/div[1]/label/i[2]")).isDisplayed());
      Assert.assertEquals("fa fa-fw fa-square-o", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[1]/div/div[1]/div[2]/div[1]/label/i[2]")).getAttribute("class"));
      //Start
      Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[1]/div/div[1]/div[2]/div[2]/button[1]/i")).isDisplayed());
      Assert.assertEquals("fa fa-play", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[1]/div/div[1]/div[2]/div[2]/button[1]/i")).getAttribute("class"));
      //Pause
      Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[1]/div/div[1]/div[2]/div[2]/button[2]/i")).isDisplayed());
      Assert.assertEquals("fa fa-pause", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[1]/div/div[1]/div[2]/div[2]/button[2]/i")).getAttribute("class"));
      //Stop
      Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[1]/div/div[1]/div[2]/div[2]/button[3]/i")).isDisplayed());
      Assert.assertEquals("fa fa-stop", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[1]/div/div[1]/div[2]/div[2]/button[3]/i")).getAttribute("class"));
      //Refresh
      Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[1]/div/div[1]/div[2]/div[2]/button[4]/i")).isDisplayed());
      Assert.assertEquals("fa fa-refresh", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[1]/div/div[1]/div[2]/div[2]/button[4]/i")).getAttribute("class"));
      //Channel status icon
      Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[1]/div/div[1]/div[2]/div[4]/span/span/span/i")).isDisplayed());
      Assert.assertEquals("fa fa-check text-success", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[1]/div/div[1]/div[2]/div[4]/span/span/span/i")).getAttribute("class"));


      //Channel panel
      Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[2]")).isDisplayed());
      //Channel Icon
      Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[2]/div/div[1]/div[1]/i")).isDisplayed());
      Assert.assertEquals("fa fa-channel channel-title-name-icon", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[2]/div/div[1]/div[1]/i")).getAttribute("class"));
      //Channel Title/Name
      Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[2]/div/div[1]/div[1]")).isDisplayed());
      Assert.assertEquals(" Channel :  basic-channel-3", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[2]/div/div[1]/div[1]")).getText());
      //Last started
      Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[2]/div/div[1]/div[4]/div[1]/div[1]/span[1]")).isDisplayed());
      Assert.assertEquals("Last Started: ", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[2]/div/div[1]/div[4]/div[1]/div[1]/span[1]")).getText());
      Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[2]/div/div[1]/div[4]/div[1]/div[1]/span[2]")).isDisplayed());
      //Uptime
      Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[2]/div/div[1]/div[4]/div[2]/div[1]/span[1]")).isDisplayed());
      Assert.assertEquals("Up Time: ", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[2]/div/div[1]/div[4]/div[2]/div[1]/span[1]")).getText());
      Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[2]/div/div[1]/div[4]/div[2]/div[1]/span[2]")).isDisplayed());
      //Failed Messages Icon and Count
      Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[2]/div/div[1]/div[4]/div[3]/div/span[1]/i")).isDisplayed());
      Assert.assertEquals("fa fa-envelope-o", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[2]/div/div[1]/div[4]/div[3]/div/span[1]/i")).getAttribute("class"));
      Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[2]/div/div[1]/div[4]/div[3]/div/span[2]")).isDisplayed());
      Assert.assertEquals("0", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[2]/div/div[1]/div[4]/div[3]/div/span[2]")).getText());
      //InFlight Messages Icon and Count
      Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[2]/div/div[1]/div[4]/div[4]/div/span[1]/i")).isDisplayed());
      Assert.assertEquals("fa fa-paper-plane-o", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[2]/div/div[1]/div[4]/div[4]/div/span[1]/i")).getAttribute("class"));
      Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[2]/div/div[1]/div[4]/div[4]/div/span[2]")).isDisplayed());
      Assert.assertEquals("0", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[2]/div/div[1]/div[4]/div[4]/div/span[2]")).getText());
      //Action buttons
        //Show Workflows
        Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[2]/div/div[1]/div[2]/div[1]/label")).isDisplayed());
        Assert.assertEquals("Show Workflows (1)", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[2]/div/div[1]/div[2]/div[1]/label")).getText());
        Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[2]/div/div[1]/div[2]/div[1]/label/i[2]")).isDisplayed());
        Assert.assertEquals("fa fa-fw fa-square-o", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[2]/div/div[1]/div[2]/div[1]/label/i[2]")).getAttribute("class"));
        //Start
        Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[2]/div/div[1]/div[2]/div[2]/button[1]/i")).isDisplayed());
        Assert.assertEquals("fa fa-play", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[2]/div/div[1]/div[2]/div[2]/button[1]/i")).getAttribute("class"));
        //Pause
        Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[2]/div/div[1]/div[2]/div[2]/button[2]/i")).isDisplayed());
        Assert.assertEquals("fa fa-pause", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[2]/div/div[1]/div[2]/div[2]/button[2]/i")).getAttribute("class"));
        //Stop
        Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[2]/div/div[1]/div[2]/div[2]/button[3]/i")).isDisplayed());
        Assert.assertEquals("fa fa-stop", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[2]/div/div[1]/div[2]/div[2]/button[3]/i")).getAttribute("class"));
        //Refresh
        Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[2]/div/div[1]/div[2]/div[2]/button[4]/i")).isDisplayed());
        Assert.assertEquals("fa fa-refresh", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[2]/div/div[1]/div[2]/div[2]/button[4]/i")).getAttribute("class"));
      //Channel status icon
      Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[2]/div/div[1]/div[2]/div[4]/span/span/span/i")).isDisplayed());
      Assert.assertEquals("fa fa-check text-success", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[2]/div/div[1]/div[2]/div[4]/span/span/span/i")).getAttribute("class"));


      //Channel panel
      Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[3]")).isDisplayed());
      //Channel Icon
      Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[3]/div/div[1]/div[1]/i")).isDisplayed());
      Assert.assertEquals("fa fa-channel channel-title-name-icon", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[3]/div/div[1]/div[1]/i")).getAttribute("class"));
      //Channel Title/Name
      Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[3]/div/div[1]/div[1]")).isDisplayed());
      Assert.assertEquals(" Channel :  basic-channel-1", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[3]/div/div[1]/div[1]")).getText());
      //Last started
      Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[3]/div/div[1]/div[4]/div[1]/div[1]/span[1]")).isDisplayed());
      Assert.assertEquals("Last Started: ", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[3]/div/div[1]/div[4]/div[1]/div[1]/span[1]")).getText());
      Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[3]/div/div[1]/div[4]/div[1]/div[1]/span[2]")).isDisplayed());
      //Uptime
      Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[3]/div/div[1]/div[4]/div[2]/div[1]/span[1]")).isDisplayed());
      Assert.assertEquals("Up Time: ", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[3]/div/div[1]/div[4]/div[2]/div[1]/span[1]")).getText());
      Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[3]/div/div[1]/div[4]/div[2]/div[1]/span[2]")).isDisplayed());
      //Failed Messages Icon and Count
      Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[3]/div/div[1]/div[4]/div[3]/div/span[1]/i")).isDisplayed());
      Assert.assertEquals("fa fa-envelope-o", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[3]/div/div[1]/div[4]/div[3]/div/span[1]/i")).getAttribute("class"));
      Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[3]/div/div[1]/div[4]/div[3]/div/span[2]")).isDisplayed());
      Assert.assertEquals("0", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[3]/div/div[1]/div[4]/div[3]/div/span[2]")).getText());
      //InFlight Messages Icon and Count
      Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[3]/div/div[1]/div[4]/div[4]/div/span[1]/i")).isDisplayed());
      Assert.assertEquals("fa fa-paper-plane-o", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[3]/div/div[1]/div[4]/div[4]/div/span[1]/i")).getAttribute("class"));
      Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[3]/div/div[1]/div[4]/div[4]/div/span[2]")).isDisplayed());
      Assert.assertEquals("0", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[3]/div/div[1]/div[4]/div[4]/div/span[2]")).getText());
      //Action buttons
        //Show Workflows
        Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[3]/div/div[1]/div[2]/div[1]/label")).isDisplayed());
        Assert.assertEquals("Show Workflows (1)", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[3]/div/div[1]/div[2]/div[1]/label")).getText());
        Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[3]/div/div[1]/div[2]/div[1]/label/i[2]")).isDisplayed());
        Assert.assertEquals("fa fa-fw fa-square-o", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[3]/div/div[1]/div[2]/div[1]/label/i[2]")).getAttribute("class"));
        //Start
        Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[3]/div/div[1]/div[2]/div[2]/button[1]/i")).isDisplayed());
        Assert.assertEquals("fa fa-play", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[3]/div/div[1]/div[2]/div[2]/button[1]/i")).getAttribute("class"));
        //Pause
        Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[3]/div/div[1]/div[2]/div[2]/button[2]/i")).isDisplayed());
        Assert.assertEquals("fa fa-pause", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[3]/div/div[1]/div[2]/div[2]/button[2]/i")).getAttribute("class"));
        //Stop
        Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[3]/div/div[1]/div[2]/div[2]/button[3]/i")).isDisplayed());
        Assert.assertEquals("fa fa-stop", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[3]/div/div[1]/div[2]/div[2]/button[3]/i")).getAttribute("class"));
        //Refresh
        Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[3]/div/div[1]/div[2]/div[2]/button[4]/i")).isDisplayed());
        Assert.assertEquals("fa fa-refresh", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[3]/div/div[1]/div[2]/div[2]/button[4]/i")).getAttribute("class"));
      //Channel status icon
      Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[3]/div/div[1]/div[2]/div[4]/span/span/span/i")).isDisplayed());
      Assert.assertEquals("fa fa-check text-success", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[3]/div/div[1]/div[2]/div[4]/span/span/span/i")).getAttribute("class"));


      //Channel panel
      Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[4]")).isDisplayed());
      //Channel Icon
      Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[4]/div/div[1]/div[1]/i")).isDisplayed());
      Assert.assertEquals("fa fa-channel channel-title-name-icon", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[4]/div/div[1]/div[1]/i")).getAttribute("class"));
      //Channel Title/Name
      Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[4]/div/div[1]/div[1]")).isDisplayed());
      Assert.assertEquals(" Channel :  basic-channel-4", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[4]/div/div[1]/div[1]")).getText());
      //Last started
      Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[4]/div/div[1]/div[4]/div[1]/div[1]/span[1]")).isDisplayed());
      Assert.assertEquals("Last Started: ", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[4]/div/div[1]/div[4]/div[1]/div[1]/span[1]")).getText());
      Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[4]/div/div[1]/div[4]/div[1]/div[1]/span[2]")).isDisplayed());
      //Uptime
      Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[4]/div/div[1]/div[4]/div[2]/div[1]/span[1]")).isDisplayed());
      Assert.assertEquals("Up Time: ", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[4]/div/div[1]/div[4]/div[2]/div[1]/span[1]")).getText());
      Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[4]/div/div[1]/div[4]/div[2]/div[1]/span[2]")).isDisplayed());
      //Failed Messages Icon and Count
      Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[4]/div/div[1]/div[4]/div[3]/div/span[1]/i")).isDisplayed());
      Assert.assertEquals("fa fa-envelope-o", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[4]/div/div[1]/div[4]/div[3]/div/span[1]/i")).getAttribute("class"));
      Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[4]/div/div[1]/div[4]/div[3]/div/span[2]")).isDisplayed());
      Assert.assertEquals("0", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[4]/div/div[1]/div[4]/div[3]/div/span[2]")).getText());
      //InFlight Messages Icon and Count
      Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[4]/div/div[1]/div[4]/div[4]/div/span[1]/i")).isDisplayed());
      Assert.assertEquals("fa fa-paper-plane-o", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[4]/div/div[1]/div[4]/div[4]/div/span[1]/i")).getAttribute("class"));
      Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[4]/div/div[1]/div[4]/div[4]/div/span[2]")).isDisplayed());
      Assert.assertEquals("0", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[4]/div/div[1]/div[4]/div[4]/div/span[2]")).getText());
      //Action buttons
        //Show Workflows
        Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[4]/div/div[1]/div[2]/div[1]/label")).isDisplayed());
        Assert.assertEquals("Show Workflows (5)", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[4]/div/div[1]/div[2]/div[1]/label")).getText());
        Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[4]/div/div[1]/div[2]/div[1]/label/i[2]")).isDisplayed());
        Assert.assertEquals("fa fa-fw fa-square-o", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[4]/div/div[1]/div[2]/div[1]/label/i[2]")).getAttribute("class"));
        //Start
        Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[4]/div/div[1]/div[2]/div[2]/button[1]/i")).isDisplayed());
        Assert.assertEquals("fa fa-play", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[4]/div/div[1]/div[2]/div[2]/button[1]/i")).getAttribute("class"));
        //Pause
        Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[4]/div/div[1]/div[2]/div[2]/button[2]/i")).isDisplayed());
        Assert.assertEquals("fa fa-pause", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[4]/div/div[1]/div[2]/div[2]/button[2]/i")).getAttribute("class"));
        //Stop
        Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[4]/div/div[1]/div[2]/div[2]/button[3]/i")).isDisplayed());
        Assert.assertEquals("fa fa-stop", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[4]/div/div[1]/div[2]/div[2]/button[3]/i")).getAttribute("class"));
        //Refresh
        Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[4]/div/div[1]/div[2]/div[2]/button[4]/i")).isDisplayed());
        Assert.assertEquals("fa fa-refresh", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[4]/div/div[1]/div[2]/div[2]/button[4]/i")).getAttribute("class"));
      //Channel status icon
      Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[4]/div/div[1]/div[2]/div[4]/span/span/span/i")).isDisplayed());
      Assert.assertEquals("fa fa-check text-success", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div/div[1]/div[4]/div/div[1]/div[2]/div[4]/span/span/span/i")).getAttribute("class"));

  }

  @And("^sees all four channels have been hidden$")
  public void fourChannels_channels_isHidden() throws InterruptedException {
    Thread.sleep(2000);
    Assert.assertFalse(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[4]/div/div")).isDisplayed());
    //Sees the unchecked show channels box
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[1]/div/div[1]/label/i[2]")).isDisplayed());
    Assert.assertEquals("fa fa-fw fa-square-o", driver.findElement(By.xpath("/html/body/section/div[2]/section[3]/div[1]/div/div[1]/div/div[1]/label/i[2]")).getAttribute("class"));
  }

  @And("^sees the 'Workflows' for channels 2 and 4$")
  public void  multiple_workflowsVisible() throws InterruptedException {
    Thread.sleep(2000);
    adapterChannelworkFlowVisibleAssertion("1", "1", "basic-workflow-2a");
    adapterChannelworkFlowVisibleAssertion("1", "2", "basic-workflow-2b");
    adapterChannelworkFlowVisibleAssertion("1", "3", "basic-workflow-2c");
    adapterChannelworkFlowVisibleAssertion("4", "1", "basic-workflow-4a");
    adapterChannelworkFlowVisibleAssertion("4", "2", "basic-workflow-4b");
    adapterChannelworkFlowVisibleAssertion("4", "3", "basic-workflow-4c");
    adapterChannelworkFlowVisibleAssertion("4", "4", "basic-workflow-4d");
    adapterChannelworkFlowVisibleAssertion("4", "5", "basic-workflow-4e");

  }

  @And("^sees the 'Workflows' for channels 2 and 4 have been hidden$")
  public void multiple_workflowsHidden() throws InterruptedException {
    Thread.sleep(2000);
    adapterChannelworkFlowHiddenAssertion("1", "1");
    adapterChannelworkFlowHiddenAssertion("4", "1");
  }

  @And("^sees the 'Diagram tab'$")
  public void sees_diagramTab() throws InterruptedException {
    Thread.sleep(2000);
    Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"config-diagram\"]")).isDisplayed());
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[6]/div/div/div[2]/div[2]/div/div[2]/div[2]/div[1]/i")).isDisplayed());
    Assert.assertEquals("fa fa-plus", driver.findElement(By.xpath("/html/body/div[6]/div/div/div[2]/div[2]/div/div[2]/div[2]/div[1]/i")).getAttribute("class"));
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[6]/div/div/div[2]/div[2]/div/div[2]/div[2]/div[2]/i")).isDisplayed());
    Assert.assertEquals("fa fa-minus", driver.findElement(By.xpath("/html/body/div[6]/div/div/div[2]/div[2]/div/div[2]/div[2]/div[2]/i")).getAttribute("class"));
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[6]/div/div/div[2]/div[2]/div/div[2]/div[2]/div[3]/i")).isDisplayed());
    Assert.assertEquals("fa fa-download", driver.findElement(By.xpath("/html/body/div[6]/div/div/div[2]/div[2]/div/div[2]/div[2]/div[3]/i")).getAttribute("class"));

    Assert.assertEquals("Basic Workflow 2c", driver.findElement(By.xpath("/html/body/div[6]/div/div/div[2]/div[2]/div/div[2]/div[4]/svg/text[50]/tspan")).getText());
  }

//*******************************************************************************************
//*******************************************************************************************

//    Using this method as a testing ground
//    @And("^testing search details$")
//    public void please_search() {
//    }

}
