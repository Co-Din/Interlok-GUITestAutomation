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


import com.google.common.io.Resources;
import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;


import static com.adaptris.stepdefs.ENVVAR.ADAPTER_STARTUP;
import static com.google.common.io.Resources.*;
import static org.openqa.selenium.io.FileHandler.delete;


public class GUIDirectoryTools {

 static WebDriver driver;
 static String configAddress = "interlok/config/config.html";


 public static String startedStoppedTime() {
     String pattern = "dd/MM/yy HH:mm";
     SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
     return simpleDateFormat.format(new Date());
 }

 public static String threadDumpDate() {
     String pattern = "yyyy/MM/dd HH:mm";
     SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
     return simpleDateFormat.format(new Date());
 }

    public static void InterlokBoot() {
        try {
            Runtime.getRuntime().exec(ADAPTER_STARTUP);
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }



 static void driverInit(String domainUrl) {

    theChosenOne();

    driver = new ChromeDriver();

//	Opens page in full size and waits 10 secs before t-out
    driver.get(domainUrl);
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
}

    protected static String searchFileDetails(String directoryPath, String fileName, String tagTitle) throws ParserConfigurationException, IOException, SAXException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(directoryPath + "\\" + fileName);
        doc.getDocumentElement().normalize();
        NodeList nList = doc.getElementsByTagName(tagTitle);
        Node nNode = nList.item(0);
        return nNode.getFirstChild().getNodeValue();
    }

    public static void adapterIdReset(File adapterDBLocation) throws IOException {

        if (adapterDBLocation.listFiles() != null) {
            try {
                for (File childFile : adapterDBLocation.listFiles()) {

                    if (childFile.isDirectory()) {
                        delete(childFile);
                    }
                }
                Files.delete(adapterDBLocation.toPath());
            } catch (IOException e) {

            }
        }
        for (final File f : adapterDBLocation.listFiles()) {
            f.delete();
        }
        adapterDBLocation.delete();
    }

    public static void theChosenOne() {
        if (System.getProperty("os.name").contains("Windows")) {
            System.setProperty("webdriver.chrome.driver", "src/resources/chromedriver_win32.exe");
        } else if (System.getProperty("os.name").contains("Mac")) {
            System.setProperty("webdriver.chrome.driver", "src/resources/chromedriver_mac64");
        } else if (System.getProperty("os.name").contains("Linux")) {
            System.setProperty("webdriver.chrome.driver", "src/resources/chromedriver_linux64");
        }
    }

    public static void tileHiddenActions(String divcontainerNumber) throws InterruptedException {
        //Tile Action Buttons
        //Hidden
        Assert.assertFalse(driver.findElement(By.xpath(String.format("/html/body/section/div[2]/section[3]/div[1]/div[%s]/div[1]/div/div[3]/div", divcontainerNumber))).isDisplayed());
        Assert.assertFalse(driver.findElement(By.xpath(String.format("/html/body/section/div[2]/section[3]/div[1]/div[%s]/div[3]", divcontainerNumber))).isDisplayed());
        Assert.assertFalse(driver.findElement(By.xpath(String.format("/html/body/section/div[2]/section[3]/div[1]/div[%s]/div[2]", divcontainerNumber))).isDisplayed());
    }

    public static void tileBasicVisibleActions(String divcontainerNumber) throws InterruptedException {
        Actions action = new Actions(driver);
        //Visible
        action.moveToElement(driver.findElement(By.xpath(String.format("/html/body/section/div[2]/section[3]/div[1]/div[%s]/div[1]/div/div[2]/div[2]/div", divcontainerNumber)))).build().perform();
        Thread.sleep(2000);
        Assert.assertTrue(driver.findElement(By.xpath(String.format("/html/body/section/div[2]/section[3]/div[1]/div[%s]/div[1]/div/div[3]/div/div[1]/a/i",divcontainerNumber))).isDisplayed());
        Assert.assertEquals("fa fa-ellipsis-h", driver.findElement(By.xpath(String.format("/html/body/section/div[2]/section[3]/div[1]/div[%s]/div[1]/div/div[3]/div/div[1]/a/i", divcontainerNumber))).getAttribute("class"));
        Assert.assertTrue(driver.findElement(By.xpath(String.format("/html/body/section/div[2]/section[3]/div[1]/div[%s]/div[1]/div/div[3]/div/div[2]/div/button[2]/i", divcontainerNumber))).isDisplayed());
        Assert.assertEquals("fa fa-times", driver.findElement(By.xpath(String.format("/html/body/section/div[2]/section[3]/div[1]/div[%s]/div[1]/div/div[3]/div/div[2]/div/button[2]/i", divcontainerNumber))).getAttribute("class"));
        Assert.assertTrue(driver.findElement(By.xpath(String.format("/html/body/section/div[2]/section[3]/div[1]/div[%s]/div[1]/div/div[3]/div/div[2]/div/button[1]/i", divcontainerNumber))).isDisplayed());
        Assert.assertEquals("fa fa-refresh", driver.findElement(By.xpath(String.format("/html/body/section/div[2]/section[3]/div[1]/div[%s]/div[1]/div/div[3]/div/div[2]/div/button[1]/i", divcontainerNumber))).getAttribute("class"));
        Assert.assertTrue(driver.findElement(By.xpath(String.format("/html/body/section/div[2]/section[3]/div[1]/div[%s]/div[1]/div/div[3]", divcontainerNumber))).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath(String.format("/html/body/section/div[2]/section[3]/div[1]/div[%s]/div[3]", divcontainerNumber))).isDisplayed());
        Assert.assertEquals("ui-resizable-handle ui-resizable-sw", driver.findElement(By.xpath(String.format("/html/body/section/div[2]/section[3]/div[1]/div[%s]/div[3]", divcontainerNumber))).getAttribute("class"));
        Assert.assertTrue(driver.findElement(By.xpath(String.format("/html/body/section/div[2]/section[3]/div[1]/div[%s]/div[2]", divcontainerNumber))).isDisplayed());
        Assert.assertEquals("ui-resizable-handle ui-resizable-se ui-icon ui-icon-gripsmall-diagonal-se", driver.findElement(By.xpath(String.format("/html/body/section/div[2]/section[3]/div[1]/div[%s]/div[2]", divcontainerNumber))).getAttribute("class"));
    }

    public static void tileAdvancedVisibleActions(String divcontainerNumber, String leftIcon, String centralIcon, String rightIcon) throws InterruptedException {
        Actions action = new Actions(driver);
        //Visible
        action.moveToElement(driver.findElement(By.xpath(String.format("/html/body/section/div[2]/section[3]/div[1]/div[%s]/div[1]/div/div[2]/div[2]/div", divcontainerNumber)))).build().perform();
        Thread.sleep(2000);
        Assert.assertTrue(driver.findElement(By.xpath(String.format("/html/body/section/div[2]/section[3]/div[1]/div[%s]/div[1]/div/div[3]/div/div[1]/a/i",divcontainerNumber))).isDisplayed());
        Assert.assertEquals("fa fa-ellipsis-h", driver.findElement(By.xpath(String.format("/html/body/section/div[2]/section[3]/div[1]/div[%s]/div[1]/div/div[3]/div/div[1]/a/i", divcontainerNumber))).getAttribute("class"));
        Assert.assertTrue(driver.findElement(By.xpath(String.format("/html/body/section/div[2]/section[3]/div[1]/div[%s]/div[1]/div/div[3]/div/div[2]/div/button[1]/i", divcontainerNumber))).isDisplayed());
        Assert.assertEquals(leftIcon, driver.findElement(By.xpath(String.format("/html/body/section/div[2]/section[3]/div[1]/div[%s]/div[1]/div/div[3]/div/div[2]/div/button[1]/i", divcontainerNumber))).getAttribute("class"));
        Assert.assertTrue(driver.findElement(By.xpath(String.format("/html/body/section/div[2]/section[3]/div[1]/div[%s]/div[1]/div/div[3]/div/div[2]/div/button[2]/i", divcontainerNumber))).isDisplayed());
        Assert.assertEquals(centralIcon, driver.findElement(By.xpath(String.format("/html/body/section/div[2]/section[3]/div[1]/div[%s]/div[1]/div/div[3]/div/div[2]/div/button[2]/i", divcontainerNumber))).getAttribute("class"));
        Assert.assertTrue(driver.findElement(By.xpath(String.format("/html/body/section/div[2]/section[3]/div[1]/div[%s]/div[1]/div/div[3]/div/div[2]/div/button[3]/i", divcontainerNumber))).isDisplayed());
        Assert.assertEquals(rightIcon, driver.findElement(By.xpath(String.format("/html/body/section/div[2]/section[3]/div[1]/div[%s]/div[1]/div/div[3]/div/div[2]/div/button[3]/i", divcontainerNumber))).getAttribute("class"));
        Assert.assertTrue(driver.findElement(By.xpath(String.format("/html/body/section/div[2]/section[3]/div[1]/div[%s]/div[1]/div/div[3]", divcontainerNumber))).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath(String.format("/html/body/section/div[2]/section[3]/div[1]/div[%s]/div[3]", divcontainerNumber))).isDisplayed());
        Assert.assertEquals("ui-resizable-handle ui-resizable-sw", driver.findElement(By.xpath(String.format("/html/body/section/div[2]/section[3]/div[1]/div[%s]/div[3]", divcontainerNumber))).getAttribute("class"));
        Assert.assertTrue(driver.findElement(By.xpath(String.format("/html/body/section/div[2]/section[3]/div[1]/div[%s]/div[2]", divcontainerNumber))).isDisplayed());
        Assert.assertEquals("ui-resizable-handle ui-resizable-se ui-icon ui-icon-gripsmall-diagonal-se", driver.findElement(By.xpath(String.format("/html/body/section/div[2]/section[3]/div[1]/div[%s]/div[2]", divcontainerNumber))).getAttribute("class"));

    }

public static void tileAdvancedVisibleMirroredActions(String divcontainerNumber, String leftIcon, String centralIcon, String rightIcon) throws InterruptedException {
    Actions action = new Actions(driver);
    //Visible
    action.moveToElement(driver.findElement(By.xpath(String.format("/html/body/section/div[2]/section[3]/div[1]/div[%s]/div[1]/div/div[2]/div[2]/div", divcontainerNumber)))).build().perform();
    Thread.sleep(2000);
    Assert.assertTrue(driver.findElement(By.xpath(String.format("/html/body/section/div[2]/section[3]/div[1]/div[%s]/div[1]/div/div[3]/div/div[1]/a/i",divcontainerNumber))).isDisplayed());
    Assert.assertEquals("fa fa-ellipsis-h", driver.findElement(By.xpath(String.format("/html/body/section/div[2]/section[3]/div[1]/div[%s]/div[1]/div/div[3]/div/div[1]/a/i", divcontainerNumber))).getAttribute("class"));
    Assert.assertTrue(driver.findElement(By.xpath(String.format("/html/body/section/div[2]/section[3]/div[1]/div[%s]/div[1]/div/div[3]/div/div[2]/div/div/button/i", divcontainerNumber))).isDisplayed());
    Assert.assertEquals(leftIcon, driver.findElement(By.xpath(String.format("/html/body/section/div[2]/section[3]/div[1]/div[%s]/div[1]/div/div[3]/div/div[2]/div/div/button/i", divcontainerNumber))).getAttribute("class"));
    Assert.assertTrue(driver.findElement(By.xpath(String.format("/html/body/section/div[2]/section[3]/div[1]/div[%s]/div[1]/div/div[3]/div/div[2]/div/button[1]/i", divcontainerNumber))).isDisplayed());
    Assert.assertEquals(centralIcon, driver.findElement(By.xpath(String.format("/html/body/section/div[2]/section[3]/div[1]/div[%s]/div[1]/div/div[3]/div/div[2]/div/button[1]/i", divcontainerNumber))).getAttribute("class"));
    Assert.assertTrue(driver.findElement(By.xpath(String.format("/html/body/section/div[2]/section[3]/div[1]/div[%s]/div[1]/div/div[3]/div/div[2]/div/button[2]/i", divcontainerNumber))).isDisplayed());
    Assert.assertEquals(rightIcon, driver.findElement(By.xpath(String.format("/html/body/section/div[2]/section[3]/div[1]/div[%s]/div[1]/div/div[3]/div/div[2]/div/button[2]/i", divcontainerNumber))).getAttribute("class"));
    Assert.assertTrue(driver.findElement(By.xpath(String.format("/html/body/section/div[2]/section[3]/div[1]/div[%s]/div[1]/div/div[3]", divcontainerNumber))).isDisplayed());
    Assert.assertTrue(driver.findElement(By.xpath(String.format("/html/body/section/div[2]/section[3]/div[1]/div[%s]/div[3]", divcontainerNumber))).isDisplayed());
    Assert.assertEquals("ui-resizable-handle ui-resizable-sw", driver.findElement(By.xpath(String.format("/html/body/section/div[2]/section[3]/div[1]/div[%s]/div[3]", divcontainerNumber))).getAttribute("class"));
    Assert.assertTrue(driver.findElement(By.xpath(String.format("/html/body/section/div[2]/section[3]/div[1]/div[%s]/div[2]", divcontainerNumber))).isDisplayed());
    Assert.assertEquals("ui-resizable-handle ui-resizable-se ui-icon ui-icon-gripsmall-diagonal-se", driver.findElement(By.xpath(String.format("/html/body/section/div[2]/section[3]/div[1]/div[%s]/div[2]", divcontainerNumber))).getAttribute("class"));

}
}
