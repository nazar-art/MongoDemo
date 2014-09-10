package com.tengen.week2;

import com.mongodb.*;

import java.net.UnknownHostException;
import java.util.Arrays;

public class InsertTest {
    public static void main(String[] args) throws UnknownHostException {
        MongoClient client = new MongoClient();
        DB courseDb = client.getDB("course");
        DBCollection collection = courseDb.getCollection("insertTest");

        DBObject doc = new BasicDBObject(/*"_id", new ObjectId()*/).append("x", 1);
        DBObject doc2 = new BasicDBObject().append("x", 2);
        System.out.println(doc);
        collection.insert(Arrays.asList(doc, doc2));
        System.out.println(doc);
        System.out.println(doc2);
    }
}
