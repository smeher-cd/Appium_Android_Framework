package com.ikea.homesmart.basics;

import com.ikea.homesmart.utils.CucumberReport;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

@RunWith(Cucumber.class)
@ContextConfiguration
@CucumberOptions(features = "src/test/resources/features",
        plugin = {"pretty",
                "html:target/cucumber-reports/cucumber-pretty",
                "json:target/cucumber-reports/cucumber-json-report.json",
                "rerun:target/cucumber-reports/failedTests.txt"},
        extraGlue =  "com.ikea.homesmart.stepdef",
        tags = " @TC02_IKEASmartApp"
        //dryRun = true

)
public class TestRunner {

    @BeforeClass
    public static void beforeClass() {
        try {
            BaseSetup.setProfilePath();
            BaseSetup.getInstance();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    @AfterClass
    public static void afterClass() {
        CucumberReport.generateCucumberHtmlReport();
    }

}




