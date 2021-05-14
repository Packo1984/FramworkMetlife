package main.java.com.businessfunctions;

import main.java.com.corecomponents.Constants;
import main.java.com.corecomponents.GenericClass;
import main.java.com.pageobjects.CDILoginPageObjects;
import main.java.com.pageobjects.CDIProperties;
import main.java.com.projectconfig.ProjectConfig;
import main.java.com.testcasedriver.TestCaseDriver;

//For more details reference the generic class file
public class CDILogin extends TestCaseDriver {

	/**
	 * This handles login for the application, retrieving the username and password
	 * 
	 * @param testData
	 * @param testname this corresponds to the Test Case name/number in the data
	 *                 file
	 */
	public void login(TestCaseDriver tcdriver)  {
		
		GenericClass coreMethods = new GenericClass();
		
		
		coreMethods.input(tcdriver.getDriver(),CDILoginPageObjects.USER_ID_XPATH, ProjectConfig.CDI_USERNAME, Constants.XPATH);

		//coreMethods.input(tcdriver.getDriver(),CDILoginPageObjects.PASSWORD_XPATH, coreMethods.decryptText(ProjectConfig.CDI_ENCRYPTED_PASSWORD), Constants.XPATH);
		coreMethods.input(tcdriver.getDriver(),CDILoginPageObjects.PASSWORD_XPATH, ProjectConfig.CDI_ENCRYPTED_PASSWORD, Constants.XPATH);

		coreMethods.click(tcdriver.getDriver(),CDILoginPageObjects.ENTER_XPATH, Constants.XPATH);

	//	Bloque de c�digo para pasar detalles al informe de ejecuci�n de prueba
		/*
		if (coreMethods.isElementPresent(tcdriver.getDriver(),CDIProperties.CLDM_LINK_XPATH, Constants.XPATH)) {
			System.out.println("S� encuentra la etiqueta Cerrar Sesi�n");
			//driver.switchTo().defaultContent();
			coreMethods.logPassedStepToReportWithScreenshot(tcdriver.getDriver(), coreMethods.test, "logueo correcto");
		}
		else {
			System.out.println("No encuentra la etiqueta Cerrar Sesi�n");
			//coreMethods.logFailedStepToReportWithScreenshot(tcdriver.getDriver(),coreMethods.test,"fallo ");
		}
		*/
		
		
		
	}
}