package com.proj.api.utils;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class MongoDbPool {
    private static MongoClient mongoClient = new MongoClient("localhost", 27017);

    public static MongoDatabase getConn(String sDataBaseName){
        return mongoClient.getDatabase(sDataBaseName);
    }
}
