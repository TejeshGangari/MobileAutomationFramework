package com.zoho.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;

public class LoginPage {

	private LoginPage loginPage;
	
	public AppiumDriver<WebElement> driver;
	
	public LoginPage(AppiumDriver<WebElement> driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="com.zoho.crm:id/login_button1")
	public WebElement signIn;
	
	@FindBy(id="login_id")
	public WebElement loginID;
	
	@FindBy(id="nextbtn")
	public WebElement btnNext;
	
	@FindBy(id="password")
	public WebElement password;
	
	
	public void login() {
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		signIn.click();
		loginID.sendKeys("tejeshkumar.gangari@gmail.com");
		btnNext.click();
		password.sendKeys("Test@123!");
		btnNext.click();
	}
	
	
}
