package sorec.evaluation.task.twitter4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

/**
 * Hello world!
 *
 */
public class App {
	private static AccessToken accessToken = new AccessToken("1179013857517408256-2Os56dlYd904qE9MBjPBzvE2NmTAAS","u7gCds8rthihTGLLglvcROeCfoBnaQVBGxZdIYrlZ7qxi");

	public List<Status> search4Twitter() throws TwitterException {

		twitter.setOAuthAccessToken(accessToken);
		try {
			Query query = new Query("juventus");
			QueryResult result;
			do {
				result = twitter.search(query);
				return result.getTweets();
			} while ((query = result.nextQuery()) != null);

		} catch (

		TwitterException te) {
			te.printStackTrace();
			System.out.println("Failed to search tweets: " + te.getMessage());
			System.exit(-1);
			throw te;
		}

	}
	Twitter twitter = TwitterFactory.getSingleton();

	@SuppressWarnings("static-access")
	public App() throws TwitterException, IOException {
		
		twitter.setOAuthConsumer("9pmJstSiURFHbJkm26ceNM2Ys", "y2hcgvz4FwcF3xNGJ1l08OmlGQpviKdVSRamuU0t1ficF7OYoo");
//		twitter.setOAuthAccessToken(new AccessToken("1179013857517408256-2Os56dlYd904qE9MBjPBzvE2NmTAAS","u7gCds8rthihTGLLglvcROeCfoBnaQVBGxZdIYrlZ7qxi"));
		RequestToken requestToken = twitter.getOAuthRequestToken();
		AccessToken accessToken = null;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (null == accessToken) {
			System.out.println("Open the following URL and grant access to your account:");
			System.out.println(requestToken.getAuthorizationURL());
			System.out.print("Enter the PIN(if aviailable) or just hit enter.[PIN]:");
			String pin = br.readLine();
			try {
				if (pin.length() > 0) {
					accessToken = twitter.getOAuthAccessToken(requestToken, pin);
				} else {
					accessToken = twitter.getOAuthAccessToken(requestToken);
				}
			} catch (TwitterException te) {
				if (401 == te.getStatusCode()) {
					System.out.println("Unable to get the access token.");
				} else {
					te.printStackTrace();
				}
			}
		}
		// persist to the accessToken for future reference.
		this.accessToken = accessToken;
	}

}
