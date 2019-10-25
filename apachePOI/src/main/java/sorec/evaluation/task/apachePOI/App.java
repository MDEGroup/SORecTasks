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


public class App {
	
	public HSSFWorkbook writeExcelFile(String filename) {		
		 HSSFWorkbook workbook = new HSSFWorkbook();
	        HSSFSheet sheet = workbook.createSheet("Datatypes in Java");
	        Object[][] datatypes = {
	                {"Datatype", "Type", "Size(in bytes)"},
	                {"int", "Primitive", 2},
	                {"float", "Primitive", 4},
	                {"double", "Primitive", 8},
	                {"char", "Primitive", 1},
	                {"String", "Non-Primitive", "No fixed size"}
	        };

	        int rowNum = 0;
	        System.out.println("Creating excel");

	        for (Object[] datatype : datatypes) {
	            Row row = sheet.createRow(rowNum++);
	            int colNum = 0;
	            for (Object field : datatype) {
	                Cell cell = row.createCell(colNum++);
	                if (field instanceof String) {
	                    cell.setCellValue((String) field);
	                } else if (field instanceof Integer) {
	                    cell.setCellValue((Integer) field);
	                }
	            }
	        }

	        try {
	            FileOutputStream outputStream = new FileOutputStream(filename);
	            workbook.write(outputStream);
	            workbook.close();           
	            
	           
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }      
	        
	        return workbook;
	    
    }
	
	public HSSFWorkbook readExcel(String filename) {	

	        try {

	            FileInputStream excelFile = new FileInputStream(new File(filename));
	            HSSFWorkbook workbook = new HSSFWorkbook(excelFile);
	            Sheet datatypeSheet = workbook.getSheetAt(0);
	            Iterator<Row> iterator = datatypeSheet.iterator();

	            while (iterator.hasNext()) {

	                Row currentRow = iterator.next();
	                Iterator<Cell> cellIterator = currentRow.iterator();

	                while (cellIterator.hasNext()) {

	                    Cell currentCell = cellIterator.next();
	                    //getCellTypeEnum shown as deprecated for version 3.15
	                    //getCellTypeEnum ill be renamed to getCellType starting from version 4.0
	                    if (currentCell.getCellType() == CellType.STRING) {
	                        System.out.print(currentCell.getStringCellValue() + "--");
	                    } else if (currentCell.getCellType() == CellType.NUMERIC) {
	                        System.out.print(currentCell.getNumericCellValue() + "--");
	                    }

	                }
	                
	                workbook.close();
	                return workbook;
	                

	            }
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
			return null;
	        
	        
	}
	
	
	public HSSFWorkbook createNewCell(String filename) throws FileNotFoundException, IOException {
		
        try (HSSFWorkbook wb = new HSSFWorkbook()) { //or new HSSFWorkbook();
            CreationHelper creationHelper = wb.getCreationHelper();
            Sheet sheet = wb.createSheet("new sheet");

            // Create a row and put some cells in it. Rows are 0 based.
            Row row = sheet.createRow((short) 0);
            // Create a cell and put a value in it.
            Cell cell = row.createCell((short) 0);
            cell.setCellValue(1);

            //numeric value
            row.createCell(1).setCellValue(1.2);

            //plain string value
            row.createCell(2).setCellValue("This is a string cell");

            //rich text string
            RichTextString str = creationHelper.createRichTextString("Apache");
            Font font = wb.createFont();
            font.setItalic(true);
            font.setUnderline(Font.U_SINGLE);
            str.applyFont(font);
            row.createCell(3).setCellValue(str);

            //boolean value
            row.createCell(4).setCellValue(true);

            //formula
            row.createCell(5).setCellFormula("SUM(A1:B1)");

            //date
            CellStyle style = wb.createCellStyle();
            style.setDataFormat(creationHelper.createDataFormat().getFormat("m/d/yy h:mm"));
            cell = row.createCell(6);
            cell.setCellValue(new Date());
            cell.setCellStyle(style);

            //hyperlink
            row.createCell(7).setCellFormula("SUM(A1:B1)");
            cell.setCellFormula("HYPERLINK(\"http://google.com\",\"Google\")");


            // Write the output to a file
            try (FileOutputStream fileOut = new FileOutputStream(filename)) {
                wb.write(fileOut);
                return wb;
                
            }
        }
        
    
	}
	
	public Workbook iterateCell(String filename) throws FileNotFoundException, IOException {
		 try (
	                FileInputStream is = new FileInputStream(filename);
	                Workbook wb = new HSSFWorkbook(is)
	            ) {
	            for (int i = 0; i < wb.getNumberOfSheets(); i++) {
	                Sheet sheet = wb.getSheetAt(i);
	                System.out.println(wb.getSheetName(i));
	                for (Row row : sheet) {
	                    System.out.println("rownum: " + row.getRowNum());
	                    for (Cell cell : row) {
	                        System.out.println(cell);
	                    }
	                }
	                
	            }
	            return wb;		 }
		
	}
	
	public Workbook addCellColors(String filename) throws FileNotFoundException, IOException {
		 try (Workbook wb = new HSSFWorkbook()) { //or new HSSFWorkbook();
	            Sheet sheet = wb.createSheet("new sheet");

	            // Create a row and put some cells in it. Rows are 0 based.
	            Row row = sheet.createRow(1);

	            // Aqua background
	            CellStyle style = wb.createCellStyle();
	            style.setFillBackgroundColor(IndexedColors.AQUA.getIndex());
	            style.setFillPattern(FillPatternType.BIG_SPOTS);
	            Cell cell = row.createCell(1);
	            cell.setCellValue(new HSSFRichTextString("X"));
	            cell.setCellStyle(style);

	            // Orange "foreground", foreground being the fill foreground not the font color.
	            style = wb.createCellStyle();
	            style.setFillForegroundColor(IndexedColors.ORANGE.getIndex());
	            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	            cell = row.createCell(2);
	            cell.setCellValue(new HSSFRichTextString("X"));
	            cell.setCellStyle(style);

	            // Write the output to a file
	            try (FileOutputStream fileOut = new FileOutputStream(filename)) {
	                wb.write(fileOut);
	               return wb;
	            }
	        }
	}
	
	
	
	
	
	public HSSFWorkbook createNewSheet(String filename) throws IOException {
		try (HSSFWorkbook wb = new HSSFWorkbook()) {
            wb.createSheet("new sheet");
            // create with default name
            wb.createSheet();
            final String name = "second sheet";
            // setting sheet name later
            wb.setSheetName(1, WorkbookUtil.createSafeSheetName(name));
            
            try (FileOutputStream fileOut = new FileOutputStream(filename)) {
                wb.write(fileOut);
                return wb;
            }
        }
	}
	
	public Workbook commentsCell(String filename) throws IOException {
	     try (Workbook wb = new HSSFWorkbook()) {

	            CreationHelper factory = wb.getCreationHelper();

	            Sheet sheet = wb.createSheet();

	            Cell cell1 = sheet.createRow(3).createCell(5);
	            cell1.setCellValue("F4");

	            Drawing<?> drawing = sheet.createDrawingPatriarch();

	            ClientAnchor anchor = factory.createClientAnchor();

	            Comment comment1 = drawing.createCellComment(anchor);
	            RichTextString str1 = factory.createRichTextString("Hello, World!");
	            comment1.setString(str1);
	            comment1.setAuthor("Apache POI");
	            cell1.setCellComment(comment1);

	            Cell cell2 = sheet.createRow(2).createCell(2);
	            cell2.setCellValue("C3");

	            Comment comment2 = drawing.createCellComment(anchor);
	            RichTextString str2 = factory.createRichTextString("XSSF can set cell comments");
	            //apply custom font to the text in the comment
	            Font font = wb.createFont();
	            font.setFontName("Arial");
	            font.setFontHeightInPoints((short) 14);
	            font.setBold(true);
	            font.setColor(IndexedColors.RED.getIndex());
	            str2.applyFont(font);

	            comment2.setString(str2);
	            comment2.setAuthor("Apache POI");
	            comment2.setAddress(new CellAddress("C3"));

	            try (FileOutputStream out = new FileOutputStream(filename)) {
	                wb.write(out);
	                return wb;
	            }
	        }
	}
	
	public HSSFWorkbook addCellDate(String filename) throws FileNotFoundException, IOException {
        try (HSSFWorkbook wb = new HSSFWorkbook()) {
            HSSFSheet sheet = wb.createSheet("new sheet");

            // Create a row and put some cells in it. Rows are 0 based.
            HSSFRow row = sheet.createRow(0);

            // Create a cell and put a date value in it.  The first cell is not styled as a date.
            HSSFCell cell = row.createCell(0);
            cell.setCellValue(new Date());

            // we style the second cell as a date (and time).  It is important to create a new cell style from the workbook
            // otherwise you can end up modifying the built in style and effecting not only this cell but other cells.
            HSSFCellStyle cellStyle = wb.createCellStyle();
            cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy h:mm"));
            cell = row.createCell(1);
            cell.setCellValue(new Date());
            cell.setCellStyle(cellStyle);

            // Write the output to a file
            try (FileOutputStream fileOut = new FileOutputStream(filename)) {
                wb.write(fileOut);
                return wb;
            }
        }
    }
		
	
	

}
