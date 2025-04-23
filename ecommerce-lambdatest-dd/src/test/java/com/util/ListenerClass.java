package com.util;
import org.testng.ITestListener;
import org.testng.ITestResult;
	public class ListenerClass implements ITestListener{
		  public void onTestStart(ITestResult res) {
			  System.out.println("Started: "+res.getName());
		  }
		  public void onTestSuccess(ITestResult res) {
			  System.out.println("Passed: "+res.getName());
		  }
		  public void onTestFailure(ITestResult res) {
			  System.out.println("Failed: "+res.getName());
		  }
		  public void onTestSkipped(ITestResult res) {
			  System.out.println("Skipped: "+res.getName());
		  }
}
