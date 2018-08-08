package com.pwBooh.pojos;

import com.pwBooh.util.ReadObjectsHelper;
import com.pwBooh.util.WriteObjectsHelper;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Logins implements Serializable {

    private static final long serialVersionUID = 1L;

    private StringProperty login;
    private BooleanProperty checked;
    /**
     * Конструктор по умолчанию.
     */
    public Logins() {
        this(null, null);
        initInstance();
    }

    public Logins(String login, Boolean checked) {
        this.login = new SimpleStringProperty(login);
        this.checked = new SimpleBooleanProperty(checked);
    }



    public String getLogin() {
        return login.get();
    }

    public void setLogin(String login) {
        this.login.set(login);
    }

    public boolean isChecked() {
        return checked.get();
    }

    public void setChecked(boolean checked) {
        this.checked.set(checked);
    }

    public StringProperty loginProperty() {
        return login;
    }

    public BooleanProperty checkedProperty() {
        return checked;
    }

    @Override
    public String toString() {
        return getLogin() + " "  + isChecked();
    }

    private void initInstance() {
        login = new SimpleStringProperty();
        checked = new SimpleBooleanProperty();
    }

    private void writeObject(ObjectOutputStream s) throws IOException {
        WriteObjectsHelper.writeAllProp(s, login, checked);
    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        initInstance();
        ReadObjectsHelper.readAllProp(s, login, checked);
    }
}

