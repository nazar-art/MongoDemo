package com.tengen.week2;

import com.mongodb.*;

import java.net.UnknownHostException;
import java.util.Random;

public class FieldSelectionTest {
    public static void main(String[] args) throws UnknownHostException {
        MongoClient client = new MongoClient();
        DB courseDb = client.getDB("course");
        DBCollection collection = courseDb.getCollection("fieldSelectionTest");
        collection.drop();
        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            collection.insert(new BasicDBObject("x", random.nextInt(2)).append("y", random.nextInt(100)).append("z", random.nextInt(1000)));
        }

        DBObject query = QueryBuilder.start("x").is(0).and("y").greaterThan(10).lessThan(70).get();
        try (DBCursor cursor = collection.find(query, new BasicDBObject(/*"x", false*/"y", true).append("_id", false))) {
            while (cursor.hasNext()) {
                DBObject cur = cursor.next();
                System.out.println(cur);
            }
        }

    }
}
