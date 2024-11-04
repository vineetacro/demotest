package projectUtilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {

    public static List<Map<String, String>> readExcelData(String moduleName, String sheetName) {
        List<Map<String, String>> data = new ArrayList<>();
        String filePath = "./testData/" + moduleName + "-Data.xlsx";

        try (FileInputStream file = new FileInputStream(new File(filePath));
             XSSFWorkbook workbook = new XSSFWorkbook(file)) {

            XSSFSheet sheet = workbook.getSheet(sheetName);
            if (sheet != null) {
                Row headerRow = sheet.getRow(0);

                for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
                    Row row = sheet.getRow(rowNum);
                    if (row != null) {
                        Map<String, String> rowData = new HashMap<>();
                        for (int cellNum = 0; cellNum < headerRow.getLastCellNum(); cellNum++) {
                            Cell cell = row.getCell(cellNum);
                            if (cell != null) {
                                String header = headerRow.getCell(cellNum).getStringCellValue();
                                String value = getCellValueAsString(cell);
                                rowData.put(header, value);
                            }
                        }
                        data.add(rowData);
                    }
                }
            } else {
                System.err.println("Sheet " + sheetName + " not found in the Excel file.");
            }

        } catch (IOException e) {
            System.err.println("Error reading data from " + moduleName + "-Data.xlsx: " + e.getMessage());
        }

        return data;
    }

    public static String getValueByLabel(String moduleName, String sheetName, String label) {
        List<Map<String, String>> sheetData = readExcelData(moduleName, sheetName);

        for (Map<String, String> row : sheetData) {
            if (row.containsKey("Field") && row.get("Field").equals(label) ||
                row.containsKey("Screen") && row.get("Screen").equals(label)) {
                return row.getOrDefault("Locator", row.getOrDefault("Value", row.get("URL")));
            }
        }

        System.err.println("Label \"" + label + "\" not found in the \"" + sheetName +
                           "\" sheet of module \"" + moduleName + "\".");
        return "";
    }

    private static String getCellValueAsString(Cell cell) {
        CellType cellType = cell.getCellType();
        switch (cellType) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                } else {
                    return Double.toString(cell.getNumericCellValue());
                }
            case BOOLEAN:
                return Boolean.toString(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            case BLANK:
                return "";
            default:
                return "";
        }
    }
}
