package com.example.company.db;

import com.example.company.model.Employee;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

/**
 * Created by Bella Galoyan
 */
public class MongoDBApplication {

    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> collection;

    public MongoDBApplication() {
        mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
        database = mongoClient.getDatabase("Company");
        collection = database.getCollection("Employees");

    }

    public void createNewEmployee(Employee employee) {
        employee.setId(System.currentTimeMillis());
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        String json = gson.toJson(employee);
        collection.insertOne(Document.parse(json));

    }

    public void updateEmployeeDocument(Employee employee) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        String json = gson.toJson(employee);
        collection.replaceOne(new Document("id", employee.getId()),
                new Document(Document.parse(json)));
    }

    public void deleteEmployeeDocument(Long id) {
        Document document = collection.find(eq("id", id)).first();
        collection.deleteOne(document);
    }

    public List<Document> findAllEmployees() {
        return collection.find().into(new ArrayList<Document>());
    }

    public Document findEmployeeById(Long id) {
        return collection.find(eq("_id", id)).first();
    }


}
