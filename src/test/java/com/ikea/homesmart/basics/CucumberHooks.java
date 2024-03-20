package com.ikea.homesmart.basics;


import io.cucumber.java.*;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.springframework.beans.factory.annotation.Autowired;

public class CucumberHooks {

    private static int failed = 0;
    private static int passed = 0;
    private static int skipped = 0;
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_GREEN_BACKGROUND = "\u001b[42;1m";
    private static final String ANSI_RED_BACKGROUND = "\u001b[41;1m";
    private static final String ANSI_YELLOW_BACKGROUND = "\u001b[43;1m";

    @Autowired
    CommonObject baseObject;

    @Before
    public void beforeScenario(Scenario sc) {
        baseObject.scenario = sc;
    }

    @After
    public void afterScenario(Scenario sc) {
        String msg;
        try {

            // Take screenshot for failed scenario
            if (baseObject.driver != null && sc.isFailed()) {
                try {
                    sc.attach(((TakesScreenshot) baseObject.driver).getScreenshotAs(OutputType.BYTES), "image/png", "");
                } catch (NoSuchSessionException e) {

                }
            }

            if (sc.getStatus().toString().equals("FAILED")) {
                failed++;
            } else if (sc.getStatus().toString().equals("PASSED")) {
                passed++;
            } else if (sc.getStatus().toString().equals("UNDEFINED")) {
                skipped++;
            }
            msg = "Current Status : >>> " + ANSI_GREEN_BACKGROUND + " PASSED : " + passed + " " + ANSI_RESET + "  " + ANSI_RED_BACKGROUND + " FAILED : " + failed + " " + ANSI_RESET + " " + ANSI_YELLOW_BACKGROUND + " SKIPPED : " + skipped + " " + ANSI_RESET;
            System.out.println("====== Status:" + sc.getStatus() + " : "+ sc.getName());
            System.out.println(msg);


            if (baseObject.driver != null) {
                try {
                    baseObject.driver.quit();
                    baseObject.driver = null;
                } catch (NoSuchSessionException s) {
                }
            }

        } catch (Throwable t) {
            //  throw t;
        }

    }

    @AfterAll
    public static void afterAll() {

    }

    @BeforeAll
    public static void beforeAll() {

    }


}
