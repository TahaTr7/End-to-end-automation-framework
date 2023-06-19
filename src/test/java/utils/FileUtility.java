package utils;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.usermodel.Sheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * This Class is developed for test data from Excel file (Read & Write
 * purpose).
 */
public class FileUtility {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileUtility.class);

    /**
     * This used to read the value from specified cell from the specified
     * sheet of the Excel file.
     * 
     * @param fileFullPath full path of Excel File
     * @param sheetName Sheet name
     * @param rowNum Row number to be read
     * @param colNum Column number to be read
     * @return cell value as string from the specified cell from the specified
     *         sheet
     */
    public static String getExcelData(String fileFullPath, String sheetName, int rowNum, int colNum) {
        String data = "";
        try {
            FileInputStream fis = new FileInputStream(fileFullPath);
            Workbook wb = WorkbookFactory.create(fis);
            Sheet sh = wb.getSheet(sheetName);
            Row row = sh.getRow(rowNum);
            data = row.getCell(colNum).toString();
            wb.close();
        } catch (Exception e) {
            LOGGER.error("Error while get data from excell: {}", e.getMessage());
        } 
        return data;
    }

    /**
     * This used to write the value from specified cell from the specified
     * sheet of the Excel file.
     * 
     * @param filePath file path
     * @param sheetName Sheet name
     * @param rowNum Row number to be read
     * @param colNum Column number to be read
     * @param data pass the data value
     *
     */
    public static void setExcelData(String filePath, String sheetName, int rowNum, int colNum, String data) {
        try {
            FileInputStream fis = new FileInputStream(filePath);
            Workbook wb = WorkbookFactory.create(fis);
            Sheet sh = wb.getSheet(sheetName);
            Row row = sh.getRow(rowNum);
            Cell cell = row.createCell(colNum);
            cell.setCellValue(data);
            FileOutputStream fos = new FileOutputStream(filePath);
            wb.write(fos);
            wb.close();
        } catch (Exception e) {
            LOGGER.error("Error while write excell file - Name :  " + filePath, e.getMessage());
        }  
    }
    
    /**
     * This is get property.
     * @param key pass key value
     * 
     * @return String key
     */
    public String getProperty(String key) {
        String value = null;
        try {
            FileInputStream fisObj = new FileInputStream("./data/commondata.properties");
            Properties pObj = new Properties();
            pObj.load(fisObj);
            value = pObj.getProperty(key);

        } catch (IOException e) {
            LOGGER.error("Error while read properties, {}", e.getMessage());
        }
        return value;
    }
}
