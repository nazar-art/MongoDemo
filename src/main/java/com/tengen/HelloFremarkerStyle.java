package com.tengen;


import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class HelloFremarkerStyle
{
    private static Logger log = Logger.getLogger(HelloFremarkerStyle.class);

    public static void main(String[] args)
    {
        try
        {
            Configuration configuration = new Configuration();
            configuration.setClassForTemplateLoading(HelloFremarkerStyle.class, "/");

            Template template = configuration.getTemplate("hello.ftl"); // /src/main/resources/
            StringWriter writer = new StringWriter();
            Map<String, Object> helloMap = new HashMap<>();
            helloMap.put("name", "Nazar!");

            template.process(helloMap, writer);
            System.out.println(writer);
        } catch (IOException e)
        {
            log.error(e);
        } catch (TemplateException e)
        {
            e.printStackTrace();
        }
    }
}
