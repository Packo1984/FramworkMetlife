package main.java.com.pageobjects;

import org.openqa.selenium.By;

public class CDILoginPageObjects {

	//CDI Application Login Page xPaths
	public static final String USER_ID_XPATH = "//input[@id='login_usuario']";
	public static final String PASSWORD_XPATH = "//input[@id='login_contrasenya']";
	public static final String ENTER_XPATH = "//span[@id='btn']";
	
	
	  private CDILoginPageObjects() {
		    throw new IllegalStateException("Utility class");
		  }

}

