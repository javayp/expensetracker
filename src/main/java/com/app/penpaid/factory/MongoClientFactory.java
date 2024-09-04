package com.app.penpaid.factory;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lombok.Getter;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Getter
@Component
public class MongoClientFactory {

    private final MongoClient mongoClient;

    @Autowired
    public MongoClientFactory(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }


    public <T> MongoCollection<T> getCollection(String databaseName, String collectionName, Class<T> clazz) {
        MongoDatabase database = mongoClient.getDatabase(databaseName);
        return database.getCollection(collectionName, clazz);
    }

    public MongoCollection<Document> getCollection(String databaseName, String collectionName) {
        MongoDatabase database = mongoClient.getDatabase(databaseName);
        return database.getCollection(collectionName);
    }

}
