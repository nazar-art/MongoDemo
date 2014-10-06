package com.tengen.week4;

import com.mongodb.*;

import java.net.UnknownHostException;
import java.util.Random;

public class Hinting {
    public static void main(String[] args) throws UnknownHostException {
        // collection with multi indexes
        MongoClient client = new MongoClient();
        DB db = client.getDB("test");
        DBCollection collection = db.getCollection("foo");
        collection.drop();
        
        Random rand = new Random();
        for (int i = 0; i <= 4005; i++) {
            collection.insert(new BasicDBObject("a", rand.nextInt(100)).append("b", rand.nextInt(100)).append("c", rand.nextInt(100)));
        }

        collection.createIndex(new BasicDBObject("a", 1).append("b", -1).append("c", 1));
        collection.createIndex(new BasicDBObject("a", 1));
        collection.createIndex(new BasicDBObject("b", -1));
        collection.createIndex(new BasicDBObject("c", 1));
        
        BasicDBObject query = new BasicDBObject("a", 4000).append("b", 4000).append("c", 4000);
        System.out.println(collection.getIndexInfo());

//        DBObject doc = collection.find(query).hint("a_1_b_-1_c_1").explain();

        BasicDBObject myHint = new BasicDBObject("a", 1).append("b", -1).append("c", 1);
        DBObject doc = collection.find(query).hint(myHint).explain();

        for (String s : doc.keySet()) {
            System.out.printf("%25s:%s%n", s, doc.get(s));
        }
    }
}
