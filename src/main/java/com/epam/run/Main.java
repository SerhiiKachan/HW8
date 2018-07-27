package com.epam.run;

import com.epam.constant.PathConst;
import com.epam.model.Component;
import com.epam.parser.*;

import java.util.*;

public class Main {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        Creator creator = new Creator();
        Validator validator = new Validator();
        Parser parser = null;
        System.out.println("Select parser\n1 - DOMParser\n2 - SAXParser\n3 - StAXParser");
        int select = scanner.nextInt();
        if (select == 1)
            parser = new DOMParser();
        if (select == 2)
            parser = new SAXParser();
        if (select == 3)
            parser = new StAXParser();
        if (parser != null) {
            List<Component> components = parser.parse();
            components.sort(Comparator.comparing(Component::getPrice));
            validator.validateXSD(PathConst.XSD_PATH, PathConst.XML_PATH);
            creator.createHTML();
            creator.createNewXMLWithNewRootName("newName");
            System.out.println("\nSet of components sorted by prices:\n");
            for (Component component : components) {
                System.out.println(component);
            }
        }
        MarAndUnmar marAndUnmar = new MarAndUnmar();
        System.out.println("\nFile marshalled.xml has been created.");
        marAndUnmar.marshall();
        System.out.println("Unmarshalled object from marshalled file:");
        marAndUnmar.unmarshall();
    }
}
