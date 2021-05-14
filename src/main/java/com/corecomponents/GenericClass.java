package main.java.com.corecomponents;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import main.java.com.projectconfig.ProjectConfig;

import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.Rotatable;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import org.apache.commons.codec.binary.Base64;

public class GenericClass {
	public final Logger LOGGER = Logger.getLogger(GenericClass.class.getName());
	protected static int counter = 1;
	public ExtentReports report;
	public ExtentTest test;
	private Properties prop = null;
	Set<String> allwindows = null;
	
	/**
	 * Generate extent report with timestamp
	 * This generates multiple reports and not recommended
	 */
	
	/*static long currentmilliseconds = System.currentTimeMillis();
	static SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy HH-mm-ss"); 
	static  Date resultdate = new Date(currentmilliseconds);
	static String date = sdf.format(resultdate);
	protected  static ExtentReports report = new ExtentReports("reports//MetlifeTestReport"+ date +".html");*/
	

	public GenericClass(String reportName) {
		report = new ExtentReports("reports//" + reportName + ".html");
	//for TCdriver extension/creation
	}
	
	public GenericClass() {
	}

	/**
	 * This will delete any cookies stored from a previous session
	 */
	public  void clearBrowserCache(WebDriver driver) {
		driver.manage().deleteAllCookies();
		LOGGER.info("Browser Cache Deleted");
	}

	/**
	 * This will refresh the webpage
	 */
	public  void refreshBrowser(WebDriver driver) {
		driver.navigate().refresh();
		LOGGER.info("Browser Refreshed");
	}

	/**
	 * Get's the current url in the browser
	 * 
	 * @param url
	 */
	public  void getURL(WebDriver driver,String url) {
		driver.get(url);
	}

	/**
	 * This will bypass a security certificate error in IE
	 */
	public  void bypassSecurityCertificate(WebDriver driver) {
		Capabilities c = ((RemoteWebDriver) driver).getCapabilities();
		if (c.getBrowserName().equalsIgnoreCase("internet explorer")) {
			driver.switchTo().defaultContent();
			driver.findElement(By.id("overridelink")).click();
		}
	}

	/**
	 * Grab control of the top most open browser window
	 */
	public  void getTopWindow(WebDriver driver) {

		try {

		Set<String> ids = driver.getWindowHandles();
		Iterator<String> iterator = ids.iterator();
		String nextID = null;
		while (iterator.hasNext()) {
			nextID = iterator.next();
		}
		driver.switchTo().window(nextID);
		LOGGER.info("Switched To Top Window");
		
		}
		catch (Exception e) {
			
			LOGGER.info("Not Switched To Top Window");
			LOGGER.warning(e.getMessage());
			logFailedStepToReport(test, "Not Switched To Top Window:" +e.getMessage());
			
			}
		
	}
	
	/**
	 * Select Text from Menu
	 * 
	 * @param we
	 * @param  text
	 * @return
	 * 
	 */
	public void selectFromMenu(WebDriver driver, WebElement we, String text) {
		try {

		if (driver.findElement(By.cssSelector("#nav > label")).isDisplayed()) {
			driver.findElement(By.cssSelector("#nav > label")).click();
		}
		we.click();
		Actions actions = new Actions(driver);
		WebDriverWait wait = new WebDriverWait(driver, 1000);
		WebElement dropdownitem = wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(text)));
		actions.moveToElement(dropdownitem);
		actions.click().build().perform();
		
		LOGGER.info("Menu Element Selected:" +text);
		
		}
		catch (Exception e) {
			
			LOGGER.info("Element Not Selected From Menu:" +text);
			LOGGER.warning(e.getMessage());
			logFailedStepToReport(test, "Element Not Selected From Menu:" +e.getMessage());
		}
	}

	/**
	 * This performs a double click on the given web element
	 * 
	 * @param element
	 */
	public void doubleClick(WebDriver driver,WebElement element) {
		try {

		Actions action = new Actions(driver);
		action.doubleClick(element).perform();
		LOGGER.info("DoubleClick Operation Performed:" +element);
		
		}
		
		catch (Exception e) {
			
			LOGGER.info("Double Click Failed:" +element);
			LOGGER.warning(e.getMessage());
			logFailedStepToReport(test, "Double Click Failed:" +e.getMessage());
		}
	}
	/**
	 * This takes a skip message and outputs it to the report
	 * 
	 * @param test    - test parameter to be used by extent report
	 * @param skipMsg - String message to be displayed for skip
	 * @throws Exception
	 */
	public void reportingSkip(ExtentTest test, String skipMsg) {
		test.log(LogStatus.SKIP, skipMsg);
		report.endTest(test);
		report.flush();
	}
	/**
	 * Switches the focus to a new frame
	 * 
	 * @param frameName
	 */

	public  void switchToFrame(WebDriver driver,String frameName) {
		try {	
			driver.switchTo().defaultContent();
			driver.switchTo().frame(frameName);
			LOGGER.info("Frame Switched:" +frameName);
		}
		catch (Exception e) {	
			LOGGER.info("Frame Not Switched:" +frameName);
			LOGGER.warning(e.getMessage());
			logFailedStepToReport(test, "Frame Not Switched:" +e.getMessage());
		}
	}

	/**
	 * Switches the focus to a second frame
	 * 
	 * @param FrameName
	 */
	public  void switchToSecondFrame(WebDriver driver,String frame1, String frame2) {
		try {		
			driver.switchTo().defaultContent();
			driver.switchTo().frame(frame1);
			driver.switchTo().frame(frame2);
			LOGGER.info("Frame Switched");
		}
		catch (Exception e) {
			
			LOGGER.info("Frame Not Switched");
			LOGGER.warning(e.getMessage());
			logFailedStepToReport(test, "Frame Not Switched:" +e.getMessage());
		}
	}


	/**
	 * Handles a pop-up alert given the title
	 * 
	 * @param title
	 */
	public void popuphandler(WebDriver driver,String title) {
		allwindows = driver.getWindowHandles();
		for (String childwindow : allwindows) {
			driver.switchTo().window(childwindow);
			String actualWindowTitle = driver.getTitle();
			LOGGER.log(Level.INFO,"ActualWindowTitle : {0}", actualWindowTitle);

			if (actualWindowTitle.contains(title)) {
				break;
			}
		}
	}

	/**
	 * Wait for with the element to be clickable or visible
	 * 
	 * @param locator          - string name set in the properties file for a web
	 *                         element
	 * @param timeoutinseconds - desired wait time
	 * @param condition        this is either visibilityOfElementLocated or
	 *                         ElementToBeClickable
	 * @return
	 */
	public  WebElement explicitWait(WebDriver driver,String locator, int timeoutinseconds, String condition) {

		WebElement element = null;
		if (condition.equalsIgnoreCase("visibilityOfElementLocated")) {
			element = new WebDriverWait(driver, timeoutinseconds)
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
			return element;
		}

		if (condition.equalsIgnoreCase("ElementToBeClickable")) {
			element = new WebDriverWait(driver, timeoutinseconds)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));
			return element;
		}
		return element;
		
	}

	/**
	 * Hovers the mouse over a specified web element
	 * 
	 * @param locatorKey - string name set in the java file for a web element
	 * @param type       - string to tell which type of locator is being passed in
	 *                   supported types: (id, name, classname, linktext, xpath,
	 *                   cssselector)
	 */
	public void mouseHover(WebDriver driver,String locatorKey, String type) {
		try {
		WebElement e = getElement(driver, locatorKey, type);
		Actions builder = new Actions(driver);
		builder.moveToElement(e).click().perform();
		LOGGER.info("MouseHover Performed:" +locatorKey);
		}
		catch (Exception e) {
			LOGGER.info("MouseHover Operation Failed:" +locatorKey);
			LOGGER.warning(e.getMessage());
			logFailedStepToReport(test, "MouseHover Operation Failed:" +e.getMessage());
		}
	}
	
	/**
	 * Get Element from List
	 * 
	 * @param locatorKey - string name set in the java file for a web element
	 * @param elementtoget - element to get     
	 * @param type       - string to tell which type of locator is being passed in
	 *                   supported types: (id, name, classname, linktext, xpath,
	 *                   cssselector)
	 */

	public WebElement getElementInAList(WebDriver driver,String locatorKey, String elementtoget, String type) {
		WebElement element = null;
		if (type.equalsIgnoreCase(Constants.XPATH)) {
			List<WebElement> elements = driver.findElements(By.xpath(locatorKey));
			for (int i = 0; i < elements.size(); i++) {
				element = elements.get(i);
				if (element.getText().equalsIgnoreCase(elementtoget)) {
					break;
				}
			}
		}
		return element;
	}

	/**
	 * Handles data from the excel file
	 * 
	 * @param filePath
	 * @param sheetName - Name of the sheet to find the data
	 * @param testCase  - Name of the Test Case within the excel file
	 * @return
	 */
	public  Map<String, String> getData(String filePath, String sheetName, String testCase) 
	{
		int testCaseRowNumber;
		XLSReader xl = new XLSReader(filePath);
		testCaseRowNumber = xl.testCaseRow(sheetName, testCase);

		return xl.gettestdata(sheetName, testCaseRowNumber);
	}



	/**
	 * Returns a true/false based on the element (checkboxes, radio buttons) being
	 * checked
	 * 
	 * @param locatorKey - string name set in the java file for a web element
	 * @param type       - string to tell which type of locator is being passed in
	 *                   supported types: (id, name, classname, linktext, xpath,
	 *                   cssselector)
	 * @return boolean
	 */
	public boolean isElementChecked(WebDriver driver,String locatorKey, String type) {
		WebElement checkbox = getElement(driver, locatorKey, type);
		return checkbox.isSelected();
	}

	/**
	 * Returns a true/false based on the element being enabled or not This can help
	 * determine whether inputs are disabled, for example
	 * 
	 * @param locatorKey - string name set in the java file for a web element
	 * @param type       - string to tell which type of locator is being passed in
	 *                   supported types: (id, name, classname, linktext, xpath,
	 *                   cssselector)
	 * @return boolean
	 */
	public boolean isElementEnabled(WebDriver driver,String locatorKey, String type) {
		WebElement element = getElement(driver, locatorKey, type);
		return element.isEnabled();
	}

	/**
	 * Returns a true/false based on the element being displayed or not
	 * 
	 * @param locatorKey - string name set in the java file for a web element
	 * @param type       - string to tell which type of locator is being passed in
	 *                   supported types: (id, name, classname, linktext, xpath,
	 *                   cssselector)
	 * @return boolean
	 */
	public boolean isElementDisplayed(WebDriver driver, String locatorKey, String type) {
		WebElement element = getElement(driver, locatorKey, type);
		return element.isDisplayed();
	}

	/**
	 * This takes a screenshot, and returns the base64 encoded string which represents the image
	 * 
	 * @param driver
	 * @return
	 * @throws InterruptedException
	 */
	public String takeScreenShotAsBase64(WebDriver driver) {
		//String filepath = null;
		String dest = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
		return "data:image/jpg;base64, " + dest;
	}

	/**
	 * This takes a failure message and outputs it to the report
	 * 
	 * @param test    - test parameter to be used by extent report
	 * @param failMsg - String message to be displayed for fail
	 * @param e
	 * @throws Exception
	 */
	public void logFailedStepToReportWithScreenshot(WebDriver driver, ExtentTest test, String failMsg) {
		//LOGGER.warning(failMsg);
		test.log(LogStatus.FAIL, failMsg);
		driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
		String base64Info = takeScreenShotAsBase64(driver);
		test.log(LogStatus.FAIL, "" + test.addScreenCapture(base64Info));
		report.endTest(test);
		report.flush();
		//Assert.fail(failMsg);

	}

	/**
	 * This takes a passing message and outputs it to the report
	 * 
	 * @param test    - test parameter to be used by extent report
	 * @param passMsg - String message to be displayed for pass
	 * @throws Exception
	 */
	public void logPassedStepToReportWithScreenshot(WebDriver driver, ExtentTest test, String passMsg) {
		test.log(LogStatus.PASS, passMsg);
		driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
        String base64Info = takeScreenShotAsBase64(driver);
		test.log(LogStatus.PASS, "" + test.addBase64ScreenShot(base64Info));
		report.endTest(test);
		report.flush();

	}

	public void input(WebDriver driver,String locatorKey, String data, String type) {
		getElement(driver,locatorKey, type).sendKeys(data);
	}

	/**
	 * Use the locator key to return the web element
	 * 
	 * @param locatorKey - string name set in the java file for a web element
	 * @param type       - string to tell which type of locator is being passed in
	 *                   supported types: (id, name, classname, linktext, xpath,
	 *                   cssselector)
	 * @return
	 */
	public WebElement getElement(WebDriver driver,String locatorKey, String type) {

		WebElement element = null;
		try {
			if (type.equalsIgnoreCase(Constants.ID)) {
				element = driver.findElement(By.id(locatorKey));
			} else if (type.equalsIgnoreCase(Constants.NAME)) {
				element = driver.findElement(By.name(locatorKey));
			} else if (type.equalsIgnoreCase(Constants.CLASSNAME)) {
				element = driver.findElement(By.className(locatorKey));
			} else if (type.equalsIgnoreCase(Constants.LINKTEXT)) {
				element = driver.findElement(By.linkText(locatorKey));
			} else if (type.equalsIgnoreCase(Constants.XPATH)) {
				element = driver.findElement(By.xpath(locatorKey));
			} else if (type.equalsIgnoreCase(Constants.CSSSELECTOR)) {
				element = driver.findElement(By.cssSelector(locatorKey));
			} else {
				Assert.fail(Constants.THE_PROVIDED_LOCATOR_IS_NOT_FOUND_AT + locatorKey);
			}
						
			} 
			catch (Exception e) {
				LOGGER.info("GetElement Method Failed");
				LOGGER.warning(e.getMessage());
				logFailedStepToReportWithScreenshot(driver, test,"GetElement Method Failed: "+e.getMessage());
				
			}

		return element;

	}

	/**
	 * Clicks the web element provided
	 * 
	 * @param locatorKey - string name set in the java file for a web element
	 * @param type       - string to tell which type of locator is being passed in
	 *                   supported types: (id, name, classname, linktext, xpath,
	 *                   cssselector)
	 */

	public void click(WebDriver driver, String locatorKey, String type) {
		getElement(driver, locatorKey, type).click();
	}

	/**
	 * click the webelement having javascript associated in the html tag.
	 * 
	 * @param locatorKey - string name set in the java file for a web element
	 * @param xpath      type - string to tell which type of locator is being passed
	 *                   in supported types: (id, name, classname, linktext, xpath,
	 *                   cssselector)
	 * @author Ravi VG
	 */

	public void javascriptExecutorClick(WebDriver driver, String locatorKey, String type) {

		WebElement element = getElement(driver, locatorKey, type);
		JavascriptExecutor js = ((JavascriptExecutor) driver);

		js.executeScript("arguments[0].click();", element);

	}

	/**
	 * This takes a passing message and outputs it to the report
	 * 
	 * @param test    - test parameter to be used by extent report
	 * @param passMsg - String message to be displayed for pass
	 * @throws Exception
	 */
	public void logPassedStepToReport(ExtentTest test, String passMsg) {
		test.log(LogStatus.PASS, passMsg);
		report.endTest(test);
		report.flush();

	}

	public void logFailedStepToReport(ExtentTest test, String failMsg) {
		//LOGGER.warning(failMsg);
		test.log(LogStatus.FAIL, failMsg);
		report.endTest(test);
		report.flush();
		//Assert.fail(failMsg);

	}

	/**
	 * Assertion to validate 2 strings are equal
	 * 
	 * @param actual   - string text on web ui
	 * @param expected - string text for what is expected
	 */
	public void assertEquals(WebDriver driver, String actual, String expected) {
		if (actual.equalsIgnoreCase(expected)) {
			logPassedStepToReportWithScreenshot(driver, test, "Data match. Expected: " + expected + " Actual: " + actual);
		} else {
			logFailedStepToReportWithScreenshot(driver, test, "Data mismatch. Expected: " + expected + " Actual: " + actual);
			Assert.fail("Data mismatch.");
		}
	}
	
	/**
	 * Assertion to validate 2 integers are equal
	 * 
	 * @param actual   - string integer value
	 * @param expected - string integer value
	 */
	
	public void assertEquals(int actual, int expected) {
		if (actual == expected) {
			
			LOGGER.info("Data Match. Expected: " + expected + " Actual: " + actual);
			logPassedStepToReport(test, "Data Match. Expected: " + expected + " Actual: " + actual);
			
		} else {
			
			LOGGER.info("Data Mismatch. Expected: " + expected + " Actual: " + actual);
			logFailedStepToReport(test, "Data mismatch. Expected: " + expected + " Actual: " + actual);
			Assert.fail("Data Mismatch.");
		}
	}
	
	/**
	 * Checks to see if the web element is present on the page
	 * 
	 * @param locatorKey - string name set in the java file for a web element
	 * @param type       - string to tell which type of locator is being passed in
	 *                   supported types: (id, name, classname, linktext, xpath,
	 *                   cssselector)
	 * @return
	 */
	
	public boolean isElementPresent(WebDriver driver, String locatorKey, String type) {
		List<WebElement> elements = new ArrayList<>();
		if (type.equalsIgnoreCase("id")) {

			elements = driver.findElements(By.id(locatorKey));

		} else if (type.equalsIgnoreCase("name")) {

			elements = driver.findElements(By.name(locatorKey));

		} else if (type.equalsIgnoreCase(Constants.CLASSNAME)) {

			elements = driver.findElements(By.className(locatorKey));

		} else if (type.equalsIgnoreCase(Constants.LINKTEXT)) {

			elements = driver.findElements(By.linkText(locatorKey));

		} else if (type.equalsIgnoreCase(Constants.XPATH)) {

			elements = driver.findElements(By.xpath(locatorKey));

		} else if (type.equalsIgnoreCase(Constants.CSSSELECTOR)) {

			elements = driver.findElements(By.cssSelector(locatorKey));

		} else {

			Assert.fail(Constants.THE_PROVIDED_LOCATOR_IS_NOT_FOUND_AT + locatorKey);
		}
		
		LOGGER.info("Element Present:" +locatorKey);
		
		return !elements.isEmpty();
				
	}
	/**
	 * Returns a true/false based whether or not the element exists on the page
	 * 
	 * @param selector this will look something like by.xpath("whicheverXpath"), it tells the
	 * 			 driver how to find the element in question
	 * @return boolean
	 */
	public boolean isElementPresent(WebDriver driver, By selector) {

	    driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
	    boolean returnVal = true;
	    
	    try{
	        driver.findElement(selector);
	        LOGGER.info("Element Present: " +selector);
	        
	        } 
	    catch (NoSuchElementException e){
	    	
	    	LOGGER.info("Element Not Present:" +selector);
			LOGGER.warning(e.getMessage());
			returnVal = false;
	        
	    } finally {
	        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	    }
	    return returnVal;
	}

	/**
	 * Returns a true/false based on the element being clickable - i.e. if it is
	 * both displayed and enabled This can help determine whether inputs are
	 * disabled, for example
	 * 
	 * @param locatorKey - string name set in the java file for a web element
	 * @param type       - string to tell which type of locator is being passed in
	 *                   supported types: (id, name, classname, linktext, xpath,
	 *                   cssselector)
	 * @return boolean
	 */
	public boolean isElementClickable(WebDriver driver, String locatorKey, String type) {
		boolean elementIsDisplayed = isElementDisplayed(driver, locatorKey, type);
		boolean elementIsEnabled = isElementEnabled(driver, locatorKey, type);
		LOGGER.info("Element Clickable");
		return (elementIsDisplayed && elementIsEnabled);	
	}


	/**
	 * Input method to default to xpath if no type is provided
	 * 
	 * @param locatorKey
	 * @param data
	 */
	public void input(WebDriver driver, String locatorKey, String data) {
		try {	
			input(driver, locatorKey, data, Constants.XPATH);
			LOGGER.info("Text Input entered: " +locatorKey);
		}
	  catch (Exception e) {
		  LOGGER.info("Text Input failed: " +locatorKey);
		  LOGGER.warning(e.getMessage());
		  logFailedStepToReportWithScreenshot(driver, test,"Text Input Failed: "+e.getMessage());		
		}
	}
	
	
	/**
	 * Enter text into a text field  having javascript associated in the html tag.
	 * 
	 * @param locatorKey - string name set in the java file for a web element
	 * @param data       - text to be entered into a text field
	 * @param xpath type - string to tell which type of locator is being passed in
	 *                   supported types: (id, name, classname, linktext, xpath,
	 *                   cssselector)
	 * @author Ravi VG
	 */
	
	public  void javascriptExecutorInput(WebDriver driver, String locatorKey, String data, String type) {
		try {
			WebElement element = getElement(driver, locatorKey, type);
			JavascriptExecutor js = ((JavascriptExecutor) driver);
			js.executeScript("arguments[0].value='"+data+"';",element);
		  	LOGGER.info("Text Input Entered: " +locatorKey);
		
		}
		catch (Exception e) {
			
			LOGGER.info("Text Input Failed: " +locatorKey);
			LOGGER.warning(e.getMessage());
			logFailedStepToReportWithScreenshot(driver, test,"Text Input Failed: "+e.getMessage());			
		}	
	}

	
	/**
	 * Click method to default to xpath if no type is provided
	 * 
	 * @param locatorKey
	 */

	public void click(WebDriver driver, String locatorKey) {
		try {
		
			click(driver, locatorKey, Constants.XPATH);
			LOGGER.info("Element clicked: " +locatorKey);
					
		}
		
		catch (Exception e) {
			
			LOGGER.info("Element Not Clicked: " +locatorKey);
			LOGGER.warning(e.getMessage());
			logFailedStepToReportWithScreenshot(driver, test, "Element Not Clicked: " +locatorKey  +e.getMessage());
		}
	}

	/**
	 * Right clicks the web element provided
	 * 
	 * @param locatorKey - string name set in the java file for a web element
	 * @param type       - string to tell which type of locator is being passed in
	 *                   supported types: (id, name, classname, linktext, xpath,
	 *                   cssselector)
	 */
	public void rightClick(WebDriver driver, String locatorKey, String type) {
		try {
			
		Actions action = new Actions(driver);
		action.contextClick(getElement(driver, locatorKey, type)).perform();
		LOGGER.info("Right Click Performed: " +locatorKey);
		
		}
		catch (Exception e) {
			
			LOGGER.info("Right Click Failed: " +locatorKey);
			LOGGER.warning(e.getMessage());
			logFailedStepToReportWithScreenshot(driver, test, "Right Click Failed: " +locatorKey  +e.getMessage());
		}
	}

	/**
	 * Clicks the dynamic web element provided
	 * 
	 * @param locatorKey  - string name set in the properties file for a web element
	 * @param locaterType - string text for the type of element to be clicked
	 */
	public void clickDynamicElement(WebDriver driver, String locatorKey, String locaterType) {
		try {
			
			getElementWithReplacementValue(driver, locatorKey, locaterType).click();
			LOGGER.info("Dynamic Element Clicked: " +locatorKey);
		}
		catch (Exception e) {
			
			LOGGER.info("Dynamic Click Failed: " +locatorKey);
			LOGGER.warning(e.getMessage());
			logFailedStepToReportWithScreenshot(driver, test, " Click Failed: " +locatorKey  +e.getMessage());
		}
	}

	/**
	 * Collects and returns a list of web elements at the specified path
	 * 
	 * @param locatorKey - string name set in the properties file for a web element
	 * @return
	 */
	public List<WebElement> getElements(WebDriver driver, String locatorKey) {
		return driver.findElements(By.xpath(prop.getProperty(locatorKey)));
	}

	/**
	 * Reads and provides the text found at the locator key
	 * 
	 * @param locatorKey - string name set in the java file for a web element
	 * @param type       - string to tell which type of locator is being passed in
	 *                   supported types: (id, name, classname, linktext, xpath,
	 *                   cssselector)
	 * @return
	 */

	public String getText(WebDriver driver, String locatorKey, String type) {	
		return getElement(driver, locatorKey, type).getText();	
	}

	/**
	 * getText method to default to xpath if no type is provided
	 * 
	 * @param locatorKey
	 */
	public void getText(WebDriver driver, String locatorKey) {
		try {
		getText(driver, locatorKey, Constants.XPATH);
		LOGGER.info("Get Text Passed: " +locatorKey);
		}
		
		catch (Exception e) {
			
			LOGGER.info("Get Text Failed: " +locatorKey);
			LOGGER.warning(e.getMessage());
			logFailedStepToReportWithScreenshot(driver, test, " Get Text Failed: " +locatorKey  +e.getMessage());
		}
	}
	

	/**
	 * Searches object for attribute, if exists returns true, else false
	 * 
	 * @param locatorKey - string name set in the java file for a web element
	 * @param attribute  - the name of the attribute whose text value you wish to
	 *                   return
	 * @param type       - string to tell which type of locator is being passed in
	 *                   supported types: (id, name, classname, linktext, xpath,
	 *                   cssselector)
	 * @return boolean
	 */
	public boolean doesElementHaveAttribute(WebDriver driver, String locatorKey, String type, String attribute) {
		String fieldValue = getElement(driver, locatorKey, type).getAttribute(attribute);
		return (fieldValue != null);
		}

	/**
	 * Handle a pop-up alert
	 * 
	 * @param locator - string name set in the java file for a web element
	 * @throws InterruptedException
	 */
	public void popupAlert(WebDriver driver, String locator, String type) throws InterruptedException {

		Thread.sleep(2000);
		switchToFrame(driver,"tapestry");
		Thread.sleep(2000);
		if (getElement(driver,locator, type).isDisplayed()) {
			Thread.sleep(2000);
			click(driver,locator, type);
		}
	}

	/**
	 * Scrolls within a window to put an element into view
	 * 
	 * @param locator - string name set in the java file for a web element
	 * @param type    - string to tell which type of locator is being passed in
	 *                supported types: (id, name, classname, linktext, xpath,
	 *                cssselector)
	 * @throws InterruptedException
	 */
	public void scrollWindow(WebDriver driver, String locator, String type) throws InterruptedException {
		WebElement element = getElement(driver, locator, type);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(2000);

	}

	/**
	 * Highlight the element provided
	 * 
	 * @param locator - string name set in the java file for a web element
	 * @param type    - string to tell which type of locator is being passed in
	 *                supported types: (id, name, classname, linktext, xpath,
	 *                cssselector)
	 */
	public void highlightElement(WebDriver driver, String locator, String type) {
		WebElement ele = getElement(driver,locator, type);
		((JavascriptExecutor) driver)
				.executeScript("argument[0].setAttribute('style','background: yellow; border: 2px solid red;');", ele);

	}

	/**
	 * Clears the value of text field
	 * 
	 * @param locator - string name set in the java file for a web element
	 * @param type    - string to tell which type of locator is being passed in
	 *                supported types: (id, name, classname, linktext, xpath,
	 *                cssselector)
	 */
	public void clearValue(WebDriver driver, String locator, String type) {
		getElement(driver, locator, type).clear();

	}

	/**
	 * clearValue method to default to xpath if no type is provided
	 * 
	 * @param locatorKey
	 */
	public void clearValue(WebDriver driver, String locator) {
		getElement(driver, locator, Constants.XPATH).clear();
	}

	public WebElement getElementWithReplacementValue(WebDriver driver, String locatorKey, String locaterType) {

		WebElement element = null;

			if (locaterType.equals(Constants.ID)) {
				element = driver.findElement(By.id(locatorKey));
			} else if (locaterType.equals(Constants.NAME)) {
				element = driver.findElement(By.name(locatorKey));
			} else if (locaterType.equals(Constants.CLASSNAME)) {
				element = driver.findElement(By.className(locatorKey));
			} else if (locaterType.equals(Constants.LINKTEXT)) {
				element = driver.findElement(By.linkText(locatorKey));
			} else if (locaterType.equals(Constants.XPATH)) {
				element = driver.findElement(By.xpath(locatorKey));
			} else if (locaterType.equals(Constants.CSSSELECTOR)) {
				element = driver.findElement(By.cssSelector(locatorKey));
			} else {
				Assert.fail("The provided Locator - " + locatorKey + " is not found");
			}
		return element;

	}



	/**
	 * Assertion to validate 2 strings are not equal
	 * 
	 * @param actual   - string text on web ui
	 * @param expected - string text for what is expected
	 */
	public void assertNotEquals(String actual, String expected) {
		Assert.assertNotEquals(actual, expected);
	}

	/**
	 * Asserts to see if the provided condition is true
	 * 
	 * @param condition
	 */
	public void assertTrue(boolean condition) {
		Assert.assertTrue(condition);

	}

	public String replacementInLocator(String locator, String replaceValue) {

		String currentLocator = prop.getProperty(locator);
		return currentLocator.replace("$replacement-value$", replaceValue);
	}

	public List<WebElement> getElementsWithReplacementValue(WebDriver driver, String locatorKey) {
		return driver.findElements(By.xpath(locatorKey));
	}

	/**
	 * Method to count the rows in table
	 * 
	 * @param xapth - xpath of the webtable
	 */
	public int getTableRowCount(WebDriver driver, String xpath) {
		try {
			WebElement webtable = driver.findElement(By.xpath(xpath));
			List<WebElement> rows = webtable.findElements(By.tagName("tr"));
			return rows.size();
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			return 0;
		}

	}

	/**
	 * Method to check the content in table
	 * 
	 * @param xapth - xpath of the webtable
	 * @param value - value to be checked in table
	 */
	public boolean checkTableContains(WebDriver driver, String tableXPath, String value){
		int count = 0;
		try {
			WebElement webtable = driver.findElement(By.xpath(tableXPath));
			List<WebElement> allRows = webtable.findElements(By.tagName("tr"));
			for (WebElement row : allRows) {
				List<WebElement> cells = row.findElements(By.tagName("td"));

				// Value of each cell
				for (WebElement cell : cells) {
					if (cell.getText().contains(value))

					{
						count = 1;
						break;
					}

				}

				if (count == 1) {
					break;
				}

			}
			if (count == 1) {
				logPassedStepToReportWithScreenshot(driver, test, value + " exists in table");
				return true;
			} else {
				logFailedStepToReport(test, value + " does not exists in table");
			}

		} catch (Exception e) {
			logFailedStepToReport(test, value + " does not exists in table"+e.getMessage());
			return false;
		}

		return true;
	}

	/**
	 * Method to check the table header
	 * 
	 * @param xapth - xpath of the webtable
	 * @param value - value to be checked in table header
	 */
	public boolean checkTableHeaderContains(WebDriver driver, String xpath, String value) {
		int cnt = 0;
		try {
			WebElement htmltable = driver.findElement(By.xpath(xpath));

			List<WebElement> allRows = htmltable.findElements(By.tagName("tr"));

			for (WebElement row : allRows) {
				List<WebElement> cells = row.findElements(By.tagName("td"));

				// Print the contents of each cell
				for (WebElement cell : cells) {
					if (cell.getText().contains(value)) {
						cnt = 1;
						break;
					}

				}
				if (cnt == 1) {
					break;
				}

			}

			if (cnt == 1) {
				logPassedStepToReportWithScreenshot(driver, test, value + " header exists in table");
				return true;
			} else {
				logFailedStepToReport(test, value + " header does not exists in table");
			}

		} catch (Exception e) {
			logFailedStepToReport(test, value + " header does not exists in table"+e.getMessage());
			return false;
		}

		return true;
	}

	/**
	 * Method to get the entire table content
	 * 
	 * @param xpath - xpath of the webtable
	 * @return
	 */
	public String getTableContent(WebDriver driver, String xpath) {
		StringBuilder builder = new StringBuilder();
		WebElement table = driver.findElement(By.xpath(xpath));
		List<WebElement> allRows = table.findElements(By.tagName("tr"));
		
		for (WebElement row : allRows) {
			List<WebElement> cells = row.findElements(By.tagName("td"));

			
			for (WebElement cell : cells) {
				builder.append(cell.getText());
			}
			
		}
		String content = builder.toString();
		if (content.length() > 1) {
			return content;
		}
		else {
		return "No data in table";
		}
	}

	/**
	 * Authenticate user pop-up Window
	 * 
	 * @param parentWindow
	 * @param handle
	 * 
	 */

	public  void authenticateUserWindow(WebDriver driver, String username, String password) {

		String parentWindow = driver.getWindowHandle();
		LOGGER.info("Switching to new window");

		Set<String> windowHandles = driver.getWindowHandles();
		Iterator<String> iterator = windowHandles.iterator();
		while (iterator.hasNext()) {
			String handle = iterator.next();
			if (!handle.contains(parentWindow)) {
				// Switch to popup
				driver.switchTo().window(handle);

				// Pass user Id and Password
				driver.findElement(By.id("Username")).sendKeys(username);
				driver.findElement(By.id("Password")).sendKeys(password);
				driver.findElement(By.name("btnLogin")).click();
			}
		}
	}
	
	/**
	 * Perform the scroll functionality in the Android mobile devices in perfecto
	 * cloud
	 * 
	 * @param String scrollobject Name
	 * @throws InterruptedException
	 * @return
	 * @author Ravi VG
	 */

	public void scrollAndroidWindow(WebDriver driver, String scrollelementtext) {

		try {
			((AndroidDriver<MobileElement>) driver)
					.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true))"
							+ ".scrollIntoView(" + "new UiSelector().textContains(\"" + scrollelementtext + "\"));");
			 LOGGER.info("Android:scroll step sucessful");

		} catch (Exception e) {
			 LOGGER.log(Level.WARNING,"exception :{0}", e.getCause());
			 LOGGER.log(Level.WARNING,"exception :{0}", e.getMessage());
		}

	}

	/**
	 * Perform the scroll functionality with reference to parent in the Android
	 * mobile devices in perfecto cloud Leverage only when there are multiple child
	 * elements with same Text Name
	 * 
	 * @param String Parent frame resource id
	 * @param String Child Name
	 * @throws InterruptedException
	 * @return
	 * @author Ravi VG
	 */

	public void scrollAndroidChildID(WebDriver driver, String frameResourceID, String childTextID){

		try {

			((AndroidDriver<MobileElement>) driver).findElementByAndroidUIAutomator(
					"new UiScrollable(new UiSelector()" + "\".resourceId(\"" + frameResourceID + "\")).scrollIntoView("
							+ "new UiSelector().textContains(\"" + childTextID + "\"));");

			 LOGGER.info("Android:scroll step executed");

		} catch (Exception e) {
			 LOGGER.log(Level.WARNING,"exception :{0}", e.getCause());
			 LOGGER.log(Level.WARNING,"exception :{0}", e.getMessage());
		}

	}

	/**
	 * Perform the scroll/swipe Vertically in Android on perfecto cloud.
	 * 
	 * @param
	 * @throws InterruptedException
	 * @return
	 * @author Ravi VG
	 * 
	 */

	public void swipeVerticalAndroid(WebDriver driver, AppiumDriver appiumDriver) {

		 LOGGER.info("Vertical Swipe");

		try {

			TouchAction action = new TouchAction((PerformsTouchActions) appiumDriver);
			int height = driver.manage().window().getSize().height;
			int width = driver.manage().window().getSize().width;

			int x = width / 2;
			int startx = (int) (height * 0.80);
			int endy = (int) (height * 0.20);

			action.press(PointOption.point(x, startx)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(3)))
					.moveTo(PointOption.point(x, endy)).release().perform();

			 LOGGER.info("Vertical Swipe success");

		} catch (Exception e) {
			 LOGGER.info("Vertical Swipe failure"); 
		}
		
	}


	/**
	 * Perform the scroll/swipe Horizontal in Android on perfecto cloud.
	 * 
	 * @param
	 * @throws InterruptedException
	 * @return
	 * @author Ravi VG
	 * 
	 */

	public void swipeHorizontalAndroid(WebDriver driver, AppiumDriver appiumDriver) {
		 LOGGER.info("Android Horizontal Swipe function");
		try {
			TouchAction action = new TouchAction((PerformsTouchActions) appiumDriver);
			int height = driver.manage().window().getSize().height;
			int width = driver.manage().window().getSize().width;

			int y = (int) (height * 0.2);
			int starty = (int) (width * 0.75);
			int endy = (int) (width * 0.35);

			action.press(PointOption.point(y, starty)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(3)))
					.moveTo(PointOption.point(y, endy)).release().perform();

			 LOGGER.info("Horizontal Swipe success");

		} catch (Exception e) {
			 LOGGER.warning("Horizontal Swipe failure");
			 LOGGER.warning(e.getMessage());
		}

	}

	/**
	 * Perform the scroll functionality in the iOS mobile devices in perfecto cloud
	 * 
	 * @param String scrollobject xpath
	 * @throws InterruptedException
	 * @return
	 * @author Ravi VG
	 */

	public void scrollWindowiOS(WebDriver driver, String scrollelementtext)  {
		try {

			driver.findElement(By.xpath(scrollelementtext));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			Map<String, String> scrollObject = new HashMap<>();
			scrollObject.put("direction", "down");
			js.executeScript("mobile: scroll", scrollObject);

			 LOGGER.info("iOS:scroll step sucessful");

		} catch (Exception e) {
			 LOGGER.warning("Scroll window failure");
			 LOGGER.warning(e.getMessage());
		}

	}

	/**
	 * Perform the scroll functionality in specified direction the iOS mobile
	 * devices in perfecto cloud
	 * 
	 * @param String scroll direction -down,up,left,right
	 * @throws InterruptedException
	 * @return
	 * @author Ravi VG
	 *
	 */

	public void scrolliOSDirection(WebDriver driver,String scrolldirection) {
		try {

			JavascriptExecutor js = (JavascriptExecutor) driver;
			Map<String, String> scrollObject = new HashMap<>();
			scrollObject.put("direction", scrolldirection);
			js.executeScript("mobile: scroll", scrollObject);

			 LOGGER.info("iOS:scroll function sucessful");

		} catch (Exception e) {
			 LOGGER.warning("scroll iOS Direction failure");
			 LOGGER.warning(e.getMessage());
		}

	}
	
	/**
	 * Perform the device rotation functionality in specified direction the mobile
	 * devices in perfecto cloud
	 * 
	 * @param String device rotation - LANDSCAPE, POTRAIT
	 * @throws InterruptedException
	 * @return
	 * @author Ravi VG
	 *
	 */

	public void deviceRotation(AppiumDriver appiumDriver, Rotatable augmentedDriver, String modeType) throws InterruptedException {
		 LOGGER.info("Mobile Device Rotation function");
		try {

			ScreenOrientation currentOrientation = appiumDriver.getOrientation();
			 LOGGER.info("CurrentOrientation : " + currentOrientation);

			if (modeType.equalsIgnoreCase("LANDSCAPE")) {
				((Rotatable) augmentedDriver).rotate(ScreenOrientation.LANDSCAPE);
				// appiumDriver.rotate(ScreenOrientation.LANDSCAPE);//Depreciated method
			} else if (modeType.equalsIgnoreCase("PORTRAIT")) {

				((Rotatable) augmentedDriver).rotate(ScreenOrientation.PORTRAIT);
				// appiumDriver.rotate(ScreenOrientation.PORTRAIT);//Depreciated method
			}

			currentOrientation = appiumDriver.getOrientation();
			 LOGGER.info("AfterRotate : " + currentOrientation);
			Thread.sleep(1000);

			 LOGGER.info("CurrentOrientation : " + currentOrientation);

			 LOGGER.info("Device Rotation success");

		} catch (Exception e) {
			 LOGGER.warning("Device Rotation failure");
			 LOGGER.warning(e.getMessage());
		}

	}
	
	/**
	 * Switch App context from Nativeapp to Webveiw
	 * 
	 * @param 
	 * @throws InterruptedException
	 * @return
	 * @author Ravi VG
	 * 
	 */

	public void changeDriverContextToWeb(AppiumDriver appiumDriver)  {
		try {
				
			Set<String> contextNames = appiumDriver.getContextHandles();

			for (String contextName : contextNames) //prints current Context View 
				{
				LOGGER.info(contextName); 
				}
		
			appiumDriver.context((String)contextNames.toArray()[1]); // set context to WEBVIEW_1
		
			for (String contextName : contextNames) //prints changed Context View
				{
				LOGGER.info(contextName);  
				}
			}
		catch (Exception e)
		{
			LOGGER.warning(e.getMessage());
		}

	
	}

	/**
	 * Switch App context from Webview to Nativeapp
	 * 
	 * @param 
	 * @throws InterruptedException
	 * @return
	 * @author Ravi VG
	 * 
	 */

	public void changeDriverContextToNative(AppiumDriver appiumDriver) {
		try {
		Set<String> contextNames = appiumDriver.getContextHandles();

		for (String contextName : contextNames) //prints current Context View 
			{
		     LOGGER.info(contextName); 
			}
		
		appiumDriver.context("NATIVE_APP");	// set context to NATIVE_APP or VISUAL
		
		for (String contextName : contextNames) //prints changed Context View
			{
		     LOGGER.info(contextName);  
			}
		}
	catch (Exception e)
	{
		LOGGER.warning(e.getMessage());
	}	

	
	}	


	/**
	 * Decrypt a base64 encrypted string and return the decrypted String
	 * 
	 * @param decodedText
	 * @param decodedString
	 * @return decodedText
	 * 
	 */
	public  String decryptText(String text) {

		// Decrypt Text
		byte[] decodedText = Base64.decodeBase64(text);

		return new String(decodedText);
	}

	/**
	 * Encrypt text Users can use this to encrypted text and add it to data sheet
	 * for sensitive data
	 * 
	 * @param encodedString
	 * @param encodedString
	 * @return encodedText
	 * 
	 */
	public  String encryptText(String text) {

		// Encrypt Text
		byte[] encodedText = Base64.encodeBase64(text.getBytes());
		LOGGER.log(Level.INFO,"Encrypted Text: {0}" , new String(encodedText));
		return new String(encodedText);
	}
	
	/**
	 * Reads and provides the value of the an attribute specified for a specific
	 * object
	 * 
	 * @param locatorKey - string name set in the java file for a web element
	 * @param attribute  - the name of the attribute whose text value you wish to
	 *                   return
	 * @param type       - string to tell which type of locator is being passed in
	 *                   supported types: (id, name, classname, linktext, xpath,
	 *                   cssselector)
	 * @return the value of that attribute
	 */
	public  String getAttribute(WebDriver driver, String locatorKey, String type, String attribute) {
		return getElement(driver,locatorKey, type).getAttribute(attribute);
		}
	

	/**
	 * Select a DropDown Value by the visible test
	 * 
	 * @param locatorKey - string name set in the java file for a web element
	 * @param data
	 * @param type       - string to tell which type of locator is being passed in
	 *                   supported types: (id, name, classname, linktext, xpath,
	 *                   cssselector)
	 *   @return                
	 */
	public void selectDropDownByVisibleText(WebDriver driver, String locatorKey, String data, String type) {
		try  {
			Select s = new Select(getElement(driver, locatorKey, type));
			s.selectByVisibleText(data);
			LOGGER.info("Select Dropdown By Text: " +locatorKey +data);
		}
		catch (Exception e) {
			LOGGER.info("Select Dropdown By Text Failed: " +locatorKey);
			LOGGER.warning(e.getMessage());
			logFailedStepToReportWithScreenshot(driver,test, " Select DropDown By Text Failed: " +locatorKey  +e.getMessage());
			}
	 }
	
	/**
	 * Select a Dropdown element by value
	 * 
	 * @param locatorKey - string name set in the java file for a web element
	 * @param value
	 * @param type       - string to tell which type of locator is being passed in
	 *                   supported types: (id, name, classname, linktext, xpath,
	 *                   cssselector)
	 * @return
	 */
	public String selectDropdownElementByValue(WebDriver driver, String locatorKey, String type, String value) {
		Select s = new Select(getElement(driver, locatorKey, type));
		s.selectByValue(value);
		return value;
	}	
      
	/**
	 * Select a DropDown Value for specified index value
	 * 
	 * @param locatorKey - string name set in the java file for a web element
	 * @param index      - drop down index value
	 * @param type       - string to tell which type of locator is being passed in
	 *                   supported types: (id, name, classname, linktext, xpath,
	 *                   cssselector)
	 * @return 
	 * @author Ravi VG
	 */
	public void selectDropDownByIndex(WebDriver driver, String locatorKey, int index, String type) {
		try {
			Select s = new Select(getElement(driver, locatorKey, type));
			s.selectByIndex(index);
			LOGGER.info("DropDown Selected :" +index);
			}
		catch (Exception e) {
			LOGGER.info("DropDown Not Selelcted :" +locatorKey +index);
			LOGGER.warning(e.getMessage());
			logFailedStepToReportWithScreenshot(driver, test, "DropDown Not Selelcted :" +e.getMessage());	
	 		}
	}
	
	/**
	 * Select DropDown Default Value (i.e.firstvalue in drop down - index(0))
	 * 
	 * @param locatorKey - string name set in the java file for a web element
	 * @param type       - string to tell which type of locator is being passed in
	 *                   supported types: (id, name, classname, linktext, xpath,
	 *                   cssselector)
	 * @return 
	 * @author Ravi VG                  
	 */
	public void selectDropDownDefaultValue(WebDriver driver, String locatorKey,String type) {
		
		
		try {
			
			Select s = new Select(getElement(driver, locatorKey, type));
			s.selectByIndex(0);
						
			LOGGER.info("Selected Default Value For:" +locatorKey);
			
			}
		
		catch (Exception e) {
			
			LOGGER.info("DropDown DefaultValue Not Selected :" +locatorKey);
			LOGGER.warning(e.getMessage());
			logFailedStepToReportWithScreenshot(driver, test, "DropDown DefaultValue Not Selected :" +e.getMessage());
			
	 		}
		
		}
	
	/**
	 * Select a Dropdown element by value using Java Script Executor
	 * 
	 * @param locatorKey - string name set in the java file for a web element
	 * @param value      - attribute value
	 * @param type       - string to tell which type of locator is being passed in
	 *                   supported types: (id, name, classname, linktext, xpath,
	 *                   cssselector)
	 * @return
	 * @author Ravi VG
	 */
	public void javascriptExecutorDropdownSelect(WebDriver driver, String locatorKey, String type, String value) {
		
				
			WebElement select = getElement(driver, locatorKey, type);
			JavascriptExecutor js = (JavascriptExecutor)driver;
			   	js.executeScript("var select = arguments[0]; "
					+ "for(var i = 0; i < select.options.length; i++)"
					+ "{ if(select.options[i].text == arguments[1])"
					+ "{ select.options[i].selected = true;} "
					+ "}", select, value);  		 
							
		}
	
	/**
	 * Reads and provides the value of the Drop Down for specified index
	 * 
	 * @param locatorKey - string name set in the java file for a web element
	 * @param index       - index value
	 * @param type       - string to tell which type of locator is being passed in
	 *                   supported types: (id, name, classname, linktext, xpath,
	 *                   cssselector)
	 * @return String    - DropDown Value
	 * @author Ravi VG 
	 */
	public String getDropDownValueByIndex(WebDriver driver, String locatorKey, int index, String type) {
		
		String dropdownvalue =null;
		
		try {
			
			Select s = new Select(getElement(driver, locatorKey, type));
			dropdownvalue  =s.getOptions().get(index).getText();
			
			LOGGER.info("Index Is: " +index);
			LOGGER.info("DropDown Value Is: " +dropdownvalue);
			
			}
		catch (Exception e) {
		
			LOGGER.info("DropDown Value Doesnt Exist For:" +index);
			LOGGER.warning(e.getMessage());
			logFailedStepToReportWithScreenshot(driver, test, "DropDown Value Doesnt Exist :" +e.getMessage());
			
	 		}
		
		 return dropdownvalue;
	
	}
	
	/**
	 * Reads and provides the Default value/first value from the Drop Down
	 * 
	 * @param locatorKey - string name set in the java file for a web element
	 * @param index       - index value
	 * @param type       - string to tell which type of locator is being passed in
	 *                   supported types: (id, name, classname, linktext, xpath,
	 *                   cssselector)
	 * @return String    - First value in the DropDown
	 * @author Ravi VG 
	 */
	public String getDropDownDefaultValue(WebDriver driver, String locatorKey, String type) {
		
		String defaultvalue =null;
		
		try {
			
			Select s = new Select(getElement(driver, locatorKey, type));
			defaultvalue  =s.getFirstSelectedOption().getText();
			
			LOGGER.info("DropDown Default Value Is:"  +defaultvalue);
			
			}
		catch (Exception e) {
		
			LOGGER.info("DropDown Default Value Doesnt Exist :");
			LOGGER.warning(e.getMessage());
			logFailedStepToReportWithScreenshot(driver, test, "DropDown Default Value Doesnt Exist :" +e.getMessage());
			
	 		}
		 return defaultvalue;
		
	}
		
	
	/**
	 * Reads the size of the DropDown and provides all values from the Drop Down
	 * 
	 * @param locatorKey - string name set in the java file for a web element
	 * @param data       - DropDown Value
	 * @param type       - string to tell which type of locator is being passed in
	 *                   supported types: (id, name, classname, linktext, xpath,
	 *                   cssselector)
	 * @return String    - DropDown Values
	 * @author Ravi VG 
	 */
	public String[] getDropDownValues(WebDriver driver, String locatorKey, String type) {
		
		 Select s = new Select(getElement(driver, locatorKey, type));
		
		 List<WebElement> selectOptionValue = s.getOptions();
		 int size =selectOptionValue.size();
		
		 LOGGER.info("DropDown Box Size Is:" +size);
		
		 String[] value = new String[size];
		
		 for(int i =0;i<size;i++)
			{
			 value[i] = selectOptionValue.get(i).getText();
			  LOGGER.info("DropDown Value Is:" +value[i]);
			}
	
		 return value;		
	
	}		

	/**
	 * Switches to and accepts an alert
	 */
	public void acceptAlert(WebDriver driver) {
		Alert at = driver.switchTo().alert();
		at.accept();
		LOGGER.info("Alert Accepted");
		
	}
}