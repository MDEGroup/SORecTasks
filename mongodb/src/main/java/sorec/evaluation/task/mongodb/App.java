package sorec.evaluation.task.mongodb;

import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.bson.BsonDocument;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
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
		try (MongoClient mongoClient = new MongoClient(
				new MongoClientURI("mongodb://admin:myadminpassword@178.238.238.209:37017"))) {
			MongoDatabase database = mongoClient.getDatabase("CROSSMINER");
			MongoCollection<Document> collection = database.getCollection(collectionName);
			documents = collection.find().into(new ArrayList<Document>());

		} catch (Exception e) {

		}
		return documents;

	}

	public Document findOneByFullName(String collectionName, String fullName) {
		Document document = null;
		try (MongoClient mongoClient = new MongoClient(
				new MongoClientURI("mongodb://admin:myadminpassword@178.238.238.209:37017"))) {
			MongoDatabase database = mongoClient.getDatabase("CROSSMINER");
			FindIterable<Document> document2 = database.getCollection(collectionName).find()
					.filter(eq("fullName", fullName));
			document = document2.first();

		} catch (Exception e) {

		}
		return document;
	}

	public Document updateDocument(String collectionName, String fullName) {
		Document document = null;

		try (MongoClient mongoClient = new MongoClient(
				new MongoClientURI("mongodb://admin:myadminpassword@178.238.238.209:37017"))) {
			MongoDatabase database = mongoClient.getDatabase("CROSSMINER");
			FindIterable<Document> document2 = database.getCollection(collectionName).find()
					.filter(eq("fullName", fullName));
			document = document2.first();
			document.put("fullName", fullName + "changed");

		} catch (Exception e) {

		}
		return document;
	}

	public boolean deleteDocument(String collectionName, String fullName) {

		try (MongoClient mongoClient = new MongoClient(
				new MongoClientURI("mongodb://admin:myadminpassword@178.238.238.209:37017"))) {
			MongoDatabase database = mongoClient.getDatabase("CROSSMINER");
			MongoCollection<Document> document2 = database.getCollection(collectionName);
			document2.deleteMany(eq("fullName", fullName));
		} catch (Exception e) {

		}
		return false;

	}

	public Collection<Document> filterQuery(String collectionName, String field) {
		Collection<Document> collection = null;
		field = "i";
		try (MongoClient mongoClient = new MongoClient(
				new MongoClientURI("mongodb://admin:myadminpassword@178.238.238.209:37017"))) {
			MongoDatabase database = mongoClient.getDatabase("CROSSMINER");
			FindIterable<Document> document2 = database.getCollection(collectionName).find(gt(field, 40));
			List<Document> collection2 = document2.into(new ArrayList<Document>());
			collection = collection2;

		} catch (Exception e) {

		}
		return collection;

	}

	public BsonDocument BsonFilter(String collectionName) {
		BsonDocument bsonDocument = null;

		try (MongoClient mongoClient = new MongoClient(
				new MongoClientURI("mongodb://admin:myadminpassword@178.238.238.209:37017"))) {
			MongoDatabase database = mongoClient.getDatabase("CROSSMINER");
			Bson filter = and(eq("_id", new ObjectId("5df3c00cbd079904b1aeb8ca")), gt("i", 89));
			Document document = database.getCollection(collectionName).find().filter(filter).first();
			bsonDocument = document.toBsonDocument(BsonDocument.class, MongoClient.getDefaultCodecRegistry());

		} catch (Exception e) {

		}
		return bsonDocument;
	}

	public MongoCollection<Document> insertSingleDocument(String collectionName) {
		MongoCollection<Document> collection = null;
		try (MongoClient mongoClient = new MongoClient(
				new MongoClientURI("mongodb://admin:myadminpassword@178.238.238.209:37017"))) {
			MongoDatabase database = mongoClient.getDatabase("CROSSMINER");
			collection = database.getCollection(collectionName);
			Document document = new Document("fullName", "testDocument");
			collection.insertOne(document);

		} catch (Exception e) {
			System.err.println("Error in query the collection " + collectionName);
			throw e;
		}
		return collection;

	}

	public MongoCollection<Document> insertMultipleDocument(String collectionName) {
		MongoCollection<Document> collection = null;
		try (MongoClient mongoClient = new MongoClient(
				new MongoClientURI("mongodb://admin:myadminpassword@178.238.238.209:37017"))) {
			MongoDatabase database = mongoClient.getDatabase("CROSSMINER");
			collection = database.getCollection(collectionName);
			List<Document> list = new ArrayList<>();
			Document document1 = new Document("fullName", "testDocument");
			Document document2 = new Document("fullName", "testDocument2");
			Document document3 = new Document("fullName", "testDocument3");
			list.add(document1);
			list.add(document2);
			list.add(document3);
			for (Document d : list) {
				collection.insertOne(d);
			}

		} catch (Exception e) {
			System.err.println("Error in query the collection " + collectionName);
			throw e;
		}
		return collection;

	}
}
