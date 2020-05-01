package DriverPackage;

import java.util.List;
import java.util.Map;

public class TestRunner extends BaseClass{

	private static Map<String, List<String>> runDetails;
	
	public static void main(String[] args) {
		runDetails = excelObj.getRunDetails();
		
	}
	
	private void invokeTest() {
		/**
		 * Implement Java Reflection API to search all the classes under Test Package and search for the method given in the run details
		 * and invoke the respective method with appropriate test data
		 */
		
	}
	
	private void getTest() {
		
	}
}
