package main.java.com.pageobjects;

public class iOSAndroidPageObjects {
	
//Application Landing Page - Basic Page Elements
// Enter your application elements here
//Mobile App
	//Amazon iOS app
	
		public static String primelink_xpath = "//*[@name='prime']";
		public static String primepage_xpath = "//*[@label='Prime gives you the best of shopping and entertainment.']";
		public static String primecontinue_xpath = "//*[@label='Continue Shopping']";
		public static String loginlink_xpath = "//XCUIElementTypeButton[@label='Sign in']";
		public static String createusercheckbox_xpath = "//*[@label='Create account . New to Amazon?']";
		public static String createuserName_xpath = "//*[@label='Amazon Sign-In']/XCUIElementTypeOther[2]/XCUIElementTypeOther[3]/XCUIElementTypeTextField[1]";
		public static String createuserphone_xpath = "//*[@label='Amazon Sign-In']/XCUIElementTypeOther[2]/XCUIElementTypeTextField[1]";
		public static String createcontinuebutton_xpath = "//*[@label='Continue']";
		public static String errormessage_xpath = "//*[@value='There was a problem']";
				
		
		public static String createsubmitbutton_xpath = "//*[@label='Submit']";
		public static String Seealldeals_xpath = "//*[@text='See all deals']";
		public static String LastMinuteDeals_xpath = "//*[@resource-id='gbSubnavTab1']";
		public static String AllDeals_xpath = "//*[@resource-id='gbSubnavTab2']";
		public static String ScrollDeals_xpath = "//*[@value='See all deals']";
		
	
		
		// Android app
		
			/*	public static String primelink_xpath = "//*[@text='Prime']";
				public static String loginlink_xpath = "//*[@resource-id='gwm-SignIn-button']";
				public static String createusercheckbox_xpath = "//*[@resource-id='register_accordion_header']/android.view.View[1]";
				public static String createuserName_xpath = "//*[@resource-id='ap_customer_name']";
				public static String createuserphone_xpath = "//*[@resource-id='ap_email']";
				public static String createcontinuebutton_xpath = "//*[@resource-id='continue']";
				public static String errormessage_xpath = "//*[@text='There was a problem']";
				
				public static String primepage_xpath = "//*[@label='Prime gives you the best of shopping and entertainment.']";
				public static String primecontinue_xpath = "//*[@label='Continue Shopping']";
				public static String Seealldeals_xpath = "//*[@text='See all deals']";
				public static String LastMinuteDeals_xpath = "//*[@resource-id='gbSubnavTab1']";
				public static String AllDeals_xpath = "//*[@resource-id='gbSubnavTab2']";*/
	
		// Japan New Mobile App
		
		public static String userid1_xpath = "//*[@value='Login ID(E-Mail address)']";
		public static String password1_xpath = "//*[@value='Password']";
		public static String clickbutton1_xpath = "//*[@label='Login']";
	
	
	// Japan Mobile App
			
		public static String accept_xpath = "//*[@label='閉�?�る']";
		public static String userid_xpath = "//*[@value='ログインID(メールアドレス)']";
		public static String password_xpath = "//*[@value='パスワード']";
		public static String clickbutton_xpath = "//*[@label=\"ログイン\"]";
		public static String claimlink_xpath = "//*[@label='JapanMobileApp']/XCUIElementTypeOther[7]/XCUIElementTypeStaticText[1]";
		public static String submitbutton_xpath ="//*[@label='�?�請求スタート']";
			
// Mobile Browser
   //USD project
	   public static String logintext_xpath = "//*[@id='userNameId']";
	   public static String passwordtext_xpath = "//*[@id='passwordId']";
	   public static String loginbutton_xpath = "//*[text()='LOG IN']";
	   public static String securityscreen_xpath = "//*[@id='divAccessCodeTitle']";
	  public static String nextbutton_xpath = "//*[@id='dijit_form_Button_0_label']";
			
}
