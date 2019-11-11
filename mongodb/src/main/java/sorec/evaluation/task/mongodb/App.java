package sorec.evaluation.task.mongodb;

import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.bson.BsonDocument;
import org.bson.Document;
import org.bson.conversions.Bson;


import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.gt;
import static com.mongodb.client.model.Filters.lt;
/**
 * Hello world!
 *
 */
public class App {
	public List<Document> findAll(String collectionName) {
		List<Document> documents = new ArrayList<Document>();
		try (MongoClient mongoClient = new MongoClient("localhost", 27017)) {
			MongoDatabase database = mongoClient.getDatabase("CROSSMINER");
			
		} catch (Exception e) {
		
		}
		return documents;
	}

	public Document findOneByFullName(String collectionName, String fullName) {
		return null;
	}

	public Document updateDocument(String collectionName, String fullName) {
		return null;
	}

	public boolean deleteDocument(String collectionName, String fullName) {
		
	}

	public Collection<Document> filterQuery(String collectionName, String field) {
		 
	}
	
	public BsonDocument BsonFilter(String collectionName) {
		Bson filter = and(eq("field1", "value"), gt("field2", "value2"));
		BsonDocument bsonDocument = filter.toBsonDocument(BsonDocument.class, MongoClient.getDefaultCodecRegistry());

	}
	
	public MongoCollection<Document>  insertSingleDocument(String collectionName) {
		try (MongoClient mongoClient = new MongoClient("localhost", 27017)) {
			
		      
		        
		} catch (Exception e) {
			System.err.println("Error in query the collection " + collectionName);
			throw e;
		}
		
	}
	public MongoCollection<Document> insertMultipleDocument(String collectionName) {
		try (MongoClient mongoClient = new MongoClient("localhost", 27017)) {
			
	 
	        
		} catch (Exception e) {
			System.err.println("Error in query the collection " + collectionName);
			throw e;
		}
	}
	
}
