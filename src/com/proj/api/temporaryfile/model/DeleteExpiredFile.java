package com.proj.api.temporaryfile.model;

import com.proj.api.utils.MongoDbPool;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;

public class DeleteExpiredFile {
    private static long lastScanTime = System.currentTimeMillis() / 1000;
    private final static long interval = 60 * 60;
    private final static long fileExpiredSecond = 3600;

    public static void doCheck() {
        long currentTime = System.currentTimeMillis() / 1000;
        if (currentTime - interval > lastScanTime) {
            return;
        }
        MongoDatabase mongoDatabase = MongoDbPool.getConn("wjxt");
        MongoCollection<Document> collection = mongoDatabase.getCollection("temporaryfile");
        collection.deleteMany(Filters.lte("create_time", currentTime - fileExpiredSecond));
        return;
    }
}
// 6adbd827e358ab65f7b9504ccd3380dc