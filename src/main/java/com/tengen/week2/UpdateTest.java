package com.tengen.week2;

import com.mongodb.*;

import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

public class UpdateTest {
    public static void main(String[] args) throws UnknownHostException {
        DBCollection collection = createCollection();
        collection.drop();

        List<String> names = Arrays.asList("alice", "bobby", "cathy", "david", "ethan");
        for (String name : names) {
            collection.insert(new BasicDBObject("_id", name));
        }

        collection.update(new BasicDBObject("_id", "alice"), new BasicDBObject("age", 24));

        collection.update(new BasicDBObject("_id", "frank"), new BasicDBObject("$set", new BasicDBObject("gender", "F")), false, true);

//        collection.remove(new BasicDBObject("_id", "alice"));

        printCollection(collection);
    }

    private static void printCollection(DBCollection collection) {
        try (DBCursor cursor = collection.find().sort(new BasicDBObject("_id", 1))) {
            while (cursor.hasNext()) {
                DBObject cur = cursor.next();
                System.out.println(cur);
            }
        }
    }

    private static DBCollection createCollection() throws UnknownHostException {
        MongoClient client = new MongoClient();
        DB courseDb = client.getDB("course");
        return courseDb.getCollection("updateTest");
    }
}
