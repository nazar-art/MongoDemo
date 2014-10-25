package com.tengen.week2;

import com.mongodb.*;

import java.net.UnknownHostException;
import java.util.Random;

public class FindCriteriaTest {
    public static void main(String[] args) throws UnknownHostException {
        MongoClient client = new MongoClient();
        DB courseDb = client.getDB("course");
        DBCollection collection = courseDb.getCollection("findCriteriaTest");
        collection.drop();

        for (int i = 0; i < 10; i++) {
            collection.insert(new BasicDBObject("x", new Random().nextInt(2))
                    .append("y", new Random().nextInt(100)));
        }

        QueryBuilder builder = QueryBuilder.start("x").is(0).and("y").greaterThan(10).lessThan(70);
        DBObject query = new BasicDBObject("x", 0).append("y", new BasicDBObject("$gt", 10).append("$lt", 90));

        System.out.println("\nCount:");
//        long count = collection.count(query);
        long count = collection.count(builder.get());
        System.out.println(count);

        System.out.println("\nFind one:");
//        try (DBCursor cursor = collection.find(query)) {
        try (DBCursor cursor = collection.find(builder.get())) {
            while (cursor.hasNext()) {
                DBObject cur = cursor.next();
                System.out.println(cur);
            }
        }
    }
}
