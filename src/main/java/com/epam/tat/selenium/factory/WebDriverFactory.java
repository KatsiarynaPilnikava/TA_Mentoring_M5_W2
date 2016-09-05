package com.epam.tat.selenium.factory;

import java.io.IOException;
import java.net.MalformedURLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.epam.tat.selenium.source.reader.ConfigReader;


public class WebDriverFactory {
	private static final String CHROME_PROPERTY = "chrome.property";
	private static final String PATH_TO_CHROME_BROWSER = "chrome.driver.path";
	private static final String CHROME = "google_chrome";
	private static final String FIREFOX = "firefox";
	
	public static WebDriver getWebDriver(String browser) throws IOException {
		ConfigReader reader = ConfigReader.getInstance();
        WebDriver driver = null;
		switch (browser) {
		case CHROME:
			System.setProperty(reader.getPropValues("prop", PATH_TO_CHROME_BROWSER), reader.getPropValues("prop", CHROME_PROPERTY));
			driver = new ChromeDriver();
			break;
		case FIREFOX:
			driver = new FirefoxDriver();
			break;
		default:
			System.setProperty(reader.getPropValues("prop", PATH_TO_CHROME_BROWSER), reader.getPropValues("prop", CHROME_PROPERTY));
			driver = new ChromeDriver();
			
		}
        return driver;
    }
	 public static WebDriver getWebDriver(String url, DesiredCapabilities capabilities) {
		 
	        WebDriver driver = null;
	        try {
	            driver = new RemoteWebDriver(new java.net.URL(url), capabilities);
	        } catch (MalformedURLException e) {
	            e.printStackTrace();
	            return null;
	        }
	        return driver;
	    }
}

