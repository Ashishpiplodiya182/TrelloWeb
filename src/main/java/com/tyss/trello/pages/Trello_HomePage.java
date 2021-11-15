package com.tyss.trello.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.tyss.trello.util.WebActionUtil;

public class Trello_HomePage {

	public WebDriver driver;
	public WebActionUtil WebActionUtil;
	public long ETO=50;

	public Trello_HomePage(WebDriver driver, Long ETO, WebActionUtil WebActionUtil) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.WebActionUtil = WebActionUtil;
		this.ETO=50;	

	}

	/*Create New Board*/
	@FindBy(xpath = "//span[contains(text(),'Create new board')]")
	private WebElement btnCreateNewBoard;

	/*Add Board Title text field*/
	@FindBy(xpath = "//input[@class='_2C8dwcFUoIOCYP']")
	private WebElement tbAddBoardTitle;

	/*Create Board Button*/
	@FindBy(xpath = "//button[contains(text(),'Create board')]")
	private WebElement btnCreateBoard;

	/**
	 * Description: Method implements to click on Create New Board Button
	 * @author Sushmita P H
	 * 
	 */
	public synchronized void clkCreateNewBoard() {
		try {
			WebActionUtil.waitForElement(btnCreateNewBoard, "Create New Board Button", 30);
			WebActionUtil.poll(2000);
			WebActionUtil.clickOnElementUsingJS(btnCreateNewBoard, "Create New Board Button");
		}catch(Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.error("Unable to click on Create New Board Button");
			Assert.fail("Unable to click on Create New Board Button");

		}
	}

	/**
	 * Description: Method implements to Add Board Title
	 * @author Sushmita P H
	 * 
	 */
	public synchronized void setBoardTitle(String boardTitle) {
		try {
			WebActionUtil.waitForElement(tbAddBoardTitle, "Create New Board Button", 30);
			WebActionUtil.typeText(tbAddBoardTitle, boardTitle, "Add Board Title text field");
		}catch(Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.error("Unable to Set Board Title");
			Assert.fail("Unable to Set Board Title");

		}
	}


	/**
	 * Description: Method implements to click on Create New Board Button
	 * @author Sushmita P H
	 * 
	 */
	public synchronized void clkCreateBoardBtn() {
		try {
			WebActionUtil.waitForElement(btnCreateBoard, "Create Board Button", 30);
			//				WebActionUtil.clickOnElementUsingJS(btnCreateNewBoard, "Create Board Button");
			WebActionUtil.clickOnWebElement(btnCreateBoard, "Create Board Button", "Unable to click on Create Board Button");
			WebActionUtil.validationinfo("Successfully created Board", "blue");
		}catch(Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.error("Unable to click on Create Board Button");
			Assert.fail("Unable to click on Create Board Button");

		}
	}



}
