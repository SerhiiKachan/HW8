package com.epam.parser;

import com.epam.model.Component;
import com.epam.model.Part;
import com.epam.model.Port;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class StAXParser implements Parser {

    @SuppressWarnings(value = "unchecked")
    @Override
    public List<Component> parse() {
        Component component = null;
        List<Component> components = new ArrayList<>();
        List<Port> ports = null;
        List<Part> parts = null;
        Part part = null;
        Port port = null;
        boolean bName = false;
        boolean bOrigin = false;
        boolean bPrice = false;
        boolean bPeripheral = false;
        boolean bEnergyConsumption = false;
        boolean bCooler = false;
        boolean bPartName = false;
        boolean bPortName = false;
        boolean bCritical = false;

        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLEventReader eventReader = null;
        try {
            eventReader = factory.createXMLEventReader(new FileReader("resources/computer.xml"));
        } catch (XMLStreamException | FileNotFoundException e) {
            e.printStackTrace();
        }

        while (eventReader.hasNext()) {
            XMLEvent event = null;
            try {
                event = eventReader.nextEvent();
            } catch (XMLStreamException e) {
                e.printStackTrace();
            }
            switch (event.getEventType()) {
                case XMLStreamConstants.START_ELEMENT:
                    StartElement startElement = event.asStartElement();
                    String qName = startElement.getName().getLocalPart();

                    if (startElement.getName().getLocalPart().equals("component")) {
                        component = new Component();
                        Attribute attr = startElement.getAttributeByName(new QName("id"));
                        component.setId(Integer.parseInt(attr.getValue()));
                        if (components == null) {
                            components = new ArrayList<>();
                        }
                    } else if (qName.equalsIgnoreCase("parts")) {
                        if (parts == null) {
                            parts = new ArrayList<>();
                        }
                    } else if (qName.equalsIgnoreCase("ports")) {
                        if (ports == null) {
                            ports = new ArrayList<>();
                        }
                    } else if (qName.equalsIgnoreCase("name")) {
                        try {
                            event = eventReader.nextEvent();
                            component.setName(event.asCharacters().getData());
                        } catch (XMLStreamException e) {
                            e.printStackTrace();
                        }
                    } else if (qName.equalsIgnoreCase("origin")) {
                        try {
                            event = eventReader.nextEvent();
                            component.setOrigin(event.asCharacters().getData());
                        } catch (XMLStreamException e) {
                            e.printStackTrace();
                        }
                    } else if (qName.equalsIgnoreCase("price")) {
                        try {
                            event = eventReader.nextEvent();
                            component.setPrice(Integer.parseInt(event.asCharacters().getData()));
                        } catch (XMLStreamException e) {
                            e.printStackTrace();
                        }
                    } else if (qName.equalsIgnoreCase("peripheral")) {
                        try {
                            event = eventReader.nextEvent();
                            component.setPeripheral(Boolean.parseBoolean(event.asCharacters().getData()));
                        } catch (XMLStreamException e) {
                            e.printStackTrace();
                        }
                    } else if (qName.equalsIgnoreCase("energy_consumption")) {
                        try {
                            event = eventReader.nextEvent();
                            component.setEnergyConsumption(Integer.parseInt(event.asCharacters().getData()));
                        } catch (XMLStreamException e) {
                            e.printStackTrace();
                        }
                    } else if (qName.equalsIgnoreCase("cooler")) {
                        try {
                            event = eventReader.nextEvent();
                            component.setCooler(Boolean.parseBoolean(event.asCharacters().getData()));
                        } catch (XMLStreamException e) {
                            e.printStackTrace();
                        }
                    } else if (qName.equalsIgnoreCase("part")) {
                        try {
                            part = new Part();
                            try {
                                event = eventReader.nextEvent();
                            } catch (XMLStreamException e) {
                                e.printStackTrace();
                            }
                            part.setPartName(event.asCharacters().getData());
                            parts.add(part);
                        } catch (NoSuchElementException e) {
                            e.getMessage();
                        }
                    } else if (qName.equalsIgnoreCase("port")) {
                        try {
                            port = new Port();
                            Attribute attr = startElement.getAttributeByName(new QName("port_id"));
                            port.setPortId(Integer.parseInt(attr.getValue()));
                            try {
                                event = eventReader.nextEvent();
                            } catch (XMLStreamException e) {
                                e.printStackTrace();
                            }
                            port.setPortName(event.asCharacters().getData());
                            ports.add(port);
                        } catch (NullPointerException e) {
                            e.getMessage();
                        }
                    } else if (qName.equalsIgnoreCase("critical")) {
                        try {
                            event = eventReader.nextEvent();
                            component.setCritical(Boolean.parseBoolean(event.asCharacters().getData()));
                        } catch (XMLStreamException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case XMLStreamConstants.CHARACTERS:
                    Characters characters = event.asCharacters();
                    if (bName) {
                        component.setName(characters.getData());
                        bName = false;
                    } else if (bOrigin) {
                        component.setOrigin(characters.getData());
                        bOrigin = false;
                    } else if (bPrice) {
                        component.setPrice(Integer.parseInt(characters.getData()));
                        bPrice = false;
                    } else if (bPeripheral) {
                        component.setPeripheral(Boolean.parseBoolean(characters.getData()));
                        bPeripheral = false;
                    } else if (bEnergyConsumption) {
                        component.setEnergyConsumption(Integer.parseInt(characters.getData()));
                        bEnergyConsumption = false;
                    } else if (bCooler) {
                        component.setCooler(Boolean.parseBoolean(characters.getData()));
                        bCooler = false;
                    } else if (bCritical) {
                        component.setCritical(Boolean.parseBoolean(characters.getData()));
                        bCritical = false;
                    } else if (bPartName) {
                        part.setPartName(characters.getData());
                        bPartName = false;
                    } else if (bPortName) {
                        port.setPortName(characters.getData());
                        bPortName = false;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    EndElement endElement = event.asEndElement();
                    if (endElement.getName().getLocalPart().equalsIgnoreCase("component")) {
                        component.setParts(parts);
                        component.setPorts(ports);
                        components.add(component);
                    }
                    break;
            }

        }
        components.sort(Comparator.comparing(Component::getPrice));
        return components;
    }
}
