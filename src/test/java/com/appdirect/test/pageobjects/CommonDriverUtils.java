package com.appdirect.test.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;



public abstract class CommonDriverUtils {

	protected WebDriver driver;
	
	public static Logger log = Logger.getLogger(CommonDriverUtils.class.getName());

	
	public CommonDriverUtils(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	boolean isElementLoaded(WebElement webElement) {
		if (webElement != null) {
			try {
				WebDriverWait wait = new WebDriverWait(driver, 20);
				wait.until(ExpectedConditions.elementToBeClickable(webElement));
				return true;
			} catch (Exception e) {
				log.error(e);
				return false;
			}
		} else
			return false;
	}
	
	boolean isElementVisible(WebElement webElement) {
		if (webElement != null) {
			try {
				WebDriverWait wait = new WebDriverWait(driver, 2);
				wait.until(ExpectedConditions.visibilityOf(webElement));
				return true;
			} catch (Exception e) {
				log.error(e);
				return false;
			}
		} else
			return false;
	}

	void hoverElement(WebElement element) {
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
	}

	
	void executeJS(String js)
	{
		JavascriptExecutor jscript = (JavascriptExecutor)driver;
		jscript.executeScript(js);
	}
	
	void waitFor(long seconds)
	{
		try {
			Thread.sleep(seconds*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void executeJS(String js, WebElement element)
	{
		JavascriptExecutor jscript = (JavascriptExecutor)driver;
		jscript.executeScript(js,element);
		
	}
	
	void selectElement(WebElement elem, String text)
	{
		Select select = new Select(elem);
		select.selectByVisibleText(text);
	}


}
