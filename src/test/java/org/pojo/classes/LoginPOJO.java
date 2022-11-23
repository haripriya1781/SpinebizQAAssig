package org.pojo.classes;

import org.base.classes.Baseclass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPOJO extends Baseclass {
	public LoginPOJO() {
		PageFactory.initElements(driver, this);

	}
	@FindBy(xpath= "//input[@type='text'])[2]")
	private WebElement emailtxt;
	@FindBy(xpath="//input[@type='password']")
	private WebElement passtxt;

	@FindBy(xpath = "(//input[@type='submit'])[2]")
	private WebElement signin;
	
	
     
	
	public WebElement getEmailtxt() {
		return emailtxt;
	}
	

	public WebElement getPasstxt() {
		return passtxt;
		
	}
	public WebElement getSignin() {
		return signin;
	}

	

}
