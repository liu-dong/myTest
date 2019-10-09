package com.dong.web.common.controller;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Set;

public class MongoDBController {
    public static void main(String[] args) {
        try {
            MongoClient mongoClient = new MongoClient("localhost",27017);
            MongoDatabase mongoDatabase = mongoClient.getDatabase("my_data");
            DB db = mongoClient.getDB("my_data");
            System.out.println("连接成功！");
            // 2.2获取该db下所有集合名称并打印
//            Set<String> collectionNames = mongoDatabase.get;
            System.out.println(mongoDatabase.getName() + "包含如下集合：");
//            for (String collectionName : collectionNames) {
//                System.out.println(collectionName);
//            }
            MongoCollection<Document> userList = mongoDatabase.getCollection("userList");
            FindIterable findIterable = userList.find();
            Document document = (Document) findIterable.first();
            System.out.println(document);
            for (Object o : findIterable) {

                System.out.println(o);
            }

        }catch (Exception e){
            System.out.println("连接失败！");
        }
    }
}
