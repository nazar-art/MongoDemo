package com.tengen.mongodb;

import com.mongodb.*;
import org.testng.annotations.Test;

import java.io.*;

public class MongoTest {

    static class Inner implements Serializable {

        private static final long serialVersionUID = -7955006812894257987L;
        private String name;

        Inner(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    @Test
    public void testMongoDB() throws Exception {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            MongoClient mongoClient = new MongoClient();
            DBCollection collection = mongoClient.getDB("test").getCollection("temp");

            Inner temp = new Inner("Current time: " + System.currentTimeMillis());

            oos.writeObject(temp);
            collection.insert(new BasicDBObject("InnerObject", baos.toByteArray()));

            readObject(collection);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readObject(DBCollection collection) {
        try {
            DBCursor cursor = collection.find();
            for (DBObject dbObject : cursor) {
                ByteArrayInputStream bais = new ByteArrayInputStream((byte[]) dbObject.get("InnerObject"));
                ObjectInputStream ois = new ObjectInputStream(bais);
                Inner temp = (Inner) ois.readObject();
                bais.close();
                ois.close();
                System.out.println(temp.getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
