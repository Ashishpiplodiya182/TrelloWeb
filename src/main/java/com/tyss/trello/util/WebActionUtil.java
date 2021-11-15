package com.tyss.trello.util;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.tyss.trello.baseutil.BaseTest;
import com.tyss.trello.reports.ExtentManager;

/**
 * Description: All the action utilities added in this class e.g
 * clickonelement,WaitforElement etc
 * 
 * @author : Shreya U ,Vivek Dogra,Aatish Slatia
 */


public class WebActionUtil {
	private static final int List = 0;
	public WebDriver driver;
	WebDriverWait wait;
	public long ETO;
	public JavascriptExecutor jsExecutor;
	public Actions action;
	public static TakesScreenshot screenshot;

	public WebActionUtil(WebDriver driver, long ETO) {
		this.driver = driver;
		this.ETO = ETO;
		wait = new WebDriverWait(driver, ETO);
		jsExecutor = (JavascriptExecutor) driver;
		action = new Actions(driver);
		screenshot=(TakesScreenshot)driver;
	}


	/*@Test
	public void kendoCalendarExample() {
		driver.navigate().to(kendoURL);
		WebElement calIcon=driver.findElement(By.cssSelector(".k-icon.k-i-calendar"));
		calIcon.click();
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("datetimepicker_dateview")));
		System.out.println("Calendar Found");
		selectKendoDate("10");
	}*/

	public void selectKendoDate(String date) {

		wait.until(ExpectedConditions.presenceOfElementLocated(By.className("k-content")));
		WebElement table = driver.findElement(By.className("k-content")); 

		System.out.println("Kendo Calendar");
		List<WebElement> tableRows = table.findElements(By.xpath("//tr"));
		for (WebElement row : tableRows) {
			List<WebElement> cells = row.findElements(By.xpath("td"));

			for (WebElement cell : cells) {
				if (cell.getText().equals(date)) {
					driver.findElement(By.linkText(date)).click();
				}
			}
		}
	}

	/**
	 * 
	 * Description Method for the pass updation in extent Report,Log file,TestNG
	 *              Report
	 * @author Shreya Ugavekar
	 */

	public static void pass(String message) {
		ExtentManager.getTestReport().pass(MarkupHelper.createLabel(message, ExtentColor.GREEN));
	}
	/**Description Method to provide info in the log,extent reports,testNg reports
	 * @author Shreya Ugavekar
	 * 
	 */
	public static void info(String message) {
		Reporter.log(message, true);
		BaseTest.logger.info(message);
		ExtentManager.getTestReport().info(message);

	}

	/**
	 * 
	 * Description Method for the Warning updation in extent Report,Log file,TestNG
	 *              Report
	 *  @author Shreya Ugavekar
	 */

	public void warn(String message) {

		BaseTest.logger.warn(message);
		Reporter.log(message, true);
	}

	/**
	 * 
	 * Description Method for the error/Failure updation in extent Report,Log
	 *              file,TestNG Report
	 *  @author Shreya Ugavekar
	 *              
	 */

	public static void fail(String message) {
		Reporter.log(message, true);
		ExtentManager.getTestReport().fail(MarkupHelper.createLabel(message, ExtentColor.RED));

	}
	public void error(String message) {

		BaseTest.logger.error(message);
		Reporter.log(message, true);
		ExtentManager.getTestReport().error(message);
	}

	/**
	 * 
	 * Description Method for fetching of get Current Date Time
	 *  @author Shreya Ugavekar
	 *              
	 */

	public static String getCurrentDateTime() {
		DateFormat customFormat = new SimpleDateFormat("MM_dd_yyyy_HH_mm_ss");
		Date currentDate = new Date();
		return customFormat.format(currentDate);
	}
	/**
	 * 
	 * Description Method clicks on web element
	 *  @author Shreya Ugavekar
	 *              
	 */

	public synchronized void clickOnWebElement(WebElement element, String elementName,String message) {


		if (isElementClickable(element, elementName)) {
			element.click();
			pass("Click on " + elementName);
		}
		else {
			fail(message);
			Assert.assertTrue(wait.until(ExpectedConditions.elementToBeClickable(element))==null);
		} 

	}
	
	

	/**
	 * 
	 * Description Check whether the Element is displayed with expected conditions
	 * @author Shreya Ugavekar
	 * @param element elementName
	 */
	public synchronized boolean isElementClickable(WebElement element, String elementName) {

		info("Verify "+elementName+" is Clickable or Not");
		try {
			
			wait.until(ExpectedConditions.visibilityOf(element));
			wait.until(ExpectedConditions.elementToBeClickable(element));
			return true;
		} catch (Exception e) {
			fail(elementName + " is not Clickable ");
			return false;
		}
	}
	
	

	public void waitForElement(WebElement element, String eleName,long seconds) {
		try {

			wait= new WebDriverWait(driver, seconds);
			
			wait.until(ExpectedConditions.visibilityOf(element));
			Assert.assertTrue(wait.until(ExpectedConditions.visibilityOf(element)) != null);

		} catch (Exception e) {
			fail( "Element is not visible---------" + eleName);

		}
	}
	public static void deleteDir(String pathToDeleteFolder) {
		File extefolder = new File(pathToDeleteFolder);
		if ((extefolder.exists())) {
			deleteFolderDir(extefolder);
		}
	}
	public static void deleteFolderDir(File folderToDelete) {
		File[] folderContents = folderToDelete.listFiles();
		if (folderContents != null) {
			for (File folderfile : folderContents) {
				if (!Files.isSymbolicLink(folderfile.toPath())) {
					deleteFolderDir(folderfile);
				}
			}

		}
		folderToDelete.delete();
	}
	/**
	 * Description Capture the screenshot of the complete screen 
	 * @author Shreya Ugavekar
	 * @param path driver
	 */
	public static String getScreenShot(String path) {

		File src = (screenshot.getScreenshotAs(OutputType.FILE));
		String destinationPath = path + getCurrentDateTime() + ".png";
		File destination = new File(destinationPath);
		try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return destinationPath;
	}

	/**
	 * Description To Enter the Text to the Text filed 
	 * @author Shreya Ugavekar 
	 * @param element value elementName
	 */
	public synchronized void typeText(WebElement element, String value, String elementName) {
		try {
			info("Enter the value into " + elementName);
			element.sendKeys(value);
			pass("User is able to type " + value + " into " + elementName);
		} catch (AssertionError error) {
			fail(" User is not able to type " + value + " into " + elementName);
			Assert.fail("Unable to type on " + elementName);
		} catch (Exception e) {
			fail(" User is not able to type " + value + "into " + elementName);
			Assert.fail("Unable to type in " + elementName);
		}
	}

	/**
	 * Description To Select the value by the Visible Text
	 * @author Shreya Ugavekar
	 * @param element value elementName
	 */
	public synchronized void selectByText(WebElement element, String value) {
		try {
			info("Select the value " + value);
			Select selecvalue=new Select(element);
			selecvalue.selectByVisibleText(value);
			pass("User is able to select the value"+value);
		} catch (Exception e) {
			fail(" User is not able to Select" + value );
			Assert.fail("Unable to select  " + value);
		}

	}
	public synchronized void switchWindowControl()
	{
		// It will return the parent window name as a String
		String mainWindow=driver.getWindowHandle();
		// It returns no. of windows opened by WebDriver and will return Set of Strings
		Set<String> set =driver.getWindowHandles();
		// Using Iterator to iterate with in windows
		Iterator<String> itr= set.iterator();
		while(itr.hasNext()){
			String childWindow=itr.next();
		}
		// Compare whether the main windows is not equal to child window. If not equal, we will clo
	}
	/*
	 * Description Switches to tab
	 * 
	 * @author Aatish Slathia
	 * @param tabindex
	 * 
	 */
	public synchronized void switchToTab(int tabindex) {
		try {
			ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
			driver.switchTo().window(tabs.get(tabindex));
			pass("Switch to tab complete");
		} catch (Exception e) {
			fail("Switching to tab failed ");

		}

	}
	/**
	 * Description Click on the check box
	 * @author Shreya Ugavekar
	 * @param element  elementName
	 */
	public void clickCheckBox(WebElement element, String elementname) {

		if (element.isSelected()) {
			pass("Already Selected " + elementname);
		} else {
			element.click();
			pass("Selected the " + elementname);

		}
	}
	/**
	 * @author Shreya Ugavekar
	 * @description Scroll to the Element
	 * @param elementName 
	 */
	public void scrollToElement(WebElement element, String elementName)  {
		info("-------------Scrolling till the Element------------");
		try {
			jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
			pass("-------------Scrolling till the Element Completed------------");
		} catch (Exception e) {
			fail("-------------Scroll Till the Element Has Failed ------------");
		}

	}
	/**
	 * Description Click on the Element using JavaSCript 
	 * @author Shreya Ugavekar
	 * @param element  elementName
	 */
	public void clickOnElementUsingJS(WebElement element, String elementName) {
		try {
			if (isElementClickable(element, elementName)) {
				pass("User is able to click " + " into " + elementName);
				jsExecutor.executeScript("arguments[0].click();", element);
			}
		} catch (NoSuchElementException e) {
			pass("User is not able to click " + " into " + elementName);
			Assert.assertTrue(wait.until(ExpectedConditions.visibilityOf(element)) == null);
		}
	}

	/**
	 * Description Double Click On Element
	 * @author Shreya Ugavekar
	 * @param element  elementName
	 */
	public void doubleClickOnElement(WebElement element, String elementName) {
		try {
			pass("User is able to click " + " into " + elementName);
			action.doubleClick(element).perform();
		} catch (Exception e) {
			fail(" User is not able to double click on  " + elementName);
			Assert.fail("Unable to Double click on  " + elementName);
		}
	}


	/**
	 * Description Clear the Text
	 * @author Shreya Ugavekar
	 * @param element elementName
	 */
	public void clearText(WebElement element, String elementName) {
		try {
			info("Clear the Text Present in" + elementName);
			element.clear();
			pass("Cleared the Text Present in" + elementName);
		}catch (Exception e) {
			fail("Unable to clear the text in "+elementName);
		}
	}
	/**
	 * Description To create the duration of the Test Run
	 * @author Aatish Slathia 
	 * @param element elementName
	 */
	public static String formatDuration(final long millis) {
		long seconds = (millis / 1000) % 60;
		long minutes = (millis / (1000 * 60)) % 60;
		long hours = millis / (1000 * 60 * 60);

		StringBuilder b = new StringBuilder();
		b.append(hours == 0 ? "00" : hours < 10 ? String.valueOf("0" + hours) :
			String.valueOf(hours));
		b.append(":");
		b.append(minutes == 0 ? "00" : minutes < 10 ? String.valueOf("0" + minutes) :    
			String.valueOf(minutes));
		b.append(":");
		b.append(seconds == 0 ? "00" : seconds < 10 ? String.valueOf("0" + seconds) :
			String.valueOf(seconds));
		return b.toString();
	}

	/**
	 * Description Wait for the angular page to load
	 * @author Aatish Slathia
	 * @param element elementName
	 */
	public void waitForAngularPageLoad() {
		try {
			ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver driver) {
					return Boolean.valueOf(((JavascriptExecutor) driver).executeScript(
							"return (window.angular !== undefined) "
									+ "&& (angular.element(document).injector() !== undefined) "
									+ "&& (angular.element(document).injector().get('$http').pendingRequests.length === 0)").toString());
				}
			};
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(pageLoadCondition);
		} catch (Exception e) {
			fail("Unable to load the page correctly");
		}
	}

	/**
	 * Description Verifies  the Text
	 * 
	 * @author Aatish Slathia
	 * @param element
	 * @param expected
	 * @param elementname
	 */
	public synchronized void verifytext( String expected, WebElement element,String elementname) {
		try {
			info("Getting text from " + elementname);
			String actual = element.getText();
			Assert.assertEquals(actual, expected);
		} catch (Exception e) {
			fail("Failed to fetch the text from " + elementname);
		}

	}


	/**
	 * Description :Checks whether an element is visible
	 * 
	 * @author Aatish Slathia
	 * @param element
	 * @param elementName
	 * 
	 */
	public synchronized boolean isElementVisible(WebElement element, String elemantName) {

		try {
			wait.until(ExpectedConditions.visibilityOf(element));
			pass(elemantName + " is Visible ");
			return true;
		} catch (Exception e) {
			return false;
		}
	}





	public void testDatePicker(String date) throws Exception {
		/*button to open calendar*/
		WebElement selectDate = driver.findElement(By.xpath("//span[@aria-controls='datetimepicker_dateview']"));
		selectDate.click();
		/*	 button to move next in calendar*/
		WebElement nextLink = driver.findElement(By.xpath(
				"//div[@id='datetimepicker_dateview']//div[@class='k-header']//a[contains(@class,'k-nav-next')]"));
		/* button to click in center of calendar header*/
		WebElement midLink = driver.findElement(By.xpath(
				"//div[@id='datetimepicker_dateview']//div[@class='k-header']//a[contains(@class,'k-nav-fast')]"));
		/* button to move previous month in calendar*/
		WebElement previousLink = driver.findElement(By.xpath(
				"//div[@id='datetimepicker_dateview']//div[@class='k-header']//a[contains(@class,'k-nav-prev')]"));
		// Split the date time to get only the date part
		String date_dd_MM_yyyy[] = (date.split(" ")[0]).split("/");
		// get the year difference between current year and year to set in calander
		int yearDiff = Integer.parseInt(date_dd_MM_yyyy[2]) - Calendar.getInstance().get(Calendar.YEAR);
		midLink.click();
		if (yearDiff != 0) {
			// if you have to move next year
			if (yearDiff > 0) {
				for (int i = 0; i < yearDiff; i++) {
					System.out.println("Year Diff->" + i);
					nextLink.click();
				}
			}
			// if you have to move previous year
			else if (yearDiff < 0) {
				for (int i = 0; i < (yearDiff * (-1)); i++) {
					System.out.println("Year Diff->" + i);
					previousLink.click();
				}
			}
		}

		Thread.sleep(1000);
		// Get all months from calendar to select correct one
		List<WebElement> list_AllMonthToBook = driver.findElements(By.xpath(
				"//div[@id='datetimepicker_dateview']//table//tbody//td[not(contains(@class,'k-other-month'))]"));
		list_AllMonthToBook.get(Integer.parseInt(date_dd_MM_yyyy[1]) - 1).click();
		Thread.sleep(1000);
		// get all dates from calendar to select correct one
		List<WebElement> list_AllDateToBook = driver.findElements(By.xpath(
				"//div[@id='datetimepicker_dateview']//table//tbody//td[not(contains(@class,'k-other-month'))]"));
		list_AllDateToBook.get(Integer.parseInt(date_dd_MM_yyyy[0]) - 1).click();
		
		
	}
	/**
	 * Description :Switch the window Control
	 * 
	 * @author Manish Kumar C D
	 * 
	 */

	public synchronized void switchWindowControlTo()
	{
		String parentWindow=driver.getWindowHandle();
		Set<String> set =driver.getWindowHandles();
		Iterator<String> itr= set.iterator();
		String parent = itr.next();
		String child1 = itr.next();
		String child2 = itr.next();

		driver.switchTo().window(child2);
	}

	public void mouseHover(WebDriver driver, WebElement element) 
	{
		Actions action = new Actions(driver);
		action.moveToElement(element).perform();
	}
	public void mouseHoverClick(WebDriver driver, WebElement element) 
	{
		Actions action = new Actions(driver);
		action.moveToElement(element).click();
	}
	public void mouseHoverList(WebDriver driver, List<WebElement> element) 
	{
		Actions action = new Actions(driver);
		action.moveToElement((WebElement) element).perform();
	}
	
	public synchronized void clickOnWebElementLink(WebElement element) 
	{
			element.click();		
	}
	
	public synchronized void selectNameFromList(WebElement element, String elementName) 
	{
		element.click();
	}
	
	public synchronized void selectOption(WebElement element, String value) 
	{
		Select select= new Select(element);
		select.selectByVisibleText(value);
		
	}
	
	public synchronized void closePopUpWindows()
	{
		
		String parentWid=driver.getWindowHandle();
		Set<String> allWindowIds=driver.getWindowHandles();
		allWindowIds.remove(parentWid);
		for(String wid:allWindowIds) 
		{
			driver.switchTo().window(wid);
			driver.close();

		}
		info("All the pop ups have been closed successfully");

		//passing back the driver control to parent window
		driver.switchTo().window(parentWid);
		info(driver.getTitle());

	}
	
	/**
	* Description :This method will select a value from drop down based on visible text
	*
	* @author Tanu
	* @param element
	* @param value
	*
	*/
	public void selectByVisibleText(WebElement element, String value)
	    {
	    try {
	info("select the value from dropdown from visible text" + value);
	Select sel = new Select(element);
	sel.selectByVisibleText(value);
	    pass("select teh value from dropdown from visible text " +value );
	} catch (Exception e) {
	    fail("Unable to select the value from dropdown from visible text " +value);

	}
	    }

	/**
	* Description :This method will select a value from drop down based on index
	*
	* @author Tanu
	* @param element
	* @param index
	*
	*/
	public void selectByIndex(WebElement element, int index)
	   {
	    try {
	info("select the value from dropdown from index " + index);
	Select sel = new Select(element);
	sel.selectByIndex(index);
	    pass("select teh value from dropdown from index " +index );
	} catch (Exception e) {
	    fail("Unable to select the value from dropdown from index " +index);
	}
	   }

	/**
	* Description :This method will deselect a value from drop down based on index
	*
	* @author Tanu
	* @param element
	* @param index
	*
	*/

	public void deSelectByIndex(WebElement element, int index)
	   {
	    try {
	info("deselect the value from dropdown from index " + index);
	Select sel = new Select(element);
	sel.deselectByIndex(index);
	    pass("deselect teh value from dropdown from index " +index );
	} catch (Exception e) {
	    fail("Unable to deselect the value from dropdown from index " +index);
	}
	   }
	/**
	* Description :This method will deselect a value from drop down based on visible text
	*
	* @author Tanu
	* @param element
	* @param value
	*
	*/
	public void deSelectByVisibleText(WebElement element, String value)
	   {
	    try {
	info("deselect the value from dropdown from visible text" + value);
	Select sel = new Select(element);
	sel.deselectByVisibleText(value);
	    pass("deselect teh value from dropdown from visible text " +value );
	} catch (Exception e) {
	    fail("Unable to deselect the value from dropdown from visible text " +value);
	}
	   }

	/**
	* Description :This method will deselect a value from drop down based on value
	*
	* @author Tanu
	* @param element
	* @param value
	*
	*/
	public void deSelectByValue(WebElement element, String value)
	   {
	    try {
	info("deselect the value from dropdown from value " + value);
	Select sel = new Select(element);
	sel.deselectByValue(value);
	    pass("deselect teh value from dropdown from value " +value );
	} catch (Exception e) {
	    fail("Unable to deselect the value from dropdown from value " +value);
	}
	   }

	/**
	* Description :This method will deselect all the value from drop down based
	*
	* @author Tanu
	* @param element
	*
	*/
	public void deSelectAll(WebElement element)
	   {
	    try {
	info("deselect the all value from dropdown  " );
	Select sel = new Select(element);
	sel.deselectAll();
	    pass("deselect the all value from dropdown  " );
	} catch (Exception e) {
	    fail("Unable to deselect the all value from dropdown  ");

	}
	   }

	/**
	* Description :This method will perform mouse over action
	*
	* @author Tanu
	* @param element
	*
	*/
	public void mouseHoverToElement(WebElement element,String elementName)
	   {
	    try {
	info("mouse hover to the  " +elementName );
	Actions action = new Actions(driver);
	action.moveToElement(element).perform();
	    pass("mouse hover to the " +elementName );
	} catch (Exception e) {
	    fail("Unable to mouse hover to the  " +elementName);

	}
	   }

	/**
	* Description :This method will perform mouse over action context click
	*
	* @author Tanu
	* @param element
	*
	*/
	public void mouseHoverToContextClick(WebElement element)
	   {
	    try {
	info("mouse hover to context click " +element );
	Actions action = new Actions(driver);
	action.contextClick(element).perform();
	    pass("mouse hover to context click " +element );
	} catch (Exception e) {
	    fail("Unable to mouse hover to context click  " +element);
	}
	   }

	/**
	* Description :This method will perform mouse over action drab and drop
	*
	* @author Tanu
	* @param sourceElement
	* @param targetElement
	*
	*/
	public void mouseHoverDragAndDrop(WebElement sourceElement,WebElement targetElement)
	   {
	    try {
	info("drop and drop from source element to " +targetElement );
	Actions action = new Actions(driver);
	action.dragAndDrop(sourceElement,targetElement).perform();
	    pass("drag and drop from source element to " +targetElement );
	} catch (Exception e) {
	    fail("Unable to drag and drop from source element to   " +targetElement);
	}
	   }
	
	/**
	 * 
	 * Description Method for the error/errorure updation in extent Report
	 * 
	 * @author Sajal, Vikas
	 * @param message,color
	 */

	public static void validationinfo(String message, String color) {
		if (color.equalsIgnoreCase("blue"))

			ExtentManager.getTestReport().info(MarkupHelper.createLabel(message, ExtentColor.BLUE));
		else if (color.equalsIgnoreCase("green"))
			ExtentManager.getTestReport().pass(MarkupHelper.createLabel(message, ExtentColor.GREEN));
		else if (color.equalsIgnoreCase("red"))
			ExtentManager.getTestReport().fail(MarkupHelper.createLabel(message, ExtentColor.RED));
	}

	

	/**
	  * Description :This method move the mouse from current position to given method
	  *
	  * @author Tanu
	  * @param xOffset
	* @param yOffset
	*
	*/
	public void moveByOffset(int xOffset,int yOffset)
	   {
	    try {
	info("move mouse from current position to given method " );
	Actions action = new Actions(driver);
	action.moveByOffset(xOffset,yOffset).click().perform();
	    pass("move mouse from current position to given method " );

	} catch (Exception e) {
	    fail("Unable to move mouse from current position to given method ");

	}
	   }

	public synchronized static void poll(long millis) {
		try {
			Thread.sleep(millis);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
}

