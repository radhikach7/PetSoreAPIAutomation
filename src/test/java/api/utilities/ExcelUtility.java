 package api.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

	public FileInputStream fi;
	public FileOutputStream fo;
	public File f;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	public CellStyle cstyle;

	private String path;

	public ExcelUtility(String path) {
		this.path = path;

	}

	public int getRowCount(String sheetname) throws IOException {
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetname);
		int rowCount = sheet.getLastRowNum();
		workbook.close();
		fi.close();
		return rowCount;

	}

	public int getCellCount(String sheetname, int rownum) throws IOException {

		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetname);
		row = sheet.getRow(rownum);
		int cellCount = row.getLastCellNum();
		workbook.close();
		fi.close();
		return cellCount;

	}

	public String getCellData(String sheetname, int rownum, int colnum) throws IOException {
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);

		sheet = workbook.getSheet(sheetname);
		row = sheet.getRow(rownum);
		cell = row.getCell(colnum);

		DataFormatter formatter = new DataFormatter();
		String data;
		try {
			data = formatter.formatCellValue(cell);// returns the formatted value of a cell as string regardless of

		} catch (Exception e) {

			data = "";
		}

		workbook.close();
		fi.close();
		return data;

	}

	// if file not available create data
	public void setCellData(String sheetname, int rownum, int colnum, String data) throws IOException {
		File xfile = new File(path);
		if (!xfile.exists())// if file not exists the create new file
		{
			workbook = new XSSFWorkbook();
			fo = new FileOutputStream(path);
			workbook.write(fo);
		}

		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);

		if (workbook.getSheetIndex(sheetname) == 1)// if sheet not exists then create new sheet.
		{
			workbook.createSheet(sheetname);
			sheet = workbook.getSheet(sheetname);

			if (sheet.getRow(rownum) == null)
			// if row not exists then create new row

			{
				sheet.createRow(rownum);
				row = sheet.getRow(rownum);

				cell = row.createCell(colnum);
				cell.setCellValue(data);
				fo = new FileOutputStream(path);

				workbook.write(fo);
				;
				workbook.close();
				fi.close();
				fo.close();

			}

		}
	}

	public void fillGreenClor(String sheetname, int rownum, int colnum) throws IOException {

		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetname);

		row = sheet.getRow(rownum);
		cell = row.getCell(colnum);

		cstyle = workbook.createCellStyle();

		cstyle.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		cstyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		cell.setCellStyle(cstyle);
		workbook.write(fo);

		workbook.close();
		fi.close();
		fo.close();

	}

}
