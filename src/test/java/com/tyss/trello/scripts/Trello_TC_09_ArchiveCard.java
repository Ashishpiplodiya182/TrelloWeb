package com.tyss.trello.scripts;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import com.tyss.trello.baseutil.BaseTest;
import com.tyss.trello.baseutil.InitializePages;
import com.tyss.trello.dataproviders.DataProviderFactory;
import com.tyss.trello.dataproviders.DataProviderFileRowFilter;

public class Trello_TC_09_ArchiveCard extends BaseTest {
	
	@DataProviderFileRowFilter(file = "./src/main/resources/data/Data.xlsx", sql = "Select * from TrelloData where SlNo ='9'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Description: Verify that whether user is able to Archive a Card")


	public void createBoard(String SLNO, String boardName,String listName,String cardName,String list2Name,String checklistName	
			,String item,String label,String description,String comment,String color) throws Exception {

		InitializePages pages = new InitializePages(driver, ETO, WebActionUtil);
		
		/*Login to Application*/
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
		
		/* Validate Card Name*/
		pages.boardPage.validateCard(cardName);
		
		/*Click on Archive*/
		pages.boardPage.clkOnArchive();
		
		List<String>cards = new ArrayList();
        cards.add("TestCard1");

        /*Validate Archive */
        pages.boardPage.validateArchive(cards, listName);
        
		/*logout*/
		pages.boardPage.clkLogOut();
	}
}
