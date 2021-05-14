package main.java.com.testcasedriver;

import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Rotatable;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.SkipException;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import main.java.com.corecomponents.Constants;
import main.java.com.corecomponents.GenericClass;
import main.java.com.projectconfig.ProjectConfig;

public class TestCaseDriver {
	protected String testname;
	protected Map<String, String> testData;
	public WebDriver driver = null;
	protected AppiumDriver<MobileElement> appiumDriver = null;
	protected Rotatable augmentedDriver;
	protected DesiredCapabilities capabilities = new DesiredCapabilities();
	protected int counter = 1;
	GenericClass coreMethods = new GenericClass();

	
	public TestCaseDriver() {
	}
	
	/**
	 * constructor to invoke execution in desktop browser, mobile browser
	 * mobile app as per the parameters defined in project config 
	 */
	public TestCaseDriver(String testType) {
		driver = appLauncher(testType);
	}
	
		
	/**
	 * constructor to invoke desktop browser execution in parallel or sequential 
	 * as per the parameters defined in testng.xml 
	 */
	 public TestCaseDriver(String testType, String browserName, String appUrl ) {
					
		driver = launchWebApp(testType, browserName,  appUrl);
			
	 }
		
	 
	 /**
	  * constructor to invoke mobile browser execution on Perfecto devices in parallel or sequential 
	  * as per the parameters defined in testng.xml 
	  */
	  public TestCaseDriver(String os, String userName, String securityToken, String deviceId,
				String browserName, String mobileUrl) {
			
		driver = launchCloudMobileBrowser(os, userName, securityToken, deviceId, browserName, mobileUrl);
	  }
		
	  
	  /**
	   * constructor to invoke mobile app execution on Perfecto devices in parallel or sequential 
	   * as per the parameters defined in testng.xml 
	   */
	  		
		public TestCaseDriver(String os, String userName, String securityToken, String deviceId,
				String bundleId) {
			
			driver = launchMobileCloud(os, userName,  securityToken, deviceId, bundleId);
		}
		
      
		
		/**
		 * To invoke desktop browser execution in parallel or sequential as per the parameters defined in testng.xml 
		 *     
		 * @param testType        testType is a string and it should be either desktopBrowser or bamboo 
		 *                        and defined in the testng.xml.This method will launch the browser in desktop  
		 *                        or execute in the bamboo server as specified in the testng.xml.
		 *                        
		 *  @param browserName    browserName is string and should be specified as ch=chrome,ff=firefox,ed=Edge,
	     *                   	  ie=Internet Explorer,op=Opera.Launches browser as specified in testng.xml
		 *  @param appUrl     web application URL and should be specified in testng.xml  
		 *  @return RemoteWebDriver  
		 * @throws Exception
		 */
		
		 public  WebDriver launchWebApp(String testType, String browserName, String appUrl) {
			 
			 coreMethods.LOGGER.info("launchWebApp method execution starts");

			String bambooURL = System.getenv("bamboo_URL");
			if (bambooURL != null) {
					coreMethods.LOGGER.log(Level.INFO,
							"Please wait while the system launches a BROWSER for testing...Using value={0} From URL variable in Bamboo",
							bambooURL);
					
					try {
						driver = browserLaunch(browserName, bambooURL);
						return driver;
					} catch (InterruptedException e) {
						coreMethods.LOGGER.severe(e.getMessage());
						Thread.currentThread().interrupt();
					}
					
				}	
			else {
				coreMethods.LOGGER.log(Level.INFO, "Web parameters invoked {0}", testType  + browserName);
				
				try {
					driver = browserLaunch(browserName,appUrl);
					 coreMethods.LOGGER.info("browser launch succesfull");
					return driver;
					
				} catch (InterruptedException e) {
					 coreMethods.LOGGER.info("browser launch failed");
					coreMethods.LOGGER.severe(e.getMessage());
					Thread.currentThread().interrupt();
				}
			}
			
			
		return driver;	
			
		}
		 
		 
		 
	  /**
	   * Login to Perfecto cloud and initiate the driver
	   * To invoke mobile browser execution on Perfecto devices either in parallel or sequential 
	   * as per the parameters defined in testng.xml 
	   * 
	   *  @param os                os is String and it should be ios or android
	   *  @param userName          Perfecto login userName and defined in ProjectConfig
	   *  @param securityToken     Perfecto login securityToken and defined in ProjectConfig
	   *  @param deviceID          mobile device id
	   *  @param browserName       browserName is String and it should be safari
	   *                              for ios and chrome for android.
	   *  @param mobileUrl         mobile application url
	   *  @return RemoteWebDriver 
	   * @throws Exception
	    */ 
		 
		public  WebDriver launchCloudMobileBrowser(String os, String userName, String securityToken, String deviceId,
				String browserName, String mobileUrl) {
			
						
			
			coreMethods.LOGGER.info("Please wait while the mobile device launches a BROWSER for testing.......");
			 coreMethods.LOGGER.log(Level.INFO,"cloudMobileBrowser parameter invoked {0}", os +browserName);
			driver = launchCloudMobileBrowser(os, userName, securityToken,
					deviceId, browserName);
			driver = navigateToMobileURL(mobileUrl);
			coreMethods.LOGGER.info("cloud mobile browser launched");
			
			return driver;
		}
		
		
		
	/**
	 * @param testType is a String that should be either desktopBrowser,
	 *                 cloudMobileBrowser, cloudMobileApp, physicalMobileBrowser 
	 *                 physicalAndroidMobileApp or physicaliOSMobileApp depending 
	 *                 on where the test should execute                
	 *                 This method will launch the desktop browser, mobile browser,
	 *                 mobile app you have specified in the
	 *                 ProjectConfig class, and if the testType specifies that the
	 *                 test should be launched on mobile, it will also connect to
	 *                 the device specified in ProjectConfig
	 * @return RemoteWebDriver
	 * @throws Exception
	 */
	public WebDriver appLauncher(String testType) {

		// If you wish to specify the app URL from bamboo - create a variable named URL
		// in bamboo and pass it here - currently only for desktopBrowser testing
		String bambooURL = System.getenv("bamboo_URL");
		if (bambooURL != null) {
			coreMethods.LOGGER.log(Level.INFO,
					"Please wait while the system launches a BROWSER for testing...Using value={0} From URL variable in Bamboo",
					bambooURL);
			try {
				driver = browserLaunch(ProjectConfig.BROWSER, bambooURL);
				return driver;
			} catch (InterruptedException e) {
				coreMethods.LOGGER.severe(e.getMessage());
				Thread.currentThread().interrupt();
			}
		}

		else if (testType.equalsIgnoreCase("desktopBrowser"))

		{
			coreMethods.LOGGER.log(Level.INFO, "Web parameters invoked {0}", testType);
			try {
				driver = browserLaunch(ProjectConfig.BROWSER, ProjectConfig.APPLICATION_URL);
				return driver;
			} catch (InterruptedException e) {
				coreMethods.LOGGER.severe(e.getMessage());
				Thread.currentThread().interrupt();
			}
		}

		else if (testType.equalsIgnoreCase("cloudMobileBrowser"))

		{
			coreMethods.LOGGER.info("Please wait while the mobile device launches a BROWSER for testing.......");
			coreMethods.LOGGER.log(Level.INFO,"cloudMobileBrowser parameter invoked {0}", testType);
			driver = launchCloudMobileBrowser(ProjectConfig.CLOUD_DEVICE_OS, ProjectConfig.MOBILE_CLOUD_USERNAME,
					ProjectConfig.MOBILE_CLOUD_SECURITY_TOKEN, ProjectConfig.CLOUD_DEVICE_ID,
					ProjectConfig.MOBILE_BROWSER);
			driver = navigateToMobileURL(ProjectConfig.MOBILE_APP_URL);
			coreMethods.LOGGER.info("cloud mobile browser launched");
			return driver;
		}

		else if (testType.equalsIgnoreCase("cloudMobileApp"))

		{
			coreMethods.LOGGER.log(Level.INFO,"cloudMobileApp parameter invoked {0}", testType);
			coreMethods.LOGGER.info("Please wait while the system launches a mobileapp for testing.......");
			driver = launchMobileCloud(ProjectConfig.CLOUD_DEVICE_OS, ProjectConfig.MOBILE_CLOUD_USERNAME,
					ProjectConfig.MOBILE_CLOUD_SECURITY_TOKEN, ProjectConfig.CLOUD_DEVICE_ID, ProjectConfig.BUNDLE_ID);
			coreMethods.LOGGER.info("cloud mobile app launched");
			return driver;
			}
		
		else if (testType.equalsIgnoreCase("physicalMobileBrowser"))

		{
			
			coreMethods.LOGGER.log(Level.INFO,"physicalMobileBrowser parameter invoked {0}", testType);
			coreMethods.LOGGER.info("Please wait while the system launches a mobile browser in physical device for testing.......");
			driver = launchMobileBrowser(ProjectConfig.PHYSICAL_DEVICE_NAME, ProjectConfig.PHYSICAL_DEVICE_ID,
					ProjectConfig.PHYSICAL_DEVICE_OS,ProjectConfig.PHYSICAL_DEVICE_PLATFORM,ProjectConfig.MOBILE_BROWSER);			
			driver = navigateToMobileURL(ProjectConfig.MOBILE_APP_URL);
			coreMethods.LOGGER.info("mobile browser launched in physical device");
			return driver;
		}
		
		else if (testType.equalsIgnoreCase("physicaliOSMobileApp"))

		{
			coreMethods.LOGGER.log(Level.INFO,"physicaliOSmobileApp parameter invoked {0}", testType);
			coreMethods.LOGGER.info("Please wait while the system launches a mobileapp in physical iOS device for testing.......");
			driver = launchMobileiOSDevice(ProjectConfig.PHYSICAL_DEVICE_NAME,ProjectConfig.PHYSICAL_DEVICE_ID, 
					ProjectConfig.PHYSICAL_DEVICE_PLATFORM, ProjectConfig.APP_BUNDLE_ID);
			coreMethods.LOGGER.info("mobile app launched in physical iOS device");
			return driver;
			
			}
		
		else if (testType.equalsIgnoreCase("physicalAndroidMobileApp"))

		{
			coreMethods.LOGGER.log(Level.INFO,"physicalandroidmobileApp parameter invoked {0}", testType);
			coreMethods.LOGGER.info("Please wait while the system launches a mobileapp in physical android device for testing.......");
			driver = launchMobileAndroidDevice(ProjectConfig.PHYSICAL_DEVICE_NAME, ProjectConfig.PHYSICAL_DEVICE_ID, 
					ProjectConfig.PHYSICAL_DEVICE_PLATFORM, ProjectConfig.APP_PACKAGE, ProjectConfig.APP_Activity);
			
			coreMethods.LOGGER.info("mobile app launched in physical android device");
			return driver;
			
				}

		// otherwise to validate the parameters in the ProjectConfig file

		else {

			coreMethods.LOGGER.log(Level.INFO,
					"Using unknown test type = {0} in test case @Before AppLauncher parameter, please use values desktopBrowser, MOBILE_BROWSER, or mobileApp",
					testType);

		}
		return null;

	}
	
	/**
	 * Initializes the driver according to the settings passed in the ProjectConfig
	 * 
	 * @param browserName
	 * @param url
	 * @return WebDriver
	 * @throws InterruptedException
	 */

	public WebDriver browserLaunch(String browserName, String url) throws InterruptedException {
		
		if (browserName.equalsIgnoreCase("ff")) {
			System.setProperty("webdriver.gecko.driver", "dependencies\\geckodriver.exe");
			driver = new FirefoxDriver();
			coreMethods.LOGGER.info("Firefox Browser Launched");
			
		} else if (browserName.equalsIgnoreCase("ch")) {
			System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");

			if (Boolean.TRUE.equals(ProjectConfig.RUN_CHROME_HEADLESS)) {
				ChromeOptions chromeOptions = new ChromeOptions();
				chromeOptions.addArguments("--headless");
				driver = new ChromeDriver(chromeOptions);
				coreMethods.LOGGER.info("Triggered Headless Option");
			}
			else {
				driver = new ChromeDriver();
				coreMethods.LOGGER.info("Chrome Browser Launched");
			}

		} else if (browserName.equalsIgnoreCase("ie")) {
			// Setting up the options to ignore the zoom and security domains
			DesiredCapabilities cap = DesiredCapabilities.internetExplorer();
			cap.setCapability("unexpectedAlertBehaviour", "accept");
			cap.setCapability("ignoreProtectedModeSettings", true);
			cap.setCapability("disable-popup-blocking", true);
			cap.setCapability("enablePersistentHover", true);
			cap.setCapability("ignoreZoomSetting", true);
			cap.setCapability("javascriptEnabled", true);
			cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			cap.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			cap.setCapability("initialBrowserUrl", url);
			cap.setCapability("requirewindowfocus", true);
			InternetExplorerOptions options = new InternetExplorerOptions();
			options.merge(cap);

			// Setting the system property for where the IEDriver is located
			System.setProperty("webdriver.ie.driver", "dependencies\\IEDriverServer-1.0.0.exe");

			// Initialize browser
			driver = new InternetExplorerDriver(options);
			coreMethods.LOGGER.info("Internet Explorer Browser Launched");

		} else if (browserName.equalsIgnoreCase("op")) {
			String path = "dependenciess\\operadriver-1.0.0.exe";
			OperaOptions options = new OperaOptions();
			
			// Currently this is user specific and has not yet been made dynamic, please
			// update it to match your path before running in opera
			
			coreMethods.LOGGER.info("Please set the opera binary path");
			// something like options.setBinary("C:\\Users\\<insert user
			// ID>\\AppData\\Local\\Programs\\Opera\\<insert opera version
			// number>\\opera.exe")
			// something like options.setBinary("C:\\Users\\<insert user ID>\\AppData\\Local\\Programs\\Opera\\<insert opera version number>\\opera.exe")

			System.setProperty("webdriver.opera.driver", path);
			
			driver = new OperaDriver(options);
			coreMethods.LOGGER.info("Opera Browser Launched");
		}

		// Method to invoke both Edge Legacy and Edge Browser.
		else if (browserName.equalsIgnoreCase("ed")) {

			// exe files needs to be insync with the Edge Browser version and import it from
			// Microsoft portal
			String path = "dependencies\\msedgedriver64.exe"; // exe for Edge Browser
			// String path = "dependencies\\MicrosoftWebDriver.exe"; // exe for Edge Legacy
			// Browser

			coreMethods.LOGGER.info("Please set the edge binary path");
			System.setProperty("webdriver.edge.driver", path);
			
			driver = new EdgeDriver();
			coreMethods.LOGGER.info("Edge Browser Launched");
				}
		driver.manage().window().maximize();
		driver.navigate().to(url);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		return driver;
	}
	
	
	  
   /**
    * Login to Perfecto cloud and initiate the driver
	* To invoke mobile app execution on Perfecto devices either in parallel or sequential 
	* as per the parameters defined in testng.xml 
	* 
	*  @param os                os is String and it should be ios or android
	*  @param userName          Perfecto login userName and defined in ProjectConfig
	*  @param securityToken     Perfecto login securityToken and defined in ProjectConfig
	*  @param deviceID          mobile device id
	*  @param bundleID          mobile app ipa and apk file
	*  @return RemoteWebDriver 
	* @throws Exception
	 */ 
	 public  WebDriver launchMobileCloud(String os, String userName, String securityToken, String deviceId,
			String bundleId) {
		 
		 WebDriver driver = null;
		 
		 coreMethods.LOGGER.log(Level.INFO,"cloudMobileApp parameter invoked {0}", os +deviceId +bundleId);
		coreMethods.LOGGER.info("Please wait while the system launches a mobileapp for testing.......");
		
		try {

			String url;
			String host = "metlife.perfectomobile.com";
			url = "https://" + host + "/nexperience/perfectomobile/wd/hub";
			capabilities.setCapability("user", userName);
			capabilities.setCapability("securityToken", securityToken);

			WebDriver appiumDriver;
			Rotatable augmentedDriver;
			if (os.equalsIgnoreCase("android")) {
				capabilities.setCapability("platformName", "Android");
				capabilities.setCapability("deviceName", deviceId);
				capabilities.setCapability("appPackage", bundleId);
				appiumDriver = new AndroidDriver(new URL(url), capabilities);
				driver = appiumDriver;
				augmentedDriver = (Rotatable) new Augmenter().augment(appiumDriver);

				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

			} else if (os.equalsIgnoreCase("ios")) {
				capabilities.setCapability("platformName", "iOS");
				capabilities.setCapability("deviceName", deviceId);
				capabilities.setCapability("clearSystemFiles", true);
				capabilities.setCapability("wdaStartupRetryInterval", "1000");
				capabilities.setCapability("useNewWDA", true);
				capabilities.setCapability("waitForQuiescence", false);
				capabilities.setCapability("shouldUseSingletonTestManager", false);
				capabilities.setCapability("bundleId", bundleId);
				// driver = new RemoteWebDriver(new URL(url), capabilities); //AppiumDriver
				driver = new IOSDriver(new URL(url), capabilities);
				appiumDriver = driver;
				augmentedDriver = (Rotatable) new Augmenter().augment(appiumDriver);

				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

			}

				coreMethods.LOGGER.info("mobile device launch successful");
		} catch (Exception e) {
			coreMethods.LOGGER.info("mobile device launch failed");
			coreMethods.LOGGER.log(Level.SEVERE,"exception :{0}", e.getCause());
			coreMethods.LOGGER.log(Level.SEVERE,"exception :{0}", e.getMessage());
		}

		return driver;
	}


	 /**
	  * Login to Perfecto cloud and initiate the driver
	  * Invoke mobile BROWSER in Perfecto cloud
	  * 
	  * @param username
	  * @param securitytoken		Perfecto login userName and defined in ProjectConfig
	  * @param deviceid          Perfecto login securityToken and defined in ProjectConfig
	  * @param mobilebrowser     browserName is String and it should be safari
	  *                        for ios and chrome for android.
	  * @return RemoteWebdriver
	  * @throws InterruptedException
	  * @author Ravi VG
	  */
	 	
	  public  WebDriver launchCloudMobileBrowser(String os, String username, String securitytoken, String deviceid,
			String mobilebrowser)  {
		  WebDriver driver = null;
		  
		  coreMethods.LOGGER.info("launchCloudMobileBrowser method execution starts");
		  
		  try {
			String url;
			String host = "metlife.perfectomobile.com";
			url = "https://" + host + "/nexperience/perfectomobile/wd/hub";
			capabilities.setCapability("user", username);
			capabilities.setCapability("securityToken", securitytoken);
			
			capabilities.setBrowserName(mobilebrowser);

			if (os.equalsIgnoreCase("android")) {
				capabilities.setCapability("platformName", "Android");
				capabilities.setCapability("deviceName", deviceid);
				driver = new RemoteWebDriver(new URL(url), capabilities);
			} else if (os.equalsIgnoreCase("ios")) {
				capabilities.setCapability("platformName", "iOS");
				capabilities.setCapability("deviceName", deviceid);
				driver = new RemoteWebDriver(new URL(url), capabilities);

			}

			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			coreMethods.LOGGER.info("Logged in to perfecto mobile BROWSER");
		  } catch (Exception e) {
			coreMethods.LOGGER.info("mobile device launch failed");
			coreMethods.LOGGER.log(Level.SEVERE,"exception :{0}", e.getCause());
			coreMethods.LOGGER.log(Level.SEVERE,"exception :{0}", e.getMessage());
		  }

		  return driver;
	  }
	
	
	/**
	 * Initiate the driver to invoke browser in physical device leveraging appiumdesktop
	 * 
	 * @param devicename
	 * @param udid
	 * @param platformVersion
	 * @param browsername
	 * @return RemoteWebDriver
	 * @throws InterruptedException
	 * @author Ravi VG
	 */


	public WebDriver launchMobileBrowser(String devicename, String udid, String deviceos, String platformversion, String browsername)  {

		try {

			String url;
			String host = "127.0.0.1:4723";
			url = "http://" + host + "/wd/hub";


			if (deviceos.equalsIgnoreCase("android")) {
				capabilities.setCapability("platformName", "Android");
			 	capabilities.setCapability("udid", udid);
			 	capabilities.setCapability("platformVersion", platformversion);
				capabilities.setCapability("deviceName", devicename);
				capabilities.setCapability("browserName", browsername);
											
				capabilities.setCapability("automationName", "UiAutomator2");
				capabilities.setCapability("newCommandTimeout", 600);
				capabilities.setCapability("launchTimeout", 90000);
				capabilities.setCapability("fullReset", false);
				capabilities.setCapability("noReset", true);
				
				appiumDriver = new AndroidDriver(new URL(url), capabilities);
				driver = appiumDriver;
				augmentedDriver = (Rotatable) new Augmenter().augment(appiumDriver);

			} else if (deviceos.equalsIgnoreCase("ios")) {
				
				capabilities.setCapability("platformName", "iOS");
				capabilities.setCapability("udid", udid);
			 	capabilities.setCapability("platformVersion", platformversion);
				capabilities.setCapability("deviceName", devicename);
				capabilities.setCapability("browserName", browsername);
				
				capabilities.setCapability("automationName", "XCUITest");			
				capabilities.setCapability("clearSystemFiles", true);
				capabilities.setCapability("wdaStartupRetryInterval", "1000");
				capabilities.setCapability("useNewWDA", true);
				capabilities.setCapability("waitForQuiescence", false);
				capabilities.setCapability("shouldUseSingletonTestManager", false);
				
				
				driver = new IOSDriver(new URL(url), capabilities);
				driver = appiumDriver;
				augmentedDriver = (Rotatable) new Augmenter().augment(appiumDriver);

			}

				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				coreMethods.LOGGER.info("Logged In To Physical Device Mobile Browser:" +udid +deviceos +browsername);
			} 
		catch (Exception e) {
			coreMethods.LOGGER.info("Physical Mobile Browser Launch Failed");
			coreMethods.LOGGER.log(Level.SEVERE,"exception :{0}", e.getCause());
			coreMethods.LOGGER.log(Level.SEVERE,"exception :{0}", e.getMessage());
			coreMethods.logFailedStepToReport(coreMethods.test," Physical Mobile Browser Launch Failed" +e.getMessage());
		}

		return driver;
	}


  /**
	 * Initiate the driver to invoke app in physical Android device using appiumdesktop
	 * 
	 * @param devicename
	 * @param udid
	 * @param platformVersion
	 * @param apppackage
	 * @param appactivity  
	 * @return RemoteWebDriver
	 * @throws InterruptedException
	 * @author Ravi VG
	 */
	
	public WebDriver launchMobileAndroidDevice(String devicename, String udid, String platformversion, String apppackage, 
		String appactivity) {

		try {

			String url;
			String host = "127.0.0.1:4723";
			url = "http://" + host + "/wd/hub";
		             
			/* Include below capability if the app package needs to be taken from local drive
			String apppath = C//Users/rvadlamudi1/APK;    
			File app = new File(apppath);
		 	capabilities.setCapability("app", app.getAbsolutePath());*/
		
		 	capabilities.setCapability("platformName", "Android");
		 	capabilities.setCapability("udid", udid);
		 	capabilities.setCapability("platformVersion", platformversion);
			capabilities.setCapability("deviceName", devicename);
			capabilities.setCapability("appPackage", apppackage);
			capabilities.setCapability("appActivity", appactivity);

			capabilities.setCapability("automationName", "UiAutomator2");
			capabilities.setCapability("newCommandTimeout", 600);
			capabilities.setCapability("launchTimeout", 90000);
			capabilities.setCapability("fullReset", false);
			capabilities.setCapability("noReset", true);
			
			appiumDriver = new AndroidDriver(new URL(url), capabilities);
			driver = appiumDriver;
			augmentedDriver = (Rotatable) new Augmenter().augment(appiumDriver);

			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
			coreMethods.LOGGER.info("Physical Mobile Android Device Launch Successful");
			
			
			} 
		catch (Exception e) {
		
		   coreMethods.LOGGER.info("Physical Mobile Android Device Launch Failed");
		   coreMethods.LOGGER.log(Level.SEVERE,"exception :{0}", e.getCause());
		   coreMethods.LOGGER.log(Level.SEVERE,"exception :{0}", e.getMessage());
		   coreMethods.logFailedStepToReport(coreMethods.test," Physical Mobile Android Device Launch Failed" +e.getMessage());
		  }

		return driver;
		}
	
	
	/**
	 * Initiate the driver to invoke app in physical iOS device using appiumdesktop
	 * 
	 * @param devicename
	 * @param udid
	 * @param platformVersion
	 * @param bundleid
	 * @return RemoteWebDriver
	 * @throws InterruptedException
	 * @author Ravi VG
	 */
	public WebDriver launchMobileiOSDevice(String devicename, String udid, String platformversion, String bundleid) {

		try {

			String url;
			String host = "127.0.0.1:4723";
			url = "http://" + host + "/wd/hub";
		             
			/* Include below capability if app package needs to be taken from local path
			String apppath = C//Users/rvadlamudi1/IPA;    
			File app = new File(apppath);
		 	capabilities.setCapability("app", app.getAbsolutePath());*/
		
		 	capabilities.setCapability("platformName", "iOS");
		 	capabilities.setCapability("udid", udid);
		 	capabilities.setCapability("platformVersion", platformversion);
			capabilities.setCapability("deviceName", devicename);
			capabilities.setCapability("bundleId", bundleid);
			
			capabilities.setCapability("automationName", "XCUITest");			
			capabilities.setCapability("clearSystemFiles", true);
			capabilities.setCapability("wdaStartupRetryInterval", "1000");
			capabilities.setCapability("useNewWDA", true);
			capabilities.setCapability("waitForQuiescence", false);
			capabilities.setCapability("shouldUseSingletonTestManager", false);
			
			
			driver = new IOSDriver(new URL(url), capabilities);
			driver = appiumDriver;
			augmentedDriver = (Rotatable) new Augmenter().augment(appiumDriver);

			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
			coreMethods.LOGGER.info("Physical Mobile iOS Device Launch Successful");
			
			
		} 
		catch (Exception e) {
		
			coreMethods.LOGGER.info("Physical Mobile iOS Device Launch Failed");
			coreMethods.LOGGER.log(Level.SEVERE,"exception :{0}", e.getCause());
			coreMethods.LOGGER.log(Level.SEVERE,"exception :{0}", e.getMessage());
			coreMethods.logFailedStepToReport(coreMethods.test," Physical Mobile iOS Device Launch Failed" +e.getMessage());
		}

	return driver;
	
	}



	/**
	 * Pass URL to the Invoke mobile BROWSER in perfecto cloud
	 * 
	 * @param mobileURL           browser invoked in mobile device. safari
	 *                            for ios and chrome for android.
	 * @return RemoteWebdriver
	 * @throws InterruptedException
	 * @author Ravi VG
	 */	

	public  WebDriver navigateToMobileURL(String mobileUrl){
		driver.manage().window().maximize();
		driver.navigate().to(mobileUrl);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		coreMethods.LOGGER.info("mobile URL launched sucessful");
		return driver;
	}
	
	
	
	/**
	 * Login to perfecto cloud and initiate the driver with userid and password
	 * 
	 * @param mobile   OS
	 * @param username
	 * @param password
	 * @param deviceid
	 * @return RemoteWebDriver
	 * @throws InterruptedException
	 * @author Ravi VG
	 */

	public  WebDriver launchMobileCloudPassword(String os, String username, String password, String deviceid,
			String bundleid) {

		try {

			String url;
			String host = "metlife.perfectomobile.com";
			url = "https://" + host + "/nexperience/perfectomobile/wd/hub";
			capabilities.setCapability("user", username);
			capabilities.setCapability("password", password);

			if (os.equalsIgnoreCase("android")) {
				capabilities.setCapability("platformName", "Android");
				capabilities.setCapability("deviceName", deviceid);
				capabilities.setCapability("appPackage", bundleid);
				appiumDriver = new AndroidDriver(new URL(url), capabilities);
				driver = appiumDriver;
				augmentedDriver = (Rotatable) new Augmenter().augment(appiumDriver);

				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

			} else if (os.equalsIgnoreCase("ios")) {
				capabilities.setCapability("platformName", "iOS");
				capabilities.setCapability("deviceName", deviceid);
				capabilities.setCapability("clearSystemFiles", true);
				capabilities.setCapability("wdaStartupRetryInterval", "1000");
				capabilities.setCapability("useNewWDA", true);
				capabilities.setCapability("waitForQuiescence", false);
				capabilities.setCapability("shouldUseSingletonTestManager", false);
				capabilities.setCapability("bundleId", bundleid);
				// driver = new RemoteWebDriver(new URL(url), capabilities); //AppiumDriver
				driver = new IOSDriver(new URL(url), capabilities);
				driver = appiumDriver;
				augmentedDriver = (Rotatable) new Augmenter().augment(appiumDriver);

				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

			}

				coreMethods.LOGGER.info("mobile device launch sucessful");
		} catch (Exception e) {
			coreMethods.LOGGER.info("mobile device launch failed");
			coreMethods.LOGGER.log(Level.SEVERE,"exception :{0}", e.getCause());
			coreMethods.LOGGER.log(Level.SEVERE,"exception :{0}", e.getMessage());
		}

		return driver;
	}

	/**
	 * Invoke mobile BROWSER in perfecto cloud with userid and password
	 * 
	 * @param username
	 * @param password
	 * @param deviceid
	 * @param mobilebrowser
	 * @return RemoteWebdriver
	 * @throws InterruptedException
	 * @author Ravi VG
	 */

	public  WebDriver launchMobileBrowserPassword(String os, String username, String password, String deviceid,
			String mobilebrowser)  {

		try {

			String url;
			String host = "metlife.perfectomobile.com";
			url = "https://" + host + "/nexperience/perfectomobile/wd/hub";
			capabilities.setCapability("user", username);
			capabilities.setCapability("password", password);
			capabilities.setBrowserName(mobilebrowser);

			if (os.equalsIgnoreCase("android")) {
				capabilities.setCapability("platformName", "Android");
				capabilities.setCapability("deviceName", deviceid);
				driver = new RemoteWebDriver(new URL(url), capabilities);
			} else if (os.equalsIgnoreCase("ios")) {
				capabilities.setCapability("platformName", "iOS");
				capabilities.setCapability("deviceName", deviceid);
				driver = new RemoteWebDriver(new URL(url), capabilities);

			}

			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			coreMethods.LOGGER.info("Logged in to perfecto mobile BROWSER");
		} catch (Exception e) {
			coreMethods.LOGGER.info("mobile device launch failed");
			coreMethods.LOGGER.log(Level.SEVERE,"exception :{0}", e.getCause());
			coreMethods.LOGGER.log(Level.SEVERE,"exception :{0}", e.getMessage());
		}

		return driver;
	}
	
	
	/**
	 * Login to perfecto cloud and initiate the driver-old
	 * 
	 * @param mobile   OS
	 * @param username
	 * @param securitytoken
	 * @param deviceid
	 * @return RemoteWebDriver
	 * @throws InterruptedException
	 * @author Ravi VG
	 */

	public  WebDriver launchMobileCloudold(String os, String username, String securitytoken, String deviceid,
			String bundleid) {

		WebDriver driver = null;
		try {

			String url;
			String host = "metlife.perfectomobile.com";
			url = "https://" + host + "/nexperience/perfectomobile/wd/hub";
			capabilities.setCapability("user", username);
			capabilities.setCapability("securityToken", securitytoken);

			WebDriver appiumDriver;
			Rotatable augmentedDriver;
			if (os.equalsIgnoreCase("android")) {
				capabilities.setCapability("platformName", "Android");
				capabilities.setCapability("deviceName", deviceid);
				capabilities.setCapability("appPackage", bundleid);
				appiumDriver = new AndroidDriver(new URL(url), capabilities);
				driver = appiumDriver;
				augmentedDriver = (Rotatable) new Augmenter().augment(appiumDriver);

				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

			} else if (os.equalsIgnoreCase("ios")) {
				capabilities.setCapability("platformName", "iOS");
				capabilities.setCapability("deviceName", deviceid);
				capabilities.setCapability("clearSystemFiles", true);
				capabilities.setCapability("wdaStartupRetryInterval", "1000");
				capabilities.setCapability("useNewWDA", true);
				capabilities.setCapability("waitForQuiescence", false);
				capabilities.setCapability("shouldUseSingletonTestManager", false);
				capabilities.setCapability("bundleId", bundleid);
				// driver = new RemoteWebDriver(new URL(url), capabilities); //AppiumDriver
				driver = new IOSDriver(new URL(url), capabilities);
				appiumDriver = driver;
				augmentedDriver = (Rotatable) new Augmenter().augment(appiumDriver);

				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

			}

				coreMethods.LOGGER.info("mobile device launch sucessful");
		} catch (Exception e) {
			coreMethods.LOGGER.info("mobile device launch failed");
			coreMethods.LOGGER.log(Level.SEVERE,"exception :{0}", e.getCause());
			coreMethods.LOGGER.log(Level.SEVERE,"exception :{0}", e.getMessage());
		}

		return driver;
	}
	
	

	public void quitDriver() {
		driver.quit();
	}
	
	public WebDriver getDriver() {
		return driver;
	}
	
	public Map<String,String> getTestData() {
		return testData;
	}
	
	public AppiumDriver getAppiumDriver() {
		return appiumDriver;
	}

	public Rotatable getAugmentedDriver() {
		return augmentedDriver;
	}
	/**
	 * This takes a skip message and outputs it to the report
	 * 
	 * @param test    - test parameter to be used by extent report
	 * @param skipMsg - String message to be displayed for skip
	 * @throws Exception
	 */
	public void reportingSkip(ExtentTest test, String skipMsg) {
		test.log(LogStatus.SKIP, skipMsg);
		coreMethods.report.endTest(test);
		coreMethods.report.flush();
	}
	
	public Map<String, String> readExcel(String testname) {
		// Set-up test variable for run execution report
		Map<String, String> testData = coreMethods.getData(Constants.INPUT_XLS, "mobiledata", testname);
		
		coreMethods.LOGGER.info("test data read" +testname);
		
		//coreMethods.test = coreMethods.report.startTest("Test-" + ": TC " + testname, testname);//included report details as Generic class constructor

		// Check to see if this test is skipped
		if (testData.get("Skip Test").equalsIgnoreCase("Y")) {
			reportingSkip(coreMethods.test, "This test was skipped");
			throw new SkipException("Skipped Test");
		}
		coreMethods.LOGGER.info("Data has been read");
		return testData;
	}
}
