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

	public Collection<Document> filterQuery(String collectionName, String field) {
		 try  (MongoClient mongoClient = new MongoClient("localhost", 27017)){
		   	MongoDatabase database = mongoClient.getDatabase("CROSSMINER");        
	        MongoCollection<Document> collection = database.getCollection(collectionName);
	        FindIterable fit = collection.find(and(lt(field, 50000),
	                gt(field, 20000))).sort(new Document(field, -1));
	        ArrayList<Document> docs = new ArrayList<Document>();
	        return fit.into(docs);           
	        
		 }catch (Exception e) {
				System.err.println("Error in query the collection " + collectionName);
				throw e;
			}
	}
	
	public BsonDocument BsonFilter(String collectionName) {
		Bson filter = and(eq("field1", "value"), gt("field2", "value2"));
		BsonDocument bsonDocument = filter.toBsonDocument(BsonDocument.class, MongoClient.getDefaultCodecRegistry());

		Bson optionalFilter = eq("field3", "optionalValue");
		BsonDocument optionalBsonDocument = optionalFilter.toBsonDocument(BsonDocument.class, MongoClient.getDefaultCodecRegistry());

		// now add the optional filter to the BsonDocument representation of the original filter
		bsonDocument.append("field3", optionalBsonDocument.get("field3"));

		Bson completeFilter = and(eq("field1", "value"), gt("field2", "value2"), eq("field3", "optionalValue"));
		BsonDocument completeBsonDocument = completeFilter.toBsonDocument(BsonDocument.class, MongoClient.getDefaultCodecRegistry());
		return completeBsonDocument;
	}
	
	public MongoCollection<Document>  insertSingleDocument(String collectionName) {
		try (MongoClient mongoClient = new MongoClient("localhost", 27017)) {
			MongoDatabase database = mongoClient.getDatabase("CROSSMINER");
			MongoCollection<Document> collection = database.getCollection(collectionName);
			 Document emp1 = new Document();
		        emp1.put("name", "yatin batra");
		        emp1.put("website", "javacodegeeks.com");
		 
		        Document emp1_addr = new Document();
		        emp1_addr.put("addr_line1", "Savannah, Illinois");      
		        emp1_addr.put("zip_code", "85794");
		        emp1.put("address", emp1_addr);
		 
		        collection.insertOne(emp1);
		        return collection;
		        
		} catch (Exception e) {
			System.err.println("Error in query the collection " + collectionName);
			throw e;
		}
		
	}
	public MongoCollection<Document> insertMultipleDocument(String collectionName) {
		try (MongoClient mongoClient = new MongoClient("localhost", 27017)) {
			MongoDatabase database = mongoClient.getDatabase("CROSSMINER");
			MongoCollection<Document> collection = database.getCollection(collectionName);
			 // Sample document.
	        Document emp2 = new Document();
	        emp2.put("name", "Charlotte Neil");
	        emp2.put("website", "webcodegeeks.com");
	 
	        Document emp2_addr = new Document();
	        emp2_addr.put("addr_line1", "Fremont, AK");
	        emp2_addr.put("zip_code", "19408");
	        emp2.put("address", emp2_addr);
	 
	        // Sample document.
	        Document emp3 = new Document();
	        emp3.put("title", "Ms.");
	        emp3.put("name", "Samantha Greens");
	        emp3.put("website", "systemcodegeeks.com");
	 
	        Document emp3_addr = new Document();
	        emp3_addr.put("addr_line1", "Cudahy, Ohio");
	        emp3_addr.put("zip_code", "31522");
	        emp3.put("address", emp3_addr);
	 
	        // Adding documents to a list.
	        List<Document> docs = new ArrayList<Document>();
	        docs.add(emp2);
	        docs.add(emp3);
	 
	        collection.insertMany(docs);
	        return collection;
	        
		} catch (Exception e) {
			System.err.println("Error in query the collection " + collectionName);
			throw e;
		}
	}
	
}
