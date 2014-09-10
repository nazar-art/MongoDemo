package com.tengen.week2;

import com.mongodb.BasicDBObject;

import java.util.Arrays;
import java.util.Date;

public class DocumentRepresentationTest {
    public static void main(String[] args) {
        BasicDBObject doc = new BasicDBObject();
        doc.put("userName", "jeremy");
        doc.put("birthday", new Date(1988, 3, 13));
        doc.put("programmer", true);
        doc.put("age", 13);
        doc.put("languages", Arrays.asList("english", "polish", "russian"));
        doc.put("address", new BasicDBObject("street", "Horodocka").append("city", "Lviv"));
        System.out.println(doc);
    }
}
