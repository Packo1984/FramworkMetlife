package main.java.com.tests;

import java.util.Map;
import java.util.logging.Level;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import main.java.com.businessfunctions.IOSAppLogin;
import main.java.com.corecomponents.GenericClass;
import main.java.com.pageobjects.iOSAndroidPageObjects;
import main.java.com.testcasedriver.TestCaseDriver;

public class AmazoniOSAndroidMobileappCreateUserValidation {
	TestCaseDriver tcdriver;
	GenericClass coreMethods = new GenericClass("MobileAppTest");

	@BeforeClass(alwaysRun = true)
	public void driverStart() {
		tcdriver = new TestCaseDriver("cloudMobileApp"); // Replace "physicalAndroidMobileApp" or "physicaliOSMobileApp"
												// parameter to launch mobile app in physical device
	}

	@Test(groups = "Native_Mobile")
	public void amazonCreateUserValidation() throws InterruptedException {
		IOSAppLogin mobileapploginobj = PageFactory.initElements(tcdriver.getDriver(), IOSAppLogin.class);
		
		coreMethods.test = coreMethods.report.startTest("Test-: TC " + "MobileAppTestScript" );

		
		mobileapploginobj.homePageValidation(tcdriver);
		

	//	coreMethods.deviceRotation(tcdriver.getAppiumDriver(), tcdriver.getAugmentedDriver(), "PORTRAIT");
		
	//	coreMethods.deviceRotation(tcdriver.getAppiumDriver(), tcdriver.getAugmentedDriver(), "LANDSCAPE");

	// coreMethods.click(tcdriver.getDriver(), iOSAndroidPageObjects.ScrollDeals_xpath, "xpath");

		// mobileapploginobj.primePageValidation(tcdriver);
		 mobileapploginobj.signInPageValidation(tcdriver);

		// ScrollWindowiOS(iOSAndroidPageObjects.ScrollDeals_xpath);
		// click(iOSAndroidPageObjects.ScrollDeals_xpath, "xpath");
		// ScrolliOS("down");

		// ScrollWindowAndroid("See all deals"); //Android Scroll Method

		coreMethods.LOGGER.info("click sucess");

		// mobileapploginobj.confirmationpage();

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