package sorec.evaluation.task.apachePOI;

import org.apache.poi.hssf.util.HSSFColor;
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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;


public class App {
		
	//7min using SORec
		public HSSFWorkbook writeExcelFile(String filename) {		
			 HSSFWorkbook workbook = new HSSFWorkbook();
		        HSSFSheet sheet = workbook.createSheet("Datatypes in Java");
		        
		        int rownum = 0,cellnum=0;
		        Row row1 = sheet.createRow((short)rownum++);

		        Cell cell10 = row1.createCell((short)cellnum++);
		        cell10.setCellValue("cell data");
		        try{             
		            //Write the workbook in file system
		            FileOutputStream out = new FileOutputStream(new File(filename));
		            workbook.write(out); 
		            out.close(); 
		            System.out.println("Spreadsheet.xlsx written successfully on disk."); 
		        } catch (Exception e) { e.printStackTrace(); } 
		        
		        return workbook;
		    
	    }
		

		//5min with SORec
		public HSSFWorkbook readExcel(String filename) {	

			File excel = null;
		    FileInputStream file = null;
		    Cell cell = null;

		    try {

		        excel = new File(filename);
		        file  = new FileInputStream(excel);

		        HSSFWorkbook workbook = new HSSFWorkbook(file);
		        HSSFSheet sheet = workbook.getSheetAt(0);
		        Iterator<Row> rowIterator = sheet.iterator();

		        while (rowIterator.hasNext()) {

		            Row row = rowIterator.next();
		            Iterator<Cell> cellIterator = row.cellIterator();

		            while (cellIterator.hasNext()) {

		                cell = cellIterator.next();

		                if (!cell.getStringCellValue().isEmpty()) {

		                        System.out.println(cell.getStringCellValue());
		                        
		                }
		            }
		        }   
		        
		        return workbook;
		        
		        } catch (FileNotFoundException e) {
		            e.printStackTrace();
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
				return null;
		        
		        
		}
		
		//7min with SORec
		public HSSFWorkbook createNewCell(String filename) throws FileNotFoundException, IOException {
	        
	        HSSFWorkbook wb = new HSSFWorkbook();
	        Font f = wb.createFont();
	        f.setBold(true);
	        CellStyle cs = wb.createCellStyle();
	        cs.setFont(f);

	        CreationHelper createHelper = wb.getCreationHelper();
	        Sheet sheet = wb.createSheet("First Sheet");
	        Row row = sheet.createRow((short) 0);

	        Cell c = null;

	        c = row.createCell(0);
	        c.setCellStyle(cs);
	        c.setCellValue(createHelper.createRichTextString("First Column"));

	        c = row.createCell(1);
	        c.setCellStyle(cs);
	        c.setCellValue(createHelper.createRichTextString("Second Column"));

	        c = row.createCell(2);
	        c.setCellStyle(cs);
	        c.setCellValue(createHelper.createRichTextString("Third Column"));
	        
	        Row row1 = sheet.createRow((short) 1);
	        c = row1.createCell(0);
	        c.setCellValue("1111");
	        
	        c = row1.createCell(1);
	        c.setCellValue("2222");
	        
	        c = row1.createCell(2);
	        c.setCellValue("3333");

	        // Write the output to a file
	        FileOutputStream fileOut;
	        try {
	            fileOut = new FileOutputStream(filename);
	            wb.write(fileOut);
	            fileOut.close();
	            return wb;
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        
	        return null;
		}
		
		//5min with SORec
		public Workbook iterateCell(String filename) throws FileNotFoundException, IOException {
			 try{
				 FileInputStream is = new FileInputStream(filename);
	             Workbook wb = new HSSFWorkbook(is);
		         Sheet firstSheet = wb.getSheetAt(0);
		         Iterator<Row> iterator = firstSheet.iterator();

		                    while(iterator.hasNext()){
		                         Row nextRow = iterator.next();
		                         Iterator<Cell> cellIterator = nextRow.cellIterator();
		                         while (cellIterator.hasNext()) {
		                             Cell cell = cellIterator.next();
		                              
		                             String text = cell.getStringCellValue();
		                             System.out.println(text);    
		                               
		                             }
		                    }
		                    wb.close();
		                    
		         return wb;		 
		         }
			 catch (IOException e) {
				 e.printStackTrace();
			 }
			 return null;
		}
		
		//20 min, at the end I have to use a search on web, SORec doesn't help me totally
		public Workbook addCellColors(String filename) throws FileNotFoundException, IOException {
			 try {	            
				 
	            Workbook wb = new HSSFWorkbook();
	            Sheet sheet = wb.createSheet("new sheet");
		           
	            Row row = sheet.createRow(1);
	             
	            CellStyle cs = wb.createCellStyle();
	 			
	 			cs.setFillBackgroundColor(IndexedColors.AQUA.getIndex());
	 			cs.setFillPattern(FillPatternType.BIG_SPOTS);

	 			Cell cell = row.createCell(1);
	 			cell.setCellValue("new one");
	 			cell.setCellStyle(cs);
	 			
	 			FileOutputStream fileout = new FileOutputStream(filename);
	 			wb.write(fileout);
		        return wb;	
				 
		        } catch(IOException e) {
		        	e.printStackTrace();
		        }
			 
			 return null;
		}
		
		//5 min, SORec doesn't help me
		public HSSFWorkbook createNewSheet(String filename) throws IOException {
			
			HSSFWorkbook wb = new HSSFWorkbook();
			
			HSSFSheet sheet1= wb.createSheet("sheet1");
			HSSFSheet sheet2= wb.createSheet("sheet2");
			HSSFSheet sheet3= wb.createSheet("sheet3");
			
			FileOutputStream out = new FileOutputStream(filename);
			wb.write(out);
			wb.close();
			out.close();
			
			return wb;

	       
		}

		//15 min, SORec doesn't help me, I had to go on the web
		public Workbook commentsCell(String filename) throws IOException {
		     try {
		    	 FileInputStream is = new FileInputStream(filename);	
		    	 Workbook wb = new HSSFWorkbook(is);
		    	 
		    	 Sheet sheet = wb.getSheetAt(0);
		    	 Row row = sheet.getRow(0);
		    	 Cell cell = row.getCell(0);
		    	 
		    	 	Drawing<?> drawing = cell.getSheet().createDrawingPatriarch();
		    	    CreationHelper factory = cell.getSheet().getWorkbook().getCreationHelper();
		    	    // When the comment box is visible, have it show in a 1x3 space
		    	    ClientAnchor anchor = factory.createClientAnchor();
		    	    anchor.setCol1(cell.getColumnIndex());
		    	    anchor.setCol2(cell.getColumnIndex() + 1);
		    	    anchor.setRow1(cell.getRowIndex());
		    	    anchor.setRow2(cell.getRowIndex() + 1);
		    	    anchor.setDx1(100);
		    	    anchor.setDx2(100);
		    	    anchor.setDy1(100);
		    	    anchor.setDy2(100);

		    	    // Create the comment and set the text+author
		    	    Comment comment = drawing.createCellComment(anchor);
		    	    RichTextString str = factory.createRichTextString("hello");
		    	    comment.setString(str);
		    	    comment.setAuthor("Apache POI");
		    	    // Assign the comment to the cell
		    	    cell.setCellComment(comment);
		    	    
		            FileOutputStream out = new FileOutputStream(new File(filename));
		            wb.write(out); 
		            out.close(); 
		            
		            return wb;
		     }	    
		    catch (IOException e) {
		    	e.printStackTrace();
		    }
		     
		     return null;
		            
		}
		
		//10 min, SORec doesn't help me (It shows me relevant posts for this task when I searched for other tasks but not when i need it) 
		public HSSFWorkbook addCellDate(String filename) throws FileNotFoundException, IOException {
	        	HSSFWorkbook wb = new HSSFWorkbook();
	            HSSFSheet sheet = wb.createSheet("new sheet");
	            
	            HSSFRow row = sheet.createRow(0);
	            HSSFCell cell = row.createCell((short) 0);
	            cell.setCellType(CellType.NUMERIC);

	            SimpleDateFormat datetemp = new SimpleDateFormat("yyyy-MM-dd");
	            Date cellValue = null;
				try {
					cellValue = datetemp.parse("1994-01-01 12:00");
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            cell.setCellValue(cellValue);

	            //binds the style you need to the cell.
	            HSSFCellStyle dateCellStyle = wb.createCellStyle();
	            short df = wb.createDataFormat().getFormat("dd-mmm");
	            dateCellStyle.setDataFormat(df);
	            cell.setCellStyle(dateCellStyle);
	           
	           return wb; 
	        
	    }
}
