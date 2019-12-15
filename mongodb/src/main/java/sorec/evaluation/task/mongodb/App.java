package sorec.evaluation.task.mongodb;

import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.bson.BsonDocument;
import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import com.mongodb.client.result.DeleteResult;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.gt;
import static com.mongodb.client.model.Filters.lt;
/**
 * Hello world!
 *
 */
public class App {
	
	//13min
	public List<Document> findAll(String collectionName) {
		List<Document> documents = new ArrayList<Document>();
		try (MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://admin:myadminpassword@178.238.238.209:37017"))) {
			MongoDatabase database = mongoClient.getDatabase("CROSSMINER");
			MongoIterable<Document> cursor = database.getCollection(collectionName).find();
			
			while (cursor.iterator().hasNext()) {
				Document document = cursor.iterator().next();
				documents.add(document);
			}
			
		} catch (Exception e) {
		
		}
		return documents;
	}

	//20min
	public Document findOneByFullName(String collectionName, String fullName) {
		Document document = new Document();
		try (MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://admin:myadminpassword@178.238.238.209:37017"))) {
			MongoDatabase database = mongoClient.getDatabase("CROSSMINER");
			MongoCollection<Document> collection = database.getCollection(collectionName);
			
			MongoIterable<Document> cursor = collection.find(eq("fullName", fullName));
			
			while (cursor.iterator().hasNext()) {
				document = cursor.iterator().next();
			}
			
			cursor.cursor().close();
	
		}catch (Exception e) {
			
			}
		return document;
	}		
		
	//5min
	public Document updateDocument(String collectionName, String fullName) {
		Document document = new Document("fullName", fullName);
		try (MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://admin:myadminpassword@178.238.238.209:37017"))) {
			MongoDatabase database = mongoClient.getDatabase("CROSSMINER");
			MongoCollection<Document> collection = database.getCollection(collectionName);
			
			collection.updateOne(eq("fullName", fullName), document );
	
	
		}catch (Exception e) {
			
			}
		return document;
	}

	//7min
	public Collection<Document> filterQuery(String collectionName, String field) {
		List<Document> documents = new ArrayList<Document>();
		try (MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://admin:myadminpassword@178.238.238.209:37017"))) {
			MongoDatabase database = mongoClient.getDatabase("CROSSMINER");
			MongoCollection<Document> collection = database.getCollection(collectionName);
			MongoCursor<Document> cursor = collection.find(eq("field", field)).cursor();
			
			while (cursor.hasNext()) {
				documents.add(cursor.next());
			}
	
		}catch (Exception e) {
			
			}
		
		return documents;
	}
	
	//15min
	public BsonDocument BsonFilter(String collectionName) {
		Bson filter = and(eq("field1", "value"), gt("field2", "value2"));
		BsonDocument bsonDocument = filter.toBsonDocument(BsonDocument.class, MongoClient.getDefaultCodecRegistry());
		try (MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://admin:myadminpassword@178.238.238.209:37017"))) {
			MongoDatabase database = mongoClient.getDatabase("CROSSMINER");
			MongoCollection<Document> collection =  database.getCollection(collectionName);
			MongoCursor<Document> cursor = collection.find(bsonDocument).cursor();
			
			while (cursor.hasNext()) {
				System.out.println(cursor.next());
			}
			
	
		}catch (Exception e) {
			
			}
		return bsonDocument;

	}
	
	//5min
	public MongoCollection<Document>  insertSingleDocument(String collectionName) {
		try (MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://admin:myadminpassword@178.238.238.209:37017"))) {
			MongoDatabase database = mongoClient.getDatabase("CROSSMINER");
			MongoCollection<Document> collection =  database.getCollection(collectionName);
			Document document = new Document("i", 5);
		    collection.insertOne(document);  
		    return collection; 
		    
		} catch (Exception e) {
			
			System.err.println("Error in query the collection " + collectionName);
			throw e;
		}
		
	}
	
	//1min
	public MongoCollection<Document> insertMultipleDocument(String collectionName) {
		try (MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://admin:myadminpassword@178.238.238.209:37017"))) {
			MongoDatabase database = mongoClient.getDatabase("CROSSMINER");
			MongoCollection<Document> collection =  database.getCollection(collectionName);
			
			List<Document> documents = new ArrayList<Document>();
			
			for (int i = 0; i < 10; i++) {
			    documents.add(new Document("i", i));
			}
		    
			collection.insertMany(documents);  
		    
		    return collection;
	 
	        
		} catch (Exception e) {
			System.err.println("Error in query the collection " + collectionName);
			throw e;
		}
	}
	
	//2min
	public boolean deleteDocument(String collectionName, String fullName) {
		try (MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://admin:myadminpassword@178.238.238.209:37017"))) {

			MongoDatabase database = mongoClient.getDatabase("CROSSMINER");
			MongoCollection<Document> collection = database.getCollection(collectionName);
			
			DeleteResult deleteresult = collection.deleteOne(eq("fullName", fullName));
			
			return deleteresult.wasAcknowledged();
			
	        
		} catch (Exception e) {
			System.err.println("Error in query the collection " + collectionName);
			throw e;
		}
	}
	
}
