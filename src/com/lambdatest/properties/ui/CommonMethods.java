package com.lambdatest.properties.ui;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class CommonMethods {
	
	protected static WebDriver    driver;
	
	public CommonMethods(WebDriver d) 
	{
		CommonMethods.driver = d;
	}
	
	protected void compareNewTabTitleAndURL(String expectedURL, String expectedTitle)
	{
		String mainWindow = driver.getWindowHandle();
		Set <String> windows = driver.getWindowHandles();
		Iterator <String> itr = windows.iterator();
		
		while(itr.hasNext())
		{
			String childWindow=itr.next();
			
			if(!(childWindow.equalsIgnoreCase(mainWindow)))
			{
				driver.switchTo().window(childWindow);
				String actualURL=driver.getCurrentUrl();
				Assert.assertEquals(actualURL, expectedURL, "Expected URL is: "+expectedURL +" and actual URL is: " +actualURL);
				String actualTitle=driver.getTitle();
				Assert.assertEquals(actualTitle, expectedTitle, "Expected title is: "+expectedTitle +" and actual title is: " +actualTitle);
				driver.close();
				driver.switchTo().window(mainWindow);
			}
		}
		
	}
	
	protected void compareTitleAndURL(String expectedURL, String expectedTitle) 
	{
		String actualURL=driver.getCurrentUrl();
		Assert.assertEquals(actualURL, expectedURL, "Expected URL is: "+expectedURL +" and actual URL is: " +actualURL);
		String actualTitle=driver.getTitle();
		Assert.assertEquals(actualTitle, expectedTitle, "Expected title is: "+expectedTitle +" and actual title is: " +actualTitle);
	}
	
	public String getCellValue(String path, String sheetName, int rowNum, int colNum) throws EncryptedDocumentException, IOException
	{
		FileInputStream fis=new FileInputStream(path);
		Workbook wb=(Workbook) WorkbookFactory.create(fis);
		Row row=wb.getSheet(sheetName).getRow(rowNum);
		String cellValue=row.getCell(colNum).toString();
		return cellValue;
	}

}
