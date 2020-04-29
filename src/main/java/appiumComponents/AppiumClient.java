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
		builder.usingPort(Integer.getInteger(prop.getProperty("AppiumServerPort")));
		builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
		builder.withArgument(GeneralServerFlag.LOG_LEVEL,"error");
		return builder;
		
	}
	
	private static DesiredCapabilities getAppCapabilities() {
		capabilities = new DesiredCapabilities();
		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, prop.getProperty("AutomationName"));
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, prop.getProperty("PlatformName"));
		capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, prop.getProperty("PlatformVersion"));
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "device");
		capabilities.setCapability(MobileCapabilityType.UDID, prop.getProperty("DeviceID"));
		capabilities.setCapability("unlockType", "pin");
		capabilities.setCapability("unlockKey", "170295");
		capabilities.setCapability(MobileCapabilityType.APP, prop.getProperty("TestAppPath"));
		return capabilities;
	}
	
	private static AppiumDriverLocalService startService() {
		service = AppiumDriverLocalService.buildService(getServiceBuilder(getAppCapabilities()));
		service.start();
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
