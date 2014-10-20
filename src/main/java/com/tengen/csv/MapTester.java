package com.tengen.csv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class MapTester {
    public static void main(String[] args) throws Exception {
        HashMap<String, String> map = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(Xml2Csv.RESULT_CSV_FILE))) {
            String str = reader.readLine();
            int counter = 1;
            while (str != null) {
                if (str.length() <= 1) {
                    continue;
                }
                String[] arr = reader.readLine().split(",");
                String key = arr[0].trim();
                String value = arr[1].trim();
                System.out.println(counter + " " + key + " -> " + value);
                map.put(key, value);
                counter++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
