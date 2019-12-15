package sorec.evaluation.task.apachePOI;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class App {

	public HSSFWorkbook writeExcelFile(String filename) {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Datatypes in Java");

		return workbook;

	}

	// 10min
	public HSSFWorkbook readExcel(String filename) {

		try {
			FileInputStream excelFile = new FileInputStream(filename);
			Workbook workbook = new XSSFWorkbook(excelFile);
			Sheet datatypeSheet = workbook.getSheetAt(0);
			Iterator<Row> iterator = datatypeSheet.iterator();

			while (iterator.hasNext()) {

				Row currentRow = iterator.next();
				Iterator<Cell> cellIterator = currentRow.iterator();

				while (cellIterator.hasNext()) {

					Cell currentCell = cellIterator.next();
					if (currentCell.getCellTypeEnum() == CellType.STRING) {
						System.out.print(currentCell.getStringCellValue() + "--");
					} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
						System.out.print(currentCell.getNumericCellValue() + "--");
					}

				}
				System.out.println();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}

	// 7 min
	public HSSFWorkbook createNewCell(String filename, Integer rowNo, Integer cellNo)
			throws FileNotFoundException, IOException {

		try (HSSFWorkbook wb = new HSSFWorkbook()) {
			CreationHelper creationHelper = wb.getCreationHelper();
			Sheet sheet = wb.createSheet("new sheet");
			Row row = sheet.createRow(rowNo);
			Cell cell = row.createCell(cellNo);
			try (FileOutputStream outputStream = new FileOutputStream(filename)) {
				workbook.write(outputStream);
			}
			return workbook;

		}
	}

	// 7min
	public Workbook iterateCell(String filename) throws FileNotFoundException, IOException {
		try (FileInputStream is = new FileInputStream(filename); Workbook wb = new HSSFWorkbook(is)) {

			for (Sheet sheet : wb) {
				for (Row row : sheet) {
					for (Cell cell : row) {

					}
				}
			}

			return wb;
		}

	}

	// 10min
	public Workbook addCellColors(String filename) throws FileNotFoundException, IOException {
		try (Workbook wb = new HSSFWorkbook()) { // or new HSSFWorkbook();
			Sheet sheet = wb.createSheet("new sheet");

			// Create a row and put some cells in it. Rows are 0 based.
			Row row = sheet.createRow(1);
			Cell cell = row.createCell(0);
			cell.setCellValue(1);

			// Aqua background
			CellStyle style = wb.createCellStyle();

			style.setFillBackgroundColor(IndexedColors.AQUA.getIndex());
			cell.setCellStyle(style);
			try (FileOutputStream outputStream = new FileOutputStream(filename)) {
				workbook.write(outputStream);

			}
			return workbook;
		}
	}

	// 3min
	public HSSFWorkbook createNewSheet(String filename) throws IOException {
		FileInputStream excelFile = new FileInputStream(filename);
		Workbook workbook = new XSSFWorkbook(excelFile);
		Sheet sheet = workbook.createSheet("New Sheet");
		try (FileOutputStream outputStream = new FileOutputStream(filename)) {
			workbook.write(outputStream);

		}
		return workbook;
	}

	}

//10min
	public Workbook commentsCell(String filename) throws IOException {
	     try (Workbook workbook = new HSSFWorkbook()) {
	    	 Sheet sheet = workbook.createSheet();
	         Drawing drawing = sheet.createDrawingPatriarch();
	            CreationHelper factory = wb.getCreationHelper();
	            ClientAnchor anchor = factory.createClientAnchor();
	            anchor.setCol1(cell.getColumnIndex());
	            anchor.setCol2(cell.getColumnIndex()+1);
	            anchor.setRow1(row.getRowNum());
	            anchor.setRow2(row.getRowNum()+3);
	            
	            Comment c = drawing.createCellComment(anchor);
	            RichTextString str = factory.createRichTextString(comment);
	            c.setString(str);

	            cell.setCellComment(c);
	            
	            try (FileOutputStream outputStream = new FileOutputStream(filename)) {
	              	 workbook.write(outputStream);
	              	
	       	 }
	       	return workbook;
	            
	}

	// 6min
	public HSSFWorkbook addCellDate(String filename) throws FileNotFoundException, IOException {
		try (HSSFWorkbook workbook = new HSSFWorkbook()) {
			HSSFSheet sheet = workbook.createSheet("new sheet");

			HSSFRow row = sheet.createRow(0);
			Cell cell = row.createCell(0);
			cell.setCellValue(new Date());
			
			CellStyle cellStyle = workbook.createCellStyle();
			CreationHelper createHelper = workbook.getCreationHelper();
			cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("m/d/yy h:mm"));
			cell.setCellValue(new Date());
			cell.setCellStyle(cellStyle);

			try (FileOutputStream outputStream = new FileOutputStream(filename)) {
				workbook.write(outputStream);

			}
			return workbook;
		}
	}

}
