package com.epam.tat.selenium.page;


import java.util.List;

import com.epam.tat.selenium.entities.User;
import com.epam.tat.selenium.util.JSUtil;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

public class LoginPage extends BasePage{
    private static final String INPUT_LOGIN_ID = "mailbox__login";
    private static final String INPUT_LOGIN_NAME = "Login";
    private static final String INPUT_PASSWORD_ID = "mailbox__password";
    private static final String LOGIN_BUTTON_ID = "mailbox__auth__button";
    
    
    
    @FindBys({
        @FindBy(id = INPUT_LOGIN_ID),
        @FindBy(name = INPUT_LOGIN_NAME)
        })
        public List<WebElement> allInputs;

    @FindBy(id = INPUT_LOGIN_ID)
    private WebElement inputUser;
    @FindBy(id = INPUT_PASSWORD_ID)
    private WebElement inputPassword;
    @FindBy(id = LOGIN_BUTTON_ID)
    private WebElement loginButton;


    public LoginPage(WebDriver driver) {
    	 super(driver);
    }

    public BasePage loginAs(User user) {
        super.clearField(inputUser);
        inputUser.sendKeys(user.getUsername());
        super.clearField(inputPassword);
        inputPassword.sendKeys(user.getPassword());
        JSUtil.highligtElement(loginButton, driver);
        loginButton.click();
		return new BasePage(driver);
    }
}
