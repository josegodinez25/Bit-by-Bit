package application;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;



public class ReadWriteExcel {

    public static void main(String[] args) {
        ReadWriteExcel obj = new ReadWriteExcel();
        String un = obj.ReadExcel("Rooms", 0, 0);
        System.out.println(un);

        obj.WriteExcel("Rooms", 5,5,"testing");
    }


    public String ReadExcel(String SheetName, int rNum, int cNum) {
        String data = "";
        try {
            FileInputStream fis = new FileInputStream("Hotel Project.xlsx");
            Workbook wb = WorkbookFactory.create(fis);
            Sheet s = wb.getSheet(SheetName);
            Row r = s.getRow(rNum);
            Cell c = r.getCell(cNum);
            data = c.getStringCellValue();

        }catch(Exception e) {
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


        }catch(Exception e) {
            System.out.println("WriteExcel catch block");
            e.printStackTrace();
        }

    }
}