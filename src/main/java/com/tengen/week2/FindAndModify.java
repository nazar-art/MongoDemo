package com.tengen.week2;

import com.mongodb.*;

import java.net.UnknownHostException;

public class FindAndModify {
    public static void main(String[] args) throws UnknownHostException {
        DBCollection collection = createCollection();
        collection.drop();

        final String counterId = "abc";
        int first;
        int numNeeded;

        numNeeded = 2;
        first = getRange(counterId, numNeeded, collection);
        System.out.println("Range: " + first + "-" + (first + numNeeded - 1));

        numNeeded = 3;
        first = getRange(counterId, numNeeded, collection);
        System.out.println("Range: " + first + "-" + (first + numNeeded - 1));

        numNeeded = 10;
        first = getRange(counterId, numNeeded, collection);
        System.out.println("Range: " + first + "-" + (first + numNeeded - 1) + "\n");

        printCollection(collection);
    }

    private static int getRange(String id, int range, DBCollection collection) {
        DBObject doc = collection.findAndModify(new BasicDBObject("_id", id), null, null, false,
                new BasicDBObject("$inc", new BasicDBObject("counter", range)), // exactly update
                true, true);
        return (int) doc.get("counter") - range + 1;
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
