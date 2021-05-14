package main.java.com.tests;

import org.testng.annotations.Test;
import main.java.com.corecomponents.GenericClass;
import main.java.com.testcasedriver.TestCaseDriver;

public class EncryptText extends GenericClass{

	TestCaseDriver tcdriver;
	GenericClass coreMethods = new GenericClass("EncryptText");
	
	@Test(groups = "Encrypt_Text")
	public void encryptText(){

		try {

			// Provide the text that needs to be encrypted
			String text = "metlife1";

			LOGGER.info("Encryption in progress");

			// Encrypt Text
			coreMethods.encryptText(text);
			
			//view main.java.com.businessfunctions.DMALogin for an example of how to decrypt password

		} catch (Exception e) {
			LOGGER.severe(e.getMessage());

		}
	}

}
