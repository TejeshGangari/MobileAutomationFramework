package DriverPackage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import Utilities.ExcelUtility;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import objectManagers.UtilityManager;

public class BaseClass {
	
	public static AppiumDriver<WebElement> driver;
	public static Properties prop;
	public static Map<String, List<String>> runDetails;
	public static Map<String, String> testData;
	public static ExcelUtility excelObj;
	
	public BaseClass() {
		try {
			excelObj = UtilityManager.getExcelObject();
			FileInputStream fi = new FileInputStream(new File(System.getProperty("user.dir")+"//config.properties"));
			prop = new Properties();
			prop.load(fi);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		runDetails = excelObj.getRunDetails();
	}
}
	