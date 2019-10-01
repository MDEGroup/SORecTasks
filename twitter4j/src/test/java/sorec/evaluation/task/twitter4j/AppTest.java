package sorec.evaluation.task.twitter4j;

import static org.junit.Assert.assertNotEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import twitter4j.TwitterException;

/**
 * Unit test for simple App.
 */
public class AppTest {
    

	private App app;
	@Before
	public void init() throws TwitterException, IOException {
		app = new App();
	}
   

    @Test
    public void search4Twitter() throws TwitterException, IOException  {
    	assertNotEquals(0, app.search4Twitter().size(), 0);
    }
   
}
