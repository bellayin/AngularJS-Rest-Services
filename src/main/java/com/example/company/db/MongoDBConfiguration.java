package com.example.company.db;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Date;

/**
 * Created by bellag on 5/23/2016.
 */
public class MongoDBConfiguration {

    public static void main(String args[]) {

         MongoClient mongoClient = new MongoClient("localhost", 27017);
         MongoDatabase database = mongoClient.getDatabase("Company");
         MongoCollection<Document> collection = database.getCollection("Employees");



        Document doc = new Document("_id", "4")
                .append("id",454545)
                .append("name", "Bella")
                .append("lastName", "Galoyan")
                .append("age", 30)
                .append("address", "EGS")
                .append("position", "Software developer");
        collection.insertOne(doc);
    }
}