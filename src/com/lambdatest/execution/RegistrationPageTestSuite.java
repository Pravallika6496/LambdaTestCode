package com.lambdatest.execution;

import java.io.FileNotFoundException;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.*;
import com.lambdatest.accounts.*;
import com.lambdatest.properties.ui.*;

public class RegistrationPageTestSuite extends LambdaProperties {

	@BeforeMethod(alwaysRun = true)
	public void instantiateBrowser() throws FileNotFoundException
	{
		getProperties();
		driver=getWebDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.get(getSignUpUrl());
	}
	
	@Test
	public void VerifyRegistrationPageElements()
	{
		VerifyRegistrationPage VerifyRegistrationPage= PageFactory.initElements(driver,VerifyRegistrationPage.class);
		
		VerifyRegistrationPage.verifyElementsPrescence();
		VerifyRegistrationPage.verifyRedirectionLinks();
		VerifyRegistrationPage.invalidScenarios();
	}
	
	@AfterMethod(alwaysRun = true)
	public void killBrowser()
	{
		driver.close();
		driver.quit();
	}
	
}
