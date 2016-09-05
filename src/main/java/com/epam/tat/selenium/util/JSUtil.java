package com.epam.tat.selenium.util;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JSUtil {
	private static final String GET_PAGE_TITLE_SCRIPT = "return document.title;";
	private static final String WAIT_FOR_PAGE_SCRIPT = "return document.readyState;";
	private static final int HIGHLIGHT_TIME_MS = 1000;
    private static final String HIGHLIGHT_SCRIPT = "arguments[0].style.border='4px solid green'";
    private static final String DISABLE_SCRIPT = "arguments[0].style.border=''";
//    private static final JavascriptExecutor js = new JavascriptExecutor();
	
	public static String getTitle(WebDriver driver) {
        return ((JavascriptExecutor) driver).executeScript(GET_PAGE_TITLE_SCRIPT).toString();
    }
	public static void waitForPage(WebDriver driver) {
		((JavascriptExecutor) driver).executeScript(WAIT_FOR_PAGE_SCRIPT).toString().equals("complete");
	}
	 public static void highligtElement(WebElement element, WebDriver driver){
			
		 ((JavascriptExecutor) driver).executeScript(HIGHLIGHT_SCRIPT, element);
	        try {
	            Thread.sleep(HIGHLIGHT_TIME_MS);
	        } catch (InterruptedException interruptedException) {
	            System.out.println(String.format("Unexpected exception:\n %s", interruptedException));
	        }
	        ((JavascriptExecutor) driver).executeScript(DISABLE_SCRIPT, element);
	    }

}
