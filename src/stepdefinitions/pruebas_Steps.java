package stepdefinitions;

import org.openqa.selenium.support.PageFactory;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import main.java.com.corecomponents.Constants;
import main.java.com.corecomponents.GenericClass;
import main.java.com.pageobjects.CDILoginPageObjects;
import main.java.com.businessfunctions.CDILogin;
import main.java.com.pageobjects.CDIProperties;
import main.java.com.projectconfig.ProjectConfig;
import main.java.com.testcasedriver.TestCaseDriver;
import cucumber.api.java.en.Then;

/* Cucumber uses its annotations and all the steps are embedded in those annotations (given, when, then, and).
 Each phrase starts with “^” so that cucumber understands the start of the step. Similarly, each step ends with “$”.*/

public class pruebas_Steps {
	
   TestCaseDriver tcdriver;
	GenericClass coreMethods = new GenericClass("Reporte_AltaCliente");
	
	public CDILogin loginobj;

	//public CDILogin loginobj = PageFactory.initElements(tcdriver.getDriver(), CDILogin.class);
	
	

	@Before
	 public void entraBefore(Scenario scenario)
    {
		System.out.println("Entra Before.");
		coreMethods.test = coreMethods.report.startTest("Test-: TC " + "Alta Cliente Persona Fisica" );
	
    }
	
	@Given("^Entra given$")
	public void entraGivenn() throws Throwable {
		System.out.println("Entra Given.");
       tcdriver = new TestCaseDriver("desktopBrowser");
		
//		driver = browserLaunch(ProjectConfig.BROWSER, main.java.com.projectconfig.ProjectConfig.APPLICATION_URL);
		coreMethods.logPassedStepToReportWithScreenshot(tcdriver.getDriver(), coreMethods.test, "conexion con exito");
		
	}
	
	

	@When("^Entra when$")
	public void entraWhen() throws Throwable {
		System.out.println("Entra When.");
				//introduce usuario y password e ingresa
		
		loginobj = PageFactory.initElements(tcdriver.getDriver(), CDILogin.class);
		loginobj.login(tcdriver);
		
		
		if (coreMethods.isElementPresent(tcdriver.getDriver(),CDIProperties.CLDM_LINK_XPATH, Constants.XPATH)) {
			System.out.println("Sí encuentra la etiqueta Cerrar Sesión");
			coreMethods.logPassedStepToReportWithScreenshot(tcdriver.getDriver(), coreMethods.test, "logueo correcto");
		}
		else {
			System.out.println("No encuentra la etiqueta Cerrar Sesión");
			coreMethods.logFailedStepToReportWithScreenshot(tcdriver.getDriver(),coreMethods.test,"fallo ");
		}
		Thread.sleep(5000);
		System.out.println("Ingresó usuario y contraseña");
	}

	@Then("^Entra then$")
	public void entraThen() throws Throwable {
		System.out.println("Entra Then.");
		
	}

	
	@After
	public void entraAfter() throws Exception {
		System.out.println("Entra After.");
	}

}
