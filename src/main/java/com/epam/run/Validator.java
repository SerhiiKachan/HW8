package com.epam.run;

import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;

public class Validator {

    private static final Logger LOG = Logger.getLogger(Validator.class);

    void validateXSD(String xsdPath, String xmlPath) {
        try {
            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = schemaFactory.newSchema(new File(xsdPath));
            javax.xml.validation.Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File(xmlPath)));
            System.out.println("Schema is valid.");
        } catch (IOException | SAXException e) {
            e.printStackTrace();
            LOG.error("Schema is not valid.");
        }
    }
}
