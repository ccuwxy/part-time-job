package com.proj.api.apicommunication.controller;

import com.proj.api.exception.file.CanNotHandlerFileException;
import com.proj.api.exception.file.MongoDbException;
import com.proj.api.utils.MongoDbPool;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.types.Binary;

import java.util.ArrayList;
import java.util.List;

public class TemToPer{
    private byte[] fileByte=null;
    private String fileName="";
    private long createTime;
    private long file_size;
    private String sFileToken;

    public TemToPer(String fileToken,String privilege_user, String check_code) throws MongoDbException,CanNotHandlerFileException{
        sFileToken = fileToken;
        MongoDatabase mongoDatabase = MongoDbPool.getConn("wjxt");
        MongoCollection<Document> collection = mongoDatabase.getCollection("temporaryfile");
        try {
            BasicDBObject basicDBObject = new BasicDBObject("file_token", fileToken);
            FindIterable<Document> result = collection.find(basicDBObject);
            Document document = result.first();
//        System.out.println("TTOPER1");
            fileByte = ((Binary) document.get("file_byte")).getData();
            fileName = (String) document.get("orgin_file_name");
            file_size = (long) document.get("size");
//        System.out.println("TTOPER2");
//        System.out.println("TTOPER3");
            MongoCollection<Document> collection1 = mongoDatabase.getCollection("permanentfile");
            long currentTime = System.currentTimeMillis() / 1000;
            Document documentP = new Document("file_token", fileToken).
                    append("orgin_file_name", fileName).
                    append("size", file_size).
                    append("file_byte", fileByte).
                    append("create_time", currentTime);

            List<Document> documents = new ArrayList<Document>();
            documents.add(documentP);
            collection1.insertMany(documents);
            // System.out.println("TTOPER4");
            collection = mongoDatabase.getCollection("temporaryfile");
            collection.deleteMany(Filters.eq("file_token", fileToken));
        } catch (Exception ex) {
            throw new CanNotHandlerFileException();
        }
       // System.out.println("TTOPER6");
    }

    public byte[] getFileByte() {
        return fileByte;
    }

    public String getFileName() {
        return fileName;
    }
    public long createTime() {
        return createTime;
    }
    public long file_size() {
        return file_size;
    }
    public String getsFileToken() {
        return sFileToken;
    }

}
