package com.ikea.homesmart.pages;

import com.ikea.homesmart.pages.android.ApplicationPage;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;


public class PageFactory {

    @Autowired
    public ApplicationPage application;

    public void pageSetup(WebDriver driver) {

        //Android
        application = org.openqa.selenium.support.PageFactory.initElements(driver, ApplicationPage.class);

    }
}
