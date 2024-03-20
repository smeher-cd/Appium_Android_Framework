package com.ikea.homesmart.basics;

import com.ikea.homesmart.helpers.PropertyReader;
import com.ikea.homesmart.helpers.TestConfig;
import io.restassured.RestAssured;
import io.restassured.config.ConnectionConfig;
import org.assertj.core.util.Strings;

import java.io.PrintStream;
import java.util.concurrent.TimeUnit;

public class BaseSetup {
    public static PrintStream printStream;
    public static PropertyReader properties;
    public static PropertyReader pageObjects;
    private static BaseSetup baseObject = null;

    private BaseSetup() {
        try {
            properties = new PropertyReader(TestConfig.ProfileFilePath);
            pageObjects = new PropertyReader("./src/test/resources/testdata/PageObjects.properties");
            RestAssured.baseURI = properties.getProp("BASE_URL");

            RestAssured.config = RestAssured.config().connectionConfig(ConnectionConfig.connectionConfig()
                    .closeIdleConnectionsAfterEachResponseAfter((long) TestConfig.IdleConnectionTimeOutForResponse, TimeUnit.SECONDS));
        } catch (Throwable t) {
            System.out.println("Error Occurred in BaseSetup Constructor is " + t);
        }

    }

    public static BaseSetup getInstance() {

        if (baseObject == null) {
            synchronized (BaseSetup.class) {
                if (baseObject == null) {
                    baseObject = new BaseSetup();
                }
            }
        }
        return baseObject;
    }

    public static void setProfilePath() {
        String env = System.getProperty("env");
        if (Strings.isNullOrEmpty(env)) {
            env = "dev";
        }
        System.out.println("Environment --- :: " + env);
        //Set Environment properties file path
        TestConfig.ProfileFilePath = TestConfig.baseDir + "/src/test/resources/profiles/" + env + "/config.properties";
    }

}
