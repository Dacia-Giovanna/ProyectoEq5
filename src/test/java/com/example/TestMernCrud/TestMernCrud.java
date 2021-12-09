package com.example.TestMernCrud;

import java.util.regex.Pattern;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.apache.commons.io.FileUtils;
import java.io.File;
import io.github.bonigarcia.wdm.WebDriverManager;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestMernCrud {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  JavascriptExecutor js;
  @Before
  public void setUp() throws Exception {
	WebDriverManager.chromedriver().setup();
    driver = new ChromeDriver();
    baseUrl = "https://www.google.com/";
    driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    js = (JavascriptExecutor) driver;
  }

  @Test
  public void testAgregar() throws Exception {
    driver.get("https://mern-crud.herokuapp.com");
    pause(5000);
    driver.findElement(By.xpath("//div[@id='root']/div/div[2]/button")).click();
    pause(5000);
    driver.findElement(By.name("name")).click();
    driver.findElement(By.name("name")).clear();
    driver.findElement(By.name("name")).sendKeys("Oskar Sierra");
    driver.findElement(By.name("email")).click();
    driver.findElement(By.name("email")).clear();
    driver.findElement(By.name("email")).sendKeys("oskar@gmail.com");
    driver.findElement(By.name("age")).click();
    driver.findElement(By.name("age")).clear();
    driver.findElement(By.name("age")).sendKeys("31");
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Gender'])[2]/following::div[1]")).click();
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Male'])[1]/following::span[1]")).click();
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Woah!'])[1]/following::button[1]")).click();
    driver.findElement(By.xpath("//i")).click();
    pause(7000);
    driver.findElement(By.xpath("//div[@id='root']/div/div[2]/table/tbody/tr/td")).click();
    try {
      assertEquals("Dacia", driver.findElement(By.xpath("//div[@id='root']/div/div[2]/table/tbody/tr/td")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
  }
  
  
  @Test
  public void testCambiar() throws Exception {
    driver.get("https://mern-crud.herokuapp.com");
    pause(7000);
    driver.findElement(By.xpath("//div[@id='root']/div/div[2]/table/tbody/tr/td[5]/button")).click();
    pause(5000);
    driver.findElement(By.name("email")).clear();
    pause(5000);
    driver.findElement(By.name("email")).sendKeys("correo.sierra@gmail.com");
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Woah!'])[1]/following::button[1]")).click();
    driver.findElement(By.xpath("//i")).click();
    pause(7000);
    driver.findElement(By.xpath("//div[@id='root']/div/div[2]/table/tbody/tr/td")).click();
    try {
      assertEquals("correo.sierra@gmail.com", driver.findElement(By.xpath("//div[@id='root']/div/div[2]/table/tbody/tr/td[2]")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
  }
  
  
  @Test
  public void testEliminar() throws Exception {
    driver.get("https://mern-crud.herokuapp.com");
    pause(7000);
    driver.findElement(By.xpath("//div[@id='root']/div/div[2]/table/tbody/tr/td[5]/button[2]")).click();
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Oskar Sierra'])[2]/following::button[1]")).click();
    
    /*List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'Oskar Sierra')]"));
    Assert.assertTrue("Text not found!", list.size() > 0);*/
    
    String bodyText = driver.findElement(By.tagName("Tbody")).getText();
    Assert.assertThat("Oskar Sierra", not(bodyText));
    
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
  
  private void pause(long mils) {
	  try {
		  Thread.sleep(mils);
	  }catch(Exception e){
		  e.printStackTrace();
	  }
  }
  
}
