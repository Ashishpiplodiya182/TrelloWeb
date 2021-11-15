package com.tyss.trello.scripts;

import org.testng.annotations.Test;

import com.tyss.trello.baseutil.BaseTest;
import com.tyss.trello.baseutil.InitializePages;
import com.tyss.trello.dataproviders.DataProviderFactory;
import com.tyss.trello.dataproviders.DataProviderFileRowFilter;

public class Trello_TC_07_AddLabel extends BaseTest {

	@DataProviderFileRowFilter(file = "./src/main/resources/data/Data.xlsx", sql = "Select * from TrelloData where SlNo ='7'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Description: Verify that whether user is able to create label for a Card")


	public void createLabelTest(String SLNO, String boardName,String listName,String cardName,String list2Name,String checklistName	
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
		pages.cardPage.addLabel(label,color);
	
		/*validate label*/
		pages.cardPage.validateLabel(color, label);

		/*Close Card PopUp*/
		pages.cardPage.clkCloseCardPopUp();

		/*logout*/
		pages.boardPage.clkLogOut();
	}

}
