package com.tengen.csv;

import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;

public class Xml2Csv {

    public static final String STYLE_SHEET = "src/main/resources/destination/destination.xsl";
    public static final String XML_SOURCE = "src/main/resources/destination/destination-types.xml";
    public static final String RESULT_CSV_FILE = "src/main/resources/destination/destination_airports.csv";

    public static void main(String[] args) throws Exception {
        File stylesheet = new File(STYLE_SHEET);
        File xmlSource = new File(XML_SOURCE);

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(xmlSource);

        StreamSource stylesource = new StreamSource(stylesheet);
        Transformer transformer = TransformerFactory.newInstance().newTransformer(stylesource);
        Source source = new DOMSource(document);
        Result outputTarget = new StreamResult(new File(RESULT_CSV_FILE));
        transformer.transform(source, outputTarget);
    }
}
