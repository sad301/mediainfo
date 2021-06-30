package com.sad301.mediainfo;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import spark.*;

/**
* JavaFX App
*/
public class App extends Application {

  private HttpServer httpServer = new HttpServer(8000);

  @Override
  public void init() {
    System.out.println("Initialize");
  }

  @Override
  public void start(Stage stage) {
    VBox box = new VBox();
    box.getChildren().add(new Label("Hello World"));
    Scene scene = new Scene(box, 640, 480);
    stage.setOnShown(e -> httpServer.start());
    stage.setOnCloseRequest(e -> httpServer.stop());
    stage.setScene(scene);
    stage.show();
  }

  public static void main(String[] args) {
    launch();
  }

}
