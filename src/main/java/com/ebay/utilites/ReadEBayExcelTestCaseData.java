package com.ebay.utilites;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ebay.model.Login;
import com.ebay.model.Product;

public class ReadEBayExcelTestCaseData {

	/**
     * This method is used to retrieve Ebay test data from given excel file
     * 
     */
	@SuppressWarnings("unchecked")
	public static List getEBayTestData(int sheetIndex) {
		List eBayExcelTestData = new ArrayList();
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(EBayConstants.EBAYTESTDATA_FILE_PATH); 
			// Using XSSF for xlsx format, for xls use HSSF
            Workbook workbook = new XSSFWorkbook(fis);
 
            int numberOfSheets = workbook.getNumberOfSheets();
            
            //looping over each workbook sheet
            //for (int i = 0; i < numberOfSheets; i++) {
            	Sheet sheet = workbook.getSheetAt(sheetIndex);
            	Iterator rowIterator = sheet.iterator();
            	
            	//iterating over each row
            	while (rowIterator.hasNext()) {
            	
            		Login login = new Login();
            		Product product = new Product();
                     Row row = (Row) rowIterator.next();
                     Iterator cellIterator = row.cellIterator();
                   //Iterating over each cell (column wise)  in a particular row.
                     while (cellIterator.hasNext()) {
  
                         Cell cell = (Cell) cellIterator.next();
                         
                         if (Cell.CELL_TYPE_STRING == cell.getCellType()) {
  
                             //Cell with index 1 contains Login user name
                             if (cell.getColumnIndex() == 1) {
                                 login.setUserName(String.valueOf(cell.getStringCellValue()));
                             }
                             //Cell with index 2 contains Login password
                             else if (cell.getColumnIndex() == 2) {
                                 login.setPassword(String.valueOf(cell.getStringCellValue()));
                             }
                             //Cell with index 3 contains Product description
                             else if (cell.getColumnIndex() == 3) {
                                 product.setDescription(String.valueOf(cell.getStringCellValue()));
                             }
                         }
                     }
                     //end iterating a row, add all the elements of a row in list
                     eBayExcelTestData.add(login);
                     eBayExcelTestData.add(product);
                 }
        //     }
  
             fis.close();
  
         } catch (FileNotFoundException e) {
             e.printStackTrace();
         } catch (IOException e) {
             e.printStackTrace();
         }
         return eBayExcelTestData;
     }
  
  
 }
