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

import sorectstudent1.sorecs1.BasicDBObject;
import sorectstudent1.sorecs1.DBObject;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.gt;
import static com.mongodb.client.model.Filters.lt;

/**
 * Hello world!
 *
 */
public class App {
	// no Sorec
	// Google: 4min
	public List<Document> findAll(String collectionName) {
		List<Document> documents = new ArrayList<Document>();
		try (MongoClient mongoClient = new MongoClient("localhost", 27017)) {
			MongoDatabase database = mongoClient.getDatabase("CROSSMINER");
			documents = (List<Document>) database.listCollections();
		} catch (Exception e) {

		}
		return documents;
	}

	// 10min (no SOREC)
	public Document findOneByFullName(String collectionName, String fullName) {
		Document cursor = new Document();
		try (MongoClient mongoClient = new MongoClient("localhost", 27017)) {
			MongoDatabase db = mongoClient.getDatabase("CROSSMINER");
			MongoCollection<Document> collection = db.getCollection(collectionName);

			BasicDBObject searchQuery = new BasicDBObject();
			searchQuery.put(fullName);

			cursor = collection.findOne(searchQuery).iterator();
		} catch (Exception e) {

		}
		return cursor;
	}

	// 10min without Sorec
	public Document updateDocument(String collectionName, String fullName) {
		Document cursor = new Document();
		try (MongoClient mongoClient = new MongoClient("localhost", 27017)) {
			MongoDatabase db = mongoClient.getDatabase("CROSSMINER");
			BasicDBObject document = new BasicDBObject();
			document.put(fullName);
			db.getCollection(collectionName).update(document);
		} catch (Exception e) {

		}
		return null;
	}

	// 20min with Sorec + google
	public boolean deleteDocument(String collectionName, String fullName) {
		try (MongoClient mongoClient = new MongoClient("localhost", 27017)) {
			MongoDatabase db = mongoClient.getDatabase("CROSSMINER");
			BasicDBObject document = new BasicDBObject();
			document.put(fullName);
			db.getCollection(collectionName).deleteOne(document);
			return true;
		} catch (Exception e) {
		}
		return false;

	}

	// 15min with Sorec
	public Collection filterQuery(String collectionName, String field) {
		Collection cursor = new Collection();

		try (MongoClient mongoClient = new MongoClient("localhost", 27017)) {
			MongoDatabase db = mongoClient.getDatabase("CROSSMINER");
			MongoCollection<Document> collection = db.getCollection(collectionName);

			BasicDBObject searchQuery = new BasicDBObject();
			searchQuery.put(field, "value");

			cursor = collection.find(searchQuery).iterator();
		} catch (Exception e) {
		}
		return cursor;

	}

	// 20min with Sorec
	public BsonDocument BsonFilter(String collectionName) {
		Bson filter = and(eq("field1", "value"), gt("field2", "value2"));
		BsonDocument bsonDocument = filter.toBsonDocument(BsonDocument.class, MongoClient.getDefaultCodecRegistry());

		try (MongoClient mongoClient = new MongoClient("localhost", 27017)) {
			MongoDatabase db = mongoClient.getDatabase("CROSSMINER");
			MongoCollection<Document> collection = db.getCollection(collectionName);
			bsonDocument = (BsonDocument) collection.find(filter).iterator();
		} catch (Exception e) {
		}
		return bsonDocument;
	}

	// 8min without Sorec
	public MongoCollection<Document> insertSingleDocument(String collectionName) {
		MongoCollection<Document> doc;

		try (MongoClient mongoClient = new MongoClient("localhost", 27017)) {
			MongoDatabase db = mongoClient.getDatabase("CROSSMINER");
			MongoCollection<Document> collection = db.getCollection(collectionName);

			BasicDBObject document = new BasicDBObject();
			document.put("name", "lokesh");

			doc = collection.insert(document);
		} catch (Exception e) {
			System.err.println("Error in query the collection " + collectionName);
			throw e;
		}
		return doc;
	}

	// 5min without Sorec
	public MongoCollection<Document> insertMultipleDocument(String collectionName) {
		MongoCollection<Document> doc;

		try (MongoClient mongoClient = new MongoClient("localhost", 27017)) {
			MongoDatabase db = mongoClient.getDatabase("CROSSMINER");
			MongoCollection<Document> collection = db.getCollection(collectionName);
			DBObject document1 = new BasicDBObject();
			document1.put("name", "Kiran");
			document1.put("age", 20);

			DBObject document2 = new BasicDBObject();
			document2.put("name", "John");

			List<DBObject> documents = new ArrayList<>();
			documents.add(document1);
			documents.add(document2);
			doc = collection.insert(documents);
		} catch (Exception e) {
			System.err.println("Error in query the collection " + collectionName);
			throw e;
		}
		return doc;
	}

}
