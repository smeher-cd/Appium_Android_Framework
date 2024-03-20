package com.ikea.homesmart.utils;


import com.ikea.homesmart.helpers.TestConfig;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;

import java.time.Duration;

import static com.ikea.homesmart.basics.BaseSetup.pageObjects;
import static com.ikea.homesmart.helpers.TestConfig.EXPLICIT_WAIT;

@Component
public class Generics {

    public WebDriverWait globalWait(AndroidDriver driver) {
        WebDriverWait wait;
        try {
            wait = new WebDriverWait(driver, Duration.ofSeconds(TestConfig.WAIT));
        } catch (Throwable t) {
            throw t;
        }
        return wait;
    }

    //Method to wait for visibility of located element
    public void waitForVisibilityOfElement(String elementName,AndroidDriver driver) throws Throwable {
        try {
           globalWait(driver).until(
                    ExpectedConditions.visibilityOfElementLocated(getByRef(elementName)));
        } catch (Exception e) {
            throw e;
        }
    }

    public void waitForInvisibilityOfElement(String elementName,AndroidDriver driver) throws Throwable {
        globalWait(driver).until(
                ExpectedConditions.invisibilityOfElementLocated(getByRef(elementName)));
    }

    public WebElement getElement(AndroidDriver driver, String elementName) {
        WebElement element = null;
        try {
            String LocatorType;
            String Locatorvalue;

            String locator = pageObjects.getProp(elementName);
            Assert.assertNotNull("'" + elementName + "' Element not found in Object Repo", locator);
            LocatorType = locator.split(":>:")[0].trim().toUpperCase();
            Locatorvalue = locator.split(":>:")[1].trim();

            switch (LocatorType) {

                case "XPATH":
                    element = driver.findElement(AppiumBy.xpath(Locatorvalue));
                    break;

                case "ID":
                    element = driver.findElement(AppiumBy.id(Locatorvalue));
                    break;

                case "CLASSNAME":
                    element = driver.findElement(AppiumBy.className(Locatorvalue));
                    break;
            }
        } catch (Exception e) {
            throw e;
        }
        return element;
    }

    public By getByRef(String elementName) throws Throwable {
        try {
            By by = null;
            String LocatorType;
            String Locatorvalue;

            String locator = pageObjects.getProp(elementName);
            Assert.assertNotNull("'" + elementName + "' Element not found in Object Repo", locator);
            // extract the locator type and value from the object
            LocatorType = locator.split(":>:")[0].trim().toUpperCase();
            Locatorvalue = locator.split(":>:")[1].trim();

            switch (LocatorType) {

                case "XPATH": // Find By using Xpath
                    by = AppiumBy.xpath(Locatorvalue);
                    break;

                case "ID": // Find By using ID
                    by = AppiumBy.id(Locatorvalue);
                    break;

                case "CLASSNAME": // Find By using ClassName
                    by = AppiumBy.className(Locatorvalue);
                    break;
            }
            if (by != null)
                return by;
            else
                throw new Exception("Element reference for '" + elementName + "' not found !!");
        } catch (Exception e) {
            throw e;
        }
    }

}
