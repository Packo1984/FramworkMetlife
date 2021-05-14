package main.java.com.pageobjects;

public class DMAMainPagePageObjects {
	
	//Application Landing Page - Basic Page Elements

	// Enter your application elements here

	// Navigation Menu Items
	
	public static final String MENU_BUTTON_XPATH="//button[@id='trigger']";
	public static final String ADMIN_MENU_XPATH="//div[@class='mp-level ng-scope mp-level-open']//a[@class='icon icon-arrow-left'][contains(text(),'Admin')]";
	public static final String CODE_LIST_MENU_XPATH ="//div[@class='mp-level mp-level-open']//a[@class='icon'][contains(text(),'Code List')]";
	
	
	
	private DMAMainPagePageObjects() {
	    throw new IllegalStateException("Utility class");
	  }
}