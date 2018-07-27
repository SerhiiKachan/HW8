package com.epam.run;

import com.epam.model.Component;
import com.epam.model.Device;
import com.epam.model.Port;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

class MarAndUnmar {

    void marshall(){
        try{
            JAXBContext jContext = JAXBContext.newInstance(Device.class);
            Marshaller marshallObj = jContext.createMarshaller();
            marshallObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            List<Component> components = new ArrayList<>();
            List<Port> ports = new ArrayList<>();
            ports.add(new Port("USB", 2));
            components.add(new Component("Component", "GB", 300, false, 25, true, new ArrayList<>(), ports, false, 7));
            Device device = new Device(components);
            marshallObj.marshal(device, new FileOutputStream("resources/marshalled.xml"));
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    void unmarshall(){
        try{
            File file = new File("resources/marshalled.xml");
            JAXBContext jContext = JAXBContext.newInstance(Device.class);
            Unmarshaller unmarshaller = jContext.createUnmarshaller();
            Device device = (Device) unmarshaller.unmarshal(file);
            System.out.println(device);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
