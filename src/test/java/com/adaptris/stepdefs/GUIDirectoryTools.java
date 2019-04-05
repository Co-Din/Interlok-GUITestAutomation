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


import org.apache.commons.collections.CollectionUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;


import static com.adaptris.stepdefs.ENVVAR.ADAPTER_STARTUP;
import static org.openqa.selenium.io.FileHandler.delete;


public class GUIDirectoryTools {

 static WebDriver driver;
 static String configAddress = "interlok/config/config.html";

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

//	Opens page in full size and waits 10 secs bgit efore t-out
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

    public static void carouselSelector(String carouselXpath) {
        //This will get the number of items in the carousel

        List<WebElement> items = driver.findElements(By.cssSelector(carouselXpath));

        ArrayList list1 = new ArrayList();
        String name;

        for (int i = 0; i < items.size(); i++) {
            int index = i + 1;

            //This will get the name of each item in carousel
            name = driver.findElement(By.cssSelector(carouselXpath + "[" + index + "]")).getText();
            list1.add(name);
        }

        //Next we click on the arrow of the carousel
        driver.findElement(By.cssSelector("div[class^=a-carousel-col] a")).click();

        //Then we new items are loaded in the carousel following the click,
        //we get the names again

        List<WebElement> nextItems = driver.findElements(By.cssSelector(carouselXpath));

        ArrayList list2 = new ArrayList();

        String newName;

        for (int i = 0; i < nextItems.size(); i++) {
            int index = i + 1;

            //This will get the name of each item in carousel
            newName = driver.findElement(By.cssSelector(carouselXpath + "[" + index + "]")).getText();
            list2.add(newName);
        }

        //Then we compare the two arrayLists are not the same
        Collection commonList = CollectionUtils.retainAll(list1, list2);

        Assert.assertTrue(commonList.size() == 0);
    }
}
