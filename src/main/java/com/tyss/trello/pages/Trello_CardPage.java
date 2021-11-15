package com.tyss.trello.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.tyss.trello.util.WebActionUtil;

public class Trello_CardPage {
	public WebDriver driver;
	public WebActionUtil WebActionUtil;
	public long ETO=50;

	public Trello_CardPage(WebDriver driver, Long ETO, WebActionUtil WebActionUtil) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.WebActionUtil = WebActionUtil;
		this.ETO=50;	

	}

	/*Description Text Area*/
	@FindBy(xpath = "//a[contains(@class,'description-fake-text-area hide-on-edit js-edit-desc  js-hide-with-draft')]")
	private WebElement taDescription;
	
	/*Description Text Area*/
	@FindBy(xpath = "//a[contains(text(),'Add a more detailed description')]")
	private WebElement txtDescription;

	/*Save Button*/
	@FindBy(xpath = "//input[contains(@class,'confirm mod-submit-edit js-save-edit')]")
	private WebElement btnSaveDescription;

	/*Comment Text Area*/
	@FindBy(xpath = "//textarea[@class='comment-box-input js-new-comment-input']")
	private WebElement taComment;

	/*Comment Save Button*/
	@FindBy(xpath = "//input[contains(@class,'primary confirm mod-no-top-bottom-margin js-add-comment')]")
	private WebElement btnSaveComment;

	/*CheckList link*/
	@FindBy(xpath = "//a[@class='button-link js-add-checklist-menu']")
	private WebElement lnkCheckList;

	/*CheckList Title text field*/
	@FindBy(id = "id-checklist")
	private WebElement tbCheckListTitle;

	/*Add CheckList button*/
	@FindBy(xpath = "//input[contains(@class,'primary wide confirm js-add-checklist')]")
	private WebElement btnAddCheckList;

	/*Add Item text field*/
	@FindBy(xpath = "//textarea[contains(@class,'new-item-text js-new-checklist-item-input')]")
	private WebElement tbAddItem;

	/*Add Item button*/
	@FindBy(xpath = "//input[contains(@class,'primary confirm mod-submit-edit js-add-checklist-item')]")
	private WebElement btnAddItem;

	/*Item CheckBox*/
	@FindBy(xpath = "//div[@class='checklist-item-checkbox enabled js-toggle-checklist-item']")
	private WebElement checkBoxItem;

	/*Labels link*/
	@FindBy(xpath = "//a[@class='button-link js-edit-labels']")
	private WebElement lnkLabels;

	/*Label pencil icon*/
	public WebElement selectLabel(String labelIcon) {

		String xpath = "//span[contains(@class,'card-label-"+labelIcon+"')]/preceding-sibling::a";

		return driver.findElement(By.xpath(xpath));
	}
	@FindBy(xpath = "//span[contains(@class,' card-label-green  js-select-label selected')]/preceding-sibling::a")
	private WebElement icnPencilLabel;

	/*Label Name text field*/
	@FindBy(id = "labelName")
	private WebElement tbLabelName;

	/*Label Save button*/
	@FindBy(xpath = "//input[@class='nch-button nch-button--primary wide js-submit']")
	private WebElement btnLabelSave;

	/*Label button*/ 
	public WebElement selectLabelColor(String labelName) {

		String xpath = "//a/following-sibling::span[contains(text(),'"+labelName+"')]";

		return driver.findElement(By.xpath(xpath));
	}

	@FindBy(xpath = "//a/following-sibling::span[contains(@class,'card-label mod-selectable card-label')]")
	private List<WebElement> btnlabelsOptions;

	@FindBy(xpath = "//span[contains(@class,'label-green active js-select-label selected')]")
	private WebElement btnlabel;

	/*Label close button*/
	@FindBy(xpath = "//a[@class='pop-over-header-close-btn icon-sm icon-close']")
	private WebElement btnlabelClose;

	/*Template button*/
	@FindBy(xpath = "//span[text()='Make template']")
	private WebElement btntemplate;

	/*Template button*/
	@FindBy(xpath = "//a[@class='icon-md icon-close dialog-close-button js-close-window']")
	private WebElement btnCloseCard;

	/*Open Card*/
	@FindBy(xpath = "//span[@class='list-card-title js-card-name']")
	private WebElement btnCard;

	/*label heading*/
	@FindBy(xpath = "//div[contains(@class,'card-detail-item-labels')]/h3[@class='card-detail-item-header']")
	private WebElement txtLabelsHeader;

	/*Auto labelName color*/
	public WebElement checkLabelColor(String color) {

		String xpath = "//h3/following-sibling::div/span[contains(@class,'card-label card-label-"+color+"')]";

		return driver.findElement(By.xpath(xpath));
	}
	
	/*Open card  button*/
	@FindBy(xpath = "//a[@class='quick-card-editor-buttons-item']/span[text()='Open card']")
	private WebElement btnOpenCard;
	

	/*Auto labelName text*/
	public WebElement checkLabelTitle(String color) {

		String xpath = "//h3/following-sibling::div/span[contains(@class,'card-label card-label-"+color+"')]/span";

		return driver.findElement(By.xpath(xpath));
	}
	
	/*Created Card Name*/
	@FindBy(xpath = "//span[@class='list-card-title js-card-name']")
	private WebElement lnkCreatedCardName;

	/*Pencil icon*/
	@FindBy(xpath = "//span[@class='icon-sm icon-edit list-card-operation dark-hover js-open-quick-card-editor js-card-menu']")
	private WebElement imgPencilIcon;
	/** 
	 * Description: Method implements to Add Description for a Card
	 * @author sushmita p h
	 * @param description
	 */
	public synchronized void addDescription(String description) {
		try {
			WebActionUtil.waitForElement(lnkCreatedCardName, "Created Card name", 30);
			WebActionUtil.poll(2000);
			WebActionUtil.mouseHoverToElement(lnkCreatedCardName, "Created Card name");
			WebActionUtil.poll(2000);
			WebActionUtil.clickOnElementUsingJS(imgPencilIcon, "click to Pencil Icon");
			WebActionUtil.poll(2000);
			WebActionUtil.clickOnWebElement(btnOpenCard, "click to Open Card Button", "Unable to click on Open card Button");
			
			WebActionUtil.waitForElement(txtDescription, "Description Area", 30);
			WebActionUtil.typeText(txtDescription, description, "Description Text Area");
		}catch(Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.error("Unable to add Description for a Card");
			Assert.fail("Unable to add Description for a Card");
		}
	}

	/** 
	 * Description: Method implements to Add Comment for a Card
	 * @author sushmita p h
	 * .
	 */
	public synchronized void addComment(String comment) {
		try {
			WebActionUtil.waitForElement(lnkCreatedCardName, "Created Card name", 90);
			WebActionUtil.mouseHoverToElement(lnkCreatedCardName, "Created Card name");
			
			WebActionUtil.clickOnElementUsingJS(imgPencilIcon, "click to Pencil Icon");
			WebActionUtil.poll(2000);
			WebActionUtil.clickOnWebElement(btnOpenCard, "click to Open Card Button", "Unable to click on Open card Button");
			
			WebActionUtil.waitForElement(taComment, "Comment Text Area", 30);
			WebActionUtil.typeText(taComment, comment, "Comment Text Area");
		}catch(Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.error("Unable to Add Comment for a Card");
			Assert.fail("Unable to Add Comment for a Card");
		}
	}

	/** 
	 * Description: Method implements to Click on Description Save Button
	 * @author sushmita p h
	 * 
	 */
	public synchronized void clkDescriptionSaveBtn() {
		try {
			WebActionUtil.waitForElement(btnSaveDescription, "Description Save Button", 30);
			WebActionUtil.clickOnWebElement(btnSaveDescription, "Description Save Button", "Unable to Click on Description Save Button");
			WebActionUtil.info("Description is added");
		}catch(Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.error("Unable to Click on Description Save Button");
			Assert.fail("Unable to Click on Description Save Button");
		}
	}

	/** 
	 * Description: Method implements to Click on Comment Save Button
	 * @author sushmita p h
	 * 
	 */
	public synchronized void clkCommentSaveBtn() {
		try {
			WebActionUtil.waitForElement(btnSaveComment, "Comment Save Button", 30);
			WebActionUtil.clickOnWebElement(btnSaveComment, "Comment Save Button", "Unable to Click on Comment Save Button");
		}catch(Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.error("Unable to Click on Comment Save Button");
			Assert.fail("Unable to Click on Comment Save Button");
		}
	}

	/** 
	 * Description: Method implements to Click on CheckList Link
	 * @author sushmita p h
	 * 
	 */
	public synchronized void clkCheckListLnk() {
		try {
			WebActionUtil.poll(3000);
			WebDriverWait wait = new WebDriverWait(driver,10);
			wait.until(ExpectedConditions.visibilityOf(btnCard));		
			WebActionUtil.clickOnWebElement(btnCard, "Created Card Button", "Unable to Click on Created Card Button");

			WebActionUtil.waitForElement(lnkCheckList, "CheckList Link", 20);
			WebActionUtil.clickOnWebElement(lnkCheckList, "CheckList Link", "Unable to Click on CheckList Link");

		}catch(Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.error("Unable to Click on CheckList Link");
			Assert.fail("Unable to Click on CheckList Link");
		}
	}

	/** 
	 * Description: Method implements to Add Checklist Title
	 * @author sushmita p h
	 * @param checkListTitle
	 */
	public synchronized void addChecklistTitle(String checkListTitle) {
		try {
			WebActionUtil.poll(2000);
			WebActionUtil.waitForElement(tbCheckListTitle, "CheckList Title", 10);

			System.out.println("Checklist label");
			//WebActionUtil.clickOnWebElement(tbCheckListTitle, "checkList Title", "Unable to Click on CheckList text field");
			WebActionUtil.clearText(tbCheckListTitle, "CheckList Title");
			WebActionUtil.typeText(tbCheckListTitle, checkListTitle, "CheckList Title");
			WebActionUtil.clickOnWebElement(btnAddCheckList, "CheckList Add Button", "Unable to Click on CheckList Add Button");

		}catch(Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.error("Unable to Add CheckList title");
			Assert.fail("Unable to Add CheckList title");
		}
	}

	/** 
	 * Description: Method implements to Add Checklist Item
	 * @author sushmita p h
	 * @param checkListItem
	 */
	public synchronized void addChecklistItem(String checkListItem) {
		try {
			WebActionUtil.waitForElement(tbAddItem, "CheckList Item", 30);
			WebActionUtil.typeText(tbAddItem, checkListItem, "checkList Item");
			WebActionUtil.clickOnWebElement(btnAddItem, "CheckList Item Add Button", "Unable to Click on Item Add Button");
			WebActionUtil.waitForElement(checkBoxItem, "Item Checkbox", 30);
			WebActionUtil.clickCheckBox(checkBoxItem, "Item Checkbox");
		}catch(Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.error("Unable to Add CheckList Item");
			Assert.fail("Unable to Add CheckList Item");
		}
	}

	/** 
	 * Description: Method implements to Click on Labels Link
	 * @author sushmita p h
	 * 
	 */
	public synchronized void clkLabelsLnk() {
		try {
			WebActionUtil.waitForElement(lnkCreatedCardName, "Created Card name", 90);
			WebActionUtil.mouseHoverToElement(lnkCreatedCardName, "Created Card name");
			
			WebActionUtil.clickOnElementUsingJS(imgPencilIcon, "click to Pencil Icon");
			WebActionUtil.clickOnWebElement(btnOpenCard, "click to Open Card Button", "Unable to click on Open card Button");
			

			WebActionUtil.waitForElement(lnkLabels, "Labels Link", 20);
			WebActionUtil.clickOnWebElement(lnkLabels, "Labels Link", "Unable to Click on Labels Link");

		}catch(Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.error("Unable to Click on Labels Link");
			Assert.fail("Unable to Click on Labels Link");
		}
	}

	/** 
	 * Description: Method implements to Add Label for a card
	 * @author sushmita p h
	 * @param labelName
	 */
	public synchronized void setLabelName(String labelName,String labelColor) {
		try {
			System.out.println("label color");
			WebActionUtil.waitForElement(selectLabel(labelColor), "Label Pencil Icon", 20);
			WebActionUtil.clickOnWebElement(selectLabel(labelColor), "Label Pencil Icon", "Unable to Click on Label Pencil Icon");

			try {
				WebActionUtil.waitForElement(tbLabelName, "label Name text field", 20);
				WebActionUtil.typeText(tbLabelName, labelName, "label Name text field");

				WebActionUtil.clickOnWebElement(btnLabelSave, "Label Save Button", "Unable to Click on Label Save Button");
			}catch(Exception e) {
				WebActionUtil.error(e.getMessage());
				WebActionUtil.error("Unable to set Label name");
				Assert.fail("Unable to set Label name");
			}

			setLabel(labelName);
		}catch(Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.error("Unable to Add Label for a card");
			Assert.fail("Unable to Add Label for a card");
		}
	}

	/** 
	 * Description: Method implements to select Created Label
	 * @author sushmita p h
	 * 
	 */
	public synchronized void setLabel(String labelName) {
		try {
			System.out.println("clicked on label");

			WebActionUtil.waitForElement(selectLabelColor(labelName), "Labels Name", 30);

			WebActionUtil.poll(3000);
			//			WebDriverWait wait = new WebDriverWait(driver,30);
			//			wait.until(ExpectedConditions.visibilityOf(selectLabelColor(labelName)));

			WebActionUtil.clickOnElementUsingJS(selectLabelColor(labelName), "Label button");
			System.out.println("clicked on label");
			//			WebActionUtil.clickOnWebElement(selectLabelColor(labelName), "Label button", "Unable to Click on Labels button");
			WebActionUtil.clickOnWebElement(btnlabelClose, "Label  Close button", "Unable to Click on Label Close button");

		}catch(Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.error("Unable to select Created Label");
			Assert.fail("Unable to select Created Label");
		}
	}

	/** 
	 * Description: Method implements to click on close card button
	 * @author sushmita p h
	 * 
	 */
	public synchronized void clkCloseCardPopUp() {
		try {
			WebActionUtil.waitForElement(btnCloseCard, "Card Close button", 30);
			WebActionUtil.clickOnWebElement(btnCloseCard, "Card Close button", "Unable to Click on Card Close button");

		}catch(Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.error("Unable to Click on Card Close button");
			Assert.fail("Unable to Click on Card Close button");
		}
	}

	/** 
	 * Description: Method implements to click on Make Template
	 * @author sushmita p h
	 * 
	 */
	public synchronized void clkOnMakeTemplate() {
		try {
			WebActionUtil.waitForElement(lnkCreatedCardName, "Created Card name", 90);
			WebActionUtil.mouseHoverToElement(lnkCreatedCardName, "Created Card name");
			
			WebActionUtil.clickOnElementUsingJS(imgPencilIcon, "click to Pencil Icon");
			WebActionUtil.clickOnWebElement(btnOpenCard, "click to Open Card Button", "Unable to click on Open Card Button");
			
//			WebDriverWait wait = new WebDriverWait(driver,20);
//			wait.until(ExpectedConditions.visibilityOf(btnCard));		
//			WebActionUtil.clickOnElementUsingJS(btnCard, "Created Card Button");			

			WebActionUtil.waitForElement(btntemplate, "Make Template button", 30);
			WebActionUtil.clickOnWebElement(btntemplate, "Make Template button", "Unable to Click on Make Template button");

		}catch(Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.error("Unable to Click on Make Template button");
			Assert.fail("Unable to Click on Make Template button");
		}
	}

	/** 
	 * Description: Method implements to Create CheckList for a Card
	 * @author sushmita p h
	 * @param  checkListTitle
	 * @param checkListItem
	 */
	public synchronized void addCheckList(String checkListTitle,String checkListItem) {
		try {

			clkCheckListLnk();

			addChecklistTitle(checkListTitle);

			addChecklistItem(checkListItem);
			WebActionUtil.info("Checklist created");
		}catch(Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.error("Unable to CreateCheckList for a Card");
			Assert.fail("Unable to CreateCheckList for a Card");
		}
	}

	/** 
	 * Description: Method implements to Create CheckList for a Card
	 * @author sushmita p h
	 * @param  checkListItem
	 * @param checkListTitle
	 */
	public synchronized void addLabel(String labelName,String color) {
		try {
			clkLabelsLnk();
			setLabelName(labelName,color);

		}catch(Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.error("Unable to CreateCheckList for a Card");
			Assert.fail("Unable to CreateCheckList for a Card");
		}
	}

	
	/** 
	 * Description: Method implements to validate Comment Created for a Card
	 * @author sushmita p h
	 * 
	 */
	public synchronized void validateComment(String expComment) {
		try {
			WebElement actualComment = driver.findElement(By.xpath("//p[text()='"+expComment+"']"));

			Assert.assertEquals(actualComment,expComment);
			WebActionUtil.info("Comment is created for a card");
		}catch(Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.error("Comment is not created for a Card");
			Assert.fail("Comment is not created for a Card");
		}
	}

	/** 
	 * Description: Method implements to validate Comment Created for a Card
	 * @author sushmita p h
	 * 
	 */
	public synchronized void validateLabel(String color,String expText) {
		try {
			boolean flag =false;
			if(txtLabelsHeader.isDisplayed()) {
				WebActionUtil.info("Label Header is displayed");
				WebActionUtil.validationinfo("Label Header is displayed", "blue");

				if(checkLabelColor(color).isDisplayed()) {
					WebActionUtil.info("Successfully created Label with color " +color);
					WebActionUtil.validationinfo("Successfully created Label with color " +color, "blue");


					if(checkLabelTitle(color).getText().equalsIgnoreCase(expText)) {
						WebActionUtil.info("Successfully created Label with text " +expText);
						WebActionUtil.validationinfo("Successfully created Label with text " +expText, "blue");


						flag = true;

					}

				}

			}

			if(flag == true) {
				WebActionUtil.info("Successfully created Label with color " +color+" and with Text " +expText);
				WebActionUtil.validationinfo("Successfully created Label with color " +color+" and with Text" +expText, "blue");
			}

			else {
				WebActionUtil.info("Failed to create Label with color " +color+" and with Text " +expText);
				WebActionUtil.fail("Failed to create Label with color " +color+" and with Text" +expText);
				Assert.fail("Failed to create Label with color " +color+" and with Text" +expText);
			}
		}
		catch(Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.error("Label is not created for a Card");
			Assert.fail("Label is not created for a Card");
		}
	}

	//	/** 
	//	 * Description: Method implements to validate Checklist Created for a Card
	//	 * @author sushmita p h
	//	 * 
	//	 */
	//	public synchronized void validateChecklist(String cardName) {
	//		try {
	//			WebElement actualChecklist = driver.findElement(By.xpath("//span[text()="+cardName+"]/parent::div//div[@class='badge js-checkitems-badge is-complete']"));
	//
	//			Assert.assertTrue(actualChecklist.isDisplayed());
	//			WebActionUtil.info("Checklist is created for a card");
	//		}catch(Exception e) {
	//			WebActionUtil.error(e.getMessage());
	//			WebActionUtil.error("Checklist is not created for a Card");
	//			Assert.fail("Checklist is not created for a Card");
	//		}
	//	}

	/** 
	 * Description: Method implements to validate Template Created for a Card
	 * @author sushmita p h
	 * 
	 */
	public synchronized void validateTemplate(String expTemplate) {
		try {
			WebElement actualTemplate = driver.findElement(By.xpath("//p[text()='"+expTemplate+"']"));

			Assert.assertEquals(actualTemplate,expTemplate);
			WebActionUtil.info("Template is created for a card");
		}catch(Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.error("Template is not created for a Card");
			Assert.fail("Template is not created for a Card");
		}
	}

}
