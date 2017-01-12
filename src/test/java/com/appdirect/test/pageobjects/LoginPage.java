package com.appdirect.test.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends CommonDriverUtils{

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	
	@FindBy(className = "login")
	WebElement loginIcon;
	
	@FindBy(id = "username")
	WebElement userEmail;

	@FindBy(id = "password")
	WebElement userPassword;

	@FindBy(name="signin")
	WebElement loginBtn;
	
	@FindBy(className="forgotPassword")
	WebElement forgotpassword;
	
	@FindBy(xpath = "//a[text()='Sign Up']")
	WebElement signup;

	@FindBy(xpath = "//a[text()='Sign up for an account']")
	WebElement signupLink;

	@FindBy(id = "rememberMe")
	WebElement rememberMe;

	@FindBy(id = "username-w-lbl")
	WebElement labelEmail;

	@FindBy(id = "password-w-lbl")
	WebElement labelPassword;
	
	@FindBy(className="feedbackPanelERROR")
	WebElement error;
	
	public void clickLoginIcon()
	{
		log.info("Click login Icon");
		if(isElementLoaded(loginIcon))
			loginIcon.click();
	}
	
	public SignUpPage clickSignUpLink()
	{
		log.info("CLick signup link from login page");
		if(isElementLoaded(signup))
			signup.click();
		return new SignUpPage(driver);
	}
	
	public SignUpPage clickSignUpBtn()
	{
		log.info("CLick signup button from login page");
		if(isElementLoaded(signupLink))
			signupLink.click();
		return new SignUpPage(driver);
	}
}
