package com.ikea.homesmart.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ikea.homesmart.helpers.TestConfig;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.Scenario;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;

@Component
public class SauceMethods {

    public AndroidDriver androiddriver;

    String sauceOption = "src/test/resources/sauceOptions.json";

    public AndroidDriver createAndroidDriver(URL appiumServerUrl, Scenario scenario) throws IOException, ParseException {

        androiddriver = new AndroidDriver(appiumServerUrl, getDesiredCapabilities("DeviceAndroidApp", sauceOption, scenario));
        return this.androiddriver;
    }

    public static DesiredCapabilities getDesiredCapabilities(String capabilityName, String capsContentRootLocation, Scenario scenario) throws IOException, ParseException {
        String jsonLocation = TestConfig.baseDir + "/" + capsContentRootLocation;
        HashMap<String, Object> caps = convertCapsToHashMap(capabilityName, jsonLocation);
        commonCaps(caps, jsonLocation);
        if (caps.containsKey("name")) {
            caps.replace("name", scenario.getName());
        }

        return new DesiredCapabilities(caps);
    }

    public static HashMap<String, Object> convertCapsToHashMap(String capabilityName, String jsonLocation) throws IOException, ParseException {
        ObjectMapper objectMapper = new ObjectMapper();
        return (HashMap) objectMapper.readValue(getCapability(capabilityName, jsonLocation).toString(), HashMap.class);
    }


    public static JSONObject getCapability(String capabilityName, String jsonLocation) throws IOException, ParseException {
        JSONArray capabilitiesArray = parseJSON(jsonLocation);
        Iterator var3 = capabilitiesArray.iterator();

        JSONObject capability;
        do {
            if (!var3.hasNext()) {
                return new JSONObject();
            }

            Object jsonObj = var3.next();
            capability = (JSONObject) jsonObj;
            if (capability.get("name") != null && capability.get("name").toString().equalsIgnoreCase(capabilityName)) {
                return (JSONObject) capability.get("caps");
            }
        } while (capability.get("sauceOption") == null);

        return (JSONObject) capability.get("sauceOption");
    }

    public static JSONArray parseJSON(String jsonLocation) throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        return (JSONArray) jsonParser.parse(new FileReader(jsonLocation));
    }

    public static void commonCaps(HashMap<String, Object> caps, String jsonLocation) throws IOException, ParseException {
        HashMap<String, Object> commoncaps = convertCapsToHashMap("CommonCapabilities", jsonLocation);
        if (caps.containsKey("username")) {
            caps.replace("username", commoncaps.get("username"));
        }

        if (caps.containsKey("accessKey")) {
            caps.replace("accessKey", commoncaps.get("accessKey"));
        }
    }

}
