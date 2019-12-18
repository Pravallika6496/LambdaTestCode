package com.lambdatest.accounts;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.Reporter;

import com.lambdatest.properties.ui.CommonMethods;

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
	protected WebElement nameField;
	
	@FindBy(name = "email")
	protected WebElement emailField;
	
	@FindBy(name = "password")
	protected WebElement password;
	
	@FindBy(name = "organization_name")
	protected WebElement orgName;
	
	@FindBy(name = "phone")
	protected WebElement phoneNumField;
	
	@FindBy(xpath = ".//button[text()='Free Sign Up']")
	protected WebElement signUpButton;
	
	String path=".//src//TestData//VerifyRegistrationPage.xlsx";
		
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
	}
	
	public void emptyUserNameTest() throws IOException
	{
		try 
		{
			emailField.sendKeys(getCellValue(path, "Sheet1", 2, 1));
			password.sendKeys(getCellValue(path, "Sheet1", 3, 1));
			orgName.sendKeys(getCellValue(path, "Sheet1", 4, 1));
			phoneNumField.sendKeys(getCellValue(path, "Sheet1", 5, 1));
			signUpButton.click();
			compareErrorMessage(nameField, "Please fill out this field.");
			Reporter.log("Error message is " +nameField.getAttribute("validationMessage"));
			takeScreenshot("emptyUserNameTestPass");
		}
		
		catch (Exception e) 
		{
			e.printStackTrace();
			takeScreenshot("emptyUserNameTestFail");
		}
	}
	
	public void emptyEmailTest() throws IOException
	{
		try 
		{
			emailField.clear();
			nameField.sendKeys(getCellValue(path, "Sheet1", 1, 1));
			signUpButton.click();
			compareErrorMessage(emailField, "Please fill out this field.");
			Reporter.log("Error message is " +emailField.getAttribute("validationMessage"));
			takeScreenshot("emptyEmailTestPass");
		}
		
		catch (Exception e) 
		{
			e.printStackTrace();
			takeScreenshot("emptyEmailTestFail");
		}
	}
	
	public void emptyPasswordTest() throws IOException
	{
		try 
		{
			password.clear();
			emailField.sendKeys(getCellValue(path, "Sheet1", 2, 1));
			signUpButton.click();
			compareErrorMessage(password, "Please fill out this field.");
			Reporter.log("Error message is " +password.getAttribute("validationMessage"));
			takeScreenshot("emptyPasswordTestPass");
		}
		
		catch (Exception e) 
		{
			e.printStackTrace();
			takeScreenshot("emptyPasswordTestFail");
		}
	}

	public void emptyPhnNumTest() throws IOException
	{
		try 
		{
			phoneNumField.clear();
			password.sendKeys(getCellValue(path, "Sheet1", 3, 1));
			signUpButton.click();
			compareErrorMessage(phoneNumField, "Please fill out this field.");
			Reporter.log("Error message is " +phoneNumField.getAttribute("validationMessage"));
			takeScreenshot("emptyPhnNumTestPass");
		}
		
		catch (Exception e) 
		{
			e.printStackTrace();
			takeScreenshot("emptyPhnNumTestFail");
		}
	}
	
	public void invalidPasswordTest() throws IOException
	{
		try 
		{
			phoneNumField.sendKeys(getCellValue(path, "Sheet1", 5, 1));
			
			invalidErrorCheck(phoneNumField, getCellValue(path, "Sheet1", 5, 1), signUpButton);
			
			password.sendKeys(getCellValue(path, "Sheet2", 2, 1));
			signUpButton.click();
			Reporter.log("Error message is " +password.getAttribute("validationMessage"));
			
			
			takeScreenshot("emptyPhnNumTestPass");
		}
		
		catch (Exception e) 
		{
			e.printStackTrace();
			takeScreenshot("emptyPhnNumTestFail");
		}
	}

}
