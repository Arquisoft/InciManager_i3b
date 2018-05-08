package uniovi.es.steps;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import uniovi.es.steps.utils.CucumberUtils;


public class AddCustomFieldsSteps {

	  private CucumberUtils utils = new CucumberUtils();
	  private WebDriver driver;
	  private String baseUrl;
	  private boolean acceptNextAlert = true;
	  private StringBuffer verificationErrors = new StringBuffer();

	  @Before
	  public void setUp() throws Exception {
	    driver = new FirefoxDriver();
	    baseUrl = "https://www.katalon.com/";
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  }

	  @When(value = "the user log in session")
	  public void testUntitledTestCasee() throws Exception {
		driver.get("http://localhost:8080/logIn");
		driver.findElement(By.name("login")).clear();
		driver.findElement(By.name("login")).sendKeys("");
		driver.findElement(By.name("login")).click();
		driver.findElement(By.name("login")).clear();
		driver.findElement(By.name("login")).sendKeys("11111111A");
		driver.findElement(By.name("password")).clear();
		driver.findElement(By.name("password")).sendKeys("123456");
		driver.findElement(By.name("kind")).clear();
		driver.findElement(By.name("kind")).sendKeys("1");
		driver.findElement(By.xpath("//button[@type='submit']")).click();  
	  }
	  
	  @Then(value = "fill the form of a field")
	  public void testUntitledTestCase2() throws Exception 
	  {
		//esta en la pagina de crearla
		  utils.textoPresentePagina(driver, "Create new incident");
		  	
		  driver.findElement(By.id("key")).click();
		  driver.findElement(By.id("key")).clear();
		  driver.findElement(By.id("key")).sendKeys("fieldname");
		  driver.findElement(By.id("value")).clear();
		  driver.findElement(By.id("value")).sendKeys("valueName");
	  }

	  @Then(value = "click Add field")
	  public void testUntitledTestCase3() throws Exception 
	  {
		  driver.findElement(By.id("addButton")).click();
	  }
	  
	  @Then(value = "see the info")
	  public void testUntitledTestCase4() throws Exception 
	  {
		  //sale el nuevo campo
		  utils.textoPresentePagina(driver, "fieldname");
		  utils.textoPresentePagina(driver, "valueName");
	  }
	  
	  @After
	  public void tearDown() throws Exception {
	    driver.quit();
	    String verificationErrorString = verificationErrors.toString();
	    if (!"".equals(verificationErrorString)) {
	      fail(verificationErrorString);
	    }
	  }

}
