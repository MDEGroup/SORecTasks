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
		assertNotNull(app.writeExcelFile("./excel.xls"));
	}
	@Test
	public void readExcelFileTest() {
		assertNotNull(app.readExcel("./excel.xls"));
	}
	@Test
	public void createNewCellTest() throws FileNotFoundException, IOException {
		assertNotNull(app.createNewCell("./excel.xls"));
	}
	@Test
	public void iterateCellTest() throws FileNotFoundException, IOException {
		assertNotEquals(app.iterateCell("./excel.xls").getNumberOfSheets(),0);
	}

	@Test
	public void addCellColorsTest() throws IOException {
		assertNotNull(app.addCellColors("./excel.xls"));
	}
	
	@Test
	public void createNewSheetTest() throws IOException {
		HSSFWorkbook wb = app.readExcel("./excel.xls");
		int begin=wb.getNumberOfSheets();		
		app.createNewSheet("./excel.xls");
		int end=wb.getNumberOfSheets();		
		assertEquals(begin, end);
	}
	
	@Test
	public void commentsCellTest() throws IOException {
		assertNotNull(app.commentsCell("./excel.xls"));
	}
	
	@Test
	public void addCellDateTest() throws IOException {
		assertNotNull(app.addCellDate("./excel.xls"));
	}
	
	


}
