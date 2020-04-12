package applicant;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.w2a.base.TestBase;

public class signin extends TestBase{
	
	@Test
	public void signIn() throws InterruptedException {
		driver.findElement(By.cssSelector(OR.getProperty("apply_btn"))).click();
		driver.findElement(By.cssSelector(OR.getProperty("email_field"))).sendKeys(OR.getProperty("invalidEmail_TEXT"));
		driver.findElement(By.cssSelector(OR.getProperty("password_field"))).sendKeys(OR.getProperty("password_TEXT"));
		driver.findElement(By.xpath(OR.getProperty("signin_btn"))).click();
		driver.findElement(By.xpath(OR.getProperty("account_lnk"))).click();
			Thread.sleep(20000);
		}

}