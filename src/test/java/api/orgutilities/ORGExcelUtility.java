package api.orgutilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ORGExcelUtility {
	
	private String path;
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private FileInputStream fis;
    private FileOutputStream fos;
    private XSSFRow row;
    private Cell cell;
    private XSSFCellStyle greenStyle;

    public ORGExcelUtility(String path) {
        this.path = path;
    }

    public int getRowCount(String sheetName) throws IOException {
        fis = new FileInputStream(path);
        workbook = new XSSFWorkbook(fis);
        sheet = workbook.getSheet(sheetName);
        int rowCount = sheet.getLastRowNum() + 1;
        fis.close();
        return rowCount;
    }

    public String[][] readExcelData(String sheetName) throws IOException {
        fis = new FileInputStream(path);
        workbook = new XSSFWorkbook(fis);
        sheet = workbook.getSheet(sheetName);
        int rowCount = sheet.getLastRowNum();
        int colCount = sheet.getRow(0).getLastCellNum();
        String[][] data = new String[rowCount][colCount];

        for (int i = 1; i <= rowCount; i++) {
            row = sheet.getRow(i);
            for (int j = 0; j < colCount; j++) {
                cell = row.getCell(j);
                data[i - 1][j] = cellToString(cell);
            }
        }
        fis.close();
        return data;
    }

    private String cellToString(Cell cell) {
        DataFormatter formatter = new DataFormatter();
        if (cell.getCellType() == CellType.NUMERIC) {
            return formatter.formatCellValue(cell);
        } else {
            return cell.getStringCellValue();
        }
    }

    public void setCellData(String sheetName, int rowNum, int colNum, String data) throws IOException {
        fis = new FileInputStream(path);
        workbook = new XSSFWorkbook(fis);
        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(rowNum);
        if (row == null) {
            row = sheet.createRow(rowNum);
        }
        cell = row.createCell(colNum);
        cell.setCellValue(data);
        fos = new FileOutputStream(path);
        workbook.write(fos);
        fis.close();
        fos.close();
    }

    public void fillCellWithGreenColor(String sheetName, int rowNum, int colNum) throws IOException {
        fis = new FileInputStream(path);
        workbook = new XSSFWorkbook(fis);
        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(rowNum);
        cell = row.getCell(colNum);
        greenStyle = workbook.createCellStyle();
        greenStyle.setFillForegroundColor(IndexedColors.GREEN.getIndex());
        greenStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cell.setCellStyle(greenStyle);
        fos = new FileOutputStream(path);
        workbook.write(fos);
        fis.close();
        fos.close();
    }
}
