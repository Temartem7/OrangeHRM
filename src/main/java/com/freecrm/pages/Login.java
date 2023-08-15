package com.freecrm.pages;

import com.freecrm.base.Base;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Properties;

public class Login extends Base {

    @FindBy (css = "input[name='username']")
    private WebElement username;

    @FindBy (css = "input[name='password']")
    private WebElement password;

    @FindBy (css = "button[type='submit']")
    private WebElement loginBtn;

    public Login(){
        super();
    }

    public boolean verifyLoginPage(){
        return username.isDisplayed();
    }
}
