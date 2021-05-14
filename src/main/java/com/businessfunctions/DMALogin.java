package main.java.com.businessfunctions;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import main.java.com.corecomponents.Constants;
import main.java.com.corecomponents.GenericClass;
import main.java.com.pageobjects.DMALoginPageObjects;
import main.java.com.pageobjects.DMAMainPagePageObjects;
import main.java.com.testcasedriver.TestCaseDriver;

//For more details reference the generic class file

public class DMALogin{
	
	
	/**
	 * This handles login for the application, retrieving the username and password
	 * 
	 * @param testData
	 * @param testname this corresponds to the Test Case name/number in the data
	 *                 file
	 */
	public void login(TestCaseDriver tcdriver, Map<String, String> testData) {
		GenericClass coreMethods = new GenericClass();
		
		coreMethods.LOGGER.info("userid is " +testData.get("User_ID"));
		
		coreMethods.input(tcdriver.getDriver(),DMALoginPageObjects.USER_ID_XPATH, testData.get("User_ID"), Constants.XPATH);
		
		//coreMethods.input(tcdriver.getDriver(),DMALoginPageObjects.USER_ID_XPATH, "CDLTADMN", Constants.XPATH);

		coreMethods.input(tcdriver.getDriver(),DMALoginPageObjects.PASSWORD_XPATH, coreMethods.decryptText("bWV0bGlmZTE="), Constants.XPATH);

		coreMethods.click(tcdriver.getDriver(),DMALoginPageObjects.ENTER_XPATH, Constants.XPATH);
//		
//		WebDriverWait wait=new WebDriverWait(driver, 10);
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(DMAMainPagePageObjects.MENU_BUTTON_XPATH)));
//		
//		// Block of code for passing details to the test run report
//		if (isElementPresent(DMAMainPagePageObjects.MENU_BUTTON_XPATH, Constants.XPATH)) {
//			driver.switchTo().defaultContent();
//			logPassedStepToReportWithScreenshot(test, "Logged in successfully");
//		} else {
//			driver.switchTo().defaultContent();
//			logFailedStepToReport(test,
//					"Element was not present at DMAMainPagePageObjects.MENU_BUTTON_XPATH - May have failed to log in");
		}

	

	/**
	 * This handles login for the application, retrieving the username and password
	 * 
	 * @param testData
	 * @param testname this corresponds to the Test Case name/number in the data
	 *                 file
	 */
	public void login(TestCaseDriver tcdriver) {
		GenericClass coreMethods = new GenericClass();
		coreMethods.input(tcdriver.getDriver(),DMALoginPageObjects.USER_ID_XPATH, "CDLTADMN", Constants.XPATH);

		coreMethods.input(tcdriver.getDriver(),DMALoginPageObjects.PASSWORD_XPATH, coreMethods.decryptText("bWV0bGlmZTE="), Constants.XPATH);

		coreMethods.click(tcdriver.getDriver(),DMALoginPageObjects.ENTER_XPATH, Constants.XPATH);
		
		/*
		 * WebDriverWait wait=new WebDriverWait(tcdriver.getDriver(), 10);
		 * wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
		 * DMAMainPagePageObjects.MENU_BUTTON_XPATH)));
		 */
		
		// Block of code for passing details to the test run report
		/*
		 * if (coreMethods.isElementPresent(tcdriver.getDriver(),DMAMainPagePageObjects.
		 * MENU_BUTTON_XPATH, Constants.XPATH)) {
		 * tcdriver.getDriver().switchTo().defaultContent();
		 * coreMethods.logPassedStepToReportWithScreenshot(tcdriver.getDriver(),
		 * coreMethods.test, "Logged in successfully"); } else {
		 * tcdriver.getDriver().switchTo().defaultContent();
		 * coreMethods.logFailedStepToReport(coreMethods.test,
		 * "Element was not present at DMAMainPagePageObjects.MENU_BUTTON_XPATH - May have failed to log in"
		 * ); }
		 */
	}
	
	
	
	
	
	
}