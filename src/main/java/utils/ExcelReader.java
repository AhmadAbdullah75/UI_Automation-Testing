package utils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelReader {

    public static String getSearchTerm(String excelFilePath, String sheetName, int rowNum, int cellNum) {
        String searchTerm = null;

        try (FileInputStream file = new FileInputStream(excelFilePath);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheet(sheetName);
            Row row = sheet.getRow(rowNum);
            Cell cell = row.getCell(cellNum);

            // Read the value as a string
            searchTerm = cell.getStringCellValue();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return searchTerm;
    }
}
