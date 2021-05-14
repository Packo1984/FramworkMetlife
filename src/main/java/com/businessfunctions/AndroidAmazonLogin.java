package main.java.com.businessfunctions;

import java.util.Map;
import main.java.com.corecomponents.Constants;
import main.java.com.corecomponents.GenericClass;
import main.java.com.pageobjects.AndroidAmazonPageObjects;
import main.java.com.testcasedriver.TestCaseDriver;

public class AndroidAmazonLogin {
	GenericClass coreMethods = new GenericClass();

	/**
	 * This method handles login for the application, retrieving the username and
	 * password
	 * 
	 * @param testData
	 * @param testname this corresponds to the coreMethods.test Case name/number in the data
	 *                 ........... file
	 */
	public void login(TestCaseDriver tcdriver, Map<String, String> testData) {
		String username = testData.get("User_ID");
		String password = testData.get("Password");
		// CreateUser Page Validation
		try {
			coreMethods.isElementPresent(tcdriver.getDriver(),AndroidAmazonPageObjects.createusercheckbox_xpath, Constants.XPATH);
			coreMethods.click(tcdriver.getDriver(),AndroidAmazonPageObjects.createusercheckbox_xpath, Constants.XPATH);
			
			coreMethods.isElementPresent(tcdriver.getDriver(),AndroidAmazonPageObjects.createuserName_xpath, Constants.XPATH);
			coreMethods.input(tcdriver.getDriver(), AndroidAmazonPageObjects.createuserName_xpath, username, Constants.XPATH);

			Thread.sleep(1000);

			coreMethods.isElementPresent(tcdriver.getDriver(),AndroidAmazonPageObjects.createuserphone_xpath, Constants.XPATH);
			coreMethods.input(tcdriver.getDriver(), AndroidAmazonPageObjects.createuserphone_xpath, password, Constants.XPATH);

			Thread.sleep(1000);
				
			coreMethods.click(tcdriver.getDriver(), AndroidAmazonPageObjects.createcontinuebutton_xpath, Constants.XPATH);
			
			
			Thread.sleep(1000);
			coreMethods.logPassedStepToReportWithScreenshot(tcdriver.getDriver(), coreMethods.test,
					" Login credentails entered successfully" + username + password);

		} catch (Exception e) {
			coreMethods.logFailedStepToReport(coreMethods.test, "Login credentails failed- May have failed to log in credentails "
					+ username + password);
			coreMethods.LOGGER.warning(e.getMessage());
		}

	}

	/**
	 * This method verifies the Prime link in the amazon application
	 * 
	 */

	public void homePageValidation(TestCaseDriver tcdriver) {

		coreMethods.LOGGER.info("Amazon Home page");

		try {

			coreMethods.isElementPresent(tcdriver.getDriver(), AndroidAmazonPageObjects.primelink_xpath, Constants.XPATH);
			Thread.sleep(1000);

			coreMethods.LOGGER.info("LoginPage success");

		} catch (Exception e)

		{
			coreMethods.logFailedStepToReportWithScreenshot(tcdriver.getDriver(), coreMethods.test,
					"LoginPage Failure, review screenshot(s) for details" + e.getMessage());

			coreMethods.LOGGER.warning(e.getMessage());
		}

	}

	/**
	 * This method verifies the Prime Confirmation Page for the amazon application
	 * 
	 */

	public void primePageValidation(TestCaseDriver tcdriver) {
		coreMethods.LOGGER.info("Amazon Prime confirmation page");

		try {

			coreMethods.isElementPresent(tcdriver.getDriver(), AndroidAmazonPageObjects.primepage_xpath, Constants.XPATH);
			coreMethods.click(tcdriver.getDriver(), AndroidAmazonPageObjects.primecontinue_xpath, Constants.XPATH);
			coreMethods.LOGGER.info("PrimePage sucess");

		} catch (Exception e) {

			coreMethods.logFailedStepToReportWithScreenshot(tcdriver.getDriver(), coreMethods.test,
					"PrimePage Failure - Refer xpaths, review screenshot(s) for details" + e.getMessage());

			coreMethods.LOGGER.warning(e.getMessage());
		}

	}

	/**
	 * This method verifies the All deals Page for the amazon application
	 * 
	 */
	public void confirmationPage(TestCaseDriver tcdriver) {

		try {

			coreMethods.isElementPresent(tcdriver.getDriver(), AndroidAmazonPageObjects.Seealldeals_xpath, Constants.XPATH);
			coreMethods.click(tcdriver.getDriver(), AndroidAmazonPageObjects.LastMinuteDeals_xpath, Constants.XPATH);
			coreMethods.LOGGER.info("All deals sucess");

			coreMethods.isElementPresent(tcdriver.getDriver(), AndroidAmazonPageObjects.LastMinuteDeals_xpath, Constants.XPATH);

			coreMethods.click(tcdriver.getDriver(), AndroidAmazonPageObjects.AllDeals_xpath, Constants.XPATH);

			coreMethods.LOGGER.info("scrollPage success");

		} catch (Exception e) {
			coreMethods.LOGGER.warning(e.getMessage());
		}
	}

	/**
	 * This method verifies the Prime Login Page for the amazon application
	 * 
	 */

	public void signInPageValidation(TestCaseDriver tcdriver){

		coreMethods.LOGGER.info("Amazon Prime login page");

		try {

			coreMethods.isElementPresent(tcdriver.getDriver(), AndroidAmazonPageObjects.loginlink_xpath, Constants.XPATH);
			coreMethods.click(tcdriver.getDriver(), AndroidAmazonPageObjects.loginlink_xpath, Constants.XPATH);
			coreMethods.LOGGER.info("coreMethods.click sucess");

		} catch (Exception e)

		{

			coreMethods.logFailedStepToReportWithScreenshot(tcdriver.getDriver(), coreMethods.test,
					"coreMethods.click Failure - Refer xpaths, review screenshot(s) for details" + e.getMessage());

			coreMethods.LOGGER.warning(e.getMessage());

		}
	}

	/**
	 * This method verifies the Password error validation
	 * 
	 */

	public void loginErrorValidation(TestCaseDriver tcdriver)  {

		if (coreMethods.isElementPresent(tcdriver.getDriver(), AndroidAmazonPageObjects.errormessage_xpath, Constants.XPATH)) {

			coreMethods.logPassedStepToReportWithScreenshot(tcdriver.getDriver(), coreMethods.test, "Error Message displayed");
		} else {

			coreMethods.logFailedStepToReport(coreMethods.test, "error message didnt displayed");
		}

	}

}
