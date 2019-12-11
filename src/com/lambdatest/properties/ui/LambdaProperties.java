package com.lambdatest.properties.ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LambdaProperties
{
		
	protected WebDriver driver;
	
	protected String Url;
	
	protected String SignUpUrl;
	
	protected String iedriver;
	
	protected String chromedriver;
	
	protected long shortWait;
	
	protected long longWait;
	
	protected long extremeLongWait;
	
	Properties config;
	
	FileInputStream fis;
	
	public String getUrl() {
		return Url;
	}
	
	public String getSignUpUrl() {
		return SignUpUrl;
	}

	public long getShortWait() {
		return shortWait;
	}

	public long getLongWait() {
		return longWait;
	}

	public long getExtremeLongWait() {
		return extremeLongWait;
	}
	
	public WebDriver getWebDriver() {
		driver = new ChromeDriver();
		return driver;
	}
	
	public void getProperties() throws FileNotFoundException{
	config = new Properties();
	
	try {
		fis = new FileInputStream(".\\src\\com\\lambdatest\\properties\\ui\\ui.properties");
		config.load(fis);
	} 
	catch (IOException e) {
		e.printStackTrace();
	}
	
	chromedriver=config.getProperty("chromedriver");
	
	iedriver=config.getProperty("iedriver");
	
	Url=config.getProperty("Url");
	
	SignUpUrl=config.getProperty("SignUpUrl");
	
	shortWait=Long.parseLong(config.getProperty("short_wait"));
	longWait=Long.parseLong(config.getProperty("long_wait"));
	extremeLongWait=Long.parseLong(config.getProperty("extreme_long_wait"));
	
	File file= new File(chromedriver);
	System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
	
	
	}
	
}
	
	
	
