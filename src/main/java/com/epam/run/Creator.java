package com.epam.run;

import org.apache.log4j.Logger;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.IOException;

public class Creator {

    private static final Logger LOG = Logger.getLogger(Creator.class);

    void createHTML() {
        try {
            File xsl = new File("resources/computer.xsl");
            StreamSource streamSource = new StreamSource(xsl);
            transformFile("resources/computer.html", streamSource);
            System.out.println("html file has been created.");
        } catch (Exception e){
            e.printStackTrace();
            LOG.error("Failed to generate html file.");
        }
    }

    void createNewXMLWithNewRootName(String name) {
        try {
            Document document = makeDoc();
            Element element = document.getDocumentElement();
            Element rootElement = document.createElement(name);
            NamedNodeMap attributes = element.getAttributes();
            for (int i = 0; i < attributes.getLength(); i++) {
                Attr attr = (Attr) document.importNode(attributes.item(i), true);
                rootElement.getAttributes().setNamedItem(attr);
            }
            while (element.hasChildNodes()) {
                rootElement.appendChild(element.getFirstChild());
            }
            element.getParentNode().replaceChild(rootElement, element);
            transformFile("resources/newXML.xml", null);
            System.out.println("new XML file has been created.");
        } catch (SAXException | IOException | TransformerException | ParserConfigurationException e) {
            e.printStackTrace();
            LOG.error("Failed to create new XML file.");
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private static void transformFile(String path, StreamSource streamSource) throws Exception{
        Document document = makeDoc();
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer;
        if (streamSource == null){
            transformer = transformerFactory.newTransformer();
        }else{
            transformer = transformerFactory.newTransformer(streamSource);
        }
        DOMSource domSource = new DOMSource(document);
        StreamResult result = new StreamResult(new File(path));
        transformer.transform(domSource, result);
    }

    public static Document makeDoc() throws Exception{
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        return documentBuilder.parse("resources/computer.xml");
    }
}
