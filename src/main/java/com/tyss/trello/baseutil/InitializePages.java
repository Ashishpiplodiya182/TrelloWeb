package com.tyss.trello.baseutil;

import org.openqa.selenium.WebDriver;

import com.tyss.trello.pages.Trello_BoardPage;
import com.tyss.trello.pages.Trello_CardPage;
import com.tyss.trello.pages.Trello_HomePage;
import com.tyss.trello.pages.Trello_LoginPage;
import com.tyss.trello.util.WebActionUtil;




/**
 * Description Initialize all pages with driver,ETO, WebAactionUtil
 * 
 * @author shreya.u@testyantra.com,vivek.d@testyantra.com,aatish.s@testyantra.com
 * 
 */

public class InitializePages {
	public Trello_LoginPage loginPage;

	public Trello_HomePage homePage;
	public Trello_CardPage cardPage;
	public Trello_BoardPage boardPage;

	public InitializePages(WebDriver driver, long ETO, WebActionUtil WebActionUtil) {

		loginPage = new Trello_LoginPage(driver, ETO, WebActionUtil);
//		usersPage = new Users_Page(driver, ETO, WebActionUtil);
		 homePage = new Trello_HomePage(driver, ETO, WebActionUtil);
		 cardPage = new Trello_CardPage(driver, ETO, WebActionUtil);
		 boardPage = new Trello_BoardPage(driver, ETO, WebActionUtil);

		
	}

}
