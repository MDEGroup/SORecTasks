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
	      
	        return workbook;
	    
    }
	
	public HSSFWorkbook readExcel(String filename) {	

	        try {

	            FileInputStream excelFile = new FileInputStream(new File(filename));
	    
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

        }
        
    
	}
	
	public Workbook iterateCell(String filename) throws FileNotFoundException, IOException {
		 try (
	                FileInputStream is = new FileInputStream(filename);
	                Workbook wb = new HSSFWorkbook(is)
	            ) {
	          
	                
	            
	            return wb;		 }
		
	}
	
	public Workbook addCellColors(String filename) throws FileNotFoundException, IOException {
		 try (Workbook wb = new HSSFWorkbook()) { //or new HSSFWorkbook();
	            Sheet sheet = wb.createSheet("new sheet");

	            // Create a row and put some cells in it. Rows are 0 based.
	            Row row = sheet.createRow(1);

	            // Aqua background
	            CellStyle style = wb.createCellStyle();
	        
	        }
	}
	
	
	
	
	
	public HSSFWorkbook createNewSheet(String filename) throws IOException {
		
        }
	}
	
	public Workbook commentsCell(String filename) throws IOException {
	     try (Workbook wb = new HSSFWorkbook()) {

	            CreationHelper factory = wb.getCreationHelper();

	            
	}
	
	public HSSFWorkbook addCellDate(String filename) throws FileNotFoundException, IOException {
        try (HSSFWorkbook wb = new HSSFWorkbook()) {
            HSSFSheet sheet = wb.createSheet("new sheet");
            
            HSSFRow row = sheet.createRow(0);

           
        }
    }
		
	
	

}
