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

	public static void main(String[]args) {
		ReadWriteExcel obj = new ReadWriteExcel();
		obj.WriteExcel("Customers", 0, 5, "ID");
	}
	
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

	public boolean isNull(int rNum, int cNum) {
		try {
			FileInputStream fis = new FileInputStream("Hotel Project.xlsx");
			Workbook wb = WorkbookFactory.create(fis);
			if (wb.getSheetAt(1).getRow(rNum).getCell(cNum) == null) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
