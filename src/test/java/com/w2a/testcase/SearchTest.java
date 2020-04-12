package com.w2a.testcase;

import org.openqa.selenium.By;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.w2a.base.TestBase;

public class SearchTest extends TestBase {

	@Test
	public void searchproduct() throws InterruptedException {
		Thread.sleep(3000);
		driver.findElement(By.xpath(OR.getProperty("Search_XPATH"))).sendKeys(OR.getProperty("Search_TEXT"));
		//driver.findElement(By.xpath(OR.getProperty("Searchbtn_XPATH"))).click();
		Thread.sleep(3000);
	click("button_XPATH");
		Thread.sleep(5000);
		
		Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("Element_XPATH"))), "Product not correct");

	}

}
