package com.epam.tat.selenium.steps;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;

import com.epam.tat.selenium.entities.User;
import com.epam.tat.selenium.page.BasePage;
import com.epam.tat.selenium.page.ComposeMailPage;

public class Step {
	private static String subject;
	private static String text;
	private static ComposeMailPage composeMailPage;
	  
	private static BasePage basePage = new BasePage(null);

	public Step(WebDriver driver) {
		basePage = new BasePage(driver);
	}
	
	public void composeMail(User user2) {
		subject = RandomStringUtils.randomAlphabetic(8);
		text = RandomStringUtils.randomAlphabetic(12);
		composeMailPage = basePage.composeNewMail();
		composeMailPage.composeMail(user2.getUsername() + "@mail.ru", subject,	text);
	}
}
