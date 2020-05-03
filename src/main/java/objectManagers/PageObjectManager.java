package objectManagers;

import com.zoho.pages.LoginPage;

import DriverPackage.BaseClass;
import appiumComponents.AppiumClient;
import io.appium.java_client.AppiumDriver;

public class PageObjectManager extends BaseClass{
	
	private AppiumDriver driver;
	private LoginPage loginPage;
	
	public PageObjectManager() {
		if(driver == null){
			driver = AppiumClient.getAppiumDriver(prop.getProperty("PlatformName"));
		}
	}
	
	public LoginPage getLoginPageObject() {
		if(loginPage!=null) {
			return loginPage;
		}else {
			return new LoginPage(driver);
		}
	}
	
}
