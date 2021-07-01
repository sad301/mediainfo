package com.sad301.mediainfo.gui;

import java.io.*;
import javafx.fxml.*;
import javafx.scene.layout.*;

public class MainPane extends BorderPane {

  public MainPane() throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("main_pane.fxml"));
    loader.setRoot(this);
    loader.setController(this);
    loader.load();
  }

  public void stop() {
    footerController.stop();
  }

  @FXML
  private VBox header;

  @FXML
  private BorderPane footer;

  @FXML
  private FooterController footerController;

  @FXML
  public void initialize() { }

}
