package com.appdirect.test.base;

import java.awt.Toolkit;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.appdirect.test.pageobjects.LoginPage;
import com.appdirect.test.pageobjects.SignUpPage;
import com.appdirect.util.Utilities;



public class TestBaseSetup {

	protected WebDriver driver;
	static String driverPath = "Dependencies/";

	static String OS_NAME = System.getProperty("os.name");
	
	public static Logger log = Logger.getLogger(TestBaseSetup.class.getName());

	protected SignUpPage signuppage;
	protected LoginPage loginPage;
	

	public TestBaseSetup() {
		System.out.println("Constructor");
		
	}

	public WebDriver getDriver() {
		return driver;
	}

	private void setDriver(char browserType, String appURL) {
		switch (browserType) {
		case 'C':
			log.info("Launching Chrome");
			driver = initChromeDriver(appURL);
			break;
		case 'S':
			log.info("Launching Safari");
			driver = initSafariDriver(appURL);
			break;
		default:
			System.out.println("browser : " + browserType + " is invalid, Launching Firefox as browser of choice..");
			driver = initSafariDriver(appURL);
		}
	}

	private static WebDriver initChromeDriver(String appURL) {
		System.out.println("Launching google chrome with new profile..");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		org.openqa.selenium.Proxy proxy = new org.openqa.selenium.Proxy();
		proxy.setHttpProxy(null);
		DesiredCapabilities dc = DesiredCapabilities.chrome();
		dc.setCapability("proxy", proxy);
//		dc.setCapability("chrome.setProxyByServer", false);
		if (OS_NAME.equalsIgnoreCase("Mac OS X")) {
			System.setProperty("webdriver.chrome.driver", "Dependencies/chromedriver");
		} else if (OS_NAME.equalsIgnoreCase("Windows 7")) {
			System.setProperty("webdriver.chrome.driver", "Dependencies/chromedriver.exe");
		}
		// System.setProperty("webdriver.chrome.driver", driverPath +
		// "chromedriver");
		WebDriver driver = new ChromeDriver(options);
		if (OS_NAME.equalsIgnoreCase("Mac OS X")) {
			java.awt.Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			Integer width = (int) screenSize.getWidth();
			Integer height = (int) screenSize.getHeight();

			Point targetPosition = new Point(0, 0);
			driver.manage().window().setPosition(targetPosition);

			// your screen resolution here
			Dimension targetSize = new Dimension(width, height);
			driver.manage().window().setSize(targetSize);

		} else if (OS_NAME.equalsIgnoreCase("Windows 7")) {
			driver.manage().window().maximize();
		}
		
		
		
		//driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get(appURL);
		return driver;
	}

	private static WebDriver initSafariDriver(String appURL) {
		System.out.println("Launching Firefox browser..");
		WebDriver driver = new SafariDriver();
		driver.manage().window().maximize();
		driver.navigate().to(appURL);
		return driver;
	}

	public void navigateToURL(String url)
	{
		log.info("Launching "+url+"...");
		driver.navigate().to(url);
	}
	

	
	public void initializeTestBaseSetup(char browserType) {
		try {
			setDriver(browserType, Utilities.getDataFromConfig("appdirectURL"));
			loginPage = new LoginPage(driver);
		} catch (Exception e) {
			System.out.println("Error....." + e.getStackTrace());
		}
	}
	
	public void navigateBack()
	{
		log.info("Navigate back");
		driver.navigate().back();
	}
	
	

	public void tearDown() {
		System.out.println("Quit Browser");
		driver.quit();
	}

}
