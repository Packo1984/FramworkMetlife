package main.java.com.tests;

import java.util.Map;
import java.util.logging.Level;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import main.java.com.businessfunctions.USDBrowserLogin;
import main.java.com.corecomponents.GenericClass;
import main.java.com.testcasedriver.TestCaseDriver;


public class USDVBiOSBrowserClaimsPageValidation {
	TestCaseDriver tcdriver;
	GenericClass coreMethods = new GenericClass("USDMobileBrowserTest");
	
	@BeforeClass(alwaysRun = true)
	public void driverStart()  {
		tcdriver = new TestCaseDriver("cloudMobileBrowser"); //Replace \"physicalMobileBrowser\" parameter to launch physical device
	}

	@Test(groups = "Mobile_Browser")
	public void claimsPageValidation()  {
		final USDBrowserLogin mobileloginobj = PageFactory.initElements(tcdriver.getDriver(), USDBrowserLogin.class);
		
		coreMethods.test = coreMethods.report.startTest("Test-: TC " + "USDMobileBrowserTestScript" );
		
		try {
			
			String testname = "MobileBrowserTestcase";

			Map<String, String> testData = tcdriver.readExcel(testname);

			mobileloginobj.login(tcdriver,testData);
			mobileloginobj.oneTimePasswordEntry(tcdriver);
			mobileloginobj.systemMaintenanceScreenValidation(tcdriver);
			mobileloginobj.claimHomeScreenValidation(tcdriver);
			
			
			coreMethods.logPassedStepToReportWithScreenshot(tcdriver.getDriver(), coreMethods.test, "USD VB mobile BROWSER test script execution success!");

		} catch (Exception e) {
			coreMethods.logFailedStepToReportWithScreenshot(tcdriver.getDriver(), coreMethods.test, "Test script execution failed, review screenshot(s) for details" + e.getMessage());
		}
		
				
	}

	@AfterClass(alwaysRun = true)
	public void driverEnd(){
		tcdriver.quitDriver();
	}	

}