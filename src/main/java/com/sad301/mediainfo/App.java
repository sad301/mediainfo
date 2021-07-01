package com.sad301.mediainfo;

import com.sad301.mediainfo.gui.*;
import javafx.application.*;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/**
* JavaFX App
*/
public class App extends Application {

  private HttpServer httpServer = new HttpServer(8000);
  private MainPane root;

  @Override
  public void init() {
    // httpServer.start();
  }

  @Override
  public void start(Stage stage) throws Exception {
    root = new MainPane();
    Scene scene = new Scene(root, 640, 480);
    scene.setOnKeyPressed(e -> {
      switch(e.getCode()) {
        case F -> stage.setFullScreen(!stage.isFullScreen());
        case Q -> Platform.exit();
      }
    });
    stage.setScene(scene);
    stage.show();
  }

  @Override
  public void stop() {
    // httpServer.stop();
    root.stop();
  }

  public static void main(String[] args) {
    launch();
  }

}
