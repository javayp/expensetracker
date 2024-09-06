package com.app.penpaid.factory;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lombok.Getter;
import org.bson.Document;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Getter
@Component
public class MongoClientFactory {

    private final MongoClient mongoClient;

    /* The code configures a CodecRegistry to handle the serialization and deserialization of
       POJOs (Plain Old Java Objects) with MongoDB.*/
    @Autowired
    public MongoClientFactory() {
        //This line creates a PojoCodecProvider with automatic mapping enabled. This provider will automatically map Java POJOs to BSON documents and vice versa.
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();

        //This line creates a CodecRegistry that includes the default codec registry and the PojoCodecProvider.
        //The CodecRegistry is responsible for managing the codecs (encoders/decoders) used to convert between Java objects and BSON.
        CodecRegistry pojoCodecRegistry = CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), CodecRegistries.fromProviders(pojoCodecProvider));

        MongoClientSettings settings = MongoClientSettings.builder().codecRegistry(pojoCodecRegistry).build();

        this.mongoClient = MongoClients.create(settings);
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
