package com.tengen.week2;

import com.mongodb.*;

import java.net.UnknownHostException;
import java.util.Random;

public class SortSkipLimitTest {
    public static void main(String[] args) throws UnknownHostException {
        MongoClient client = new MongoClient();
        DB courseDb = client.getDB("course");
        DBCollection lines = courseDb.getCollection("sortSkipLimitTest");
        lines.drop();
        Random rand = new Random();

        for (int i = 0; i < 10; i++) {
            lines.insert(new BasicDBObject("_id", i).append("start",
                    new BasicDBObject("x", rand.nextInt(2)).append("y", rand.nextInt(90) + 10))
                    .append("end",
                            new BasicDBObject("x", rand.nextInt(2)).append("y", rand.nextInt(90) + 10)));
        }

//        try (DBCursor cursor = lines.find().sort(new BasicDBObject("_id", -1)).skip(2).limit(10)) {
        try (DBCursor cursor = lines.find().sort(new BasicDBObject("start.x", 1).append("start.y", -1)).skip(2).limit(10)) {
            while (cursor.hasNext()) {
                DBObject cur = cursor.next();
                System.out.println(cur);
            }
        }
    }
}
