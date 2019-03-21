/*
 * Copyright 2015 Adaptris Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.adaptris.stepdefs;


import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;


import static com.adaptris.stepdefs.EnvVar.ADAPTER_STARTUP;
import static org.assertj.core.util.Files.delete;


public class GUIDirectoryTools {

    public static void InterlokBoot() {
        try {
            Runtime.getRuntime().exec(ADAPTER_STARTUP);
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    protected static String searchFileDetails(String directoryPath, String fileName, String tagTitle) throws ParserConfigurationException, IOException, SAXException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(directoryPath + "\\" + fileName);
        doc.getDocumentElement().normalize();
        NodeList nList = doc.getElementsByTagName(tagTitle);
        Node nNode = nList.item(0);
        return nNode.getFirstChild().getNodeValue();
    }

    public static void adapterIdReset(File adapterDBLocation) throws IOException {

        if(adapterDBLocation.listFiles() != null) {
            try {
                for (File childFile : adapterDBLocation.listFiles()) {

                    if (childFile.isDirectory()) {
                        delete(childFile);
                    }
                }
                Files.delete(adapterDBLocation.toPath());
            } catch (IOException e) {

            }
        }
        for (final File f : adapterDBLocation.listFiles())
        {
            f.delete();
        }
        adapterDBLocation.delete();
    }

}
