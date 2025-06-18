package org.example;

import com.mongodb.client.*;
import org.bson.Document;


import static com.mongodb.client.model.Filters.*;

public class Main {
    public static void main(String[] args) {
        // Connect to MongoDB
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase database = mongoClient.getDatabase("library");
        MongoCollection<Document> collection = database.getCollection("books");

        // Create books
        FictionBook fiction = new FictionBook("Harry Potter", "J.K. Rowling", "1234567890", "Fantasy");
        NonFictionBook nonfiction = new NonFictionBook("Sapiens", "Yuval Noah Harari", "9876543210", "History");

        // Insert into MongoDB
        collection.insertOne(fiction.toDocument());
        collection.insertOne(nonfiction.toDocument());

        // Retrieve and display all books
        System.out.println("\n--- Books from MongoDB ---\n");
        FindIterable<Document> books = collection.find();
        for (Document doc : books) {
            System.out.println(doc.toJson());
        }

        mongoClient.close();
    }
}
