package utils;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {

    public static String getProductFromExcel(String productName) {
        String excelFilePath = "src/test/resources/testdata/testData.xlsx"; // Provide path to the Excel file
        String sheetName = "Sheet1";  // The sheet where product names are stored
        String productQuery = "";

        try (FileInputStream file = new FileInputStream(excelFilePath);
             XSSFWorkbook workbook = new XSSFWorkbook(file)) {

            XSSFSheet sheet = workbook.getSheet(sheetName);
            DataFormatter formatter = new DataFormatter();
            int rowCount = sheet.getLastRowNum();

            for (int i = 1; i <= rowCount; i++) {  // Assuming first row is the header
                String productInExcel = formatter.formatCellValue(sheet.getRow(i).getCell(0));
                if (productInExcel.equalsIgnoreCase(productName)) {
                    productQuery = productInExcel;
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return productQuery;
    }
}
