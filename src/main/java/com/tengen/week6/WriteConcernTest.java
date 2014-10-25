package com.tengen.week6;

import com.mongodb.*;

import java.net.UnknownHostException;
import java.util.Arrays;

public class WriteConcernTest {
    public static void main(String[] args) throws UnknownHostException {
        MongoClient client = new MongoClient(Arrays.asList(
                new ServerAddress("localhost", 27017)
                /*, new ServerAddress("localhost", 27018),
                new ServerAddress("localhost", 27019)*/));

        client.setWriteConcern(WriteConcern.JOURNALED);

        DB db = client.getDB("course");
        db.setWriteConcern(WriteConcern.JOURNALED);
        DBCollection coll = db.getCollection("write.test");
        coll.setWriteConcern(WriteConcern.JOURNALED);
        coll.drop();

        DBObject object = new BasicDBObject("_id", 1);

        coll.insert(object, WriteConcern.JOURNALED);
        try {
            coll.insert(object);
        } catch (MongoException.DuplicateKey e) {
            System.out.println(e.getMessage());
        }
    }
}
