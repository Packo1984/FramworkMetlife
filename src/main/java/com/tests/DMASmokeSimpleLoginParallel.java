package main.java.com.tests;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import main.java.com.businessfunctions.DMALogin;
import main.java.com.corecomponents.Constants;
import main.java.com.corecomponents.GenericClass;
import main.java.com.pageobjects.DMAMainPagePageObjects;
import main.java.com.projectconfig.ProjectConfig;
import main.java.com.testcasedriver.TestCaseDriver;

public class DMASmokeSimpleLoginParallel{
	TestCaseDriver tcdriver;
	GenericClass coreMethods = new GenericClass("DMASmokeSimpleLoginParallel");
	
	
	@Parameters({"testType","browserName", "appUrl"})
	
	@BeforeClass(alwaysRun = true)
	public void driverStart(String testType, String browserName,String appUrl) {
		
			tcdriver = new TestCaseDriver(testType, browserName, appUrl);
	   }

	
	@Test
	public void simpleLoginVerification() {
		coreMethods.test = coreMethods.report.startTest("Test-: TC " + "simpleLoginVerification" );
		try {
			DMALogin loginobj = PageFactory.initElements(tcdriver.getDriver(), DMALogin.class);

			loginobj.login(tcdriver);
			
			WebDriverWait wait=new WebDriverWait(tcdriver.getDriver(), 10);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(DMAMainPagePageObjects.MENU_BUTTON_XPATH)));
			
			// Block of code for passing details to the test run report
			if (coreMethods.isElementPresent(tcdriver.getDriver(), DMAMainPagePageObjects.MENU_BUTTON_XPATH, Constants.XPATH)) {
				tcdriver.getDriver().switchTo().defaultContent();
				coreMethods.logPassedStepToReportWithScreenshot(tcdriver.getDriver(), coreMethods.test, "Logged in successfully");
			} else {
				tcdriver.getDriver().switchTo().defaultContent();
				coreMethods.logFailedStepToReport(coreMethods.test,
						"Element was not present at DMAMainPagePageObjects.MENU_BUTTON_XPATH - May have failed to log in");
			}
			
			Thread.sleep(3000);
			coreMethods.click(tcdriver.getDriver(), DMAMainPagePageObjects.MENU_BUTTON_XPATH);
			Thread.sleep(1000);

			coreMethods.click(tcdriver.getDriver(), DMAMainPagePageObjects.ADMIN_MENU_XPATH);
			Thread.sleep(1000);

			coreMethods.click(tcdriver.getDriver(), DMAMainPagePageObjects.CODE_LIST_MENU_XPATH);
			
			System.out.println("successfully navigated menu");
			// Example pass logged to test report -
			// this will show the comment "I navigated the menu!" and a screenshot in the
			// summary report
			coreMethods.logPassedStepToReportWithScreenshot(tcdriver.getDriver(), coreMethods.test, "I navigated the menu to the Code List!");

		} catch (Exception e) {
			e.printStackTrace();
			coreMethods.LOGGER.warning(e.getMessage());
			coreMethods.logFailedStepToReportWithScreenshot(tcdriver.getDriver(), coreMethods.test, e.getMessage());
		}
	}

	@AfterClass(alwaysRun = true)
	public void driverEnd() {
		tcdriver.quitDriver();
	}
}
