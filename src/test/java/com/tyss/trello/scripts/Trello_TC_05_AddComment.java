package com.tyss.trello.scripts;

import org.testng.annotations.Test;

import com.tyss.trello.baseutil.BaseTest;
import com.tyss.trello.baseutil.InitializePages;
import com.tyss.trello.dataproviders.DataProviderFactory;
import com.tyss.trello.dataproviders.DataProviderFileRowFilter;

public class Trello_TC_05_AddComment extends BaseTest {
	
	@DataProviderFileRowFilter(file = "./src/main/resources/data/Data.xlsx", sql = "Select * from TrelloData where SlNo ='5'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Description: Verify that whether user is able to create comment for a Card")


	public void addCommentTest(String SLNO, String boardName,String listName,String cardName,String list2Name,String checklistName	
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
		
		/* Enter List Name*/
		pages.boardPage.setListName(listName);
		
		/* Enter Card Name*/
		pages.boardPage.setCardName(cardName);

		/*Add CheckList for a Card*/
		pages.cardPage.addComment(comment);
		
		/*click on save cooment button*/
		pages.cardPage.clkCommentSaveBtn();
		
		/*Close Card PopUp*/
		pages.cardPage.clkCloseCardPopUp();
		
		/* validate comment*/
		pages.boardPage.validateComment(cardName);
		
		/*logout*/
		pages.boardPage.clkLogOut();
	}

}
