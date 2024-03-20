package com.ikea.homesmart.pages.android;


import com.ikea.homesmart.pages.PageFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Autowired;

public class ApplicationPage {

    @Autowired
    public PageFactory pageFactory;

    @FindBy(xpath = "//*[@id='next']")
    WebElement nextButton;


    /**
     * This method is used to navigate to next page
     */
    public void clickonNextButton() {
        nextButton.click();
    }

}
