package main.java.com.businessfunctions;
import java.util.Map;
import java.util.logging.Level;

import main.java.com.corecomponents.GenericClass;
import main.java.com.pageobjects.USDVBPageObjects;
import main.java.com.testcasedriver.TestCaseDriver;

public class USDBrowserLogin {
	GenericClass coreMethods = new GenericClass();
	/**
	 * This handles login for the application, retrieving the username and password
	 * 
	 * @param testData
	 * @param testname this corresponds to the coreMethods.test Case name/number in the data
	 *   ........ file
	 */
	public void login(TestCaseDriver tcdriver, Map<String, String> testData) throws Exception {
		
		coreMethods.LOGGER.info("login mobile USD page");
		
		
		try {
			coreMethods.input(tcdriver.getDriver(), USDVBPageObjects.UserName_xpath, testData.get("User_ID"), "xpath");
			coreMethods.logPassedStepToReportWithScreenshot(tcdriver.getDriver(), coreMethods.test, "UserName element exists");
			
		}
		catch(Exception e) {
			coreMethods.LOGGER.log(Level.SEVERE,"exception :{0}", e.getCause());
			coreMethods.LOGGER.log(Level.SEVERE,"exception :{0}", e.getMessage());
			
			coreMethods.logFailedStepToReportWithScreenshot(tcdriver.getDriver(), coreMethods.test, "User Name Doesnt exists, review screenshot(s) for details" + e.getMessage());
		}
		
				
		try {
			coreMethods.input(tcdriver.getDriver(), USDVBPageObjects.Password_xpath, testData.get("Password"), "xpath");
			coreMethods.logPassedStepToReportWithScreenshot(tcdriver.getDriver(), coreMethods.test, "Password element exists");
		}
		catch(Exception e) {
			
			coreMethods.LOGGER.log(Level.SEVERE,"exception :{0}", e.getCause());
			coreMethods.LOGGER.log(Level.SEVERE,"exception :{0}", e.getMessage());
			coreMethods.logFailedStepToReportWithScreenshot(tcdriver.getDriver(), coreMethods.test, "Password Doesnt exists, review screenshot(s) for details" + e.getMessage());
		}
		
		
		coreMethods.click(tcdriver.getDriver(), USDVBPageObjects.LoginBtn_xpath, "xpath");
	}
	
	
	
	public void oneTimePasswordEntry(TestCaseDriver tcdriver) throws Exception
	{
				
		if (coreMethods.isElementPresent(tcdriver.getDriver(), USDVBPageObjects.ForSecurityoverlayheader_xpath, "xpath")) {
			tcdriver.getDriver().switchTo().defaultContent();
			coreMethods.logPassedStepToReportWithScreenshot(tcdriver.getDriver(), coreMethods.test, "security overlay screen displayed");
		} else {
			tcdriver.getDriver().switchTo().defaultContent();
			coreMethods.logFailedStepToReport(coreMethods.test, "security overlay not displayed ");
		}
		

		String title1 = tcdriver.getDriver().getTitle();
		coreMethods.LOGGER.info("title is " +title1);
		
		try {
			coreMethods.click(tcdriver.getDriver(), USDVBPageObjects.CheckBox_xpath, "xpath");
			String accesscode = coreMethods.getText(tcdriver.getDriver(), USDVBPageObjects.OTPpasscode_xpath, "xpath");
			coreMethods.input(tcdriver.getDriver(), USDVBPageObjects.AcesscodeEntry_xpath, accesscode, "xpath");
			
			coreMethods.click(tcdriver.getDriver(), USDVBPageObjects.NextBtn_xpath, "xpath");
			coreMethods.logPassedStepToReportWithScreenshot(tcdriver.getDriver(), coreMethods.test, "ontime password screen login  successfull and clicked ");
		}		
		catch(Exception e) {
			coreMethods.logFailedStepToReport(coreMethods.test, "onetime password screen failed");
			coreMethods.LOGGER.log(Level.SEVERE,"exception :{0}", e.getCause());
			coreMethods.LOGGER.log(Level.SEVERE,"exception :{0}", e.getMessage());
		}
		Thread.sleep(1000);	
		
	
		
	}
		
	public void systemMaintenanceScreenValidation(TestCaseDriver tcdriver) throws Exception
	{
		
		String title = tcdriver.getDriver().getTitle();
		coreMethods.LOGGER.info("title is " +title);
		coreMethods.click(tcdriver.getDriver(), USDVBPageObjects.AlertscreenButton_xpath, "xpath");
		
	}
				    		
	
	public void  claimHomeScreenValidation(TestCaseDriver tcdriver) throws Exception {
				

		try {
			coreMethods.isElementDisplayed(tcdriver.getDriver(), USDVBPageObjects.metlifelogo_xpath,"xpath");
			coreMethods.isElementPresent(tcdriver.getDriver(), USDVBPageObjects.groupname_xpath, "xpath");
			coreMethods.isElementPresent(tcdriver.getDriver(), USDVBPageObjects.welcomemsg_xpath, "xpath");
			coreMethods.isElementPresent(tcdriver.getDriver(), USDVBPageObjects.profileicon_xpath, "xpath");
			coreMethods.isElementPresent(tcdriver.getDriver(), USDVBPageObjects.Dashboard_xpath, "xpath");
		     coreMethods.logPassedStepToReportWithScreenshot(tcdriver.getDriver(), coreMethods.test, "Claimscreen elements exists ");
		}		
		catch(Exception e) {
			coreMethods.logFailedStepToReport(coreMethods.test, "Claimscreen elements doesnt exists");
			coreMethods.LOGGER.log(Level.SEVERE,"exception :{0}", e.getCause());
			coreMethods.LOGGER.log(Level.SEVERE,"exception :{0}", e.getMessage());
		}
		
		
		coreMethods.scrollWindow(tcdriver.getDriver(), USDVBPageObjects.privacypolicy_xpath, "xpath");
		
		
		
		try {
			coreMethods.isElementPresent(tcdriver.getDriver(), USDVBPageObjects.logout_xpath, "xpath");
			coreMethods.click(tcdriver.getDriver(), USDVBPageObjects.logout_xpath, "xpath");
		     coreMethods.logPassedStepToReportWithScreenshot(tcdriver.getDriver(), coreMethods.test, "logout sucess");
		}		
		catch(Exception e) {
			coreMethods.logFailedStepToReport(coreMethods.test, "logout failure");
			coreMethods.LOGGER.log(Level.SEVERE,"exception :{0}", e.getCause());
			coreMethods.LOGGER.log(Level.SEVERE,"exception :{0}", e.getMessage());
		}
		
		
		
	}
	
	
}

