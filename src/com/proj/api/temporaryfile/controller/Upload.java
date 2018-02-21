package com.proj.api.temporaryfile.controller;

import com.proj.api.FileInfo;
import com.proj.api.temporaryfile.model.DeleteExpiredFile;
import com.proj.api.utils.MongoDbPool;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Upload {
    private String sFileToken;
    public static MongoClient mongoClient;
    public static MongoDatabase mongoDatabase;
    public Upload(FileInfo fileInfo) throws ParseException {
        MongoDatabase mongoDatabase = MongoDbPool.getConn("wjxt");
        MongoCollection<Document> collection = mongoDatabase.getCollection("temporaryfile");

        long currentTime=System.currentTimeMillis()/1000;
        Document document = new Document("file_token", fileInfo.getMd5FileName()).
                append("orgin_file_name", fileInfo.getOrginFileName()).
                append("size", fileInfo.getFile_size()).
                append("file_byte", fileInfo.getFile_byte()).
                append("create_time", fileInfo.getCreateTime());

        List<Document> documents = new ArrayList<Document>();
        documents.add(document);
        collection.insertMany(documents);
        this.sFileToken=fileInfo.getMd5FileName();
        DeleteExpiredFile.doCheck();

    }

    public String getsFileToken() {
        return sFileToken;
    }
}
