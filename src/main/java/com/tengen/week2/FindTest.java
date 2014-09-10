package com.tengen.week2;

import com.mongodb.*;

import java.net.UnknownHostException;
import java.util.Random;

public class FindTest {
    public static void main(String[] args) throws UnknownHostException {
        MongoClient client = new MongoClient();
        DB courseDb = client.getDB("course");
        DBCollection collection = courseDb.getCollection("findTest");
        collection.drop();

        for (int i = 0; i < 10; i++) {
            collection.insert(new BasicDBObject("x", new Random().nextInt(100)));
        }

        System.out.println("Find one");
        DBObject one = collection.findOne();
        System.out.println(one);

        System.out.println("\nFind all");
        try (DBCursor cursor = collection.find()) {
            while (cursor.hasNext()) {
                DBObject cur = cursor.next();
                System.out.println(cur);
            }
        }

        System.out.println("\nCount");
        long count = collection.count();
        System.out.println(count);
    }
}
