package com.citrix.g2w.reporting.write.test;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoDBTestClient {

	public static void main(String[] args) {
		MongoClient mongoClient = new MongoClient();
		MongoDatabase database = mongoClient.getDatabase("mydb");
		Document doc = new Document("name", "MongoDB")
				.append("type", "database").append("count", 1)
				.append("info", new Document("x", 203).append("y", 102));
		MongoCollection<Document> collection = database.getCollection("test");
		collection.insertOne(doc);
		Document myDoc = collection.find().first();
		System.out.println(myDoc.toJson());
		mongoClient.close();
	}
}
