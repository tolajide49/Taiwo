package applicant;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.w2a.base.TestBase;

public class forgotpassword extends TestBase{
	@Test
	public void forgotPassword() throws InterruptedException{
		driver.findElement(By.cssSelector(OR.getProperty("apply_btn"))).click();
		driver.findElement(By.xpath(OR.getProperty("forgotpsswrd"))).click();
		driver.findElement(By.cssSelector(OR.getProperty("forgetemail"))).sendKeys("email_TEXT");
		driver.findElement(By.xpath(OR.getProperty("sendbtn"))).click();
		Thread.sleep(5000);
		
		
	}

}
