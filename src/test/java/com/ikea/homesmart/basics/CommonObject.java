package com.ikea.homesmart.basics;


import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.Scenario;
import org.springframework.stereotype.Component;


@Component
public class CommonObject {
        public AndroidDriver driver;
        public Scenario scenario;

}
