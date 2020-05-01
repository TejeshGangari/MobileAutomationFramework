package objectManagers;

import Utilities.ExcelUtility;

public class UtilityManager {

	private static ExcelUtility excelObj=null;
	
	public static ExcelUtility getExcelObject() {
		return  (excelObj==null) ? new ExcelUtility():excelObj;
	}
	
}
