package objectManagers;

import DriverPackage.BaseClass;
import appiumComponents.AppiumClient;
import io.appium.java_client.AppiumDriver;

public class PageObjectManager extends BaseClass{
	
	private static final AppiumDriver driver;
	private static LoginPage loginPage;
	
	static {
		driver = AppiumClient.getAppiumDriver(prop.getProperty("platformName"));
	}
	
	public static LoginPage getLoginPageObject() {
		if(loginPage!=null) {
			return loginPage;
		}else {
			return new LoginPage(driver);
		}
	}
	
}
