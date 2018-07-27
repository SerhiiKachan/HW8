package com.epam.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "partName"
})
@XmlRootElement(name = "part")
public class Part {

    @XmlElement(name = "part_name", required = true)
    private String partName;

    public Part(String partName) {
        this.partName = partName;
    }

    public Part(){}

    public String getPartName() {
        return partName;
    }

    public void setPartName(String value) {
        this.partName = value;
    }

    @Override
    public String toString() {
        return "Part{" +
                "partName='" + partName + '\'' +
                '}';
    }
}
