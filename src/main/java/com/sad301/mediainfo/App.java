package com.sad301.mediainfo;

import com.sad301.mediainfo.controller.MainView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
* JavaFX App
*/
public class App extends Application {

  private HttpServer httpServer;

  @Override
  public void start(Stage stage) throws Exception {
    MainView root = new MainView(this);
    Scene scene = new Scene(root, 640, 480);
    stage.setTitle("FQMediaInfo");
    stage.setScene(scene);
    stage.setMinWidth(640);
    stage.setMinHeight(480);
    stage.setOnShowing(e -> {
      httpServer = new HttpServer(8000);
      httpServer.start();
    });
    stage.show();
  }

  @Override
  public void stop() {
    httpServer.stop();
  }

  public static void main(String args) {
    launch(args);
  }
}
