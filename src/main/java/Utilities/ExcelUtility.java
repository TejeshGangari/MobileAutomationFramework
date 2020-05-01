package Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

	private Map<String, List<String>> runDetails;
	private Map<String, String> testData;
	private Workbook wb;
	private FileInputStream fi;
	
	public ExcelUtility() {
		try {
			fi = new FileInputStream(new File(System.getProperty("user.dir")+"//Run Manager.xlsx"));
			wb = new XSSFWorkbook(fi);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Map<String, List<String>> getRunDetails(){
		List<String> testDetails = new ArrayList<String>();
		Sheet sheet = wb.getSheet("Test Info");
		int rowCount = sheet.getLastRowNum();
		for(int rowIterator=1;rowIterator<rowCount;rowIterator++) {
			Row row = sheet.getRow(rowIterator);
			if(row.getCell(2).getStringCellValue().equalsIgnoreCase("YES")) {
				int colCount = row.getLastCellNum();
				for(int colInterator=1;colInterator<colCount;colInterator++) {
					testDetails.add(row.getCell(colInterator).getStringCellValue());
				}
				runDetails.put(row.getCell(1).getStringCellValue(), testDetails); //Test Case ID is the first cell value
			}
		}
		
		return runDetails;
	}
	
	public Map<String, String> getTestData(String testID){
		boolean testDataFound=false;
		Sheet sheet = wb.getSheet("Test Data");
		int rowCount = sheet.getLastRowNum();
		for(int rowIterator=1;rowIterator<rowCount;rowIterator++) {
			Row row = sheet.getRow(rowIterator);
			int colCount = row.getLastCellNum();
			for(int colInterator=1;colInterator<colCount;colInterator++) {
				 if(sheet.getRow(rowIterator).getCell(0).getStringCellValue().equals(testID)) {
					 testDataFound = true;
					 testData.put(sheet.getRow(0).getCell(colInterator).getStringCellValue(), row.getCell(colInterator).getStringCellValue());
				 }
			}
			if(testDataFound)
				break;
		}	
		return testData;
	}
}
