package com.w2a.testcase;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.w2a.base.TestBase;

public class LoginTest extends TestBase {
	
	@Test
	public void validLogin () throws InterruptedException {
		
		click("HomeloginBtn_XPATH");
		Thread.sleep(2000);
		driver.findElement(By.id(OR.getProperty("email_ID"))).sendKeys(OR.getProperty("email_TEXT"));
		driver.findElement(By.id(OR.getProperty("password_ID"))).sendKeys(OR.getProperty("password_TEXT"));		
		click("loginBtn_XPATH");
		Thread.sleep(3000);
		Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("ELECTONIC_XPATH"))), "Your Credential is not Correct");
		log.debug("Log in successfully executed");
		
		
	}

}
