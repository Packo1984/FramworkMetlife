package main.java.com.projectconfig;

public class ProjectConfig {
	
	/**
	 *  Desktop Execution Parameters
	 *  @param Browser 			   - To invoke desktop browser ch=chrome,ff=firefox,ed=Edge,
	 *                   			 ie=Internet Explorer,op=Opera.This parameter needs to be
	 *                   			 setup in testng.xml for parallel execution  
	 *  @param RUN_CHROME_HEADLESS - To invoke headless browser
	 *  @param APPLICATION_URL     - Application URL.This parameter needs to be
	 *                   			 setup in testng.xml for parallel execution
	 */
	
	 public static final String BROWSER = "ch";
	 public static final Boolean RUN_CHROME_HEADLESS = false;
     
	 // DMA Application
	 public static final String APPLICATION_URL = "http://mxsrvwasmui1t.alico.corp/ClienteUnico/login.jsp";

	/**
	 *  Environment Configuration   
	 *  @param  MOBILE_CLOUD_USERNAME     - Perfecto login userName
	 *  @Param MOBILE_CLOUD_SECURITY_TOKEN - Perfecto login securityToken 
	 */
	
	public static final String MOBILE_CLOUD_USERNAME = "i@metlife.com";
	public static final String MOBILE_CLOUD_SECURITY_TOKEN = "eyJhbGciOiJVRxrEG6uAhwpM_iKhI0";
	  
	/**
	 *  Cloud Mobile Device Execution Parameters. Below parameters needs to be
	 *  setup in respective testng.xml for parallel execution.
	 *  
	 *  @param CLOUD_DEVICE_OS    - mobile device OS. ios, android, windows
	 *  @param CLOUD_DEVICE_ID    - mobile device id 
	 *  @param BUNDLE_ID 		  -  mobile app ipa and apk file 
	 *  @param MOBILE_BROWSER     - browser invoked in mobile device. safari
	 *                              for ios and chrome for android.
	 *  @param MOBILE_APP_URL     - mobile application url
	 *  
	 */
	
     public static final String CLOUD_DEVICE_OS = "ios"; 
   	 public static String BUNDLE_ID = "com.amazon.Amazon";
    //  public static final String BUNDLE_ID = "com.amazon.mShop.android.shopping";// android
	
   	 public static final String CLOUD_DEVICE_ID ="00008030-0001302A2230802E"; //iOS device
	// public static final String CLOUD_DEVICE_ID = "444F51334D483098";// android device
	
	 public static final String MOBILE_BROWSER = "safari"; 
 	 public static final String MOBILE_APP_URL = "https://int.employer.servicing.online.metlife.com";
   	 
 	
 	 /**
 	 *  Physical Mobile Device Execution Parameters.
 	 *  
 	 *  @param PHYSICAL_DEVICE_OS       - mobile device OS. ios, android, windows
 	 *  @param PHYSICAL_DEVICE_PLATFORM - mobile device platform version eg: 8.1.1 
 	 *  @param PHYSICAL_DEVICE_ID       - mobile device UDID 
 	 *  @param PHYSICAL_DEVICE_NAME     - mobile device name
 	 *  @param APP_BUNDLE_ID 		    - ios app ipa file name
 	 *  @param APP_PACKAGE              - android apk file name
 	 *  @Param APP_Activity             - android app activity name
 	 *  @param APP_PACKAGE_PATH         - app package path
 	 *    
 	 */
 	 
 	 
 	 
 	 public static final String PHYSICAL_DEVICE_OS = "android"; 
 	 public static final String PHYSICAL_DEVICE_PLATFORM = "8.1.1"; 
 	 public static final String PHYSICAL_DEVICE_ID = "444F51334D483098"; 
 	 public static final String PHYSICAL_DEVICE_NAME = "iPhone";	
	
 	 // Mobile App specifications
 	 public static final String APP_BUNDLE_ID = "com.amazon.mShop.android.shopping";
 	 public static final String APP_PACKAGE_PATH = "C:\\QE Framework\\Demo";
 	 public static final String APP_PACKAGE = "com.amazon.mShop.android.shopping"; 
 	 public static final String APP_Activity = "com.amazon.mShop.android.shopping.MainActivity";
	
	
 	 
	/**
	  *  Deprecated parameters and additional details
	  *  
	  *  
	  */
	  
	public static final String MOBILE_CLOUD_PASSWORD = "eyJhbGciOiJIUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICIzZTVjN2Y1OC00ZjEwLTQxODktYjg3OS0yMzNkOTU0ZjY5MWMifQ.eyJqdGkiOiIwYmJhOGQzYS04YmUxLTRjNzktYjVmNS0zNTQwY2IxZDhkNzgiLCJleHAiOjAsIm5iZiI6MCwiaWF0IjoxNTg2ODkyNDA3LCJpc3MiOiJodHRwczovL2F1dGgucGVyZmVjdG9tb2JpbGUuY29tL2F1dGgvcmVhbG1zL21ldGxpZmUtcGVyZmVjdG9tb2JpbGUtY29tIiwiYXVkIjoiaHR0cHM6Ly9hdXRoLnBlcmZlY3RvbW9iaWxlLmNvbS9hdXRoL3JlYWxtcy9tZXRsaWZlLXBlcmZlY3RvbW9iaWxlLWNvbSIsInN1YiI6IjA4NTY4MjE0LWU0YjctNGIxZS1iMWUxLWVlZWFlYmNmYWEyNCIsInR5cCI6Ik9mZmxpbmUiLCJhenAiOiJvZmZsaW5lLXRva2VuLWdlbmVyYXRvciIsIm5vbmNlIjoiZmY1ZmVkOTctYWYwYy00NjVkLTg0NWEtMTU2NDg0MDA2ZjE5IiwiYXV0aF90aW1lIjowLCJzZXNzaW9uX3N0YXRlIjoiZjFlODMwN2QtMmQxMi00NmM1LWE3ZjgtZjA5NWZmM2Y2YWVlIiwicmVhbG1fYWNjZXNzIjp7InJvbGVzIjpbIm9mZmxpbmVfYWNjZXNzIl19LCJyZXNvdXJjZV9hY2Nlc3MiOnsiYWNjb3VudCI6eyJyb2xlcyI6WyJtYW5hZ2UtYWNjb3VudCIsIm1hbmFnZS1hY2NvdW50LWxpbmtzIiwidmlldy1wcm9maWxlIl19fSwic2NvcGUiOiJvcGVuaWQgb2ZmbGluZV9hY2Nlc3MifQ.xNo-z_k72wNYHU6uOjRbB0yCVRxrEG6uAhwpM_iKhI0";
	
	 
	
	// CDI Application
	//   public static String APPLICATION_URL = "https://qa2.cdi.metlife.com/";
	public static final String CDI_USERNAME = "N3409378";
	public static final String CDI_ENCRYPTED_PASSWORD = "MexPass01";
	
	// public static String APPLICATION_URL = "https://qamig.cdi.metlife.com";
	// USD Web application
	 //  public static String APPLICATION_URL = "https://int.employer.servicing.online.metlife.com/dashboard/home";
	
	
	
	private ProjectConfig() {
		    throw new IllegalStateException("Utility class");
		  }
}
