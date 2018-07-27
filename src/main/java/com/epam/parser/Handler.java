package com.epam.parser;

import com.epam.model.Component;
import com.epam.model.Part;
import com.epam.model.Port;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class Handler extends DefaultHandler {

    private List<Component> componentList = null;
    private List<Port> portList = null;
    private List<Part> partList = null;
    private Component component = null;
    private Port port = null;
    private Part part = null;

    private boolean bName = false;
    private boolean bOrigin = false;
    private boolean bPrice = false;
    private boolean bPeripheral = false;
    private boolean bEnergyConsumption = false;
    private boolean bCooler = false;
    private boolean bPartName = false;
    private boolean bPortName = false;
    private boolean bCritical = false;

    List<Component> getComponentList() {
        return componentList;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes)
            throws SAXException {

        if (qName.equalsIgnoreCase("component")) {
            String id = attributes.getValue("id");
            component = new Component();
            component.setId(Integer.parseInt(id));
            if (componentList == null) {
                componentList = new ArrayList<>();
            }
        } else if (qName.equalsIgnoreCase("parts")) {
            if (partList == null) {
                partList = new ArrayList<>();
            }
        } else if (qName.equalsIgnoreCase("ports")) {
            if (portList == null) {
                portList = new ArrayList<>();
            }
        } else if (qName.equalsIgnoreCase("name")) {
            bName = true;
        } else if (qName.equalsIgnoreCase("origin")) {
            bOrigin = true;
        } else if (qName.equalsIgnoreCase("price")) {
            bPrice = true;
        } else if (qName.equalsIgnoreCase("peripheral")) {
            bPeripheral = true;
        } else if (qName.equalsIgnoreCase("energy_consumption")) {
            bEnergyConsumption = true;
        } else if (qName.equalsIgnoreCase("cooler")) {
            bCooler = true;
        } else if (qName.equalsIgnoreCase("critical")) {
            bCritical = true;
        } else if (qName.equalsIgnoreCase("part")) {
            part = new Part();
            part.setPartName(attributes.getValue("part_name"));
            partList.add(part);
        } else if (qName.equalsIgnoreCase("port")) {
            String id = attributes.getValue("port_id");
            port = new Port();
            port.setPortId(Integer.parseInt(id));
            port.setPortName(attributes.getValue("port_name"));
            portList.add(port);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("component")) {
            component.setParts(partList);
            component.setPorts(portList);
            componentList.add(component);
        }
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {

        if (bName) {
            component.setName(new String(ch, start, length));
            bName = false;
        } else if (bOrigin) {
            component.setOrigin(new String(ch, start, length));
            bOrigin = false;
        } else if (bPrice) {
            component.setPrice(Integer.parseInt(new String(ch, start, length)));
            bPrice = false;
        } else if (bPeripheral) {
            component.setPeripheral(Boolean.parseBoolean(new String(ch, start, length)));
            bPeripheral = false;
        } else if (bEnergyConsumption) {
            component.setEnergyConsumption(Integer.parseInt(new String(ch, start, length)));
            bEnergyConsumption = false;
        } else if (bCooler) {
            component.setCooler(Boolean.parseBoolean(new String(ch, start, length)));
            bCooler = false;
        } else if (bCritical) {
            component.setCritical(Boolean.parseBoolean(new String(ch, start, length)));
            bCritical = false;
        } else if (bPartName) {
            part.setPartName(new String(ch, start, length));
            bPartName = false;
        } else if (bPortName) {
            port.setPortName(new String(ch, start, length));
            bPortName = false;
        }
    }

}
