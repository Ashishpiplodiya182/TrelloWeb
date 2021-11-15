package com.tyss.trello.baseutil;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.ProfilesIni;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateDriver {
	
	private static CreateDriver instance = null;
	
	private ThreadLocal <WebDriver> webDriver =new ThreadLocal<WebDriver>();
	

	/*constructor*/
	 private CreateDriver() {
	 }
	 
	 /**
	  * getInstance method to retrieve active driver instance
	  *
	  * @return CreateDriver
	  */
	  public static CreateDriver getInstance() {
	  if ( instance == null ) {
	  instance = new CreateDriver();
	  }
	  return instance;
	  }
	  
	  @SuppressWarnings("deprecation")
	public  WebDriver setdriver(String browser)
	  {
		if (browser.equalsIgnoreCase("Chrome")) {
			WebDriverManager.chromedriver().setup();
			return  new ChromeDriver();
		}
		else if(browser=="Firefox")
		{
			WebDriverManager.firefoxdriver().setup();
			return  new FirefoxDriver();
		}
		else
		{
		
		}
		return null;
     }
	  
	  
	  public DesiredCapabilities setWebDriverCapabilities(String browser, DesiredCapabilities capabilities) throws IOException {

		    switch (browser) {
		        case "ie":
		            capabilities = new DesiredCapabilities().internetExplorer();
		            break;
		        case "firefox":
		            capabilities = new DesiredCapabilities().firefox();
		            break;
		        case "chrome":
		        	
		            capabilities = new DesiredCapabilities().chrome();
		            
		            break;
		        case "edge":
		            capabilities = new DesiredCapabilities().edge();
		            break;
		        case "safari":
		            capabilities = new DesiredCapabilities().safari();
		            break;
		        default:
		            capabilities = null;
		    }
		    
		    return capabilities;
	  }
		
	  /**
		 * Description :Sets the driver instance
		 *
		 * @author Aatish Slathia ,Shreya, Vivek
		 * @param browser
		 *
		 */
		public WebDriver launchBrowser(String browser) {
			if (browser.equalsIgnoreCase("Chrome")) {
				System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
				return new ChromeDriver();
			} else if (browser.equalsIgnoreCase("Firefox")) {
				System.setProperty("webdriver.gecko.driver", "./drivers/geckodriver.exe");
				return new FirefoxDriver();
			} else if (browser.equalsIgnoreCase("Edge")) {
				System.setProperty("webdriver.edge.driver", "./drivers/EdgeDriver.exe");
				return new EdgeDriver();
			} else {
				return null;
			}

		}

		/**
		* Description : Sets the web driver for Grid
		*
		* @author Sajal
		* @param browser
		* @param LOCAL_HUB_URL
		*/
		public WebDriver setWebDriver(String browser, String LOCAL_HUB_URL){
		browser = browser.toLowerCase();
		DesiredCapabilities capabilities = null;
		switch (browser) {
		case "ie":
		capabilities = new DesiredCapabilities().internetExplorer();
		break;
		case "firefox":
		FirefoxOptions firefoxOptions = new FirefoxOptions();
		// firefoxOptions.setHeadless(true);
		
		firefoxOptions.setBinary("C:\\Program Files\\Mozilla Firefox\\firefox.exe"); 
        //This is the location where you have installed Firefox on your machine
		ProfilesIni profile = new ProfilesIni();
		String userName = System.getProperty("user.name");
		System.out.println(userName);
//		FirefoxProfile testprofile = profile.getProfile("C:\\Users\\sushmita\\AppData\\Roaming\\Mozilla\\Firefox\\Profiles");
//		System.out.println(testprofile);
//		testprofile.setPreference("dom.webnotifications.enabled", false);
//		testprofile.setPreference("dom.push.enabled", false);
		capabilities = new DesiredCapabilities();
		capabilities.setBrowserName(BrowserType.FIREFOX);
//		capabilities.setCapability(FirefoxDriver.PROFILE, testprofile);
		capabilities.setCapability(firefoxOptions.FIREFOX_OPTIONS, firefoxOptions);
		firefoxOptions.merge(capabilities);
		break;
		case "chrome":
		ChromeOptions chromeOptions = new ChromeOptions();
		//  chromeOptions.addArguments("--headless");
		chromeOptions.addArguments("--disable-popup-blocking");
		chromeOptions.addArguments("--disable-infobars");
		chromeOptions.addArguments("--disable-notifications");
		capabilities = new DesiredCapabilities();
		capabilities.setBrowserName(BrowserType.CHROME);
		capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
		chromeOptions.merge(capabilities);
		break;
		case "edge":
		EdgeOptions edgeoptions = new EdgeOptions();
		capabilities = new DesiredCapabilities();
		capabilities.setBrowserName(BrowserType.EDGE);
		capabilities.setPlatform(Platform.WINDOWS);
		break;
		case "safari":
		capabilities = new DesiredCapabilities().safari();
		break;
		default:
		capabilities = null;
		}
		try {
		return new RemoteWebDriver(new URL(LOCAL_HUB_URL), capabilities);
		} catch (MalformedURLException e) {
		BaseTest.logger.info("The given HUB URL is Malformed");
		}
		return null;
		}


		public WebDriver setWebDriver(String browser) {
			if (browser.equalsIgnoreCase("Chrome")) {
				System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
				String userPath = System.getProperty("user.home") + "\\AppData\\Local\\Google\\Chrome\\UserData";
				ChromeOptions chromeOpt = new ChromeOptions();
				chromeOpt.addArguments("user-data-dir=" + userPath);
				return new ChromeDriver(chromeOpt);
			}
	 else if (browser.equalsIgnoreCase("Firefox")) {
				System.setProperty("webdriver.gecko.driver", "./drivers/geckodriver.exe");
				return new FirefoxDriver();
			} else if (browser.equalsIgnoreCase("Edge")) {
				
			} else {

			}
			return null;
		}			
}
		