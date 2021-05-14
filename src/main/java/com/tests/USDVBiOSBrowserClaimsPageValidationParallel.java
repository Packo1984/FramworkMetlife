package main.java.com.tests;

import java.util.Map;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import main.java.com.businessfunctions.USDBrowserLogin;
import main.java.com.corecomponents.GenericClass;
import main.java.com.projectconfig.ProjectConfig;
import main.java.com.testcasedriver.TestCaseDriver;


public class USDVBiOSBrowserClaimsPageValidationParallel {
	TestCaseDriver tcdriver;
	GenericClass coreMethods = new GenericClass("USDVBiOSBrowserClaimsPageValidationParallel");
	
	@Parameters({"os", "deviceId", "browserName", "mobileUrl"})
	@BeforeClass(alwaysRun = true)
	public void driverStart(String os, String deviceId, String browserName, String mobileUrl)  {
		
		tcdriver = new TestCaseDriver(os, ProjectConfig.MOBILE_CLOUD_USERNAME, ProjectConfig.MOBILE_CLOUD_SECURITY_TOKEN, 
				deviceId, browserName, mobileUrl);
		
	}

	@Test
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