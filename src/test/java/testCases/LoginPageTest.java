package testCases;

import java.util.Map;

import com.zoho.pages.LoginPage;

import DriverPackage.BaseClass;
import Utilities.ExcelUtility;
import objectManagers.PageObjectManager;

public class LoginPageTest extends BaseClass{

	public static LoginPage loginPage;
	public static ExcelUtility excelObj;
	private Map<String, String> testData;
	
	
	public LoginPageTest(Map<String, String> testData) {
		this.testData = testData;
		loginPage = new PageObjectManager().getLoginPageObject();
	}
	
	
	public void LoginTest() {
		loginPage.login(testData.get("User Name"), testData.get("Password"));
	}
}
