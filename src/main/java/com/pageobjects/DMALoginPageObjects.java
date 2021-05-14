package main.java.com.pageobjects;

public class DMALoginPageObjects {
	//DMA Application Login Page xPaths
	public static final String USER_ID_XPATH = "//input[@id='USER']";
	public static final String PASSWORD_XPATH = "//input[@id='PASSWORD']";
	public static final String ENTER_XPATH = "//input[@id='cmdEnter']";
	
	private DMALoginPageObjects() {
	    throw new IllegalStateException("Utility class");
	  }
}