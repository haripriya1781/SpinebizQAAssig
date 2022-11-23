package org.base.classes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Baseclass {
	public static WebDriver driver;
	public static void browserLaunch(String webbrowser) {
		if (webbrowser.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			 driver = new ChromeDriver();
		}
		else if (webbrowser.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			 driver = new ChromeDriver();
		}
		else if (webbrowser.equals("edge")) {
			WebDriverManager.chromedriver().setup();
			 driver = new EdgeDriver();
		}
		
		driver.manage().window().maximize();
	}
	public static void launchUrl(String url) {
     driver.get(url);
	}
	
	
	public static void search(WebElement srchElement, String text) {
		srchElement.sendKeys(text);

	}
	public static void passText(WebElement txtElement, String txt) {
		txtElement.sendKeys(txt);

	}
	public static void click(WebElement clk) {
  clk.click();
	}

	
	
		
		
		public static void closeTheBrowser() {
			driver.close();
		}
public static void selectDrop(WebElement location, String value) {
	Select s = new Select(location);
	s.selectByValue(value);
}



		}
	
	

	


