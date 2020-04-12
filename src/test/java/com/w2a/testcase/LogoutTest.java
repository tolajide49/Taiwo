package com.w2a.testcase;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.w2a.base.TestBase;

public class LogoutTest extends TestBase {
	
	@Test
	public void logout() throws InterruptedException {
		
		
		
		Thread.sleep(3000);
		
		click("HomeloginBtn_XPATH");
		Thread.sleep(2000);
		driver.findElement(By.id(OR.getProperty("email_ID"))).sendKeys(OR.getProperty("email_TEXT"));
		driver.findElement(By.id(OR.getProperty("password_ID"))).sendKeys(OR.getProperty("password_TEXT"));		
		click("loginBtn_XPATH");
		click("Logout_account_xpath");
		Thread.sleep(3000);
		click("Logout_xpath");
		
		Thread.sleep(5000);
	}
}
