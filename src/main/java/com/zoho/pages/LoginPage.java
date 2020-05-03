package com.zoho.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;

public class LoginPage {
	
	public AppiumDriver<WebElement> driver;
	
	public LoginPage(AppiumDriver<WebElement> driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	}
	
	@FindBy(id="com.zoho.crm:id/login_button1")
	public WebElement signIn;
	
	@FindBy(xpath="//input[@id='login_id']")
	public WebElement loginID;
	
	@FindBy(xpath="//button[@id='nextbtn']")
	public WebElement btnNext;
	
	@FindBy(xpath="//input[@id='password']")
	public WebElement password;
	
	
	public void login(String userName, String pass) {
		signIn.click();
		driver.context("WEBVIEW_chrome");
		loginID.sendKeys(userName);
		btnNext.click();
		password.sendKeys(pass);
		btnNext.click();	
	}
	
	
}
