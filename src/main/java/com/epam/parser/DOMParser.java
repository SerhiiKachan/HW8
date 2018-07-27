package com.epam.parser;

import com.epam.run.Creator;
import com.epam.model.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

public class DOMParser implements Parser {

    @Override
    public List<Component> parse() {
        List<Component> componentList = new ArrayList<>();
        List<Port> portList;
        List<Part> partList;
        try {
            Document document = Creator.makeDoc();
            NodeList components = document.getElementsByTagName("component");
            for (int i = 0; i < components.getLength(); i++) {
                Node compNode = components.item(i);
                if (compNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) compNode;
                    int id = Integer.parseInt(element.getAttribute("id"));
                    String name = element.getElementsByTagName("name").item(0).getTextContent();
                    String origin = element.getElementsByTagName("origin").item(0).getTextContent();
                    int price = Integer.parseInt(element.getElementsByTagName("price").item(0).getTextContent());
                    boolean peripheral = Boolean.parseBoolean(element.getElementsByTagName("peripheral").item(0).getTextContent());
                    int energyConsumption = Integer.parseInt(element.getElementsByTagName("energy_consumption").item(0).getTextContent());
                    boolean cooler = Boolean.parseBoolean(element.getElementsByTagName("cooler").item(0).getTextContent());
                    try {
                        partList = new ArrayList<>();
                        NodeList parts = document.getElementsByTagName("part");
                        for (int j = 0; j < parts.getLength(); j++) {
                            Node partsNode = parts.item(j);
                            if (partsNode.getNodeType() == Node.ELEMENT_NODE) {
                                partList.add(new Part(element.getElementsByTagName("part_name").item(j).getTextContent()));
                            }
                        }
                    } catch (NullPointerException e) {
                        partList = null;
                    }
                    int portId;
                    String portName;
                    portList = new ArrayList<>();
                    try {
                        NodeList ps = document.getElementsByTagName("ports");
                        for (int l = 0; l < ps.getLength(); l++) {
                            NodeList ports = document.getElementsByTagName("port");
                            for (int k = 0; k < ports.getLength(); k++) {
                                Node portNode = ports.item(k);
                                if (portNode.getNodeType() == Node.ELEMENT_NODE) {
                                    portId = Integer.parseInt(element.getElementsByTagName("port").item(k).getAttributes()
                                            .getNamedItem("port_id").getNodeValue());
                                    portName = element.getElementsByTagName("port_name").item(k).getTextContent();
                                    portList.add(new Port(portName, portId));
                                }
                            }
                        }
                    } catch (NullPointerException e) {
                        e.getMessage();
                    }
                    boolean critical = Boolean.parseBoolean(element.getElementsByTagName("critical").item(0).getTextContent());
                    componentList.add(new Component(name, origin, price, peripheral, energyConsumption, cooler, partList, portList, critical, id));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return componentList;
    }
}
