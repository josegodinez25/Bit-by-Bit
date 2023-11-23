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

public class ReadWriteExcel {

	// obtains information from the excel sheet
	// it needs the sheet name, row, and column
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

	// prints data into the excel sheet
	// needs the sheet name, row, column, and the String you want to print
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

	// checks if the the cell is null
	// needs row, column, and the index of the sheet you want to check
	public boolean isNull(int rNum, int cNum, int index) {
		try {
			FileInputStream fis = new FileInputStream("Hotel Project.xlsx");
			Workbook wb = WorkbookFactory.create(fis);
			if (wb.getSheetAt(index).getRow(rNum).getCell(cNum) == null) {
				return true;
			} else if (wb.getSheetAt(index).getRow(rNum).getCell(cNum).getStringCellValue() == "") {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	// returns a cell from a certain sheet
	public Cell getCell(String SheetName, int rNum, int cNum) {
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

	public String getEmail(String customerID) {
		try {
			FileInputStream fis = new FileInputStream("Hotel Project.xlsx");
			Workbook wb = WorkbookFactory.create(fis);
			Sheet sheet = wb.getSheet("Customers");

			for (Row row : sheet) {
				String id = row.getCell(3).getStringCellValue();
				if (id.equals(customerID)) {
					return row.getCell(3).getStringCellValue();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
