package com.sad301.mediainfo.controller;

import com.sad301.mediainfo.dao.*;
import com.sad301.mediainfo.model.*;

import java.util.*;

import javafx.application.*;
import javafx.fxml.*;
import javafx.scene.text.*;

public class HeaderPaneController {

  private boolean loop = true;

  public void start() {
    Thread t = new Thread(() -> {
      while(loop) {
        Map<Config.Key, String> map = ConfigDAO.retrieveAsMap2();
        Platform.runLater(() -> {
          txtCompanyName.setText(map.get(Config.Key.COMPANY_NAME));
          txtCompanyAddress.setText(map.get(Config.Key.COMPANY_ADDRESS));
        });
        try {
          Thread.sleep(1000);
        }
        catch(InterruptedException exc) {
          exc.printStackTrace();
        }
      }
    });
    t.start();
  }

  public void stop() {
    loop = false;
  }

  @FXML
  private Text txtCompanyName;

  @FXML
  private Text txtCompanyAddress;

  @FXML
  public void initialize() throws Exception { }

}
