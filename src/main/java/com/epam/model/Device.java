package com.epam.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "component"
})
@XmlRootElement(name = "Device")
public class Device {

    @XmlElement
    private List<Component> component;

    public Device(List<Component> components) {
        this.component = components;
    }

    public Device(){}

    public List<Component> getComponent() {
        if (component == null) {
            component = new ArrayList<>();
        }
        return this.component;
    }

    public void setComponent(List<Component> components) {
        this.component = components;
    }

    @Override
    public String toString() {
        return "Device{" +
                "component=" + component +
                '}';
    }
}
