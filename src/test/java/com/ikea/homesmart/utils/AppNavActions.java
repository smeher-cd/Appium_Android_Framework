package com.ikea.homesmart.utils;

import com.ikea.homesmart.helpers.TestConfig;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import io.cucumber.java.Scenario;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;

import static com.ikea.homesmart.basics.BaseSetup.pageObjects;

@Component
public class AppNavActions {

    @Autowired
    Generics generics;

    public void swipeDown(AndroidDriver driver, int times) {
        for (int i = 0; i < times; i++) {
            // Get the size of the device screen
            Dimension size = driver.manage().window().getSize();

            // Define start and end points for the swipe action
            int startX = size.width / 2;
            int startY = (int) (size.height * 0.8);
            int endY = (int) (size.height * 0.2);

            // Perform the swipe action
            TouchAction touchAction = new TouchAction(driver);
            touchAction.press(PointOption.point(startX, startY))
                    .moveTo(PointOption.point(startX, endY))
                    .release()
                    .perform();
        }
    }

    public void click(AndroidDriver driver, String elementName, Scenario scenario) throws Throwable {
        String locatorvalue;
        try {
            generics.globalWait(driver).until(
                    ExpectedConditions.elementToBeClickable(generics.getByRef(elementName)));
            WebElement element = generics.getElement(driver, elementName);
            element.click();
            locatorvalue = pageObjects.getProp(elementName).split(":>:")[1].trim();
            scenario.log("WebElement : " + " " + elementName + " " + ", With Locator : " + " " + locatorvalue + " ," + " Is Clicked Successfully");

        } catch (Throwable e) {
            throw e;
        }
    }



    public void scrollToText(AndroidDriver driver, String elementTxt){
        try{
            driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textMatches("+elementTxt+").instance(0))"));
        }catch (Throwable t) {
            throw t;
        }
    }
}
