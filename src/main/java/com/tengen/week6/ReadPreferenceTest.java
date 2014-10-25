package com.tengen.week6;

import com.mongodb.*;

import java.net.UnknownHostException;
import java.util.Arrays;

public class ReadPreferenceTest {
    public static void main(String[] args) throws UnknownHostException {
        MongoClient client = new MongoClient(Arrays.asList(
                new ServerAddress("localhost", 27017)
                /*, new ServerAddress("localhost", 27018),
                new ServerAddress("localhost", 27019)*/));
        client.setReadPreference(ReadPreference.primary());

        client.setWriteConcern(WriteConcern.JOURNALED);

        DB db = client.getDB("course");
        DBCollection coll = db.getCollection("write.test");
        coll.setReadPreference(ReadPreference.primary());

        try (DBCursor cursor = coll.find().setReadPreference(ReadPreference.nearest())) {
            while (cursor.hasNext()) {
                System.out.println(cursor.next());
            }
        }
    }
}
