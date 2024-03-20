package com.ikea.homesmart.driver;

import com.ikea.homesmart.basics.CommonObject;
import com.ikea.homesmart.helpers.TestConfig;
import com.ikea.homesmart.utils.SauceMethods;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import static com.ikea.homesmart.basics.BaseSetup.properties;

@Component
public class DriverFactory {

    public AppiumDriver driver;

    @Autowired
    public SauceMethods Saucelabs;

    @Autowired
    public CommonObject baseObject;

    public AppiumDriver getDriver(String platform, String exEnv) throws Exception {
        if (driver == null) {
            if (platform.equalsIgnoreCase(TestConfig.PLATFORM_ANDROID) && exEnv.equalsIgnoreCase("Local")) {
                driver = initAndroidDriver();
            } else if (platform.equalsIgnoreCase(TestConfig.PLATFORM_ANDROID) && exEnv.equalsIgnoreCase("Saucelab")) {
                driver = Saucelabs.createAndroidDriver(new URL(properties.getProp("SauceUrl")), baseObject.scenario);
            } else {
                throw new IllegalArgumentException("Unsupported platform: " + platform);
            }
        }
        return driver;
    }

    public AndroidDriver initAndroidDriver() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("deviceName", "Android Emulator");
        caps.setCapability("appPackage", "your_app_package");
        caps.setCapability("appActivity", "your_app_activity");

        return new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps);
    }


    public void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}


