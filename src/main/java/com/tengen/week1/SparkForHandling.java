package com.tengen.week1;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.halt;
import static spark.Spark.post;

public class SparkForHandling
{
    private static Logger log = Logger.getLogger(SparkForHandling.class);

    public static void main(String[] args)
    {
        Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(SparkForHandling.class, "/");

        get("/", (req, res) -> {
            Map<String, Object> fruitsMap = new HashMap<>();
            fruitsMap.put("fruits", Arrays.asList("apple", "orange", "banana", "peach"));

            StringWriter writer = new StringWriter();
            try
            {
                Template fruitTemplate = configuration.getTemplate("fruitPicker.ftl");
                fruitTemplate.process(fruitsMap, writer);
            } catch (IOException | TemplateException e)
            {
                halt(500);
                log.error(e);
            }
            return writer;
        });

        post("/favorite_fruit", (req, res) -> {
            String fruit = req.queryParams("fruit");
            if (fruit == null)
            {
                return "Why didn't you pick one?";
            } else
            {
                return "Your favorite fruit is " + fruit;
            }
        });
    }
}
