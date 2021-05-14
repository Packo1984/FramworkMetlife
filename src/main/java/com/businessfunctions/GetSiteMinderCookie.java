package main.java.com.businessfunctions;

import org.testng.annotations.Test;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static io.restassured.config.RedirectConfig.redirectConfig;
import static io.restassured.config.RestAssuredConfig.newConfig;

public class GetSiteMinderCookie {

	private GetSiteMinderCookie() {
	    throw new IllegalStateException("Utility class");
	  }
	
	@Test(groups = "DMA_API")
	public static String getSiteMinderCookie(String siteMinderURL, String username, String password) {
		Response response = 
				given()
				
				.config(newConfig().redirect(redirectConfig().followRedirects(false)))
				.contentType("application/x-www-form-urlencoded; charset=UTF-8")
				.formParam("USER",username)
				.formParam("PASSWORD", password)
				
				.when()
				
				.post(siteMinderURL);
			
		return response.getCookie("SMSESSION");

	}
}
