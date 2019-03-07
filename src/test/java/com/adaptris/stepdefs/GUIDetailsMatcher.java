package com.adaptris.stepdefs;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;


public class GUIDetailsMatcher {

    protected static String searchFileDetails(String directoryPath, String fileName, String tagTitle) throws ParserConfigurationException, IOException, SAXException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(directoryPath + "\\" + fileName);
        doc.getDocumentElement().normalize();
        NodeList nList = doc.getElementsByTagName(tagTitle);
        Node nNode = nList.item(0);
        return nNode.getFirstChild().getNodeValue();
    }

//    protected static String searchFile(String directoryPath, String fileName) {
//
//        return;
//    }

}
