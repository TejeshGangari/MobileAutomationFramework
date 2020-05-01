package com.zoho.tests;

import com.zoho.pages.LoginPage;

import objectManagers.PageObjectManager;

public class SanityTest {

	public static LoginPage loginPage;
	
	public static void main(String[] args) {
		PageObjectManager pom = new PageObjectManager();
		//loginPage = pom.getLoginPageObject();
		//loginPage.login();
	}
}
