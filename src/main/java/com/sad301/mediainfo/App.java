package com.sad301.mediainfo;

import com.sad301.mediainfo.controller.*;
import com.sad301.mediainfo.dao.*;
import com.sad301.mediainfo.server.*;

import java.net.*;
import java.sql.*;

import javafx.application.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

/**
* JavaFX App
*/
public class App extends Application {

  private BorderPane frontPane;
  private FrontPaneController frontPaneController;
  private com.sad301.mediainfo.server.App httpServer;

  @Override
  public void init() throws Exception {
    loadFrontPane();
  }

  @Override
  public void start(Stage stage) throws Exception {
    Scene scene = new Scene(frontPane, 640, 480);
    scene.setOnKeyPressed(e -> {
      switch(e.getCode()) {
        case F -> stage.setFullScreen(!stage.isFullScreen());
        case Q -> Platform.exit();
      }
    });
    stage.setTitle("MediaInfo");
    stage.setScene(scene);
    stage.setOnShown(e -> frontPaneController.start());
    stage.show();
  }

  @Override
  public void stop() throws Exception {
    frontPaneController.stop();
  }

  private void loadFrontPane() throws Exception {
    URL url = getClass().getResource("/com/sad301/mediainfo/fxml/front_pane.fxml");
    FXMLLoader loader = new FXMLLoader(url);
    frontPane = loader.load();
    frontPaneController = loader.getController();
  }

  public static void main(String[] args) {
    launch(args);
  }

}
