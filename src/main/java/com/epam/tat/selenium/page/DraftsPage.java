package com.epam.tat.selenium.page;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

public class DraftsPage extends BasePage {
    private static final String MAIL_TITLE_XPATH = "//a[@title=\"";
    private static final String BODY = "//body";
    @FindBy(xpath = BODY)
    private WebElement page;
    private WebElement savedMail;

    public DraftsPage(WebDriver driver) {
        super(driver);
    }

    public boolean checkIfMailSaved(String mailTo) {
        goToDraft();
        try {
            savedMail = driver.findElement(By.xpath(MAIL_TITLE_XPATH + mailTo + "\"]"));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public ComposeMailPage openSavedMail() {
        savedMail.click();
		return new ComposeMailPage(driver);
    }

    public void clearDrafts() {
        page.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        page.sendKeys(Keys.DELETE);
    }
}
