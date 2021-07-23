package com.sad301.mediainfo.controller;

import javafx.fxml.*;

public class FrontPaneController {

  public void start() {
    headerPaneController.start();
    footerPaneController.start();
  }

  public void stop() {
    headerPaneController.stop();
    footerPaneController.stop();
  }

  @FXML
  private HeaderPaneController headerPaneController;

  @FXML
  private FooterPaneController footerPaneController;

  @FXML
  public void initialize() { }

}
