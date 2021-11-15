package com.tyss.trello.scripts;

import org.testng.annotations.Test;

import com.tyss.trello.baseutil.BaseTest;
import com.tyss.trello.baseutil.InitializePages;
import com.tyss.trello.dataproviders.DataProviderFactory;
import com.tyss.trello.dataproviders.DataProviderFileRowFilter;

public class Trello_TC_03_CopyCard extends BaseTest {
	
	@DataProviderFileRowFilter(file = "./src/main/resources/data/Data.xlsx", sql = "Select * from TrelloData where SlNo ='3'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Description: Verify that whether user is able to Copy a Card")


	public void copyCardTest(String SLNO, String boardName,String listName,String cardName,String list2Name,String checklistName	
			,String item,String label,String description,String comment,String color) throws Exception {

		InitializePages pages = new InitializePages(driver, ETO, WebActionUtil);
		pages.loginPage.loginToApplication(prop.getProperty("Email"),prop.getProperty("Password"));
		
		/* Click on Create New Board */
		pages.homePage.clkCreateNewBoard();
		
		/* Enter Board Name*/
		pages.homePage.setBoardTitle(boardName);
		
		/*Click on Create Board */
		pages.homePage.clkCreateBoardBtn();
	
		/* Enter List Name*/
		pages.boardPage.setListName(listName);
		
		/* Enter Card Name*/
		pages.boardPage.setCardName(cardName);
		
		/* Enter Card Name*/
		pages.boardPage.validateCard(cardName);
		
		/* Enter List Name*/
		pages.boardPage.setListName(list2Name);
		
		/* click on copy card*/
		pages.boardPage.clkOnCopy(list2Name);
		
		/* Validate on copy card*/
		pages.boardPage.validateCopyCard(listName,list2Name);
		
		/*logout*/
		pages.boardPage.clkLogOut();
	}

}
