package uniovi.es;

import java.util.regex.Pattern;

import javax.validation.constraints.AssertTrue;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class CreateAndVisualizeIncident {
  private WebDriver driver;
  private String baseUrl;
  private String URL = "http://localhost:8080";
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "https://www.katalon.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testUntitledTestCase() throws Exception {
    driver.navigate().to(URL);
    driver.findElement(By.name("login")).click();
    driver.findElement(By.name("login")).clear();
    driver.findElement(By.name("login")).sendKeys("11111111A");
    driver.findElement(By.name("password")).click();
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys("123456");
    driver.findElement(By.name("kind")).click();
    driver.findElement(By.name("kind")).clear();
    driver.findElement(By.name("kind")).sendKeys("1");
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    driver.findElement(By.id("key")).click();
    driver.findElement(By.id("key")).clear();
    driver.findElement(By.id("key")).sendKeys("custom1");
    driver.findElement(By.id("value")).click();
    driver.findElement(By.id("value")).clear();
    driver.findElement(By.id("value")).sendKeys("value");
    driver.findElement(By.id("title")).click();
    driver.findElement(By.id("title")).clear();
    driver.findElement(By.id("title")).sendKeys("test");
    driver.findElement(By.id("message")).click();
    driver.findElement(By.id("message")).clear();
    driver.findElement(By.id("message")).sendKeys("test");
    driver.findElement(By.id("aditionalInfo")).click();
    driver.findElement(By.id("aditionalInfo")).clear();
    driver.findElement(By.id("aditionalInfo")).sendKeys("test");
    driver.findElement(By.id("tags")).click();
    driver.findElement(By.id("tags")).clear();
    driver.findElement(By.id("tags")).sendKeys("tag1,tag2");
    driver.findElement(By.xpath("//form[@action='/createIncidence']")).click();
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    
    assertTrue(driver.findElements(By.xpath("//*[contains(text(),'test')]")).size()>0);
    
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
