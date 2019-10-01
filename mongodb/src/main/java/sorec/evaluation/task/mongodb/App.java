package sorec.evaluation.task.mongodb;

import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

/**
 * Hello world!
 *
 */
public class App {
	public List<Document> findAll(String collectionName) {
		List<Document> documents = new ArrayList<Document>();
		try (MongoClient mongoClient = new MongoClient("localhost", 27017)) {
			MongoDatabase database = mongoClient.getDatabase("CROSSMINER");
			MongoCollection<Document> collection = database.getCollection(collectionName);
			MongoCursor<Document> cursor = collection.find().iterator();
			try {
				while (cursor.hasNext()) {
					documents.add(cursor.next());
				}
			} finally {
				cursor.close();
			}
		} catch (Exception e) {
			System.err.println("Error in query the collection " + collectionName);
			throw e;
		}
		return documents;
	}

	public Document findOneByFullName(String collectionName, String fullName) {
		try (MongoClient mongoClient = new MongoClient("localhost", 27017)) {
			MongoDatabase database = mongoClient.getDatabase("CROSSMINER");
			MongoCollection<Document> collection = database.getCollection(collectionName);
			return collection.find(eq("fullName", fullName)).first();
		} catch (Exception e) {
			System.err.println("Error in query the collection " + collectionName);
			throw e;
		}
	}

	public Document updateDocument(String collectionName, String fullName) {
		try (MongoClient mongoClient = new MongoClient("localhost", 27017)) {
			MongoDatabase database = mongoClient.getDatabase("CROSSMINER");
			MongoCollection<Document> collection = database.getCollection(collectionName);
			collection.updateOne(eq("fullName", fullName), new Document("$set", new Document("year", 1110)));
			return collection.find(eq("fullName", fullName)).first();
		} catch (Exception e) {
			System.err.println("Error in query the collection " + collectionName);
			throw e;
		}
	}

	public boolean deleteDocument(String collectionName, String fullName) {
		try (MongoClient mongoClient = new MongoClient("localhost", 27017)) {
			MongoDatabase database = mongoClient.getDatabase("CROSSMINER");
			MongoCollection<Document> collection = database.getCollection(collectionName);
			return collection.deleteOne(eq("fullName", fullName)).getDeletedCount() > 0 ? true : false;
		} catch (Exception e) {
			System.err.println("Error in query the collection " + collectionName);
			throw e;
		}
	}
}
