package com.w2a.listeners;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;

import com.relevantcodes.extentreports.LogStatus;
import com.w2a.base.TestBase;
import com.w2a.utilities.TestUtil;

public class CustomListeners extends TestBase implements ITestListener {

	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onTestSuccess(ITestResult result) {
		
		test.log(LogStatus.PASS, result.getName() .toUpperCase() + "PASS");
		rep.endTest(test);
		rep.flush();
	}

	public void onTestFailure(ITestResult result) {
		
		System.setProperty("org.uncommons.reportng.escape-output","false");
		try {
			TestUtil.captureScreenshot();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		test.log(LogStatus.FAIL, result.getName() .toUpperCase() + "Failed with exception : "+result.getThrowable());
		test.log(LogStatus.FAIL, test.addScreenCapture(TestUtil.screenshotName));
		
		Reporter.log("Click to see screenshot");
		//Reporter.log("<a target=\"_blank\" href=\"E:\\screenshot\\error.png\">Screenshot</a>");
		Reporter.log("<a target=\"_blank\" href="+TestUtil.screenshotName+">Screenshot</a>");
		Reporter.log("<br>");
		Reporter.log("<br>");
		Reporter.log("<a target=\"_blank\" href="+TestUtil.screenshotName+"><img src ="+TestUtil.screenshotName+" height=200 width=200></a>");
		rep.endTest(test);
		rep.flush();
		
	}

	public void onTestSkipped(ITestResult arg0) {

		test.log(LogStatus.SKIP, arg0.getName().toUpperCase()+ " Skipped the test as the Run Mode is NO");
		rep.endTest(test);
		rep.flush();
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext arg0) {
		test = rep.startTest(arg0.getName().toUpperCase());
		//Runmodes - Y 
		/*if (!TestUtil.isTestRunnable(arg0.getName(),excel)) {
			
			throw new SkipException("Skipping the test "+arg0.getName().toUpperCase()+ " as the Run Mode is NO");
			
		}*/
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

}
