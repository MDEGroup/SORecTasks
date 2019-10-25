package sorec.evaluation.task.apachePOI;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;

public class AppTest {
	App app;
	
	@Before
	public void init() {
		app = new App();
	}
	
	@Test
	public void writeExcelTest() {
		assertNotNull(app.writeExcelFile("C://Users//claudio//Desktop//excel.xls"));
	}
	@Test
	public void readExcelFileTest() {
		assertNotNull(app.readExcel("C://Users//claudio//Desktop//excel.xls"));
	}
	@Test
	public void createNewCellTest() throws FileNotFoundException, IOException {
		assertNotNull(app.createNewCell("C://Users//claudio//Desktop//excel.xls"));
	}
	@Test
	public void iterateCellTest() throws FileNotFoundException, IOException {
		assertNotEquals(app.iterateCell("C://Users//claudio//Desktop//excel.xls").getNumberOfSheets(),0);
	}

	@Test
	public void addCellColorsTest() throws IOException {
		assertNotNull(app.addCellColors("C://Users//claudio//Desktop//excel.xls"));
	}
	@Test
	public void createNewSheetTest() throws IOException {
		HSSFWorkbook wb = app.readExcel("C://Users//claudio//Desktop//excel.xls");
		int begin=wb.getNumberOfSheets();		
		app.createNewSheet("C://Users//claudio//Desktop//excel.xls");
		int end=wb.getNumberOfSheets();		
		assertEquals(begin, end);
	}
	
	@Test
	public void commentsCellTest() throws IOException {
		assertNotNull(app.commentsCell("C://Users//claudio//Desktop//excel.xls"));
	}
	
	@Test
	public void addCellDateTest() throws IOException {
		assertNotNull(app.addCellDate("C://Users//claudio//Desktop//excel.xls"));
	}
	
	


}
