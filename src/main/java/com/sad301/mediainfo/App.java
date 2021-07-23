package com.sad301.mediainfo;

import java.net.URL;

import com.sad301.mediainfo.controller.FrontPaneController;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
* JavaFX App
*/
public class App extends Application {

  private BorderPane frontPane, testPane;
  private FrontPaneController frontPaneController;

  @Override
  public void init() throws Exception {
    loadTestPane();
    loadFrontPane();
  }

  @Override
  public void start(Stage stage) throws Exception {
    Scene scene = new Scene(testPane, 640, 480);
    scene.setOnKeyPressed(e -> {
      switch(e.getCode()) {
        case F -> stage.setFullScreen(!stage.isFullScreen());
        case Q -> Platform.exit();
      }
    });
    stage.setTitle("MediaInfo");
    stage.setScene(scene);
    // stage.setOnShown(e -> frontPaneController.start());
    stage.show();
  }

  @Override
  public void stop() throws Exception {
    // frontPaneController.stop();
  }

  private void loadTestPane() throws Exception {
    URL url = getClass().getResource("/com/sad301/mediainfo/fxml/test_pane.fxml");
    FXMLLoader loader = new FXMLLoader(url);
    testPane = loader.load();
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
