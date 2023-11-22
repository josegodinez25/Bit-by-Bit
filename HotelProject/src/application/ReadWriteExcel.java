package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
/**
 * @author Nathan
 * @version 11/20/23
 */
public class ReadWriteExcel {
	
	//obtains information from the excel sheet
	//it needs the sheet name, row, and column
	/**
	 * gets the data inside of a cell
	 * @param SheetName Name of the excel Sheet
	 * @param rNum Row of targeted Cell
	 * @param cNum Column of targeted Cell
	 * @return
	 */
	public String ReadExcel(String SheetName, int rNum, int cNum) {
		String data = "";
		try {
			FileInputStream fis = new FileInputStream("Hotel Project.xlsx");
			Workbook wb = WorkbookFactory.create(fis);
			Sheet s = wb.getSheet(SheetName);
			Row r = s.getRow(rNum);
			Cell c = r.getCell(cNum);
			c.setCellType(CellType.STRING);
			data = c.getStringCellValue();

		} catch (Exception e) {
			System.out.println("ReadExcel catch block");
			e.printStackTrace();
		}
		return data;
	}

	//prints data into the excel sheet
	//needs the sheet name, row, column, and the String you want to print
	/**
	 * prints out DATA into a cell
	 * @param SheetName Name of the excel sheet
	 * @param rNum Row of targeted Cell
	 * @param cNum Column of targeted Cell
	 * @param DATA Data gets inputed into the cell
	 */
	public void WriteExcel(String SheetName, int rNum, int cNum, String DATA) {
		try {

			FileInputStream fis = new FileInputStream("Hotel Project.xlsx");
			Workbook wb = WorkbookFactory.create(fis);

			Sheet s = wb.getSheet(SheetName);
			Row r = s.getRow(rNum);
			Cell c = r.createCell(cNum);
			c.setCellValue(DATA);
			FileOutputStream fos = new FileOutputStream("Hotel Project.xlsx");
			wb.write(fos);

		} catch (Exception e) {
			System.out.println("WriteExcel catch block");
			e.printStackTrace();
		}

	}

	//checks if the the cell is null
	//needs row, column, and the index of the sheet you want to check
	/**
	 * checks if the cell has any data inside
	 * @param rNum Row of targeted Cell
	 * @param cNum Column of targeted Cell
	 * @param index the index of the sheet you want to check
	 * @return returns true or false if the cell is null or not
	 */
	public boolean isNull(int rNum, int cNum, int index) {
		try {
			FileInputStream fis = new FileInputStream("Hotel Project.xlsx");
			Workbook wb = WorkbookFactory.create(fis);
			if (wb.getSheetAt(index).getRow(rNum).getCell(cNum) == null) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}


	/**
	 * returns a cell from a certain sheet
	 * @param SheetName
	 * @param rNum Row of targeted Cell
	 * @param cNum Column of targeted Cell
	 * @return returns the cell of the certain row and column
	 */
	public Cell getCell(String SheetName,int rNum, int cNum) {
		Cell c = null;
		try {
			FileInputStream fis = new FileInputStream("Hotel Project.xlsx");
			Workbook wb = WorkbookFactory.create(fis);
			Sheet s = wb.getSheet(SheetName);
			Row r = s.getRow(rNum);
			c = r.getCell(cNum);
		} catch (Exception e) {
			System.out.println("getCell catch block");
			e.printStackTrace();
		}
		return c;
	}
	
	/**
	 * uses the ID to find that customers email
	 * @param customerID The customers reservation ID
	 * @return returns the customers email
	 */
	public String getEmail(String customerID) {
		try {
			FileInputStream fis = new FileInputStream("Hotel Project.xlsx");
			Workbook wb = WorkbookFactory.create(fis);
			Sheet sheet = wb.getSheet("Customers");
			
			for (Row row : sheet) {
				String id = row.getCell(3).getStringCellValue();
				if (id.equals(customerID) ) {
					return row.getCell(3).getStringCellValue();
				}
			}
			
			} catch (Exception e ) {
				e.printStackTrace();
		}
		return null;
	}

}
