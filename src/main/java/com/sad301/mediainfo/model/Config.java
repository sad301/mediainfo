package com.sad301.mediainfo.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Config {

    public static Config init() {
        return new Config();
    }

    private ObjectProperty<Key> key;
    private StringProperty value;

    public Config() {
        this.key = new SimpleObjectProperty<>();
        this.value = new SimpleStringProperty();
    }

    public Config(Key key, String value) {
        this.key = new SimpleObjectProperty<>(key);
        this.value = new SimpleStringProperty(value);
    }

    public void setKey(Key key) {
        this.key.set(key);
    }

    public void setValue(String value) {
        this.value.set(value);
    }

    public Key getKey() {
        return this.key.get();
    }

    public String getValue() {
        return this.value.get();
    }

    @Override
    public String toString() {
        return getKey().toString();
    }

    public Config key(Key key) {
        setKey(key);
        return this;
    }

    public Config value(String value) {
        setValue(value);
        return this;
    }

    public enum Key {

        USER_USERNAME       ("user.username"),
        USER_PASSWORD       ("user.password"),
        USER_TOKEN_VALUE    ("user.token.value"),
        USER_TOKEN_CREATED  ("user.token.created");

        private final String name;

        Key (String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }

        public static Key get(String name) {
            Key temp = null;
            for (Key key: Key.values()) {
                if (key.toString().equals(name)) {
                    temp = key;
                }
            }
            return temp;
        }
    }

}
