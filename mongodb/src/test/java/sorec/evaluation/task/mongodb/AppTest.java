package sorec.evaluation.task.mongodb;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import org.bson.Document;
import org.junit.Before;
import org.junit.Test;


/**
 * Unit test for simple App.
 */
public class AppTest 
{

	private App app;
	@Before
	public void init() {
		app = new App();
	}
	@Test
	public void findAllTest() {		
		assertNotEquals(app.findAll("artifact").size(), 0);
	}
	@Test
	public void findOneByFullNameTest() {	
		Document art = app.findOneByFullName("artifact", "neo4j-contrib/sparql-plugin");
		assertNotNull(art);
	}
	@Test
	public void updateDocumentTest() {	
		Document art = app.updateDocument("artifact", "neo4j-contrib/sparql-plugin");
		assertNotNull(art);
	}
	@Test
	public void deleteDocumentTest() {	
		int init = app.findAll("artifact").size();
		app.deleteDocument("artifact", "AskNowQA/AutoSPARQL");
		int post = app.findAll("artifact").size();
		assertEquals(init, post+1);
	}
}
