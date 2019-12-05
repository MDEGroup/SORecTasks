package sorec.evaluation.task.apachePOI;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.Comment;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.ss.util.WorkbookUtil;

public class App {
	
	/*
	 * notes: before start the methods implementation I spent 10/15 minutes 
	 * to fix the pom.xml, AppTest.java and some little errors in the code above. 
	 */
	
	// 18:50 - START SESSION
	public HSSFWorkbook writeExcelFile(String filename) {

		try {
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet sheet = workbook.createSheet("Datatypes in Java");

			FileOutputStream outputStream = new FileOutputStream(filename);
			workbook.write(outputStream);
			workbook.close();

			return workbook;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;

	}

	// 18:52
	public HSSFWorkbook readExcel(String filename) {

		try {

			FileInputStream excelFile = new FileInputStream(new File(filename));
			HSSFWorkbook workbook = new HSSFWorkbook(excelFile);

			return workbook;

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	// 19:02
	public HSSFWorkbook createNewCell(String filename) throws FileNotFoundException, IOException {

		try (HSSFWorkbook wb = new HSSFWorkbook()) { // or new HSSFWorkbook();

			CreationHelper creationHelper = wb.getCreationHelper();

			Sheet sheet = wb.createSheet("new sheet");

			// Create a row and put some cells in it. Rows are 0 based.
			Row row = sheet.createRow(0);
			// Create a cell and put a value in it.
			Cell cell = row.createCell(0);
			cell.setCellValue(1);

			// Write the output to a file
			try (OutputStream fileOut = new FileOutputStream(filename)) {
				wb.write(fileOut);
			}

			return wb;
		}
	}

	// 19:06
	public Workbook iterateCell(String filename) throws FileNotFoundException, IOException {
		try (FileInputStream is = new FileInputStream(filename); Workbook wb = new HSSFWorkbook(is)) {

			int i = 0;

			// A che serve iterare se torna il WorkBook?!
			for (Sheet sheet : wb) {
				for (Row row : sheet) {
					for (Cell cell : row) {
						i++;
					}
				}
			}

			return wb;
		}
	}

	// 19:09
	public Workbook addCellColors(String filename) throws FileNotFoundException, IOException {
		try (Workbook wb = new HSSFWorkbook()) { // or new HSSFWorkbook();
			Sheet sheet = wb.createSheet("new sheet Color");

			// Create a row and put some cells in it. Rows are 0 based.
			Row row = sheet.createRow(1);

			// Aqua background
			CellStyle style = wb.createCellStyle();
			
			style.setFillBackgroundColor(IndexedColors.AQUA.getIndex());
			style.setFillPattern(FillPatternType.BIG_SPOTS);

			Cell cell = row.createCell(1);
			cell.setCellValue("X");
			cell.setCellStyle(style);

			// Write the output to a file
			try (OutputStream fileOut = new FileOutputStream(filename)) {
				wb.write(fileOut);
			}

			return wb;
		}
	}

	// 19:11
	public HSSFWorkbook createNewSheet(String filename) throws IOException {
		try (HSSFWorkbook wb = new HSSFWorkbook()) { // or new HSSFWorkbook();

			Sheet sheet = wb.createSheet("new sheet x");

			// Write the output to a file
			try (OutputStream fileOut = new FileOutputStream(filename)) {
				wb.write(fileOut);
			}

			return wb;
		}

	}

	// 19:12
	public Workbook commentsCell(String filename) throws IOException {
		try (Workbook wb = new HSSFWorkbook()) {

			CreationHelper factory = wb.getCreationHelper();

			Sheet sheet = wb.createSheet();

			Row row = sheet.createRow(3);
			Cell cell = row.createCell(5);

			cell.setCellValue("F4");

			Drawing drawing = sheet.createDrawingPatriarch();

			// When the comment box is visible, have it show in a 1x3 space
			ClientAnchor anchor = factory.createClientAnchor();
			anchor.setCol1(cell.getColumnIndex());
			anchor.setCol2(cell.getColumnIndex() + 1);
			anchor.setRow1(row.getRowNum());
			anchor.setRow2(row.getRowNum() + 3);

			// Create the comment and set the text+author
			Comment comment = drawing.createCellComment(anchor);
			RichTextString str = factory.createRichTextString("Hello, World!");

			comment.setString(str);
			comment.setAuthor("Apache POI");

			// Assign the comment to the cell
			cell.setCellComment(comment);

			try (OutputStream out = new FileOutputStream(filename)) {
				wb.write(out);
			}
			wb.close();

			return wb;
		}
	}

	// 19:16
	public HSSFWorkbook addCellDate(String filename) throws FileNotFoundException, IOException {
		try (HSSFWorkbook wb = new HSSFWorkbook()) {
			HSSFSheet sheet = wb.createSheet("new sheet");

			HSSFRow row = sheet.createRow(0);

			CreationHelper createHelper = wb.getCreationHelper();

			Cell cell = row.createCell(0);

			cell.setCellValue(new Date());

			CellStyle cellStyle = wb.createCellStyle();
			cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("m/d/yy h:mm"));
			cell = row.createCell(1);
			cell.setCellValue(new Date());
			cell.setCellStyle(cellStyle);

			// Write the output to a file
			try (OutputStream fileOut = new FileOutputStream(filename)) {
				wb.write(fileOut);
			}

			return wb;
		}
	}

	// 19:17 END SESSION
	// note: all info for writing code, come from: https://poi.apache.org/components/spreadsheet/quick-guide.html
}
