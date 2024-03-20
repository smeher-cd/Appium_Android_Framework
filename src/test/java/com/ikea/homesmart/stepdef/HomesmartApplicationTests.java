package com.ikea.homesmart.stepdef;

import com.ikea.homesmart.HomesmartApplication;
import com.ikea.homesmart.basics.CommonObject;
import com.ikea.homesmart.utils.AppNavActions;
import com.ikea.homesmart.utils.Generics;
import com.ikea.homesmart.utils.SauceMethods;
import io.appium.java_client.AppiumBy;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URL;

import static com.ikea.homesmart.basics.BaseSetup.properties;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {HomesmartApplication.class})
public class HomesmartApplicationTests {

    @Autowired
    public SauceMethods Saucelabs;
    @Autowired
    public CommonObject baseObject;

    @Autowired
    public AppNavActions appNavActions;
    @Autowired
    public Generics generics;

    @Given("User Launch the IKEA Home Smart application")
    public void userLaunchTheIKEAHomeSmartApplication() throws Throwable {
        baseObject.driver = Saucelabs.createAndroidDriver(new URL(properties.getProp("SauceUrl")), baseObject.scenario);
    }

    @When("User getting started by reading the product glance")
    public void userGettingStartedByReadingTheProductGlance() throws Throwable {
        appNavActions.click(baseObject.driver,"next_btn",baseObject.scenario);
        appNavActions.click(baseObject.driver,"next_btn",baseObject.scenario);
        appNavActions.click(baseObject.driver,"next_btn",baseObject.scenario);
        appNavActions.click(baseObject.driver,"last_next_btn",baseObject.scenario);
        appNavActions.click(baseObject.driver,"get_started_button",baseObject.scenario);
    }

    @Then("User choose a Country {string}")
    public void userChooseACountry(String country) throws Throwable {
        appNavActions.click(baseObject.driver,"Country_ViewGroup",baseObject.scenario);
        //appNavActions.scrollToText(baseObject.driver,"Sweden");
        baseObject.driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0))" +".scrollIntoView(new UiSelector()"+".textMatches(\""+"Sweden"+"\").instance(0))"));
        appNavActions.click(baseObject.driver,"Country_Sweden",baseObject.scenario);
        appNavActions.click(baseObject.driver,"Country_next_btn",baseObject.scenario);
        appNavActions.click(baseObject.driver,"Country_next_btn",baseObject.scenario);
    }

    @Then("User read and accept privacy statement")
    public void userReadAndAcceptPrivacyStatement() throws Throwable {
        appNavActions.swipeDown(baseObject.driver,5);
        appNavActions.click(baseObject.driver,"Privacy_next_btn",baseObject.scenario);
    }

    @Then("User accept the consent for analytics")
    public void userAcceptTheConsentForAnalytics() throws Throwable{
        appNavActions.click(baseObject.driver,"Consent_Analytics_CheckBox_Yes",baseObject.scenario);
        appNavActions.click(baseObject.driver,"Consent_Analytics_Primary_btn",baseObject.scenario);
        appNavActions.click(baseObject.driver,"Consent_Analytics_hint",baseObject.scenario);
        appNavActions.click(baseObject.driver,"Consent_Analytics_Primary_btn",baseObject.scenario);
    }

    @Then("User Connects to the Hub")
    public void userConnectsToTheHub() throws Throwable{
        //Check Out DIRIGERA Hub
        appNavActions.click(baseObject.driver,"Check_Out_Dirigera_btn",baseObject.scenario);
        //Connect to Hub
        appNavActions.click(baseObject.driver,"Connect_to_Hub_btn",baseObject.scenario);
        Assert.assertTrue("Info Hub is not displayed",generics.getElement(baseObject.driver, "Hub_general").isDisplayed());
        Assert.assertTrue("Info Cables and Plug is not displayed",generics.getElement(baseObject.driver, "Hub_Cables_and_Plug").isDisplayed());
        appNavActions.click(baseObject.driver,"Hub_Get_Started_btn",baseObject.scenario);
        //Navigate through 4 pages
        appNavActions.click(baseObject.driver,"Hub_Navigate_next_btn",baseObject.scenario);
        appNavActions.click(baseObject.driver,"Hub_Navigate_next_btn",baseObject.scenario);
        appNavActions.click(baseObject.driver,"Hub_Navigate_next_btn",baseObject.scenario);
        appNavActions.click(baseObject.driver,"Hub_Navigate_next_btn",baseObject.scenario);
        generics.waitForInvisibilityOfElement("Looking_for_nearby_Hub",baseObject.driver);
    }

    @Then("User click on privacy statement link")
    public void userClickOnPrivacyStatementLink() throws Throwable {
        appNavActions.swipeDown(baseObject.driver,5);
        appNavActions.click(baseObject.driver,"Privacy_doc_link",baseObject.scenario);
    }

    @And("User should view the privacy statement document")
    public void userShouldViewThePrivacyStatementDocument() throws Throwable {
        generics.waitForVisibilityOfElement("url_bar",baseObject.driver);
        WebElement prvcUrl = generics.getElement(baseObject.driver,"url_bar");
        Assert.assertTrue("Privacy statement WebView is not displayed",prvcUrl.isDisplayed());
    }
}
