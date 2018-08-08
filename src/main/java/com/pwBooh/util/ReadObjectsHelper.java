package com.pwBooh.util;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.property.StringProperty;

import java.io.IOException;
import java.io.ObjectInputStream;

public class ReadObjectsHelper {

    // automatic fill a set of properties with values contained in ObjectInputStream
    public static void readAllProp(ObjectInputStream s, Property... properties) throws IOException, ClassNotFoundException {
        for (Property prop : properties) {
            if (prop instanceof StringProperty) ((StringProperty) prop).setValue(s.readUTF());
            else if (prop instanceof ObjectProperty) ((ObjectProperty) prop).setValue(s.readObject());
            else throw new RuntimeException("Unsupported object type : " + prop == null ? null : prop.toString());
        }
    }
}