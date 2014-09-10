package com.tengen.week1;

import static spark.Spark.*;

public class HelloWorldSparkStyle
{
    public static void main(String[] args)
    {
        get("/hello", (req, res) -> "Hello World from Spark");
    }
}
