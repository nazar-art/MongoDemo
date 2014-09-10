package com.tengen;

import static spark.Spark.*;

public class SparkRoutes
{

    public static void main(String[] args)
    {
        get("/", (req, res) -> "Hello World\n");
        get("/test", (req, res) -> "This is a test page\n");
        get("/echo/:thing", (req, res) -> req.params(":thing"));

    }
}
