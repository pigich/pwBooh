package com.pwBooh.util;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.property.StringProperty;

import java.io.IOException;
import java.io.ObjectOutputStream;

public class WriteObjectsHelper {

    // automatic write set of properties to ObjectOutputStream
    public static void writeAllProp(ObjectOutputStream s, Property... properties) throws IOException {
        s.defaultWriteObject();
        for (Property prop : properties) {
            if (prop instanceof StringProperty) s.writeUTF(((StringProperty) prop).getValueSafe());
            else if (prop instanceof ObjectProperty) s.writeObject(((ObjectProperty) prop).get());
            else throw new RuntimeException("type of property is wrong : " + prop.toString());
        }
    }
}