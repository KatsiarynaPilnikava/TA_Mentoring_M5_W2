package com.epam.tat.selenium.tests;


import org.testng.Assert;
import org.testng.annotations.Test;

import com.epam.tat.selenium.page.LoginPage;
import com.epam.tat.selenium.steps.Step;
import com.epam.tat.selenium.util.JSUtil;

public class MailTest extends BasicTest {

    //private static final String MAIL_TITLE_XPATH = "//a[@title=\"";
    private String subject;
    private String text;

    @Test(description = "input login, password, press login button, check that login was successful", groups = "login test")
    public void loginTest() {
        loginPage = new LoginPage(driver);
        System.out.println("Attempting to login as user1...");
        basePage = loginPage.loginAs(user1);
        System.out.println("Login successful.");
        System.out.println("Verifying if login was successful");
        Assert.assertTrue(basePage.checkLogin(), "Login was insuccessful, logout button not found");
       
        System.out.println("Verification passed");
    }

    @Test(description = "compose email to be send to user2, subject and text fields should be generated automaticly", dependsOnGroups = "login test")
    public void composeMailTest() {
    	System.out.println("Attempting to open new window.");
    	Step step = new Step(driver);
    	step.composeMail(user2);
        Assert.assertTrue(composeMailPage.checkMailContent(text), "Wrong mail body or recipient");
    }


    @Test(description = "save mail drat, go to draft folder and check that email presents there", dependsOnMethods = "composeMailTest")
    public void checkDraftExists() {
    	composeMailPage.saveAsDraft();
        System.out.println("New mail saved as draft");
    	System.out.println("Attempting to open draft folder...");
    	draftsPage = composeMailPage.goToDraft();
    	JSUtil.waitForPage(driver);
        handleAlert();
        Assert.assertTrue(draftsPage.checkIfMailSaved(user2.getUsername() + "@mail.ru"), "Drafts folder is empty");
        System.out.println("There is saved mail in draft folder");
    }

    @Test(description = "open previously saved mail and check if content was saved properly", dependsOnMethods = "checkDraftExists")
    public void checkDraftContentTest() {
    	composeMailPage =  draftsPage.openSavedMail();
    	JSUtil.waitForPage(driver);
        Assert.assertTrue(composeMailPage.checkMailContent(user2.getUsername() + "@mail.ru", subject, text), "Not all elements were found successfully.");
    }

      
    @Test(description = "send mail, go to \"sent\" folder and check if message presents there", dependsOnMethods = "checkDraftContentTest")
    public void checkThatEmailWasSend() {
    	System.out.println("Trying to send draft mail...");
        composeMailPage.sendMail();
        System.out.println("Open sent folder");
        sentPage = basePage.goToSent();
        JSUtil.waitForPage(driver);
        System.out.println("Check sent mail folder");
        Assert.assertTrue(sentPage.checkIfMailSaved(user2 + "@mail.ru"), "Sent folder is empty");
    }

    @Test(description = "go to draft and make sure that email no longer presents at this folder", dependsOnMethods = "checkThatEmailWasSend")
    public void checkEmailNotAtDrafts() {
        System.out.println("Opening drafts folder.");
        draftsPage = sentPage.goToDraft();
        JSUtil.waitForPage(driver);
        System.out.println("Check if mail disappeared from drafts folder");
        Assert.assertFalse(draftsPage.checkIfMailSaved(user2.getUsername() + "@mail.ru"), "Drafts folder is not empty");
    }
}
