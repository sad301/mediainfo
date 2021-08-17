package com.sad301.mediainfo.controller;

import com.sad301.mediainfo.App;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class MainView extends BorderPane {

    private final App app;

    public MainView(App app) throws IOException {
        this.app = app;
        FXMLLoader loader = new FXMLLoader(App.class.getResource("/com/sad301/mediainfo/fxml/main.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        loader.load();
    }

    @FXML
    private void initialize() { }
}
