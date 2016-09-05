package com.epam.tat.selenium.tests;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import junit.framework.Assert;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.epam.tat.selenium.entities.ConfigData;
import com.epam.tat.selenium.entities.User;
import com.epam.tat.selenium.factory.CapabilitiesFactory;
import com.epam.tat.selenium.factory.SourceFactory;
import com.epam.tat.selenium.factory.SourceFactory.Source;
import com.epam.tat.selenium.factory.WebDriverFactory;
import com.epam.tat.selenium.page.BasePage;
import com.epam.tat.selenium.page.ComposeMailPage;
import com.epam.tat.selenium.page.DraftsPage;
import com.epam.tat.selenium.page.LoginPage;
import com.epam.tat.selenium.page.SentPage;
import com.epam.tat.selenium.source.reader.ConfigReader;
import com.epam.tat.selenium.source.reader.XMLReader;

public class BasicTest {

	private static final int TIMEOUT = 20;
	protected WebDriver driver;
	protected User user1;
	protected User user2;
	protected LoginPage loginPage;
	protected BasePage basePage;
	protected ComposeMailPage composeMailPage;
	protected DraftsPage draftsPage;
	protected SentPage sentPage;

	@BeforeClass
	public void initUsers() {
		user1 = new User("pilnikava_1", "1UserPassword");
		user2 = new User("pilnikava_2", "2UserPassword");
	}

	
	@BeforeTest
	@Parameters({ "config", "property"})
	public void initStartPage(String config, String property) throws IOException {
		XMLReader.convertToXML();
		ConfigReader reader = ConfigReader.getInstance();
		String configType = reader.getPropValues(config, property);
		Assert.assertNotNull(configType);
		Source source = SourceFactory.getSource(configType);  
		Assert.assertNotNull("Wrong source type",source);
		ConfigData data = source.getReader().read();
		System.out.println(String.format("Initializing %s browser driver", data.getBrowser()));
        if ("".equals(data.getGridUrl())) {
            driver = WebDriverFactory.getWebDriver(data.getBrowser());
        } else {
            driver = WebDriverFactory.getWebDriver(data.getGridUrl(), CapabilitiesFactory.getDesiredCapabilities(data.getBrowser()));
        }
        driver.manage().timeouts().implicitlyWait(TIMEOUT, TimeUnit.SECONDS);
        System.out.println(String.format("Browser driver for  %s browser was successfully initialized", data.getBrowser()));
        driver.get(data.getUrl());

	}

	@AfterTest
	public void clearEmailAndDisposeDriver() {
		basePage = loginPage.loginAs(user1);
		draftsPage = basePage.goToDraft();
		handleAlert();
		draftsPage.clearDrafts();
		sentPage = draftsPage.goToSent();
		handleAlert();
		sentPage.clearSentMails();
		sentPage.logout();
		driver.close();
	}

	protected void handleAlert() {
		try {
			Alert alert = driver.switchTo().alert();
			alert.accept();
		} catch (NoAlertPresentException ignore) {
		}
	}
}
