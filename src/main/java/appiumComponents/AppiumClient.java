package appiumComponents;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import DriverPackage.BaseClass;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

public class AppiumClient extends BaseClass{

	private static AppiumDriverLocalService service;
	private static AppiumServiceBuilder builder;
	private static DesiredCapabilities capabilities;
	
	private static AppiumServiceBuilder getServiceBuilder(DesiredCapabilities capabilities) {
		builder = new AppiumServiceBuilder();
		builder.withCapabilities(capabilities);
		builder.withIPAddress(prop.getProperty("AppiumServer"));
		builder.usingAnyFreePort();
		builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
		builder.withArgument(GeneralServerFlag.LOG_LEVEL,"error");
		return builder;
		
	}
	
	private static DesiredCapabilities getAppCapabilities() {
		String platformName = prop.getProperty("PlatformName");
		capabilities = new DesiredCapabilities();
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, platformName);
		capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, prop.getProperty("PlatformVersion"));
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Ammu");
		capabilities.setCapability(MobileCapabilityType.UDID, prop.getProperty("DeviceID"));
		if(platformName.equals("Android")) {
			capabilities.setCapability("unlockType", "pin");
			capabilities.setCapability("unlockKey", "170295");
			capabilities.setCapability("appPackage", prop.getProperty("appPackage"));
			capabilities.setCapability("appActivity", prop.getProperty("appActivity"));
			capabilities.setCapability("noReset", true);
			capabilities.setCapability("chromedriverExecutableDir", prop.getProperty("ChromeDriverPath"));
		}else {
			capabilities.setCapability("xcodeOrgId", "W57AX676T7");
			capabilities.setCapability("xcodeSigningId", "iPhone Developer");
			capabilities.setCapability(MobileCapabilityType.APP, prop.getProperty("TestAppPath"));
		}
		return capabilities;
	}
	
	private static AppiumDriverLocalService startService() {
		try {
			service = AppiumDriverLocalService.buildService(getServiceBuilder(getAppCapabilities()));
			service.start();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return service;
	}
	
	public static AppiumDriver<WebElement> getAppiumDriver(String driverType) {
		startService();
		switch(driverType.toUpperCase()) {
		case "ANDROID":
			driver = new AndroidDriver<WebElement>(service.getUrl(), capabilities);
			break;
		case "IOS":
			driver = new IOSDriver<WebElement>(service.getUrl(), capabilities);
			break;
		}
		return driver;
	}
	
	public static void stopServer() {
		service.stop();
	}
}
