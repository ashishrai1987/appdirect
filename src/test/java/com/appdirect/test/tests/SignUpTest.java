package com.appdirect.test.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.appdirect.test.base.TestBaseSetup;
import com.appdirect.test.base.TestData;
import com.appdirect.util.ExcelUtils;



public class SignUpTest extends TestBaseSetup{
	
	ExcelUtils excel;
	@Parameters("browserType")
	@BeforeClass
	public void setUp(char browserType)
	{
		initializeTestBaseSetup(browserType);
	}	
	
	@Test
	public void verifyLogin()
	{
		loginPage.clickLoginIcon();
		signuppage = loginPage.clickSignUpBtn();
	}
	
	@Test(dataProvider = "users" , dataProviderClass = TestData.class, dependsOnMethods = { "verifyLogin" })
	public void verfySignUpforInvalidUsers(String user) throws IOException
	{
		System.out.println("Invalid User: "+user);
		signuppage.enterEmail(user);
		signuppage.clickSignUpBtn();
		Assert.assertFalse(signuppage.getSignUpVerificationMessage().contains("Thanks for registering"));
	}
		
	
	@Test(dataProvider = "users", dataProviderClass = TestData.class, dependsOnMethods = { "verifyLogin" })
	public void verfySignUpforValidUsers(String user) throws IOException
	{
		System.out.println("Valid User: "+user);
		user=user.substring(0, user.indexOf('@'))+String.valueOf((int)(Math.random()*10000))+user.substring(user.indexOf('@'),user.length());
		signuppage.enterEmail(user);
		signuppage.clickSignUpBtn();
		Assert.assertTrue(signuppage.getSignUpVerificationMessage().contains("Thanks for registering"));
		navigateBack();
		loginPage.clickSignUpBtn();
	}
	
	
	@Test(dataProvider = "users", dataProviderClass = TestData.class, dependsOnMethods = { "verifyLogin" })
	public void verifyRegisteredUsers(String user)
	{
		signuppage.enterEmail(user);
		signuppage.clickSignUpBtn();
		Assert.assertTrue(signuppage.getRegisteredMailMessage().contains("already been registered"));
	}
	
	
	
	@AfterClass(alwaysRun=true)
	public void tearDown()
	{
		super.tearDown();
	}

}
