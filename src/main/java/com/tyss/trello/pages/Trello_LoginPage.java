package com.tyss.trello.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.tyss.trello.baseutil.BaseTest;
import com.tyss.trello.util.WebActionUtil;
/**
 * Description This class has the implementations of the Login 
 * @author Tanu katiyar
 */
public class Trello_LoginPage {
	
	public WebDriver driver;
	public WebActionUtil WebActionUtil;
	public long ETO = 10;

	public Trello_LoginPage(WebDriver driver, long ETO, WebActionUtil WebActionUtil) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.WebActionUtil = WebActionUtil;
		this.ETO = ETO;
	}

	/* Login In Button */
	@FindBy(xpath = "//span[text()='Log in']")
	private WebElement lnkLoginIn;

	/* User Name Text Box */
	@FindBy(id = "user")
	private WebElement txtEmail;
	
	/* Login in with Atlassian */
	@FindBy(id = "login")
	private WebElement btnLoginAtlassian;

	/* Password Text Box */
	@FindBy(name = "password")
	private WebElement txtPassword;
	
	/* Login Button*/
     @FindBy(xpath = "//span[text()='Log in']")
	 private WebElement btnLogin;
     
     
 	/**
 	 * Description Method to Login to the application
 	 * @author Tanu Katiyar
 	 * @param username
 	 * @param password
 	 * 
 	 */
	public synchronized void loginToApplication(String email,String password) {
		try {
			WebActionUtil.info("Login To the App");
			WebActionUtil.clickOnWebElement(lnkLoginIn, "Login In link", "Unable To Click On Login in Link");
			WebActionUtil.waitForElement(txtEmail, "Email Text Box", 20);
			WebActionUtil.typeText(txtEmail, email, "Email Text Box");
			WebActionUtil.clickOnWebElement(btnLoginAtlassian, "Login with Atlassian Button", "Unable To Click On Login with Atlassian Button");
			WebActionUtil.poll(3000);
			WebActionUtil.typeText(txtPassword, password, "Password Text Box");
			WebActionUtil.waitForElement(btnLogin, "Login Button", 20);
			WebActionUtil.clickOnWebElement(btnLogin, "Login Button", "Unable To Click On Login Button");
			WebActionUtil.validationinfo("Successfully logged in to the Application", "blue");
		} catch (Exception e) {
		
			WebActionUtil.error(e.getMessage());
			WebActionUtil.error("-Unable to Login in the Application");
			Assert.fail("Unable to Login in the Login Page");
		}

	}

}
