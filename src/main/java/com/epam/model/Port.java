package com.epam.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "portName"
})
@XmlRootElement(name = "port")
public class Port {

    @XmlElement(name = "port_name", required = true)
    private String portName;
    @XmlAttribute(name = "port_id")
    private int portId;

    public Port(String portName, int portId) {
        this.portName = portName;
        this.portId = portId;
    }

    public Port(){}

    public String getPortName() {
        return portName;
    }

    public void setPortName(String value) {
        this.portName = value;
    }

    public int getPortId() {
        return portId;
    }

    public void setPortId(int value) {
        this.portId = value;
    }

    @Override
    public String toString() {
        return "Port{" +
                "portName='" + portName + '\'' +
                ", portId=" + portId +
                '}';
    }
}
