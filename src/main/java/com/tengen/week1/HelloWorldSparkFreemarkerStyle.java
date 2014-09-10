package com.tengen.week1;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.halt;

public class HelloWorldSparkFreemarkerStyle
{
    private static Logger log = Logger.getLogger(HelloWorldSparkFreemarkerStyle.class);

    public static void main(String[] args)
    {
        final Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(HelloWorldSparkFreemarkerStyle.class, "/");

        get("/hello", (req, res) -> {
            StringWriter writer = new StringWriter();
            try
            {
                Template template = configuration.getTemplate("hello.ftl");
                Map<String, Object> helloMap = new HashMap<>();
                helloMap.put("name", "Nazar!");

                template.process(helloMap, writer);
            } catch (IOException | TemplateException e)
            {
                halt(500);
                log.error(e);
            }
            return writer;
        });

    }
}
