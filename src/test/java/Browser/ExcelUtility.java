package Browser;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {
	
	//Instances for the Excel File Handling
	public static FileInputStream fi;
	public static FileOutputStream fo;
	public static XSSFWorkbook wbook;
	public static XSSFSheet sheet;
	public static XSSFRow row;
	public static XSSFCell cell;
	
	//Method Getting Cell Data
	public static String getCellData(String file,String sheetName,int rownum,int cellnum) throws Exception {
		
		fi = new FileInputStream(file);
		wbook = new XSSFWorkbook(fi);
		sheet = wbook.getSheet(sheetName);
		row = sheet.getRow(rownum);
		cell = row.getCell(cellnum);
		
		String data;
		
		try {
			DataFormatter formatter = new DataFormatter();
			data = formatter.formatCellValue(cell);
			return data;
		}
		catch(Exception e) {
			data = "";
		}
		fi.close();
		wbook.close();
		return data;
		
	}
	
	//Method Setting Cell Data
	public static void setCellData(String file,String sheetName,String cellvalue,int rownum,int colnum) throws Exception {
		
	    fi = new FileInputStream(file);
        wbook = new XSSFWorkbook(fi);
	    sheet = wbook.getSheet(sheetName);

	    // Create the row if it doesn't exist
	    XSSFRow row = sheet.getRow(rownum);
	    if (row == null) {
	        row = sheet.createRow(rownum);
	    }
	    
	    // Create the cell if it doesn't exist
	    XSSFCell cell = row.getCell(colnum);
	    if (cell == null) {
	        cell = row.createCell(colnum);
	    }
	    
	    // Set the cell value 
	    cell.setCellValue(cellvalue);

	    // Write the changes and close the workbook
	    FileOutputStream fos = new FileOutputStream(file);
	    wbook.write(fos);
	    fi.close();
	    fos.close();
	    wbook.close();
	    
	
	}
}
