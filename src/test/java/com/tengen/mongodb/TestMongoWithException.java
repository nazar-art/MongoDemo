package com.tengen.mongodb;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.Serializable;

public class TestMongoWithException {

    static class Inner implements Serializable {

        private static final long serialVersionUID = 3535821317097113696L;
        private String name;

        public Inner(String s) {
            name = s;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    @Test
    public void testName() throws Exception {
        try {
            MongoClient mongoClient = new MongoClient();
            DBCollection collection = mongoClient.getDB("test").getCollection("temp");
            collection.drop();

            Inner temp = new Inner("Current time: " + System.currentTimeMillis());

            collection.insert(new BasicDBObject().append("Inner Object", temp));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
