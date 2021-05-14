package main.java.com.tests;

import java.util.Map;
import java.util.logging.Level;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import main.java.com.businessfunctions.AndroidAmazonLogin;
import main.java.com.businessfunctions.IOSAppLogin;
import main.java.com.corecomponents.GenericClass;
import main.java.com.pageobjects.iOSAndroidPageObjects;
import main.java.com.projectconfig.ProjectConfig;
import main.java.com.testcasedriver.TestCaseDriver;

public class AmazonAndroidMobileappCreateUserValidationParallel {
	TestCaseDriver tcdriver;
	GenericClass coreMethods = new GenericClass("AmazonAndroidMobileappCreateUserValidationParallel");
	
	@Parameters({"os", "deviceId", "bundleId"})
 	@BeforeClass(alwaysRun = true)
	public void driverStart(String os, String deviceId, String bundleId) {
		
		tcdriver = new TestCaseDriver(os, ProjectConfig.MOBILE_CLOUD_USERNAME, ProjectConfig.MOBILE_CLOUD_SECURITY_TOKEN, 
				deviceId, bundleId);
		
	}

	@Test
	public void amazonCreateUserValidation() throws InterruptedException {
		
		AndroidAmazonLogin mobileapploginobj = PageFactory.initElements(tcdriver.getDriver(), AndroidAmazonLogin.class);
		
		
		
		coreMethods.test = coreMethods.report.startTest("Test-: TC " + "MobileAppTestScript" );

		
		mobileapploginobj.homePageValidation(tcdriver);
		
		
		 mobileapploginobj.signInPageValidation(tcdriver);

		
		coreMethods.LOGGER.info("click sucess");

		
		try {

		String testname = "AmazonAppTestcase";

			Map<String, String> testData = tcdriver.readExcel(testname);

			mobileapploginobj.login(tcdriver, testData);

			coreMethods.logPassedStepToReportWithScreenshot(tcdriver.getDriver(),coreMethods.test,
					"Amazon app prime user creation test script execution success!");

		} catch (Exception e) {

			coreMethods.logFailedStepToReportWithScreenshot(tcdriver.getDriver(),coreMethods.test,
					"Amazon app prime user creation Test script execution failed, review screenshot(s) for details:"
							+ e.getMessage());
		}

		mobileapploginobj.loginErrorValidation(tcdriver);
	}

	@AfterClass(alwaysRun = true)
	public void driverEnd() {
		tcdriver.quitDriver();
	}

}