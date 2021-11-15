package com.tyss.trello.scripts;

import org.testng.annotations.Test;

import com.tyss.trello.baseutil.BaseTest;
import com.tyss.trello.baseutil.InitializePages;
import com.tyss.trello.dataproviders.DataProviderFactory;
import com.tyss.trello.dataproviders.DataProviderFileRowFilter;

public class Trello_TC_04_AddDescription extends BaseTest {
	
	@DataProviderFileRowFilter(file = "./src/main/resources/data/Data.xlsx", sql = "Select * from TrelloData where SlNo ='4'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Description: Verify that whether user is able to Create Description for a Card")

	public void addDescriptionTest(String SLNO, String boardName,String listName,String cardName,String list2Name,String checklistName	
			,String item,String label,String description,String comment,String color) throws Exception
	{
		InitializePages pages = new InitializePages(driver, ETO, WebActionUtil);
		
		pages.loginPage.loginToApplication(prop.getProperty("Email"),prop.getProperty("Password"));
		
		/* Click on Create New Board */
		pages.homePage.clkCreateNewBoard();
		
		/* Enter Board Name*/
		pages.homePage.setBoardTitle(boardName);
		
		/*Click on Create Board */
		pages.homePage.clkCreateBoardBtn();
		
		//Thread.sleep(3000);
		/* Enter List Name*/
		pages.boardPage.setListName(listName);
	
		/* Enter Card Name*/
		pages.boardPage.setCardName(cardName);
		
		/*Add Description for a Card*/
		pages.cardPage.addDescription(description);
				
		/*Click on Save button*/
		pages.cardPage.clkDescriptionSaveBtn();
		
		/*Close Card PopUp*/
		pages.cardPage.clkCloseCardPopUp();
		
		/*Validate Description*/
		pages.boardPage.validateDescription(cardName);
		
		/*logout*/
		pages.boardPage.clkLogOut();
	}
}
