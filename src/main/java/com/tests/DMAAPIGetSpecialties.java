package main.java.com.tests;

import org.testng.annotations.Test;
import io.restassured.internal.support.Prettifier;
import io.restassured.response.ResponseOptions;
import main.java.com.businessfunctions.GetSiteMinderCookie;
import main.java.com.corecomponents.GenericClass;

import static io.restassured.RestAssured.*;
import static io.restassured.config.RestAssuredConfig.*;
import static io.restassured.config.RedirectConfig.*;
import static org.hamcrest.Matchers.*;

import java.util.logging.Level;

public class DMAAPIGetSpecialties{

	@Test(groups = "DMA_API")
	public void getSpecialtiesAndVerifyCodes() {
		GenericClass coreMethods = new GenericClass("getSpecialtiesStatusCode");
		
		coreMethods.test = coreMethods.report.startTest("Test-" + ": TC " + "getSpecialtiesStatusCode");
		
		try {
		String cookie = GetSiteMinderCookie.getSiteMinderCookie(
				"https://ssologin-qa.metlife.com/siteminderagent/forms/ldap/login.fcc?TYPE=100663297&REALMOID=06-00096e47-c842-1b9f-b7de-676bac18f0d1&GUID=&SMAUTHREASON=0&METHOD=GET&SMAGENTNAME=si76Rg5ZddZWaQ8u0zKsbq5qaoBsrK3YGu1V6b2WhCjh2t7GZkZA9OKZfv2xZaRi&TARGET=$SM$https%3a%2f%2fqacon%2edma%2emetlife%2ecom%2fadm%2fgetspecialties%2ehtm"
				,"DMATST01", coreMethods.decryptText("bWV0bGlmZTE="));
		ResponseOptions<?> response = 
				
		given()
		
		.config(newConfig().redirect(redirectConfig().followRedirects(false)))
		.cookie("SMSESSION",cookie)
		
		.when()
		
		.get("https://qacon.dma.metlife.com/adm/getspecialties.htm")
		
		.then()
		
		.body(
			"msg.msgTxt", equalTo("Data retrieved successfully."), "msg.msgCode", equalTo("000"),
			"specialties[0].code", equalTo(0) , "specialties[0].description",equalTo("NON-LICENSED DENTIST"),
			"specialties[1].code", equalTo(10) , "specialties[1].description",equalTo("ORAL SURGERY"),
			"specialties[2].code", equalTo(15) , "specialties[2].description",equalTo("ENDODONTIST"),
			"specialties[3].code", equalTo(20) , "specialties[3].description",equalTo("ORTHODONTIST"),
			"specialties[4].code", equalTo(30) , "specialties[4].description",equalTo("PEDIATRIC"),
			"specialties[5].code", equalTo(40) , "specialties[5].description",equalTo("PERIODONTIST"),
			"specialties[6].code", equalTo(50) , "specialties[6].description",equalTo("PROSTHODONTIST"),
			"specialties[7].code", equalTo(60) , "specialties[7].description",equalTo("ORAL PATHOLOGIST"),
			"specialties[8].code", equalTo(70) , "specialties[8].description",equalTo("PUBLIC HEALTH"),
			"specialties[9].code", equalTo(80) , "specialties[9].description",equalTo("GENERAL"),
			"specialties[10].code", equalTo(400) , "specialties[10].description",equalTo("CRNA"),
			"specialties[11].code", equalTo(600) , "specialties[11].description",equalTo("ANESTHESIOLOGIST"),
			"specialties[12].code", equalTo(700) , "specialties[12].description",equalTo("HYGIENIST"),
			"specialties[13].code", equalTo(800) , "specialties[13].description",equalTo("ORAL & MAXILLO RADIOLOGY")
		)
		
		.extract().response();
		String prettyResponse = new Prettifier().getPrettifiedBodyIfPossible(response,response.body());
		coreMethods.LOGGER.log(Level.FINE,prettyResponse);
		coreMethods.logPassedStepToReport(coreMethods.test, prettyResponse);
		}
		catch (Exception e) {
			coreMethods.LOGGER.severe(e.getMessage());
		}
		

	}

}
