package com.sad301.mediainfo.controller;

import com.sad301.mediainfo.*;

import java.time.*;
import java.time.format.*;
import java.util.*;

import javafx.application.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.text.*;

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
