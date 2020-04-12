package com.w2a.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.w2a.utilities.ExcelReader;
import com.w2a.utilities.ExtentManager;
import com.w2a.utilities.TestUtil;

public class TestBase {

	public static WebDriver driver;
	public static Properties config = new Properties();
	public static Properties OR  = new Properties();
	public static FileInputStream fis;
	public static Logger log = Logger.getLogger("devpinoyLogger");
	public static ExcelReader excel = new ExcelReader(
			System.getProperty("user.dir") + "\\src\\test\\resources\\excel\\testdata.xlsx");
	public static WebDriverWait wait;
	public ExtentReports rep = ExtentManager.getInstance();
	public static ExtentTest test;
	public static String browser;
	
	@BeforeSuite
	public void setUp () {
		
		if (driver==null) {
			
			try {
				fis = new FileInputStream (System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Config.properties");
				System.out.println("System Path :: " + System.getProperty("user.dir") + "\\src\\test\\resources\\excel\\testdata.xlsx");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				config.load(fis);
				log.debug("Config file loaded !!!");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			try {
				fis = new FileInputStream (System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\OR.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				OR.load(fis);
				log.debug("Config file loaded !!!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			//Parameterizing build browser 
	if(System.getenv("browser")!=null && System.getenv("browser").isEmpty()) {
				
				browser = System.getenv("browser");
				
			}else {
				
				browser = config.getProperty("browser");
				
			}
			
			config.setProperty("browser", browser);
				
			
			
			if(config.getProperty("browser").equals("firefox")) {
				
				System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\geckodriver.exe");
				driver = new FirefoxDriver();
				log.debug("Firefox launched !!!");
			}else if (config.getProperty("browser").equals("chrome")) {
				
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\chromedriver.exe");
				driver = new ChromeDriver();
				log.debug("Chrome Launched !!!");
			}else if (config.getProperty("browser").equals("ie")) {
				
				System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\iedriver.exe");
				driver = new InternetExplorerDriver();
			}
		
			driver.get(config.getProperty("testsiteurl"));
			log.debug("Navigated to : " +config.getProperty("testsiteurl"));
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")), TimeUnit.SECONDS);
		}
		
		
	}
	
	public void click(String Locator) {
		if (Locator.endsWith("_CSS")) {
			driver.findElement(By.cssSelector(OR.getProperty(Locator))).click();
		}else if (Locator.endsWith("_XPATH")) {
			driver.findElement(By.xpath(OR.getProperty(Locator))).click();
		}else if (Locator.endsWith("_ID")) {
			driver.findElement(By.id(OR.getProperty(Locator))).click();
		}else if (Locator.endsWith("_CLASSNAME")) {
			driver.findElement(By.className(OR.getProperty(Locator))).click();
		}else if (Locator.endsWith("_LINKTEXT")) {
			driver.findElement(By.linkText(OR.getProperty(Locator))).click();
		}
			  
		//test.log(LogStatus.INFO, "Clicking on : "+Locator);
	}
	
	public void type(String Locator, String value) {
		
		if (Locator.endsWith("_CSS")) {
		driver.findElement(By.cssSelector(OR.getProperty(Locator))).sendKeys(value);
		}else if (Locator.endsWith("_ID")) {
			driver.findElement(By.id(OR.getProperty(Locator))).sendKeys(value);
		}else if (Locator.endsWith("_XPATH")) {
			driver.findElement(By.xpath(OR.getProperty(Locator))).sendKeys(value);
		}else if (Locator.endsWith("_CLASSNAME")) {
			driver.findElement(By.className(OR.getProperty(Locator))).sendKeys(value);
		}else if (Locator.endsWith("_LINKTEXT")){
			driver.findElement(By.linkText(OR.getProperty(Locator))).sendKeys(value);
		}
		
		test.log(LogStatus.INFO, "Typing in on : "+Locator+" entered value as "+value);
	}
	
	static WebElement dropdown;
	
	public void select (String Locator, String value) {
		
		if (Locator.endsWith("_CSS")) {
			dropdown = driver.findElement(By.cssSelector(OR.getProperty(Locator)));
		}else if (Locator.endsWith("_XPATH")) {
			dropdown = driver.findElement(By.xpath(OR.getProperty(Locator)));
		}else if (Locator.endsWith("_ID")) {
			dropdown = driver.findElement(By.id(OR.getProperty(Locator)));
		}else if (Locator.endsWith("_CLASSNAME")) {
			dropdown = driver.findElement(By.className(OR.getProperty(Locator)));
		}else if (Locator.endsWith("_LINKTEXT")) {
			dropdown = driver.findElement(By.linkText(OR.getProperty(Locator)));
		}
		
		Select select = new Select (dropdown);
		select.selectByVisibleText(value);
		test.log(LogStatus.INFO, "Selecting from dropdown : "+ Locator + " value as " + value);
	}
	
	
	public boolean isElementPresent(By by) {
		
		try {
			
			driver.findElement(by);
			return true;
			
		}catch(NoSuchElementException e) {
			
			return false;
			
		}
		
	}
	
	
	public static void verifyEquals(String expected,String actual) throws IOException {
		
		try {
			
			Assert.assertEquals(actual, expected);
			
		}catch(Throwable t) {
			
			TestUtil.captureScreenshot();
			//ReportNG
			Reporter.log("<br>"+"Verification failure : "+t.getMessage () +"<br>");
			Reporter.log("<a target=\"_blank\" href="+TestUtil.screenshotName+"><img src ="+TestUtil.screenshotName+" height=200 width=200></a>");
			Reporter.log("<br>");
			//ExtentReport
			test.log(LogStatus.FAIL, "Verification failed with exception : "+ "Failed with exception : "+t.getMessage ());
			test.log(LogStatus.FAIL, test.addScreenCapture(TestUtil.screenshotName));
			
			
		}
	}
	
	
	
	@AfterSuite
	public void tearDown () {
		
		if(driver!=null) {
		driver.quit();
		
		log.debug("Test Execution Completed");
		}
	}
}
