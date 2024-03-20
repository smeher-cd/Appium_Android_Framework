package com.ikea.homesmart.utils;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.springframework.stereotype.Component;

import static io.appium.java_client.service.local.flags.GeneralServerFlag.*;

@Component
public class AppiumServerManager {

    public static AppiumDriverLocalService service;

    public static AppiumDriverLocalService getService () {
        return service;
    }

    public static void startServer (final String platformName) {
        final AppiumServiceBuilder builder = new AppiumServiceBuilder ();
        if (platformName.equalsIgnoreCase ("android")) {
            builder.withIPAddress ("127.0.0.1")
                    .usingPort (4723)
                    .withArgument (BASEPATH, "/wd/hub")
                    .withArgument (SESSION_OVERRIDE)
                    .withArgument (LOG_LEVEL, "debug")
                    .withArgument (USE_DRIVERS, "uiautomator2");
        } else if (platformName.equalsIgnoreCase ("ios")) {
            builder.withIPAddress ("127.0.0.1")
                    .usingPort (4723)
                    //.usingDriverExecutable (new File ("E:\\Program Files\\nodejs\\node.exe"))
                    .withArgument (BASEPATH, "/wd/hub")
                    .withArgument (SESSION_OVERRIDE)
                    .withArgument (LOG_LEVEL, "debug")
                    .withArgument (USE_DRIVERS, "xcuitest")
                    .withArgument (ALLOW_INSECURE, "chromedriver_autodownload");
        }

        service = AppiumDriverLocalService.buildService (builder);
        service.start ();
    }

    public static void stopServer () {
        service.stop ();
    }
}
