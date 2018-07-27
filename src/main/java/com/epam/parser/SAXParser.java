package com.epam.parser;

import com.epam.model.Component;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;


public class SAXParser extends Handler implements Parser{
    @Override
    public List<Component> parse() {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        List<Component> componentList = null;
        try {
            javax.xml.parsers.SAXParser saxParser = saxParserFactory.newSAXParser();
            Handler handler = new Handler();
            saxParser.parse(new File("resources/computer.xml"), handler);
            componentList = handler.getComponentList();
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        if (componentList != null) {
            componentList.sort(Comparator.comparing(Component::getPrice));
        }
        return componentList;
    }
}
