package com.lambdatest.accounts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.Reporter;

import com.lambdatest.properties.ui.*;

public class VerifyRegistrationPage extends CommonMethods
{
	
	public VerifyRegistrationPage(WebDriver d) {
		super(d);
	}
	
	@FindBy(xpath = ".//h1[@class='form_title']")
	protected WebElement signUpTitle;
	
	@FindBy(xpath = ".//a[@class='logo']")
	protected WebElement lambdaTestLogo;
	
	@FindBy(xpath = ".//a[text()='Terms of Service']")
	protected WebElement termsOfServiceLink;
	
	@FindBy(xpath = ".//a[text()='Privacy Policy']")
	protected WebElement privacyPolicyLink;
	
	@FindBy(xpath = ".//a[text()='Sign In']")
	protected WebElement signInLink;
	
	@FindBy(name = "name")
	protected WebElement userName;
		
	public void verifyElementsPrescence()
	{
		Assert.assertTrue(lambdaTestLogo.isDisplayed(), "Lambdatest logo isn't displayed");
		Assert.assertTrue(signUpTitle.isDisplayed(), "Sign Up title isn't displayed");
		Assert.assertTrue(termsOfServiceLink.isDisplayed(), "Terms of service link isn't displayed");
		Assert.assertTrue(signInLink.isDisplayed(), "Sign in link isn't displayed");
		Reporter.log("All the elements are displayed on registration page as expected");
	}
	
	public void verifyRedirectionLinks()
	{
		lambdaTestLogo.click();
		compareNewTabTitleAndURL("https://www.lambdatest.com/", "Cross Browser Testing Tools | Free Automated Website Testing | LambdaTest");
		Reporter.log("LambdaTest Logo redirection verified");
		privacyPolicyLink.click();
		compareNewTabTitleAndURL("https://www.lambdatest.com/privacy", "Privacy Policy | LambdaTest");
		Reporter.log("Privacy policy redirection verified");
		termsOfServiceLink.click();
		compareNewTabTitleAndURL("https://www.lambdatest.com/terms-of-service", "Terms of Service - LambdaTest");
		Reporter.log("Terms of service redirection verified");
		signInLink.click();
		compareTitleAndURL("https://accounts.lambdatest.com/login", "Login - LambdaTest");
		Reporter.log("Signin redirection verified");
		driver.navigate().back();
		System.out.println("On signup page");
	}

	

}
