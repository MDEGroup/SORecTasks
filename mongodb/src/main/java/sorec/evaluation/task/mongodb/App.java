package sorec.evaluation.task.mongodb;

import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.bson.BsonDocument;
import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.bulk.DeleteRequest;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.gt;
import static com.mongodb.client.model.Filters.lt;

/**
 * Hello world!
 *
 */
public class App {

	// 18:27 - START SESSION
	// * https://mongodb.github.io/mongo-java-driver/3.4/driver/getting-started/quick-start/ (not by CROSSMINER)
	public List<Document> findAll(String collectionName) {

		List<Document> documents = new ArrayList<Document>();

		try (MongoClient mongoClient = new MongoClient(
				new MongoClientURI("mongodb://admin:myadminpassword@178.238.238.209:37017/"))) {
			MongoDatabase database = mongoClient.getDatabase("CROSSMINER");

			MongoCollection<Document> collection = database.getCollection(collectionName);

			MongoCursor<Document> cursor = collection.find().iterator();
			while (cursor.hasNext()) {
				documents.add(cursor.next());
			}

			cursor.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return documents;
	}

	// 18:43
	// * https://stackoverflow.com/questions/31108943/mongodb-fetch-collections-by-java-driver
	public Document findOneByFullName(String collectionName, String fullName) {

		Document document = null;

		try (MongoClient mongoClient = new MongoClient(
				new MongoClientURI("mongodb://admin:myadminpassword@178.238.238.209:37017/"))) {

			MongoDatabase database = mongoClient.getDatabase("CROSSMINER");

			MongoCollection<Document> collection = database.getCollection(collectionName);

			BasicDBObject searchQuery = new BasicDBObject();
			searchQuery.put("fullName", fullName);

			MongoCursor<Document> cursor = collection.find(searchQuery).iterator();

			if (cursor.hasNext()) {
				document = cursor.next();
			}

			cursor.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return document;
	}

	// 18:50
	// * https://stackoverflow.com/questions/48463768/getting-returning-the-newly-inserted-or-newly-modified-document-in-mongodb-with (not by CROSSMINER)
	public Document updateDocument(String collectionName, String fullName) {

		Document updatedDoc = null;

		try (MongoClient mongoClient = new MongoClient(
				new MongoClientURI("mongodb://admin:myadminpassword@178.238.238.209:37017/"))) {
			MongoDatabase database = mongoClient.getDatabase("CROSSMINER");

			MongoCollection<Document> collection = database.getCollection(collectionName);

			updatedDoc = collection.findOneAndUpdate(eq("fullName", fullName),
					new Document("$set", new Document("fullName", "booooh")));

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return updatedDoc;
	}

	// 19:03
	// * https://mongodb.github.io/mongo-java-driver/3.4/driver/getting-started/quick-start/ (not by CROSSMINER)
	public boolean deleteDocument(String collectionName, String fullName) {

		try (MongoClient mongoClient = new MongoClient(
				new MongoClientURI("mongodb://admin:myadminpassword@178.238.238.209:37017/"))) {
			MongoDatabase database = mongoClient.getDatabase("CROSSMINER");

			MongoCollection<Document> collection = database.getCollection(collectionName);

			DeleteResult deleteResult = collection.deleteOne(eq("fullName", fullName));
			
			return deleteResult.wasAcknowledged();

		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return false;
	}

	// 19:08
	// notes: I don't have understood what is the method goal.
	// Nel test vedo che viene passato field = "field", ma non trovo nulla all'interno del db con quel nome
	public Collection<Document> filterQuery(String collectionName, String field) {
		
		// ??? 
		
		return null;
	}
	
	// 19:14
	// * https://stackoverflow.com/questions/45977697/bson-pretty-print-using-java-mongodb-driver
	// notes: I don't think it's the right implementation, but I don't figure out what I have to return if the type is BsonDocument 
	public BsonDocument BsonFilter(String collectionName) {
		Bson filter = and(eq("field1", "value"), gt("field2", "value2"));
		return filter.toBsonDocument(BsonDocument.class, MongoClient.getDefaultCodecRegistry());
	}

	// 19:19
	// * https://mongodb.github.io/mongo-java-driver/3.4/driver/getting-started/quick-start/ (not by CROSSMINER)
	// notes: why return MongoCollection<Document>? is not it better insertedId?
	public MongoCollection<Document> insertSingleDocument(String collectionName) {
		
		MongoCollection<Document> collection = null;
		
		try (MongoClient mongoClient = new MongoClient(
				new MongoClientURI("mongodb://admin:myadminpassword@178.238.238.209:37017/"))) {
			
			MongoDatabase database = mongoClient.getDatabase("CROSSMINER");

			collection = database.getCollection(collectionName);
			
			collection.insertOne(new Document("test", "test"));

		} catch (Exception e) {
			System.err.println("Error in query the collection " + collectionName);
			throw e;
		}
		
		return collection;
	}

	// 19:25
	// * https://mongodb.github.io/mongo-java-driver/3.4/driver/getting-started/quick-start/ (not by CROSSMINER)
	public MongoCollection<Document> insertMultipleDocument(String collectionName) {
		
		MongoCollection<Document> collection = null;

		try (MongoClient mongoClient = new MongoClient(
				new MongoClientURI("mongodb://admin:myadminpassword@178.238.238.209:37017/"))) {

			MongoDatabase database = mongoClient.getDatabase("CROSSMINER");

			collection = database.getCollection(collectionName);

			List<Document> documents = new ArrayList<Document>();
			
			for (int i = 0; i < 10; i++) {
				documents.add(new Document("test", i));
			}

			collection.insertMany(documents);

		} catch (Exception e) {
			System.err.println("Error in query the collection " + collectionName);
			throw e;
		}

		return collection;
	}

}
