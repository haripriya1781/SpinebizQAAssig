package org.altoro;

import java.util.List;

import org.base.classes.Baseclass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ValidCredential extends Baseclass {

	@Parameters({"vuserid" , "vpass","iuserid" , "ipass"})

	@Test
	private void validLogin(String usrTxt, String passTxt, String emailTxt, String passwTxt ) {

		// 1. Navigate to application:

		browserLaunch("chrome");
		launchUrl("http://testfire.net/");

		// 2 Login with invalid credential:
		
		driver.findElement(By.id("LoginLink")).click();
		driver.findElement(By.xpath("(//input[@type='text'])[2]")).sendKeys(emailTxt);
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys(passwTxt);
		driver.findElement(By.xpath("(//input[@type='submit'])[2]")).click();


		// 3. Login with valid credential:
		driver.findElement(By.xpath("(//input[@type='text'])[2]")).sendKeys(usrTxt);
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys(passTxt);
		driver.findElement(By.xpath("(//input[@type='submit'])[2]")).click();
		
		// 3. validate sigininng in with valid credential
		WebElement Welcome = driver.findElement(By.xpath("//h1[ contains(text(),'Hello Admin User')]"));
		Assert.assertTrue(Welcome.isDisplayed(), "Login is not succesful");

		// 4 to click on view account summary

		driver.findElement(By.xpath("//a[text()='View Account Summary']")).click();

		// 5.Select the 800001 Checking account option
		WebElement acclist = driver.findElement(By.id("listAccounts"));
		selectDrop(acclist, "800001");

		// 6.Assert the Available Balance in the account
		driver.findElement(By.id("btnGetAccount")).click();
		WebElement table = driver.findElement(By.xpath("//table[@cellpad"
				+ "ding='1']"));
		WebElement endingBal1 = driver
				.findElement(By.xpath("(//td[contains(text(),\"Ending balance\")]/following::td)[1]"));
		String endingBal = endingBal1.getText();
		String availBal = driver.findElement(By.xpath("//td[contains(text(),\"Available balance\")]/following::td[1]"))
				.getText();
		Assert.assertTrue(availBal.equalsIgnoreCase(endingBal), "Ending and Available balance doesnt match");

		// 7.Click on the Transfer Funds option in the Left Pane
		driver.findElement(By.xpath("//a[text()='Transfer Funds']")).click();

		// 8.Enter the following data & click on the Transfer Funds button
		WebElement toAcc = driver.findElement(By.xpath("//select[@id='toAccount']"));
		selectDrop(toAcc, "800001");
		WebElement amt = driver.findElement(By.xpath("//input[@id='transferAmount']"));
		String s3 = "9876.0";
		amt.sendKeys(s3);
		driver.findElement(By.xpath("(//input[@type='submit'])[2]")).click();
		// 9.Validate the Transaction Details message is shown below with correct amount

		WebElement transaDetails = driver.findElement(By.xpath(
				"//span[contains(text(),' was successfully transferred from Account 800000 into Account 800001 ')]"));
		String TransactionMsg = driver.findElement(By.xpath("//span[@id=\"_ctl0__ctl0_Content_Main_postResp\"]/span"))
				.getText();
		Assert.assertTrue(transaDetails.isDisplayed(), "incorrect amount in transaction detalis");
		Assert.assertTrue(TransactionMsg.contains(s3), "Transaction msg does not match with the amount entered");
		// 10.Click on the View Recent Transactions option in the left pane
		driver.findElement(By.xpath("//a[text()='View Recent Transactions']")).click();

		// 11.Validate that the first two transactions being shown is as per the
		// previous transaction
		List<WebElement> amount = driver
				.findElements(By.xpath("(//td[contains(text(),\"Amount\")]/following::tr/td[5])"));
		String lastTrans = amount.get(0).getText();
		String lastsecondtrans = amount.get(1).getText().replace("-", "");
		Assert.assertTrue(lastTrans.equalsIgnoreCase(lastsecondtrans), "Last 2 transcations are not equal");

		// 12.Click on Contact Us on Top Right and then click on online form option
		// under Email Section
		driver.findElement(By.xpath("//a[text()='Contact Us']")).click();
		driver.findElement(By.xpath("//a[text()='online form']")).click();
		// 13.Enter some details here and click on Submit button.
		driver.findElement(By.name("email_addr")).sendKeys("abc@gmail.com");
		driver.findElement(By.xpath("//input[@name='subject']")).sendKeys("Test");
		driver.findElement(By.xpath("//textarea[@name='comments']")).sendKeys("Good");
		driver.findElement(By.xpath("(//input[@type='submit'])[2]")).click();
		// 14.Validate that on clicking Submit, a Thank You message is displayed
		WebElement thankyou = driver.findElement(By.xpath("//h1[contains(text(),'Thank You')]"));

		Assert.assertTrue(thankyou.isDisplayed(), "Thankyou message not displayed");
		// 15.Click on the Sign Off button on top right. .
		driver.findElement(By.xpath("//font[text()='Sign Off']")).click();
		// 16. Validate that the user is Signed Off
		WebElement signoff = driver.findElement(By.xpath("//font[text()='Sign In']"));
		Assert.assertTrue(signoff.isDisplayed(), "Dint sign off");
		closeTheBrowser();
	}

}
