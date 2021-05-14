package main.java.com.tests;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import main.java.com.businessfunctions.DMALogin;
import main.java.com.corecomponents.Constants;
import main.java.com.corecomponents.GenericClass;
import main.java.com.pageobjects.DMACodeListPageObjects;
import main.java.com.pageobjects.DMAMainPagePageObjects;
import main.java.com.testcasedriver.TestCaseDriver;

public class DMARegressionVerifyRequiredCode {
	TestCaseDriver tcdriver;
	GenericClass coreMethods = new GenericClass("verifyRequiredCode");

	@BeforeClass(alwaysRun = true)
	public void driverStart() {
		tcdriver = new TestCaseDriver("desktopBrowser");
	}

	/**
	 * This is a functional test case, that searches for a code that should appear
	 * in the code list of every environment in DMA It Tests the following
	 * functions: GetAttribute,
	 * 
	 * @param testname this corresponds to the Test Case name/number in the data
	 *                 file
	 * @throws Exception
	 */
	@Test(groups = "DMA_Regression")
	public void verifyRequiredCode() {
		try {
			coreMethods.test = coreMethods.report.startTest("Test-" + ": TC " + "verifyRequiredCode");
			DMALogin loginobj = PageFactory.initElements(tcdriver.getDriver(), DMALogin.class);

			loginobj.login(tcdriver);

			WebDriverWait wait = new WebDriverWait(tcdriver.getDriver(), 10);
			wait.until(
					ExpectedConditions.visibilityOfElementLocated(By.xpath(DMAMainPagePageObjects.MENU_BUTTON_XPATH)));

			// Block of code for passing details to the test run report
			if (coreMethods.isElementPresent(tcdriver.getDriver(), DMAMainPagePageObjects.MENU_BUTTON_XPATH,
					Constants.XPATH)) {
				tcdriver.getDriver().switchTo().defaultContent();
				coreMethods.logPassedStepToReportWithScreenshot(tcdriver.getDriver(), coreMethods.test,
						"Logged in successfully");
			} else {
				tcdriver.getDriver().switchTo().defaultContent();
				coreMethods.logFailedStepToReport(coreMethods.test,
						"Element was not present at DMAMainPagePageObjects.MENU_BUTTON_XPATH - May have failed to log in");
			}
			Thread.sleep(3000);
			coreMethods.click(tcdriver.getDriver(), DMAMainPagePageObjects.MENU_BUTTON_XPATH);
			Thread.sleep(1000);

			coreMethods.click(tcdriver.getDriver(), DMAMainPagePageObjects.ADMIN_MENU_XPATH);
			Thread.sleep(Constants.MICROWAIT);

			coreMethods.click(tcdriver.getDriver(), DMAMainPagePageObjects.CODE_LIST_MENU_XPATH);
			// Example pass logged to test report -
			// this will show the comment "I clicked the menu item!" and a screenshot in the
			// summary report
			coreMethods.logPassedStepToReport(coreMethods.test, "I navigated the menu!");
			Thread.sleep(Constants.MICROWAIT);

			if (coreMethods.isElementPresent(tcdriver.getDriver(), By.xpath("abcdefg"))) {
				coreMethods.logFailedStepToReport(coreMethods.test,
						"element 'abcdefg' present that is not supposed to be");
			}

			coreMethods.click(tcdriver.getDriver(), DMACodeListPageObjects.CODE_ID_SEARCH_XPATH);
			coreMethods.input(tcdriver.getDriver(), DMACodeListPageObjects.CODE_ID_SEARCH_XPATH, "DMAPrMtrcTyp");
			coreMethods.logPassedStepToReport(coreMethods.test, "I entered the code");
			Thread.sleep(Constants.MICROWAIT);

			coreMethods.click(tcdriver.getDriver(), DMACodeListPageObjects.CODE_EDIT_ICON_XPATH);
			Thread.sleep(Constants.MICROWAIT);

			Map<String,String> testData = tcdriver.readExcel("VerifyCodeList");
			
			coreMethods.assertEquals(
					tcdriver.getDriver(), coreMethods.getAttribute(tcdriver.getDriver(),
							DMACodeListPageObjects.SHORTNAME_FIELD_XPATH, "xpath", "placeholder"),
					testData.get("Expected Description"));
			coreMethods.logPassedStepToReport(coreMethods.test, "I verified the description!");
			coreMethods.click(tcdriver.getDriver(), DMACodeListPageObjects.CANCEL_EDIT_XPATH);
			Thread.sleep(Constants.MICROWAIT);

			coreMethods.click(tcdriver.getDriver(), DMACodeListPageObjects.CONFIRM_CANCEL_XPATH);
			Thread.sleep(Constants.MICROWAIT);

			coreMethods.isElementPresent(tcdriver.getDriver(), DMACodeListPageObjects.PROFILE_ICON_XPATH, "xpath");
			coreMethods.click(tcdriver.getDriver(), DMACodeListPageObjects.PROFILE_ICON_XPATH);
			Thread.sleep(Constants.MICROWAIT);

			coreMethods.click(tcdriver.getDriver(), DMACodeListPageObjects.LOGOUT_XPATH);
			coreMethods.logPassedStepToReport(coreMethods.test, "Successfully logged out");
			System.out.println("successfully verified code and logged out");

		} catch (Exception e) {
			e.printStackTrace();
			coreMethods.logFailedStepToReportWithScreenshot(tcdriver.getDriver(), coreMethods.test, e.getMessage());
		}

	}

	@AfterClass(alwaysRun = true)
	public void driverEnd() {
		tcdriver.quitDriver();
	}
}