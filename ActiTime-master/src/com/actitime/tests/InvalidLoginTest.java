package com.actitime.tests;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.actitime.generic.BaseTest;
import com.actitime.generic.ExcelData;
import com.actitime.pages.LoginPage;

public class InvalidLoginTest extends BaseTest
{
	@Test(priority=2)
	public void testInvalidLogin() throws InterruptedException
	{
		String loginTitle = ExcelData.getData(file_path, "TC01", 1, 2);
		String expectedErrorMessge = ExcelData.getData(file_path, "TC02", 1, 2);
		
		LoginPage lp = new LoginPage(driver);
		
		//To verify login page
		lp.verifyPage(loginTitle);
		
		int rc = ExcelData.getRowCount(file_path, "TC02");
	
		for(int i=1;i<=rc;i++)
		{
			String username = ExcelData.getData(file_path, "TC02", i, 0);
			String password = ExcelData.getData(file_path, "TC02", i, 1);
	
			//Enter invalid username
			Reporter.log("Invalid username: "+username,true);
			lp.enterUserName(username);
			
			//Enter invalid password
			Reporter.log("Invalid password: "+password,true);
			lp.enterPassword(password);
			
			//click on Login
			lp.clickOnLogin();
			Thread.sleep(1000);
			
			//verify error message
			String actualErrorMessage = lp.verifyErrorMessage();
			
			Reporter.log("Actual error messge: "+actualErrorMessage,true);
			Reporter.log("Expected error message: "+expectedErrorMessge,true);
			
			Assert.assertEquals(actualErrorMessage, expectedErrorMessge, "Error message is not matching...");
			Reporter.log("Error messge is matching......",true);
			Reporter.log("==================================================",true);
		}
	}
}
