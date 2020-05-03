package testPackage;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import DriverPackage.BaseClass;
import Utilities.ExcelUtility;
import appiumComponents.AppiumClient;
import objectManagers.UtilityManager;

public class TestRunner{

	private static Map<String, List<String>> runDetails;
	private static Map<String, String> testData;
	private static ExcelUtility excelObj;
	private static TestRunner testRunner;
	
	static {
		excelObj = UtilityManager.getExcelObject();
	}
	
	public static void main(String[] args) {
		testRunner = new TestRunner();
		runDetails = excelObj.getRunDetails();
		for(String testId:runDetails.keySet()) {
			testData = excelObj.getTestData(testId);
			testRunner.invokeTest(runDetails,testId);
		}
	}
	
	private void invokeTest(Map<String, List<String>> runDetails, String testId) {
		
		try {
			Class<?> className = Class.forName("testCases."+runDetails.get(testId).get(0));
			Object obj = className.getConstructor(Map.class).newInstance(testData);
			Method[] methodList = className.getDeclaredMethods();
			for(Method method:methodList) {
				if(method.getName().equals(testId)){
					method.invoke(obj);
				}
			}
			testTearDown();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void getTest() {
		/**
		 * Yet to be implemented
		 */
	}
	
	private void testTearDown() {
		AppiumClient.stopServer();
	}
}
