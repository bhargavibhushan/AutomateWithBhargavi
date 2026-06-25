package com.automationexercise.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelUtils {

    private static Workbook workbook;
    private static Sheet sheet;

    public static void openExcel(String filePath, String sheetName) 
            throws IOException {
        FileInputStream fis = new FileInputStream(filePath);
        workbook = new XSSFWorkbook(fis);
        sheet = workbook.getSheet(sheetName.trim());
        System.out.println("Sheet found: " + sheet);
    }

    public static int getRowCount() {
        return sheet.getLastRowNum();
    }

    public static String getCellData(int rowNum, int colNum) {
        Row row = sheet.getRow(rowNum);
        Cell cell = row.getCell(colNum);
        if (cell == null) return "";
        return cell.toString();
    }

    public static void closeExcel() throws IOException {
        workbook.close();
    }
}