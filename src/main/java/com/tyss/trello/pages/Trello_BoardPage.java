package com.tyss.trello.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.tyss.trello.util.WebActionUtil;

public class Trello_BoardPage {
	
	/**
	 * Description This class has the implementations of the Board page
	 * @author Tanu katiyar
	 */		
		public WebDriver driver;
		public WebActionUtil WebActionUtil;
		public long ETO = 10;

		public Trello_BoardPage(WebDriver driver, long ETO, WebActionUtil WebActionUtil) {
			this.driver = driver;
			PageFactory.initElements(driver, this);
			this.WebActionUtil = WebActionUtil;
			this.ETO = ETO;
		}

		/*List Name Plus Icon*/
		@FindBy(xpath = "//span[@class='icon-sm icon-add']")
		private WebElement imgPlusList;
		
		/*List Name*/
		@FindBy(xpath="//input[@class='list-name-input']")
		private WebElement txtListName;
		
		/*  List Name*/
		public WebElement listname(String listName) {

			String xpath ="//textarea[text()='"+listName+"']";
			return driver.findElement(By.xpath(xpath));
		}
		
		/*Add List*/
		@FindBy(xpath = "//div/input[@class='nch-button nch-button--primary mod-list-add-button js-save-edit']")
		private WebElement btnAddList;
		
		
		/*Add Card*/
		@FindBy(xpath = "//span[text()='Add a card']")
		private WebElement txtAddCard;
		
		/*Add Card*/
		@FindBy(xpath = "//span[text()='Add another list']")
		private WebElement txtAddAnotherList;
		
		
		/*Add Card*/
		@FindBy(xpath = "//div/textarea[@class='list-card-composer-textarea js-card-title']")
		private WebElement txtCardName;
		
		/*Add List*/
		@FindBy(xpath = "//div/input[@class='nch-button nch-button--primary confirm mod-compact js-add-card']")
		private WebElement btnAddCard;
		
		/*Add List*/
		@FindBy(className = "nch-button nch-button--primary confirm mod-compact js-add-card")
		private WebElement lnkCreatedCard;
		
		/*Add List*/
		@FindBy(xpath = "//span[@class='list-card-title js-card-name']")
		private WebElement lnkCreatedCardName;
		
		/*Pencil icon*/
		@FindBy(xpath = "//span[@class='icon-sm icon-edit list-card-operation dark-hover js-open-quick-card-editor js-card-menu']")
		private WebElement imgPencilIcon;
		
		
		/*Move button*/
		@FindBy(xpath = "//a[@class='quick-card-editor-buttons-item js-move-card']/span[text()='Move']")
		private WebElement btnMove;
		
		/*Copy button*/
		@FindBy(xpath = "//a[@class='quick-card-editor-buttons-item js-copy-card']/span[text()='Copy']")
		private WebElement btnCopy;
		
		/*list Option*/
		@FindBy(className = "js-select-list")
		private WebElement ddList;
		
		/*Submit button Move/Copy*/
		@FindBy(xpath = "//input[@class='nch-button nch-button--primary wide js-submit']")
		private WebElement btnSubmitCardAction;
		
		
		/*Archive button*/
		@FindBy(xpath = "//a[@class='quick-card-editor-buttons-item js-archive']/span[text()='Archive']")
		private WebElement btnArchive;
		
		/*Show menu button*/
		@FindBy(xpath = "//span[text()='Show menu']")
		private WebElement btnShowMenu;
		
		/*More option*/
		@FindBy(xpath = "//a[@class='board-menu-navigation-item-link js-open-more']")
		private WebElement optionMore;
		
		/*close Board option*/
		@FindBy(xpath = "//a[@class='board-menu-navigation-item-link js-close-board']")
		private WebElement optionCloseBoard;
		
		/*close option*/
		@FindBy(xpath = "//input[@class='js-confirm full nch-button nch-button--danger']")
		private WebElement optionClose;
		
		/*permanently delete board*/
		@FindBy(xpath = "//button[text()='Permanently delete board']")
		private WebElement lnkPermanentlyDelete;
		
		/*permanently delete board*/
		@FindBy(xpath = "//button[text()='Delete']")
		private WebElement btnDelete;
		
		
		 /*list name*/
        @FindBy(xpath = "//textarea[contains(@class,'list-header-name')]")
        private List<WebElement> txtCreatedListName;

        /*Validate list card*/
        public List<WebElement> listCard(String listName) {

            String xpath = "//h2[text()='"+listName+"']/parent::div/following-sibling::div//span[contains(@class,'list-card-title ')]";

            return driver.findElements(By.xpath(xpath));
        }	
        
		
		
		/**
		 * Description : This method Enter List Name in List Text Area.
		 * @author Tanu
		 * @param listName
		 * 
		 */
		public synchronized void setListName(String listName) {
			try {
				WebActionUtil.waitForElement(txtListName, "Enter List name", 30);
				WebActionUtil.clickOnWebElement(txtListName, "click to enter the ListName", "Unable to click to enter the ListName");
				WebActionUtil.poll(2000);
				WebActionUtil.typeText(txtListName, listName, "Enter List name");
				WebActionUtil.clickOnWebElement(btnAddList, "click to Add List Button", "Unable to click to Add List Button");
//				WebActionUtil.moveByOffset(10,10);
		}catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.info("List Name is Created");
			Assert.fail("Unable to create List Name ");
		}
	}
		
		/**
		 * Description : This method Enter List Name in List Text Area.
		 * @author Tanu
		 * @param listName
		 * 
		 */
		public synchronized void setAnotherListName(String listName) {
			try {
				WebActionUtil.waitForElement(txtAddAnotherList, "Enter List name", 10);
				WebActionUtil.clickOnWebElement(txtAddAnotherList, "click to enter the ListName", "Unable to click to enter the ListName");
				WebActionUtil.typeText(txtListName, listName, "Enter List name");
				WebActionUtil.clickOnWebElement(btnAddList, "click to Add List Button", "Unable to click to Add List Button");
				WebActionUtil.moveByOffset(10,10);

		}catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.info("List Name is Created");
			Assert.fail("Unable to create List Name ");
		}
	}
		
		/**
		 * Description : This method Enter Card Name in card Text Area.
		 * @author Tanu
		 * @param cardName
		 */
		public synchronized void setCardName(String cardName) {
			try {
				WebActionUtil.waitForElement(txtAddCard, "Enter Card name", 90);
				WebActionUtil.clickOnWebElement(txtAddCard, "click to enter the CardName", "Unable to click to enter the CardName");
				WebActionUtil.typeText(txtCardName, cardName, "Enter List name");
				WebActionUtil.clickOnWebElement(btnAddCard, "click to Add Card Button", "Unable to click to Add Card Button");

				
		}catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.info("Card Name is Created");
			Assert.fail("Unable to create Card Name ");
		}
	}
		
		/**
		 * Description : This method click on card Action.
		 * @author Tanu
		 * @param cardName
		 */
		public synchronized void clkOnMove(String listName) {
			try {
				WebActionUtil.waitForElement(lnkCreatedCardName, "Created Card name", 90);
				WebActionUtil.mouseHoverToElement(lnkCreatedCardName, "Created Card name");
				WebActionUtil.clickOnElementUsingJS(imgPencilIcon, "click to Pencil Icon");
				WebActionUtil.clickOnWebElement(btnMove, "click to Move Button", "Unable to click on Move Button");		
				//WebActionUtil.waitForElement(ddList, "Created Card name", 90);
		
				WebActionUtil.selectByVisibleText(ddList, listName);
				
				WebActionUtil.clickOnElementUsingJS(btnSubmitCardAction, "click to Move Button");
		}catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.info("click to Move Button");
			Assert.fail("Unable to click on Move Button");
		}
	}

		/**
		 * Description : This method Enter List Name in List Text Area.
		 * @author Tanu
		 * @param cardName
		 */
		public synchronized void clkOnCopy(String listName) {
			try {
				WebActionUtil.waitForElement(lnkCreatedCardName, "Created Card name", 90);
				WebActionUtil.mouseHoverToElement(lnkCreatedCardName, "Created Card name");
				
				WebActionUtil.clickOnElementUsingJS(imgPencilIcon, "click to Pencil Icon");
				WebActionUtil.clickOnWebElement(btnCopy, "click to copy Button", "Unable to click on copy Button");
				//WebActionUtil.waitForElement(ddList, "Created Card name", 90);
				
				WebActionUtil.selectByVisibleText(ddList, listName);
				WebActionUtil.clickOnWebElement(btnSubmitCardAction, "click to copy Button", "Unable to click on copy Button");
				
		}catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.info("click to Copy Button");
			Assert.fail("Unable to click on Copy Button");
		}
	}
		
		/**
		 * Description : This method click on Archive.
		 * @author Tanu
		 * @param cardName
		 */
		public synchronized void clkOnArchive() {
			try {
				WebActionUtil.waitForElement(lnkCreatedCardName, "Created Card name", 30);
				WebActionUtil.poll(3000);
				WebActionUtil.mouseHoverToElement(lnkCreatedCardName, "Created Card name");
				WebActionUtil.clickOnElementUsingJS(imgPencilIcon, "click to Pencil Icon");
				WebActionUtil.waitForElement(btnArchive, "Archive Card", 90);
				WebActionUtil.clickOnWebElement(btnArchive, "click to Archive Button", "Unable to click on Archive Button");
				
		}catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.info("click to Archive Button");
			Assert.fail("Unable to click on Archive Button");
		}
	}
		
		/**
		 * Description : This method click on Close Board.
		 * @author Tanu
		 * @param cardName
		 */
		public synchronized void clkOnCloseBoard() {
			try {
				WebActionUtil.waitForElement(btnShowMenu, "Show Menu Option", 30);
				WebActionUtil.clickOnWebElement(btnShowMenu, "click to Show Menu Option", "Unable to click on Show Menu Option");
			    WebActionUtil.poll(3000);
				WebActionUtil.clickOnWebElement(optionMore, "click to More Option", "Unable to click on More Option");
				WebActionUtil.clickOnWebElement(optionCloseBoard, "click to Close Board Option", "Unable to click on Close Board Option");
				WebActionUtil.clickOnWebElement(optionClose, "click to Close Option", "Unable to click on Close Option");
			    WebActionUtil.poll(2000);

				WebActionUtil.clickOnWebElement(lnkPermanentlyDelete, "click to Permanently Delete Option", "Unable to click on Permanently Delete Option");
				WebActionUtil.clickOnWebElement(btnDelete, "click to Delete Option", "Unable to click on Delete Option");

		}catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.info("click to Close Board Button");
			Assert.fail("Unable to click on Close Board Button");
		}
	}

		/** 
		 * Description: Method implements to validate Checklist Created for a Card
		 * @author sushmita p h
		 * 
		 */
		/*Auto labelName color*/
		public WebElement actualChecklist(String cardName) {

			String xpath = "//span[text()='"+cardName+"']/parent::div//div[@class='badge js-checkitems-badge is-complete']";

			return driver.findElement(By.xpath(xpath));
		}
		public synchronized void validateChecklist(String cardName) {
			try {
				Assert.assertTrue(actualChecklist(cardName).isDisplayed());
				WebActionUtil.info("Checklist is created for a card");
				WebActionUtil.validationinfo("Checklist is created for a card", "blue");
				
			}catch(Exception e) {
				WebActionUtil.error(e.getMessage());
				WebActionUtil.error("Checklist is not created for a Card");
				Assert.fail("Checklist is not created for a Card");
			}
		}
		
		/** 
		 * Description: Method implements to validate Template Created for a Card
		 * @author sushmita p h
		 * 
		 */
		/*Validate Template*/
		public WebElement template(String cardName) {

			String xpath = "//span[text()='"+cardName+"']/parent::div//div[@class='badge is-template']";

			return driver.findElement(By.xpath(xpath));
		}
		
		public synchronized void validateTemplate(String cardName) {
			try {
				Assert.assertTrue(template(cardName).isDisplayed());
				WebActionUtil.info("Template is created for a card");
				WebActionUtil.validationinfo("Template is created for a card", "blue");
			}catch(Exception e) {
				WebActionUtil.error(e.getMessage());
				WebActionUtil.error("Template is not created for a Card");
				Assert.fail("Template is not created for a Card");
			}
		}
		
		/** 
		 * Description: Method implements to validate Description Created for a Card
		 * @author sushmita p h
		 * 
		 */
		
		/*Validate Template*/
		public WebElement description(String cardName) {

			String xpath = "//span[text()='"+cardName+"']/parent::div//div[@class='badge is-icon-only']/span";

			return driver.findElement(By.xpath(xpath));
		}
		public synchronized void validateDescription(String cardName) {
			try {
				Assert.assertTrue(description(cardName).isDisplayed());
				WebActionUtil.info("Description is created for a card");
				WebActionUtil.validationinfo("Description is created for a card", "blue");
			}catch(Exception e) {
				WebActionUtil.error(e.getMessage());
				WebActionUtil.error("Description is not created for a Card");
				Assert.fail("Description is not created for a Card");
			}
		}
		
		/*Validate Commmet*/
		public WebElement checkCommennt(String cardName) {

			String xpath = "//span[text()='"+cardName+"']/parent::div//div[@class='badge']/span[@class='badge-icon icon-sm icon-comment']";

			return driver.findElement(By.xpath(xpath));
		}
		public synchronized void validateComment(String cardName) {
			try {
				Assert.assertTrue(checkCommennt(cardName).isDisplayed());
				WebActionUtil.info("Commmet is created for a card");
				WebActionUtil.validationinfo("Commmet is created for a card", "blue");
			}catch(Exception e) {
				WebActionUtil.error(e.getMessage());
				WebActionUtil.error("Commmet is not created for a Card");
				Assert.fail("Commmet is not created for a Card");
			}
		}
		
		/*Title Link*/
        @FindBy(xpath = "//span[@class='SETnvPbUKHW-cx _3XZPT267JcxN7w _1hy9FIxNNwKdWB']")
        private WebElement lnkTitle;

        /*Logout Button*/
        @FindBy(xpath = "//span[contains(text(),'Log out')]")
        private WebElement btnLogOut;


        /**
         * Description: Method implements to click on Logout Button
         * @author Ramya R
         *
         */
        public synchronized void clkLogOut() {
            try {
                WebActionUtil.waitForElement(lnkTitle, "Title Link", 30);
                WebActionUtil.clickOnElementUsingJS(lnkTitle, "Title Link");

                WebActionUtil.waitForElement(btnLogOut, "Logout Button", 30);
                WebActionUtil.clickOnElementUsingJS(btnLogOut, "Logout Button");


            }catch(Exception e) {
                WebActionUtil.error(e.getMessage());
                WebActionUtil.error("Unable to click on Logout Button");
                Assert.fail("Unable to click on Logout Button");

            }
        }

       
        /**
         * Description: Method implements to validate Created Card
         * @author Tanu
         *
         */
        public synchronized void validateCard(String expected) {
            try {
                String actual = lnkCreatedCardName.getText();
                Assert.assertEquals(actual,expected);
                WebActionUtil.info("Card is Created");
                WebActionUtil.validationinfo("Card is Created", "blue");
            }catch(Exception e) {
                WebActionUtil.error(e.getMessage());
                WebActionUtil.error("Card is not Created");
                Assert.fail("Card is not Created");
            }
        }


        /**
         * Description: Method implements to validate Move Card
         * @author Tanu
         *
         */

        public synchronized void validateMoveCard(String actualList,String movedList,List<String> cards) {
            try {
                List<String>actualCards = new ArrayList();
                List<String>movedCards = new ArrayList();
                 for(WebElement ele:txtCreatedListName) {
                     System.out.println(ele.getText());
                     if(ele.getText().equalsIgnoreCase(actualList)) {
                         for(WebElement ele1:listCard(actualList) ) {
                         actualCards.add(ele1.getText());

                         }
                     }
                     if(ele.getText().equalsIgnoreCase(movedList)) {
                         for(WebElement ele1:listCard(movedList) ) {
                             movedCards.add(ele1.getText());

                         }
                     }
                 }
                 System.out.println(actualCards );
                 System.out.println(movedCards );

                 if(actualCards.isEmpty()&& movedCards.equals(cards)) {
                     WebActionUtil.info("Card is moved from "+actualList+" to "+movedList);
                     WebActionUtil.pass("Card is moved from "+actualList+" to "+movedList);
                 }else {
                     WebActionUtil.info("Failed to move card from "+actualList+" to "+movedList);
                     WebActionUtil.fail("Failed to move card from "+actualList+" to "+movedList);
                     Assert.fail("Failed to move card from "+actualList+" to "+movedList);

                 }

            }catch(Exception e) {
                WebActionUtil.error(e.getMessage());
                WebActionUtil.error("validation fail");
                Assert.fail("validation fail");
            }
        }

        /**
         * Description: Method implements to validate CopyCard
         * @author Tanu
         *
         */
        public synchronized void validateCopyCard(String actualList,String copyedList) {
            WebDriverWait wait= new WebDriverWait(driver, 05);
            try {
                List<String>actualCards = new ArrayList();
                List<String>movedCards = new ArrayList();
                 for(WebElement ele:txtCreatedListName) {
                     Thread.sleep(2000);
                    // wait.until(ExpectedConditions.visibilityOf(ele));
                     if(ele.getText().equalsIgnoreCase(actualList)) {
                         for(WebElement ele1:listCard(actualList) ) {
                             Thread.sleep(2000);
                            // wait.until(ExpectedConditions.visibilityOf(ele1));
                         actualCards.add(ele1.getText());

                         }
                     }
                     if(ele.getText().equalsIgnoreCase(copyedList)) {

                         for(WebElement ele1:listCard(copyedList) ) {
                             Thread.sleep(2000);
                            // wait.until(ExpectedConditions.visibilityOf(ele1));
                             movedCards.add(ele1.getText());

                         }
                     }
                 }
                 System.out.println(actualCards );
                 System.out.println(movedCards );

                 if(actualCards.equals(movedCards)) {
                     WebActionUtil.info("Card is copy from "+actualList+" to "+copyedList);
                     WebActionUtil.pass("Card is copy from "+actualList+" to "+copyedList);
                 }else {
                     WebActionUtil.info("Failed to copy card from "+actualList+" to "+copyedList);
                     WebActionUtil.fail("Failed to copy card from "+actualList+" to "+copyedList);
                     Assert.fail("Failed to copy card from "+actualList+" to "+copyedList);

                 }

            }catch(Exception e) {
                WebActionUtil.error(e.getMessage());
                WebActionUtil.error("validation fail");
                Assert.fail("validation fail");
            }
        }


        /**
         * Description: Method implements to validate Archive Card
         * @author Tanu
         *
         */
        public synchronized void validateArchive(List<String> deletedElements,String listName) {
            try {
                List<String>actualCards = new ArrayList();
                for(WebElement card:listCard(listName)) {

                    actualCards.add(card.getText());

                }

                    for(int i=0;i<deletedElements.size();i++)
                    {
                        int count =0;
                        for(String cards :actualCards)
                        {
                            if(deletedElements.get(i).equalsIgnoreCase(cards))
                            {
                                count++;
                            }
                        }
                        if(count==0)
                        {

                            WebActionUtil.info(deletedElements.get(i)+" got Deleted from the Board ");
                            WebActionUtil.validationinfo(deletedElements.get(i)+" got Deleted from the Board ", "blue");


                        }
                    }
            }catch(Exception e) {
                WebActionUtil.error(e.getMessage());
                WebActionUtil.error("validation fail");
                Assert.fail("validation fail");
            }
        }
        
        /**
         * Description: Method implements to validate CloseBoard
         * @author Tanu
         *
         */
        public synchronized void validateCloseBoard(String boardName) {
            List<WebElement> list = driver.findElements(By.xpath("//div[@class='board-tile-details-name']"));
            ArrayList<String> actual = new ArrayList<String>();
            boolean value = false;
            try {
                for(WebElement webelement : list) {
                    if(webelement.getText().contains(boardName))
                    {
                        value = true;
                        break;
                    }
                }
                        if(value==true)
                        {
                            WebActionUtil.info(boardName+" Board is not Deleted");
                            WebActionUtil.validationinfo(boardName+" Board is not Deleted ", "blue");

                        }
                        else
                        {
                            WebActionUtil.info(boardName+" Board is  Deleted");
                            WebActionUtil.validationinfo(boardName+" Board is Deleted ", "blue");
                        }

            }catch(Exception e) {
                WebActionUtil.error(e.getMessage());
                WebActionUtil.error("Delete Board validation fail");
                Assert.fail("Delete Board validation fail");
            }
        }
}
