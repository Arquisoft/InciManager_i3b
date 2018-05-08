package uniovi.es.steps;

import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import uniovi.es.steps.utils.CucumberUtils;

public class SeeIncidenceListSteps {
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

	  @When(value = "the user log in")
	  public void testUntitledTestCasete() throws Exception {
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
	  
	  @Then(value = "click the option of review my incidence")
	  public void testUntitledTestCase2() throws Exception 
	  {
		  utils.textoPresentePagina(driver, "Create new incident");
		  driver.findElement(By.linkText("Review my incidents")).click();
	  } 
	  
	  @Then(value = "see the list of incidences")
	  public void testUntitledTestCase3() throws Exception 
	  {
		  utils.textoPresentePagina(driver, "title");
		  utils.textoPresentePagina(driver, "Comments(from the operators)");		  
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
