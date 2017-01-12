package com.appdirect.test.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignUpPage extends CommonDriverUtils{

	public SignUpPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	
	@FindBy(name = "emailAddress")
	WebElement userEmail;

	@FindBy(xpath = "//form[@name='signupForm']//h3")
	WebElement signupLabel;
	
	@FindBy(xpath = "//form[@name='signupForm']//button")
	WebElement signupBtn;
	
	@FindBy(id="signupLoginLink")
	WebElement loginLink;
	
	@FindBy(id="partnerRegisterLink")
	WebElement partnerSignupLink;
	
	@FindBy(className="signupConfirmationPanel")
	WebElement signupVerificationMsg;
	
	
	@FindBy(xpath="//p[contains(text(),'This email address has already been registered')]")
	WebElement regUser;
	

	public void enterEmail(String email)
	{
		log.info("Enter user: "+email);
		if(isElementLoaded(userEmail))
			userEmail.clear();
			userEmail.sendKeys(email);
	}
	
	public void clickSignUpBtn()
	{
		log.info("Click Signup");
		if(isElementLoaded(signupBtn))
			signupBtn.click();
	}
	
	public void clickLogin()
	{
		log.info("Click login from signup page");
		if(isElementVisible(loginLink))
			loginLink.findElement(By.tagName("a")).click();
	}

	public void clickPartnerSignup()
	{
		log.info("CLick partner signup link from signup page");
		if(isElementVisible(partnerSignupLink))
			partnerSignupLink.findElement(By.tagName("a")).click();
	}
	
	public String getSignUpVerificationMessage()
	{
		
		String msg="";
		if(isElementVisible(signupVerificationMsg))
			msg = signupVerificationMsg.findElement(By.tagName("h3")).getText();;
		return msg;
	}
	
	public String getRegisteredMailMessage()
	{
		String msg="";
		if(isElementVisible(regUser))
			msg = regUser.getText();
		return msg;
	}
	
	
	
}
