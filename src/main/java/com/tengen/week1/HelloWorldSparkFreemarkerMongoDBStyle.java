package com.tengen.week1;

import com.mongodb.*;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.StringWriter;
import java.net.UnknownHostException;

import static spark.Spark.get;
import static spark.Spark.halt;

public class HelloWorldSparkFreemarkerMongoDBStyle
{
    private static Logger log = Logger.getLogger(HelloWorldSparkFreemarkerMongoDBStyle.class);

    public static void main(String[] args) throws UnknownHostException
    {
        final Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(HelloWorldSparkFreemarkerStyle.class, "/");

        MongoClient client = new MongoClient(new ServerAddress("localhost", 27017));

        DB database = client.getDB("course");
        DBCollection collection = database.getCollection("hello");

        get("/hello", (req, res) -> {
            StringWriter writer = new StringWriter();
            try
            {
                Template template = configuration.getTemplate("hello.ftl");
                DBObject document = collection.findOne();

                template.process(document, writer);
            } catch (IOException | TemplateException e)
            {
                halt(500);
                log.error(e);
            }
            return writer;
        });

    }
}
