package com.epam.model;

import java.math.BigInteger;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "name",
        "origin",
        "price",
        "peripheral",
        "energyConsumption",
        "cooler",
        "parts",
        "ports",
        "critical"

})
@XmlRootElement(name = "component")
public class Component {

    @XmlElement(required = true)
    private String name;
    @XmlElement(required = true)
    private String origin;
    @XmlElement(required = true)
    private int price;
    @XmlElement(required = true)
    private boolean peripheral;
    @XmlElement(name = "energy_consumption", required = true)
    private int energyConsumption;
    @XmlElement(required = true)
    private boolean cooler;
    @XmlElement(required = true)
    private List<Part> parts;
    @XmlElement(required = true)
    private List<Port> ports;
    @XmlElement(required = true)
    private boolean critical;
    @XmlAttribute(name = "id")
    private int id;

    public Component(String name, String origin, int price, boolean peripheral, int energyConsumption, boolean cooler, List<Part> parts, List<Port> ports, boolean critical, int id) {
        this.name = name;
        this.origin = origin;
        this.price = price;
        this.peripheral = peripheral;
        this.energyConsumption = energyConsumption;
        this.cooler = cooler;
        this.parts = parts;
        this.ports = ports;
        this.critical = critical;
        this.id = id;
    }

    public Component(){}

    public String getName() {
        return name;
    }

    public void setName(String value) {
        this.name = value;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String value) {
        this.origin = value;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int value) {
        this.price = value;
    }

    public boolean getPeripheral() {
        return peripheral;
    }

    public void setPeripheral(boolean value) {
        this.peripheral = value;
    }

    public int getEnergyConsumption() {
        return energyConsumption;
    }

    public void setEnergyConsumption(int value) {
        this.energyConsumption = value;
    }

    public boolean getCooler() {
        return cooler;
    }

    public void setCooler(boolean value) {
        this.cooler = value;
    }

    public List<Part> getParts() {
        return parts;
    }

    public void setParts(List<Part> value) {
        this.parts = value;
    }

    public List<Port> getPorts() {
        return ports;
    }

    public void setPorts(List<Port> value) {
        this.ports = value;
    }

    public boolean getCritical() {
        return critical;
    }

    public void setCritical(boolean critical) {
        this.critical = critical;
    }

    public int getId() {
        return id;
    }

    public void setId(int value) {
        this.id = value;
    }

    @Override
    public String toString() {
        return "Component{" +
                "name='" + name + '\'' +
                ", origin='" + origin + '\'' +
                ", price=" + price +
                ", peripheral=" + peripheral +
                ", energyConsumption=" + energyConsumption +
                ", cooler=" + cooler +
                ", parts=" + parts +
                ", ports=" + ports +
                ", critical=" + critical +
                ", id=" + id +
                '}';
    }
}
