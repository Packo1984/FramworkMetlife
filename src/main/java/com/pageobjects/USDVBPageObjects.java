package main.java.com.pageobjects;

public class USDVBPageObjects {
	
		
//USD project 
		//Login Page
		
		  public static  final String LoginPageheader_xpath = "//*[@class='login-title-header']/h2";
		  public static  final String UserName_xpath = "//*[@id='userNameId']";
		  public static  final String Password_xpath = "//*[@id='passwordId']";
		  public static  final String LoginBtn_xpath = "(//button)[1]/span[contains(text(),'LOG IN')]";
		  
		  //security overlay
		  public static  final String SecurityoverlayFrame_xpath = "//*[@id='authSecureOverlay']";
		  public static  final String ForSecurityoverlayheader_xpath = "//h1[@id='divAccessCodeTitle']";
		  public static  final String OTPpasscode_xpath = "//*[@id='divAccessCode']//p[2]//span[2]";
		  public static  final String AcesscodeEntry_xpath = "//*[@name='accesscode']";
		  public static  final String NextBtn_xpath = "//*[@id='dijit_form_Button_0_label']";
		  public static  final String CheckBox_xpath = "//*[@id='chkRememberDevice']";
		
		  //System Maintenance alert screen
		  public static  final String AlertscreenButton_xpath = "//*[@id='modal-continue']/div";
		  
		  //Claims Dashboard
		    public static  final String metlifelogo_xpath = "//*[@aria-label='MetlifeLogo']";
			public static  final String Dashboard_xpath = "//*[contains(text(),'DASHBOARD')]";
			public static  final String groupname_xpath = "//*[@class='co-branding title-lg']";
			public static  final String welcomemsg_xpath = "//*[@class='welcome']";
			public static  final String profileicon_xpath = "//*[@class='metlife-profile nav-icon svg-content']";
			public static  final String logout_xpath = "//*[@id='logout-account-navigation-link-svg']/div/div/div";
			public static  final String privacypolicy_xpath = "//*[contains(text(),'Privacy Policy')]";
			public static  final String termsofuse_xpath = "//*[contains(text(),'Terms of Use')]";
			public static  final String contactus_xpath = "//*[contains(text(),'Contact Us')]";
			public static  final String metlifelogofooter_xpath = "//*[@id='metlife_logo_1-footer']";
			public static  final String ClaimsInquiryHeader_xpath ="//*[@id='cardheader_claims_inquiry']";
			public static  final String Claimslink_xpath ="//*[@id='navBar_ClaimCenter-span-id']";
		
	//Additional XPath		
			
			  
		  public static  final String updateMailPage = "//h1";
		  public static  final String UpdatePhoneNumb = "//h1[contains(text(),'Verify your phone')]";
		  public static  final String NotNowLink = "//*[@id='cancelLink']";
		  public static  final String UpdateContinue = "//*[@id='onePhoneNumUpdate']";
		  public static  final String SkipLink = "//*[@id='skipEmailButton_label']";
		  public static  final String AlertOverlay = "(//*[@class='message-content-body']//label)[1]";
		  public static  final String Continuebtn = "//*[@id='modal-continue']";
		  public static  final String MyAccountsPage = "//h1[@id='headerLabelKey']";
		  
		  //internal login
		public static  final String username= "//input[@id='UserIdInput']";
		public static  final String pasword= "//input[@id='pwdtxt']";
		public static  final String domain= "//select[@id='DOMAIN']";
		public static  final String signin= "//div[@id='signInButton']";
		public static  final String logintitle= "//div[@id='SignInTitle']";
		public static  final String ClaimDetails_ClaimNumber = "//*[@class='claimdetailheader']//h1";
		public static  final String Processed_Circle = "((//*[@class='ml-status-bar-elements']//div[contains(text(),'Processed')])/..)[1]";
		public static  final String Processing_Circle = "((//*[@class='ml-status-bar-elements']//div[contains(text(),'Processing')])/..)[1]";
		public static  final String Submitted_circle = "((//*[@class='ml-status-bar-elements']//div[contains(text(),'Submitted')])/..)[1]";
		//smoke validation
		public static  final String banercard= "//*[@class='banner-card']";
		public static  final String topMessagecard= "(//*[@class='message-heading-content'])[1]";
		public static  final String Claiminquirycard= "//*[contains(text(),'Claims Inquiry')]";
		public static  final String helpfultoolcard= "//*[contains(text(),'Helpful tools')]";
		
		public static  final String Statusbar= "//*[@class='ml-status-bar']";
	    public static  final String DentalBenifitsheader= "//*[contains(text(),'Dental Benefits')]";
	    public static  final String Claimsummarycard= "//*[contains(text(),'Claims Summary')]";
	    public static  final String ProcedeureDeatailCard= "//*[contains(text(),'Procedure Details')]";
	    public static  final String PaymentSummaryCard = "//*[@class='card-header']//h2[contains(text(),'Payment Summary')]";
	    
		public static  final String Claims= "//span[contains(text(),'CLAIMS')]";
		public static  final String claimsearchresult= "//*[contains(text(),'Claims Search Results')]";
		public static  final String Dentalgroupnumdd= "//*[contains(text(),'Dental group Number')]";
		public static  final String dentalgroupnum= "//a[contains(text(),'145242')]";
		public static  final String Employeeidentifierdd= "//*[contains(text(),'Employee Identifier')]";
		public static  final String emplLastname= "//*[contains(text(),'Employee Last Name')]";
		public static  final String empName= "//*[@id='empName_input']";
		public static  final String search= "//*[@id='search-button_11']";
		public static  final String claimnum= "(//tbody//tr[@class='tablerow collapse']/td[5])[7]";
		

}
